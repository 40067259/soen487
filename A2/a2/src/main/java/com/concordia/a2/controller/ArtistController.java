package com.concordia.a2.controller;

import com.concordia.a2.pojo.Artist;
import com.concordia.a2.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("artist/")
public class ArtistController {

    @Autowired
    AlbumService albumService;
    @GetMapping(path = "getArtist/{nickname}")
    public Artist getArtist(@PathVariable(value = "nickname") String nickname){
        return albumService.getArtist(nickname);
    }
}
