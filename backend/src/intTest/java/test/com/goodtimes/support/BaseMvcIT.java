package test.com.goodtimes.support;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

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

}
