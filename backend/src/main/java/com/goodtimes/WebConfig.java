package com.goodtimes;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.net.UnknownHostException;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/app/");
//    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/app/").setViewName("forward:/app/index.html");
    }

    public @Bean
    Mongo mongo() throws UnknownHostException {
        return new MongoClient("localhost");
    }
}
