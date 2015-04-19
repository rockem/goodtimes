package com.goodtimes.users;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigInteger;

@Getter
@EqualsAndHashCode
public class GoodtimesUser {

    private BigInteger id;
    private String username;
    private String password;
    private String email;

    public GoodtimesUser() {}

    public GoodtimesUser(String user, String pass, String mail) {
        this.username = user;
        this.password = pass;
        this.email = mail;
    }

    public GoodtimesUser(BigInteger id, String user, String pass, String mail) {
        this(user, pass, mail);
        this.id = id;
    }
}
