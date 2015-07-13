package it.com.goodtimes.users;

import com.goodtimes.users.GoodtimesUser;
import com.goodtimes.users.UsersRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import it.com.goodtimes.support.MockMvcHelper;
import org.junit.Test;
import org.mockito.internal.matchers.EndsWith;
import org.springframework.beans.factory.annotation.Autowired;
import it.com.goodtimes.support.TestContext;
import it.com.goodtimes.support.WebAppTestContext;

import java.math.BigInteger;

import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppTestContext.class})
@WebAppConfiguration
public class UsersControllerIT {

    public static final String API_USERS = "/api/users/";

    @Autowired
    private UsersRepository usersRepository;

    protected MockMvc mockMvc;
    @Autowired
    private WebApplicationContext applicationContext;

    @Before
    public void setup() throws Exception {
        mockMvc = webAppContextSetup(applicationContext).build();
    }

    @Test
    public void shouldCreateNewUser() throws Exception {
        GoodtimesUser user = GoodtimesUser.builder().username("USER").password("PASS").build();
        String userId = "4";
        when(usersRepository.save(user)).thenReturn(createUserWithIdFrom(userId, user));
        new MockMvcHelper(mockMvc)
                .postObjectToUrl(user, API_USERS)
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", new EndsWith(API_USERS + userId)));
        verify(usersRepository).save(argThat(new IsPasswordEncrypted(user.getPassword())));
        verify(usersRepository).save(argThat(new UserIsEnabled()));
    }

    private class IsPasswordEncrypted extends ArgumentMatcher<GoodtimesUser> {

        private final String originalPassword;

        public IsPasswordEncrypted(String originalPassword) {
            this.originalPassword = originalPassword;
        }

        @Override
        public boolean matches(Object argument) {
            return new BCryptPasswordEncoder().matches(originalPassword, ((GoodtimesUser)argument).getPassword());
        }
    }

    private class UserIsEnabled extends ArgumentMatcher<GoodtimesUser> {

        @Override
        public boolean matches(Object argument) {
            GoodtimesUser gtu = (GoodtimesUser) argument;
            return gtu.isEnabled() &&
                    gtu.isAccountNonExpired() &&
                    gtu.isAccountNonLocked() &&
                    gtu.isCredentialsNonExpired();
        }
    }

    private GoodtimesUser createUserWithIdFrom(String id, GoodtimesUser user) {
        return GoodtimesUser.builder()
                .id(id)
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail()).build();
    }

    @Test
    public void shouldDeleteUsersByUsername() throws Exception {
        mockMvc.perform(delete(API_USERS + "kuku"))
                .andExpect(status().isOk());
        verify(usersRepository).removeByUsername("kuku");

    }
}