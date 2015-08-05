package com.goodtimes.users;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigInteger;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GoodtimesUser implements UserDetails {

    @Id
    private String id;
    private String email;
    private String password;
    @Indexed(unique = true)
    private String username;
    @Singular private Set<String> roles;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;


    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof GoodtimesUser) {
            return username.equals(((GoodtimesUser) rhs).username);
        }
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
    }

    public static GoodtimesUserBuilder createBuilderFrom(GoodtimesUser goodtimesUser) {
        return GoodtimesUser.builder()
                .id(goodtimesUser.getId())
                .username(goodtimesUser.getUsername())
                .password(goodtimesUser.getPassword())
                .email(goodtimesUser.getEmail())
                .roles(goodtimesUser.getRoles() == null ? new HashSet<>() : goodtimesUser.getRoles());
    }

}
