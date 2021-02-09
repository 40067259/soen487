package com.example.a1.controller;

import com.example.a1.rest.Album;
import com.example.a1.rest.AlbumRest;
import com.example.a1.restImp.AlbumRestImp;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

@Path("jersey/")//it is a must
public class AlbumJerseyController {
    AlbumRest albumRest = new AlbumRestImp();
    @GET
    @Path("/albums")
    @Produces(MediaType.TEXT_PLAIN)
   public String getAlbums(){
       StringBuilder sb = new StringBuilder();
       CopyOnWriteArrayList<Album> list = albumRest.listAlbums();
       for(int i = 0; i < list.size(); i++){
           sb.append(list.get(i).toString());
           sb.append("\n");
           sb.append("\n");
       }
       String res = sb.toString().trim();
       if(res == null || res.length() == 0) return "Sorry, no albums now";
       else return res;
   }

   @GET
   @Path("/get/{isrc}")
   @Produces(MediaType.TEXT_PLAIN)
   public String getAlbumByIsrc(@PathParam("isrc") String isrc){
        Album album = albumRest.getAlbum(isrc);
        if(album == null)
            return "Sorry, no such an album with isrc "+isrc;
        else
            return album.toString();
   }

   @POST
   @Path("/createAlbum/{isrc}/{title}/{description}/{year}/{artist}")
   public String addAlbum(@PathParam("isrc")String isrc,@PathParam("title")String title,
                          @PathParam("description")String description,
                          @PathParam("year")String year,@PathParam("artist")String artist){
       System.out.println("post...........");
        if(isrc == null || title == null || year == null || artist == null){
            return "Sorry, failure. Your request is incomplete";
        }
        Album album = new Album();
        album.setTitle(title);
        album.setArtist(artist);
        album.setIsrc(isrc);
        album.setYear(year);
        if(description == null || description.length() == 0)album.setDescription("No description now");
        else album.setDescription(description);
        boolean success = albumRest.addAlbum(album);
        if(success) return "Created an album successfully!";
        else return "Sorry, your albums has already existed";
   }

    @PUT
    @Path("/modifyAlbum/{isrc}/{title}/{description}/{year}/{artist}")
    public String updateAlbum(@PathParam("isrc")String isrc,@PathParam("title")String title,
                           @PathParam("description")String description,
                           @PathParam("year")String year,@PathParam("artist")String artist){
        if(isrc == null || isrc.length()==0 ||
                title == null || title.length() ==0||
                year == null || year.length() ==0 ||
                artist == null || artist.length() == 0){
            return "Sorry, failure. Your request is incomplete";
        }
        Album album = new Album();
        album.setTitle(title);
        album.setArtist(artist);
        album.setIsrc(isrc);
        album.setYear(year);
        if(description == null)album.setDescription("No description now");
        else album.setDescription(description);
        boolean success = albumRest.updateAlbum(album);
        if(success) return "Updated the album successfully!";
        else return "Sorry, your targeted album doesn't exist";
    }

    @DELETE
    @Path("/deleteAlbum/{isrc}")
    public String deleteAlbum(@PathParam("isrc")String isrc){
        if(isrc == null) return "Sorry, your request information is incomplete";
        boolean success = albumRest.deleteAlbum(isrc);
        if(!success) return "Sorry, your targeted album doesn't exist";
        return "Your targeted album has been deleted successfully";
    }

}
