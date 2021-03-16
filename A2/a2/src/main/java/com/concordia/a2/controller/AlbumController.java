package com.concordia.a2.controller;

import com.concordia.a2.exception.RepException;
import com.concordia.a2.mapper.AlbumMapper;
import com.concordia.a2.pojo.Album;
import com.concordia.a2.pojo.Artist;
import com.concordia.a2.pojo.LogEntry;
import com.concordia.a2.service.AlbumService;

import elemental.json.JsonObject;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("album/")
public class AlbumController {
    List<Album> list = new CopyOnWriteArrayList<>();

    @Autowired
    AlbumService service;

    @GetMapping(path = "get/{isrc}",produces = "application/json")
    public List<Album> getAlbum(@PathVariable("isrc") String isrc){
        System.out.println("Some caller comes");
        list.clear();
        Album album = service.getAlbumInfo(isrc);
        if(album == null) return null;
        Artist artist = service.getArtist(album.getAuthor());
        album.setArtist(artist);
        list.add(album);
         return list;
    }

    @GetMapping(path = "getAlbumCover/{isrc}",produces = "application/json")
    public Album getAlbumCover(@PathVariable("isrc") String isrc){
        System.out.println("*******");
        Album album = service.getAlbumInfo(isrc);
        if(album == null) return null;
        return album;
    }

    @GetMapping(path = "getAllAlbums",produces = "application/json")
    public List<Album> getAllAlbums(){
        list = service.getAlbumList();
        for(int i = 0; i < list.size();i++){
            String author = list.get(i).getAuthor();
            Artist artist = service.getArtist(author);
            list.get(i).setArtist(artist);
        }
        Collections.sort(list);
        return list;
    }


    //,consumes = "application/x-www-form-urlencoded"
    @PostMapping(path = "createAlbum",produces = "application/json")
    public String CreateAlbum(@RequestParam(value = "isrc") String isrc , @RequestParam(value = "title") String title,
                              @RequestParam(value = "year") String year, @RequestParam(value = "description",required = false) String des,
                              @RequestParam(value = "cover", required = false) MultipartFile cover, @RequestParam(value = "nickname") String nickname,
                              @RequestParam(value = "firstname",required = false) String firstname, @RequestParam(value = "lastname",required = false) String lastname,
                              @RequestParam(value = "bio",required = false) String bio) throws RepException, IOException, SQLException {
        System.out.println("post comes");
        if(isrc == null || isrc.length() == 0 ) isrc = UUID.randomUUID().toString().toLowerCase().substring(0,6);
        if(isrc == null || isrc.length() == 0 || nickname == null || nickname.length() == 0||
           year == null || year.length() == 0 || title == null || title.length() == 0)
            throw new RepException("Miss some parameters, please try again");
        Blob blob = null;
        if(cover != null ){
            System.out.println(cover.getContentType()+" "+cover.getBytes().length);
             blob = new SerialBlob(cover.getBytes());
        }else{
            System.out.println("cover is empty");
        }
        Artist artist = service.getArtist(nickname);
        System.out.println("artist "+artist);
        if(artist == null) service.createArtist(nickname,firstname,lastname,bio);
         service.createAlbum(isrc,title,des,year,nickname,blob);
         service.createLogEntry(service.getPostTime(), "CREATE",isrc);
        return "Created an album successfully";
    }

    @PutMapping(value = "updateAlbum")
    public String updateAlbum(@RequestParam(value = "isrc") String isrc , @RequestParam(value = "title",required = false) String title,
                              @RequestParam(value = "year",required = false) String year, @RequestParam(value = "description",required = false) String des,
                              @RequestParam(value = "cover", required = false) MultipartFile cover, @RequestParam(value = "nickname",required = false) String nickname,
                              @RequestParam(value = "firstname",required = false) String firstname, @RequestParam(value = "lastname",required = false) String lastname,
                              @RequestParam(value = "bio",required = false) String bio) throws IOException, SQLException {

        //judge isrc missed or not
        if(isrc.equals("null")) return "Sorry, you miss  album's isrc. Please check and try again";
        //check nickname missed or not
        if(nickname.equals("null") && (!firstname.equals("null") || !lastname.equals("null")|| !bio.equals("null")))
            return "Sorry,your must entry artist stage name if you want update artist info ";
        //check if the targeted album exists
        Album album = service.getAlbumInfo(isrc);
        if(album == null) return "Sorry, your album isrc doesn't exist, double check or create a new album";

        Blob blob = null;
        if(cover != null) blob = new SerialBlob(cover.getBytes());


        //create a new album
        Artist artist = null;
        if(!nickname.equals("null")){
            String nick = nickname.toLowerCase();
            artist = service.getArtist(nick);
            if(artist == null) service.createArtist(nickname,firstname,lastname,bio);
        }

        if(nickname != null && nickname.length() > 0) System.out.println(nickname+nickname.length()+"*******have nickname***********");
        //update the existed album
        if(!nickname.equals("null") && artist != null){
            String first = !firstname.equals("null") ? firstname : artist.getFirstname();
            String last = !lastname.equals("null") ? lastname : artist.getLastname();
            String bi = !bio.equals("null") ? bio : artist.getBio();
            System.out.println(first+" "+last+" "+bi);
            service.updateArtist(nickname,first,last,bi);
        }

        //update album
        String titleMod = !title.equals("null") ? title : album.getTitle();
        String desMod = !des.equals("null") ? des : album.getDescription();
        String yearMod = !year.equals("null") ? year: album.getYear();
        String nicknameMod = !nickname.equals("null") ? nickname : album.getAuthor();
        //no cover update or with cover
        if(blob == null) service.updateAlbumNoCover(isrc,titleMod,desMod,yearMod,nicknameMod);
        else{
            service.updateAlbum(isrc,titleMod,desMod,yearMod,nicknameMod,blob);
        }
        //update LogEntry
        service.createLogEntry(service.getPostTime(), "UPDATE",isrc);

        return "Updated album successfully";

    }

    @PutMapping(value = "updateAlbumCover")
    public String updateCover(@RequestParam(value="isrc") String isrc,@RequestParam(value="cover",required = false)  MultipartFile cover)
            throws RepException, IOException, SQLException {
        System.out.println("update comes");

      if(isrc == null){
            return "Sorry, we can't complete the operation, please take care of your isrc";
       }

        Blob blob = null;
        if(cover != null){
            blob = new SerialBlob(cover.getBytes());
        }
        service.updateAlbumCover(isrc,blob);
        service.createLogEntry(service.getPostTime(), "UPDATE",isrc);
        return "album cover was updated successfully";
    }

    @DeleteMapping(path = "deleteAlbum/{isrc}",produces = "application/json")
    public String deleteAlbum(@PathVariable("isrc") String isrc) throws RepException {
        if(isrc == null || isrc.length() == 0) throw new RepException("isrc is empty, isrc is a must");

        Album a = service.getAlbumInfo(isrc);
        if(a == null) return "Sorry,No such an isrc, please double check";
        service.deleteAlbum(isrc);
        service.createLogEntry(service.getPostTime(), "DELETE",isrc);

        return "Album "+isrc+" was deleted successfully";
    }


}
