package it.com.goodtimes.users;

import com.goodtimes.users.GoodtimesUser;
import com.goodtimes.users.UsersRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
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

import static org.mockito.Mockito.verify;
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
        GoodtimesUser user = new GoodtimesUser("USER", "PASS", "MAIL");
        int userId = 4;
        when(usersRepository.save(user)).thenReturn(createUserWithIdFrom(userId, user));
        new MockMvcHelper(mockMvc)
                .postObjectToUrl(user, API_USERS)
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", new EndsWith(API_USERS + userId)));
        ;
    }

    private GoodtimesUser createUserWithIdFrom(int id, GoodtimesUser user) {
        return GoodtimesUser.builder()
                .id(BigInteger.valueOf(id))
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