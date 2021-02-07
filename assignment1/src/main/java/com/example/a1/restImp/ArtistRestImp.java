package com.example.a1.restImp;

import com.example.a1.rest.Artist;
import com.example.a1.rest.ArtistRest;

import java.util.concurrent.CopyOnWriteArrayList;


public class ArtistRestImp implements ArtistRest {
    public static CopyOnWriteArrayList<Artist> artistList = new CopyOnWriteArrayList<>();

    public static Artist getArtistByName(String nickName){
        for(int i = 0;i < artistList.size(); i++){
            if(artistList.get(i).getNickName().equals(nickName)) return artistList.get(i);
        }
        return null;
    }

    /*static{
        String taylorBio = "Taylor Alison Swift is an American singer-songwriter. Her narrative songwriting, which often" +
                " takes inspiration from her personal life, has received widespread critical plaudits and media coverage." +
                " Born in West Reading, Pennsylvania, Swift relocated to Nashville, Tennessee, in 2004 to pursue a career " +
                "in country music";
        String katyBio = "Katy Perry, original name Katheryn Elizabeth Hudson, (born October 25, 1984, Santa Barbara, " +
                "California, U.S.), American pop singer who gained fame for a string of anthemic and often sexually " +
                "suggestive hit songs, as well as for a playfully cartoonish sense of style.";
        Artist taylor = new Artist("Taylor Alison Swift","Taylor","Swift",taylorBio);
        Artist katy = new Artist("Katy Perry","Katheryn","Hudson",katyBio);
        artistList.add(taylor);
        artistList.add(katy);

    }*/

    @Override
    public CopyOnWriteArrayList<Artist> getAllArtists() {
        return artistList;
    }

    @Override
    public Artist getArtist(String nickName) {
        for(int i = 0; i < artistList.size();i++){
            Artist target = artistList.get(i);
            if(target.getNickName().equals(nickName)){
                return target;
            }
        }
        return null;
    }

    @Override
    public boolean addArtist(Artist artist) {
        for(int i = 0; i < artistList.size();i++){
            Artist target = artistList.get(i);
            if(target.getNickName().equals(artist.getNickName())){
                return false;
            }
        }
        artistList.add(artist);
        return true;
    }

    @Override
    public boolean updateArtist(Artist artist) {
        for(int i = 0; i < artistList.size(); i++){
            Artist target = artistList.get(i);
            if(target.getNickName().equals(artist.getNickName())){
               artistList.set(i,artist);
               return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteArtist(String nickName) {
        for(int i = 0; i < artistList.size();i++){
            if(artistList.get(i).getNickName().equals(nickName)){
                artistList.remove(i);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String nickName = "Katy Perry";
        Artist a = getArtistByName(nickName);

        System.out.println(a);
    }
}
