package com.goodtimes.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.Subject;
import java.math.BigInteger;
import java.security.Principal;

@RestController
public class AuthController {

    @RequestMapping("/api/user")
    public Principal user(Principal user) {
        return user;
    }
}
