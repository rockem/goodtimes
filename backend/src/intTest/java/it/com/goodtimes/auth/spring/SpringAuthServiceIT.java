package it.com.goodtimes.auth.spring;

import com.goodtimes.auth.AuthService;
import com.goodtimes.users.GoodtimesUser;
import com.goodtimes.users.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.math.BigInteger;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class SpringAuthServiceIT {

    @Configuration
    @ComponentScan(basePackages = {"com.goodtimes.auth"})
    static class ContextConfiguration {

        @Bean
        public UsersRepository usersRepository() {
            return Mockito.mock(UsersRepository.class);
        }
    }

    public static final GoodtimesUser CURRENT_USER = GoodtimesUser.builder()
            .id(BigInteger.valueOf(45))
            .username("eli")
            .password("pass")
            .email("e.e@gmail.com").build();


    @Autowired
    private AuthService authService;
    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void retrieveCurrentUserId() throws Exception {
        when(usersRepository.findByUsername(CURRENT_USER.getUsername())).thenReturn(CURRENT_USER);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(createUserFromCurrent(), null));

        assertThat(authService.getCurrentUserId(), is(CURRENT_USER.getId()));
    }

    private User createUserFromCurrent() {
        return new User(CURRENT_USER.getUsername(), CURRENT_USER.getPassword(), new ArrayList<>());
    }
}