package it.com.goodtimes.events;


import com.goodtimes.events.EventsRepository;
import com.goodtimes.events.GoodtimeEvent;
import com.goodtimes.users.GoodtimesUser;
import com.google.gson.Gson;
import org.junit.Before;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import it.com.goodtimes.support.BaseMvcIT;
import it.com.goodtimes.support.MockMvcHelper;
import org.junit.Test;
import org.mockito.internal.matchers.EndsWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class EventsControllerIT extends BaseMvcIT {

    private static final GoodtimeEvent SAVED_EVENT =
            new GoodtimeEvent(BigInteger.valueOf(2), "kuku", "Kuku's dinner", BigInteger.valueOf(45));
    private static final String API_EVENTS = "/api/events/";

    private static final GoodtimesUser CURRENT_USER = GoodtimesUser.builder()
            .id(BigInteger.valueOf(45))
            .username("kuku")
            .build();

    private static final UsernamePasswordAuthenticationToken CURRENT_PRINCIPAL =
            new UsernamePasswordAuthenticationToken(
                    CURRENT_USER,
                    CURRENT_USER.getPassword(),
                    CURRENT_USER.getAuthorities());


    @Autowired
    private EventsRepository eventsRepository;

    @Before
    public void setUp() throws Exception {
        super.setup();
    }

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
        GoodtimeEvent gte = new GoodtimeEvent(null, SAVED_EVENT.getName(), SAVED_EVENT.getDescription(), null);
        when(eventsRepository.save(addIdTo(CURRENT_USER.getId(), gte))).thenReturn(SAVED_EVENT);
        mockMvc.perform(post(API_EVENTS)
                .contentType(MockMvcHelper.CONTENT_TYPE)
                .content(new Gson().toJson(gte))
                .principal(CURRENT_PRINCIPAL))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", new EndsWith(API_EVENTS + SAVED_EVENT.getId().toString())));
    }

    private GoodtimeEvent addIdTo(BigInteger id, GoodtimeEvent event) {
        GoodtimeEvent result = event.createAClone();
        result.setUserId(id);
        return result;
    }

    @Test
    public void retrieveAllEventsForCurrentUser() throws Exception {
        when(eventsRepository.findAllByUserId(CURRENT_USER.getId()))
                .thenReturn(Collections.singletonList(SAVED_EVENT));

        mockMvc.perform(get(API_EVENTS).principal(CURRENT_PRINCIPAL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MockMvcHelper.CONTENT_TYPE))
                .andExpect(jsonPath("$[0].id", is(SAVED_EVENT.getId().intValue())))
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
