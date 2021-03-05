package com.concordia.a2.controller;

import com.concordia.a2.exception.RepException;
import com.concordia.a2.mapper.AlbumMapper;
import com.concordia.a2.pojo.Album;
import com.concordia.a2.pojo.Artist;
import com.concordia.a2.pojo.LogEntry;
import com.concordia.a2.service.AlbumService;
import com.concordia.a2.service.AlbumServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
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
        list.add(service.getAlbumInfo(isrc));
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
        System.out.println("***********************************************************************");
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
