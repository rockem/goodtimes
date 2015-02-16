package test.com.goodtimes.controller;

import com.goodtimes.entity.GoodtimeEvent;
import com.goodtimes.repository.EventsRepository;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class EventsControllerTest {

    public static final GoodtimeEvent SAVED_EVENT = new GoodtimeEvent(2L, "kuku", "Kuku's dinner");

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private EventsRepository eventsRepository;


    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldCreateNewEvent() throws Exception {
        GoodtimeEvent gte = new GoodtimeEvent(SAVED_EVENT.getName(), SAVED_EVENT.getDescription());
        when(eventsRepository.save(gte)).thenReturn(SAVED_EVENT);

        MvcResult result = this.mockMvc.perform(post("/events")
                .contentType(contentType)
                .content(new Gson().toJson(gte)))
                .andExpect(status().isCreated()).andReturn();

        String locationHeaders = result.getResponse().getHeaders("Location").get(0);
        assertTrue("Location header should include link to newly created event",
                locationHeaders.endsWith(SAVED_EVENT.getId().toString()));
        verify(eventsRepository).save(gte);
    }
}
