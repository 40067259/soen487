package com.example.a1.artistClient;

import com.example.a1.rest.Artist;
import com.example.a1.rest.ArtistRest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class artistClient implements ArtistRest {

    public static void sendRequest(URL url, String verb)
    {
        try{
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod(verb.toUpperCase());
            conn.setRequestProperty("Accept", "text/plain");
            if(conn.getResponseCode() != 200)
            {
                throw new RuntimeException("Failed : HTTP error code : "+conn.getResponseCode());

            }
            Scanner sc = new Scanner(conn.getInputStream());
            StringBuilder stringResponse = new StringBuilder();
            while (sc.hasNext())
            {
                stringResponse.append(sc.nextLine());
                stringResponse.append("\n");
            }

            System.out.println(stringResponse.toString());
            conn.disconnect();

            }catch(IOException e)
            {
                e.printStackTrace();
            }

        }


    @Override
    public CopyOnWriteArrayList<Artist> getAllArtists() {

        try
        {
            URL url = new URL("http://localhost:8080/artists");
            sendRequest(url,"get");
        }catch(MalformedURLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Artist getArtist(String nickName) {
        try
        {
            nickName = nickName.contains(" ")?nickName.replace(" ","%20"):nickName;
            String query = "?nickname="+nickName;
            URL url = new URL("http://localhost:8080/artists"+query);
            sendRequest(url,"get");
        }catch(MalformedURLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean addArtist(Artist artist) {

        if (artist.getBio().contains(" "))
        {
            artist.setBio(artist.getBio().replace(" ","%20"));
        }

        if (artist.getNickName().contains(" "))
        {
            artist.setNickName(artist.getNickName().replace(" ","%20"));
        }

        try
        {
            String query = "?nickname="+artist.getNickName()+"&firstname="+artist.getFirstName()+"&lastname="+artist.getLastName()
                    +"&bio="+artist.getBio();
            URL url = new URL("http://localhost:8080/createArtist"+query);
            sendRequest(url,"post");
            return true;
        }catch(MalformedURLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateArtist(Artist artist) {

        if (artist.getBio().contains(" "))
        {
            artist.setBio(artist.getBio().replace(" ","%20"));
        }

        if (artist.getNickName().contains(" "))
        {
            artist.setNickName(artist.getNickName().replace(" ","%20"));
        }

        try
        {
            String query = "?nickname="+artist.getNickName()+"&firstname="+artist.getFirstName()+"&lastname="+artist.getLastName()
                    +"&bio="+artist.getBio();
            URL url = new URL("http://localhost:8080/updateArtist"+query);
            sendRequest(url,"put");
            return true;
        }catch(MalformedURLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteArtist(String nickName) {

        try
        {
            nickName = nickName.contains(" ")?nickName.replace(" ","%20"):nickName;
            String query = "?nickname="+nickName;
            URL url = new URL("http://localhost:8080/deleteArtist"+query);
            sendRequest(url,"delete");
            return true;
        }catch(MalformedURLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args)
    {
        Artist a = new Artist("gg bb","kk","bb","ssrr esdasd");
        Artist b = new Artist("ccbb","kk","bb","sswawesdasd");
        Artist c = new Artist("ccbb","aa","bb","cc");
        artistClient t = new artistClient();
        t.addArtist(a);
        t.addArtist(b);

        t.getArtist("ccbb");
        t.updateArtist(c);
        t.getArtist("ccbb");
    }
}
