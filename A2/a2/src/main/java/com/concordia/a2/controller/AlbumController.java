package com.concordia.a2.controller;

import com.concordia.a2.exception.RepException;
import com.concordia.a2.mapper.AlbumMapper;
import com.concordia.a2.pojo.Album;
import com.concordia.a2.pojo.Artist;
import com.concordia.a2.pojo.LogEntry;
import com.concordia.a2.service.AlbumService;
import com.concordia.a2.service.AlbumServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Path("album/")
public class AlbumController {
    List<Album> list = new CopyOnWriteArrayList<>();

    @Autowired
    AlbumService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("get/{isrc}")
    public List<Album> getAlbum(@PathParam("isrc") String isrc){
        System.out.println("Some caller comes");
        list.add(service.getAlbumInfo(isrc));
         return list;
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("createAlbum")
    public String CreateAlbum(@FormParam("isrc") String isrc , @FormParam("title") String title,
                              @FormParam("year") String year, @FormParam("description") String des,
                              @FormParam("cover") File cover, @FormParam("nickname") String nickname,
                              @FormParam("firstname") String firstname, @FormParam("lastname") String lastname,
                              @FormParam("bio") String bio) throws RepException {
        System.out.println("post comes");
        if(isrc == null || isrc.length() == 0 ) isrc = UUID.randomUUID().toString().toLowerCase().substring(0,6);
        if(isrc == null || isrc.length() == 0 || nickname == null || nickname.length() == 0||
           year == null || year.length() == 0 || title == null || title.length() == 0)
            throw new RepException("Miss some parameters, please try again");
        Artist artist = service.getArtist(isrc);
        if(artist == null) service.createArtist(nickname,firstname,lastname,bio);
         service.createAlbum(isrc,title,des,year,nickname,cover);

         service.createLogEntry(service.getPostTime(), "CREATE",isrc);
        return "Created an album successfully";
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("deleteAlbum/{isrc}")
    public String deleteAlbum(@PathParam("isrc") String isrc) throws RepException {
        if(isrc == null || isrc.length() == 0) throw new RepException("isrc is empty, isrc is a must");

        Album a = service.getAlbumInfo(isrc);
        if(a == null) return "Sorry,No such an isrc, please double check";
        service.deleteAlbum(isrc);
        service.createLogEntry(service.getPostTime(), "DELETE",isrc);

        return "Album "+isrc+" was deleted successfully";
    }


}
