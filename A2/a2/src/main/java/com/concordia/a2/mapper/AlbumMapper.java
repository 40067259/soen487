package com.concordia.a2.mapper;

import com.concordia.a2.pojo.Album;
import com.concordia.a2.pojo.Artist;
import com.soen487.log_ws.LogEntry;
import org.apache.ibatis.annotations.*;

import java.io.File;
import java.sql.Blob;
import java.util.List;

@Mapper
public interface AlbumMapper {

     @Select("select * from album where isrc = #{isrc}")
     public Album getAlbumByIsrc(String isrc);

     @Select("select * from artist where nickname = #{nickname}")
     public Artist getArtistByNickname(String nickname);

     @Select("select * from album")
     public List<Album> getAlbumList();

     @Select("select * from artist")
     public List<Artist> getArtistList();

    @Select("select * from logentry")
    public List<LogEntry> getAllEntry();

     @Select("select * from logentry where type = #{type} and " +
             "STRCMP(timestamp,#{from}) >= 0 and STRCMP(timestamp,#{to}) <= 0")
     public List<LogEntry> getLogEntry(String from,String to,String type);

     @Select("select * from logentry where type = #{type}")
     public List<LogEntry> getLogEntryNoLimit(String type);

     @Select("select cover from album WHERE isrc = #{isrc}")
     public byte[] getCover(String isrc);

    @Insert("insert into album(isrc,title,description,year,author,cover) " +
            "values(#{isrc},#{title},#{description},#{year},#{author},#{cover})")
     public int createAlbum(String isrc, String title, String description, String year, String author, Blob cover);

     @Insert("insert into artist(nickname,firstname,lastname,bio) " +
             "values(#{nickname},#{firstname},#{lastname},#{bio})")
     public int createArtist(String nickname,String firstname, String lastname,String bio);

     @Insert("insert into logentry(timestamp,type,isrc) values (#{timestamp},#{type},#{isrc})")
     public int createLogEntry(String timestamp,String type, String isrc);

     @Delete("delete from album WHERE isrc = (#{isrc})")
     public int deleteAlbumByPostId(String isrc);

     @Update("UPDATE album SET title=#{title},description=#{description},year=#{year}," +
            "author=#{author},cover=#{cover} WHERE isrc = #{isrc}")
     public int updateAlbum(String isrc,String title, String description, String year, String author, Blob cover);

    @Update("UPDATE album SET title=#{title},description=#{description},year=#{year}," +
            "author=#{author} WHERE isrc = #{isrc}")
    public int updateAlbumNoCover(String isrc,String title, String description, String year, String author);

     @Update("UPDATE artist SET firstname=#{firstname},lastname=#{lastname},bio=#{bio}" +
            "WHERE nickname = #{nickname}")
     public int updateArtist(String nickname, String firstname, String lastname, String bio);

     @Update("UPDATE album SET cover = #{cover} WHERE isrc = #{isrc}")
     public int updateCover(String isrc,Blob cover);


}
