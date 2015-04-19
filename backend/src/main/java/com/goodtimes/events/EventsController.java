package com.goodtimes.events;

import com.goodtimes.support.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(null,
                HttpUtil.createPostHttpHeaders(saved_event.getId().toString()),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable final BigInteger id) {
        eventsRepository.delete(id);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteAll() {
        eventsRepository.deleteAll();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<GoodtimeEvent> findAll() {
        return eventsRepository.findAll();
    }
}