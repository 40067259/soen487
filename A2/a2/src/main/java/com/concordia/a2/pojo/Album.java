package com.concordia.a2.pojo;

import com.concordia.a2.exception.DuplicatedIdException;

import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Album implements Comparable {
    static Set<String> idSet = new HashSet<>();
    @XmlAttribute
    private String isrc;
    @XmlElement
    private String title;
    @XmlElement
    private String description;
    @XmlElement
    private String year;
    @XmlElement
    private String author;
    @XmlElement
    private Artist artist;
    @XmlElement
    public byte[] cover;


    public Album(String title, String description, String year,String author, Artist artist,byte[] cover) {
        String id = UUID.randomUUID().toString().toLowerCase().substring(0,6);
        while(idSet.contains(id)){
            id = UUID.randomUUID().toString().toLowerCase().substring(1,7);
        }
        this.cover = cover;
        this.isrc = id;
        this.title = title;
        this.description = description;
        this.year = year;
        this.author = author;
        this.artist = artist;
        idSet.add(this.isrc);
    }

    public Album(String isrc, String title, String description, String year, String author,Artist artist,byte[] cover) throws DuplicatedIdException {
        if(idSet.contains(isrc)) throw new DuplicatedIdException();
        this.isrc = isrc;
        this.title = title;
        this.description = description;
        this.year = year;
        this.author = author;
        this.artist = artist;
        this.cover = cover;
        idSet.add(isrc);
    }

    public Album(){}

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    public static Set<String> getIdSet() {
        return idSet;
    }

    public static void setIdSet(Set<String> idSet) {
        Album.idSet = idSet;
    }

    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Album{" +
                "isrc='" + isrc + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", year='" + year + '\'' +
                ", artist=" + author +
                '}';
    }


    @Override
    public int compareTo(Object o) {
        Album tem = (Album) o;
        return this.title.compareTo(((Album) o).title);
    }
}
