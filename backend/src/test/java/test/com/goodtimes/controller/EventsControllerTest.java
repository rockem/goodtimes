package test.com.goodtimes.controller;

import com.goodtimes.controller.EventsController;
import com.goodtimes.entity.GoodtimeEvent;
import com.goodtimes.repository.EventsRepository;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.Charset;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class EventsControllerTest {

    public static final GoodtimeEvent SAVED_EVENT = new GoodtimeEvent(2L, "kuku", "Kuku's dinner");

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    private EventsRepository eventsRepository;


    @Before
    public void setup() throws Exception {
        eventsRepository = mock(EventsRepository.class);
        EventsController controller = new EventsController(eventsRepository);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void shouldCreateNewEvent() throws Exception {
        GoodtimeEvent gte = new GoodtimeEvent(SAVED_EVENT.getName(), SAVED_EVENT.getDescription());
        when(eventsRepository.save(gte)).thenReturn(SAVED_EVENT);

        MvcResult result = postObjectToUrl(gte, "/rest/events")
                .andExpect(status().isCreated()).andReturn();

        String locationHeaders = result.getResponse().getHeaders("Location").get(0);
        assertTrue("Location header should include link to newly created event",
                locationHeaders.endsWith(SAVED_EVENT.getId().toString()));
    }

    private ResultActions postObjectToUrl(GoodtimeEvent gte, String url) throws Exception {
        return this.mockMvc.perform(post(url)
                .contentType(contentType)
                .content(new Gson().toJson(gte)));
    }

    @Test
    public void retrieveAllExistingEvents() throws Exception {
        when(eventsRepository.findAll())
                .thenReturn(Arrays.asList(SAVED_EVENT));

        ResultActions result = mockMvc.perform(get("/rest/events"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.[0].id", is(SAVED_EVENT.getId().intValue())));
                //.andExpect(jsonPath("$[0].name", is(SAVED_EVENT.getName())))
                //.andExpect(jsonPath("$[0].description", is(SAVED_EVENT.getDescription())));

    }
}
