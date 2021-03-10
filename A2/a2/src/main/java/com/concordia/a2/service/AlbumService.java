package com.concordia.a2.service;

import com.concordia.a2.pojo.Album;
import com.concordia.a2.pojo.Artist;

import java.io.File;
import java.sql.Blob;
import java.util.List;

public interface AlbumService {

       String getPostTime();
       int createAlbum(String isrc,String title, String description, String year, String author, Blob cover);
       int createArtist(String nickname, String firstname, String lastname, String bio);
       int createLogEntry(String timestamp,String type,String isrc);
       int updateAlbum(String isrc,String title, String description, String year, String author, Blob cover);
       int updateAlbumNoCover(String isrc,String title, String description, String year, String author);
       int updateArtist(String nickname,String firstname,String lastname,String bio);
       int deleteAlbum(String isrc);


       Album getAlbumInfo(String isrc);
       Artist getArtist(String nickname);
       List<Album> getAlbumList();
       List<Artist> getArtistList();

       int updateAlbumCover(String isrc,Blob cover);
       int deleteAlbumCover(String isrc);
       byte[] getAlbumCover(String isrc);

       /*List<LogEntry> getChangeLogs(String from,String to,String type);*/
       String clearLogs();

}
