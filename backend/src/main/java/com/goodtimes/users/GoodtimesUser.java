package com.goodtimes.users;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class GoodtimesUser {

    @Id
    private BigInteger id;
    private String username;
    private String password;
    private String email;
    private List<String> roles = new ArrayList<>();

    public GoodtimesUser() {}

    public GoodtimesUser(String user, String pass, String mail) {
        this.username = user;
        this.password = pass;
        this.email = mail;
    }

}
