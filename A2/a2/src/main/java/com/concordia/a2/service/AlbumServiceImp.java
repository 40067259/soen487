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
    public int createAlbum(String isrc,String title, String description, String year, String author, Blob cover) {
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
    public int updateAlbum(String isrc, String title, String description, String year, String author, Blob cover) {
        return mapper.updateAlbum(isrc,title,description,year,author,cover);
    }

    @Override
    public int updateArtist(String nickname, String firstname, String lastname, String bio) {
        return mapper.updateArtist(nickname,firstname,lastname,bio);
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
        return mapper.getAlbumList();
    }

    @Override
    public List<Artist> getArtistList() {
        return mapper.getArtistList();
    }

    @Override
    public int updateAlbumCover(String isrc, Blob cover) {
        return mapper.updateCover(isrc,cover);
    }

    @Override
    public int deleteAlbumCover(String isrc) {
        return mapper.updateCover(isrc,null);
    }

    @Override
    public int updateAlbumNoCover(String isrc, String title, String description, String year, String author) {
        return mapper.updateAlbumNoCover(isrc,title,description,year,author);
    }

    @Override
    public byte[] getAlbumCover(String isrc) {
        return mapper.getCover(isrc);
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
