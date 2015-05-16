package com.goodtimes.users;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigInteger;

@EqualsAndHashCode
public class User {

    @Getter
    private BigInteger id;
    @Getter
    private String username;
    @Getter
    private String password;
    @Getter
    private String email;

    public User() {}

    public User(String user, String pass, String mail) {
        this.username = user;
        this.password = pass;
        this.email = mail;
    }
}
