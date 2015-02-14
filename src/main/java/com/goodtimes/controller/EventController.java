package com.goodtimes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @RequestMapping("/greeting")
    public String greeting() {
        return "hello world";
    }
}