package com.goodtimes.events;

import com.goodtimes.auth.AuthService;
import com.goodtimes.support.HttpUtil;
import com.goodtimes.users.GoodtimesUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventsController {

    private final EventsRepository eventsRepository;

    @Autowired
    public EventsController(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody GoodtimeEvent event, Principal user) {
        event.setUserId(getCurrentUserIdFrom(user));
        GoodtimeEvent saved_event = eventsRepository.save(event);
        return new ResponseEntity<>(null,
                HttpUtil.createPostHttpHeaders(saved_event.getId().toString()),
                HttpStatus.CREATED);
    }

    private BigInteger getCurrentUserIdFrom(Principal user) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) user;
        return ((GoodtimesUser) token.getPrincipal()).getId();
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
    public List<GoodtimeEvent> findAll(Principal user) {
        return eventsRepository.findAllByUserId(getCurrentUserIdFrom(user));
    }
}