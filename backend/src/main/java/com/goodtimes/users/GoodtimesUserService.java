package com.goodtimes.users;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GoodtimesUserService implements UserDetailsService {

    public static final String ADMIN_USERNAME = "goodtimesAdmin";
    public static final String ADMIN_PASSWORD = "ksud7dksD3rdTGS345skdnsfhk";
    private final UsersRepository usersRepository;

    public GoodtimesUserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(ADMIN_USERNAME.equals(username)) {
            return new User(ADMIN_USERNAME, ADMIN_PASSWORD, Collections.singletonList(new SimpleGrantedAuthority("ADMIN")));
        }
        return getUserFromRepository(username);
    }

    private UserDetails getUserFromRepository(String username) {
        GoodtimesUser user = usersRepository.findByUsername(username);
        validateUser(user, username);
        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    private void validateUser(GoodtimesUser user, String username) {
        if(user == null) {
            throw new UsernameNotFoundException(String.format("User:%s not found", username));
        }
    }
}
