package com.goodtimes.events;

import com.goodtimes.auth.AuthService;
import com.goodtimes.support.HttpUtil;
import com.goodtimes.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventsController {

    private final AuthService authService;
    private final EventsRepository eventsRepository;


    @Autowired
    public EventsController(EventsRepository eventsRepository, AuthService authService) {
        this.eventsRepository = eventsRepository;
        this.authService = authService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody GoodtimeEvent event) {
        event.setUserId(authService.getCurrentUserId());
        GoodtimeEvent saved_event = eventsRepository.save(event);
        return new ResponseEntity<>(null,
                HttpUtil.createPostHttpHeaders(saved_event.getId().toString()),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable final BigInteger id) {
        eventsRepository.delete(id);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAll() {
        eventsRepository.deleteAll();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<GoodtimeEvent> findAll() {
        return eventsRepository.findAllByUserId(authService.getCurrentUserId());
    }
}