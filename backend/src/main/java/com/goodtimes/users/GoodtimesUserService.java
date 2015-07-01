package com.goodtimes.users;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static java.util.stream.Collectors.toList;

public class GoodtimesUserService implements UserDetailsService {

    private final UsersRepository usersRepository;

    public GoodtimesUserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserFromRepository(username);
    }

    private UserDetails getUserFromRepository(String username) {
        GoodtimesUser user = usersRepository.findByUsername(username);
        validateUser(user, username);
        return user;
    }

    private void validateUser(GoodtimesUser user, String username) {
        if(user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
    }
}
