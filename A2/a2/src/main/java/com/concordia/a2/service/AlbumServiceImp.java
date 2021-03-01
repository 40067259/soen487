package com.concordia.a2.service;

import com.concordia.a2.mapper.AlbumMapper;
import com.concordia.a2.pojo.Album;
import com.concordia.a2.pojo.Artist;
import com.concordia.a2.pojo.LogEntry;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AlbumServiceImp implements AlbumService{

    @Autowired
    AlbumMapper mapper;

    @Override
    public String getPostTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date = formatter.format(now);
        return date;
    }

    @Override
    public int createAlbum(String isrc,String title, String description, String year, String author, File cover) {
        return mapper.createAlbum(isrc,title,description,year,author,cover);
    }

    @Override
    public int createArtist(String nickname, String firstname, String lastname, String bio) {
        return mapper.createArtist(nickname, firstname, lastname, bio);
    }

    @Override
    public int createLogEntry(String timestamp, String type, String isrc) {
        return mapper.createLogEntry(timestamp,type,isrc);
    }

    @Override
    public String updateAlbum(String isrc, String title, String description, String year, Artist artist, Blob cover) {
        return null;
    }

    @Override
    public int deleteAlbum(String isrc) {
        return mapper.deleteAlbumByPostId(isrc);
    }


    @Override
    public Artist getArtist(String nickname) {
        return mapper.getArtistByNickname(nickname);
    }

    @Override
    public Album getAlbumInfo(String isrc) {
        return mapper.getAlbumByIsrc(isrc);
    }

    @Override
    public List<Album> getAlbumList() {
        return null;
    }

    @Override
    public String updateAlbumCover(String isrc, Blob cover) {
        return null;
    }

    @Override
    public String deleteAlbumCover(String isrc) {
        return null;
    }

    @Override
    public byte[] getAlbumCover(String isrc) {
        return new byte[0];
    }

    @Override
    public List<LogEntry> getChangeLogs(String from, String to, String type) {
        return null;
    }

    @Override
    public String clearLogs() {
        return null;
    }
}
