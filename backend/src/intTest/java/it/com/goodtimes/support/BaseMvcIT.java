package it.com.goodtimes.support;

import com.goodtimes.auth.AuthService;
import com.goodtimes.events.EventsRepository;
import com.goodtimes.users.UsersRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppTestContext.class})
@WebAppConfiguration
public abstract class BaseMvcIT {

    protected MockMvc mockMvc;
    @Autowired
    private WebApplicationContext applicationContext;

    @Before
    public void setup() throws Exception {
        mockMvc = webAppContextSetup(applicationContext).build();
    }


    @Autowired
    private EventsRepository eventsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private AuthService authService;

    @After
    public void tearDown() throws Exception {
        reset(eventsRepository);
        reset(usersRepository);
        reset(authService);
    }
}
