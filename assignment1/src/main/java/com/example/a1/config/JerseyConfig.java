package com.example.a1.config;

import com.example.a1.controller.AlbumJerseyController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
//@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig(){
        register(AlbumJerseyController.class);
        //packages("com.example.a1.config");
    }
}
