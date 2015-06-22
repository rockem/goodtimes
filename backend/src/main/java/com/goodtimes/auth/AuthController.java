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
        return new MyPrincipal(user);
    }

    public static class MyPrincipal implements Principal {

        public BigInteger id = BigInteger.ONE;

        private final Principal user;

        public MyPrincipal(Principal user) {
            this.user = user;
        }

        @Override
        public String getName() {
            return user.getName();
        }

        @Override
        public boolean implies(Subject subject) {
            return user.implies(subject);
        }

        @Override
        public boolean equals(Object obj) {
            return user.equals(obj);
        }

        @Override
        public int hashCode() {
            return user.hashCode();
        }
    }
}
