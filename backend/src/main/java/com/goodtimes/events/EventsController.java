package com.goodtimes.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventsController {

    private EventsRepository eventsRepository;

    @Autowired
    public EventsController(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody GoodtimeEvent event) {
        GoodtimeEvent saved_event = eventsRepository.save(event);
        return new ResponseEntity<>(null, createHttpHeaders(saved_event), HttpStatus.CREATED);
    }

    private HttpHeaders createHttpHeaders(GoodtimeEvent saved_event) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(
                ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(saved_event.getId()).toUri());
        return httpHeaders;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable final Long id) {
        eventsRepository.delete(BigInteger.valueOf(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<GoodtimeEvent> findAll() {
        return eventsRepository.findAll();
    }
}