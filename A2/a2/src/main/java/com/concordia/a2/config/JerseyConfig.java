package com.concordia.a2.config;

import com.concordia.a2.controller.AlbumController;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

    @Component
    public class JerseyConfig extends ResourceConfig {
        public JerseyConfig(){
            register(AlbumController.class);
            //register(MultiPartFeature.class);

        }
    }

