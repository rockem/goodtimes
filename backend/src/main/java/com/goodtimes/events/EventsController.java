package com.goodtimes.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(
                ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved_event.getId()).toUri());
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable final Long id) {
        eventsRepository.delete(BigInteger.valueOf(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<GoodtimeEvent> findAll() {
        return eventsRepository.findAll();
    }
}