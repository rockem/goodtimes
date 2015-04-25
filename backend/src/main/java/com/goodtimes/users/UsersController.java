package com.goodtimes.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UsersController {


    @RequestMapping(method = RequestMethod.GET)
    public String findAll() {
        return "Eli";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> signup(@RequestBody String body) {
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }
}
