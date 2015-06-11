package test.com.goodtimes.events;


import com.goodtimes.events.EventsRepository;
import com.goodtimes.events.GoodtimeEvent;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import test.com.goodtimes.support.BaseMvcIT;
import test.com.goodtimes.support.MockMvcHelper;
import org.junit.Test;
import org.mockito.internal.matchers.EndsWith;
import org.springframework.beans.factory.annotation.Autowired;
import test.com.goodtimes.support.TestContext;
import test.com.goodtimes.support.WebAppTestContext;

import java.math.BigInteger;
import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class EventsControllerIT extends BaseMvcIT {

    public static final GoodtimeEvent SAVED_EVENT = new GoodtimeEvent(BigInteger.valueOf(2L), "kuku", "Kuku's dinner");
    public static final String API_EVENTS = "/api/events/";

    @Autowired
    private EventsRepository eventsRepository;

    @Test
    public void shouldDeleteEvent() throws Exception {
        when(eventsRepository.findAll()).thenReturn(Collections.singletonList(SAVED_EVENT));
        mockMvc.perform(
                delete(API_EVENTS + SAVED_EVENT.getId()))
                .andExpect(status().isOk());
        verify(eventsRepository).delete(SAVED_EVENT.getId());
    }

    @Test
    public void shouldCreateNewEvent() throws Exception {
        GoodtimeEvent gte = new GoodtimeEvent(null, SAVED_EVENT.getName(), SAVED_EVENT.getDescription());
        when(eventsRepository.save(gte)).thenReturn(SAVED_EVENT);

        new MockMvcHelper(mockMvc).postObjectToUrl(gte, API_EVENTS)
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", new EndsWith(API_EVENTS + SAVED_EVENT.getId().toString())));

    }

    @Test
    public void retrieveAllExistingEvents() throws Exception {
        when(eventsRepository.findAll())
                .thenReturn(Collections.singletonList(SAVED_EVENT));

        mockMvc.perform(get(API_EVENTS))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MockMvcHelper.CONTENT_TYPE))
                .andExpect(jsonPath("$.[0].id", is(SAVED_EVENT.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(SAVED_EVENT.getName())))
                .andExpect(jsonPath("$[0].description", is(SAVED_EVENT.getDescription())));
    }

    @Test
    public void shouldDelegateDeleteAll() throws Exception {
        mockMvc.perform(
                delete(API_EVENTS))
                .andExpect(status().isOk());
        verify(eventsRepository).deleteAll();
    }
}