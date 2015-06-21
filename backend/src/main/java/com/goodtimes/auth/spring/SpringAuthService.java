package com.goodtimes.auth.spring;

import com.goodtimes.auth.AuthService;
import com.goodtimes.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class SpringAuthService implements AuthService {

    private UsersRepository usersRepository;

    @Autowired
    public SpringAuthService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public BigInteger getCurrentUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return usersRepository.findByUsername(username).getId();
    }
}
