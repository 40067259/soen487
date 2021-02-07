

package com.example.a1.controller;

import com.example.a1.rest.AlbumRest;
import com.example.a1.rest.Artist;
import com.example.a1.rest.ArtistRest;
import com.example.a1.restImp.ArtistRestImp;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;
import java.io.OutputStream;
import java.util.concurrent.CopyOnWriteArrayList;


public class ArtistServletController extends HttpServlet {
    //need to update
   private ArtistRest artistRest = new ArtistRestImp();

    private void sendResponse(HttpServletResponse response,String payload){
        try{
            OutputStream out = response.getOutputStream();
            out.write(payload.getBytes());
            out.flush();
        }catch(Exception e){
            throw new HTTPException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        String nickname = request.getParameter("nickname");
        if(nickname == null){
            CopyOnWriteArrayList<Artist> artists = artistRest.getAllArtists();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < artists.size(); i++){
                sb.append(artists.get(i).toString());
                sb.append("\n");
                sb.append("\n");
            }
            String res = sb.toString().trim();
            if(res == null || res.length() == 0) sendResponse(response,"Sorry, no album now");
            else sendResponse(response,sb.toString().trim());
        }else{
            Artist artist = artistRest.getArtist(nickname);
            if(artist == null) sendResponse(response,"Sorry,no such an artist called "+nickname);
            else sendResponse(response,artist.toString());
        }
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String nickName = request.getParameter("nickname");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String bio = request.getParameter("bio");

        if(nickName == null || firstName == null || lastName == null){
            throw new HTTPException(HttpServletResponse.SC_BAD_REQUEST);
        }

        Artist artist = new Artist();
        artist.setNickName(nickName);
        artist.setFirstName(firstName);
        artist.setLastName(lastName);
        if(bio == null) artist.setBio("No bio now");
        else artist.setBio(bio);
        boolean success = artistRest.addArtist(artist);

        String msg = success ? "Add artist successfully !!!" : "Internal Server Error 500";
        sendResponse(response,msg);
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response){
        String nickName = request.getParameter("nickname");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String bio = request.getParameter("bio");

        if(nickName == null || firstName == null || lastName == null){
            throw new HTTPException(HttpServletResponse.SC_BAD_REQUEST);
        }

        Artist artist = new Artist();
        artist.setNickName(nickName);
        artist.setFirstName(firstName);
        artist.setLastName(lastName);
        if(bio == null) artist.setBio("No bio now");
        else artist.setBio(bio);

        boolean success = artistRest.updateArtist(artist);
        String msg = success ? "Updated successfully!!!" : "Your targeted artist doesn't exist";
        sendResponse(response,msg);
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response){
        String nickName = request.getParameter("nickname");
        if(nickName == null) throw new HTTPException(HttpServletResponse.SC_BAD_REQUEST);

        boolean success = artistRest.deleteArtist(nickName);
        String msg = success ? "The artist with nickname "+nickName+" has been deleted successfully":
                "Sorry, there is no such an artist whose nickname is "+nickName;
        sendResponse(response,msg);

    }
}
