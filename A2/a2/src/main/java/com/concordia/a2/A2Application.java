package com.concordia.a2;

import com.concordia.a2.service.logServiceImp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.xml.ws.Endpoint;

@SpringBootApplication
public class A2Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8867/logService", new logServiceImp());
        //SpringApplication.run(A2Application.class, args);
    }

   /*@Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        return multipartResolver;
    }*/

}
