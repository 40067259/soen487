package com.concordia.a2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.xml.ws.Endpoint;

@Configuration
public class RestConfig {

   /* @Bean(name = "soapEndpoint")
    public Endpoint endpoint() {
        Endpoint p = new Endpoint();
        return multipartResolver;
    }*/
}
