package com.goodtimes.events;

import com.goodtimes.support.HttpUtil;
import com.goodtimes.users.GoodtimesUser;
import com.goodtimes.users.UserEvent;
import com.goodtimes.users.UsersEventsRepository;
import com.goodtimes.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/events")
public class EventsController {

    private final EventsRepository eventsRepository;
    private final UsersEventsRepository usersEventsRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public EventsController(EventsRepository eventsRepository,
                            UsersEventsRepository usersEventsRepository,
                            UsersRepository usersRepository) {
        this.eventsRepository = eventsRepository;
        this.usersEventsRepository = usersEventsRepository;
        this.usersRepository = usersRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody GoodtimeEvent event, Principal user) {
        String userId = getCurrentUserIdFrom(user);
        GoodtimeEvent saved_event = eventsRepository.save(event);
        usersEventsRepository.save(new UserEvent(userId, saved_event.getId()));
        return new ResponseEntity<>(null,
                HttpUtil.createPostHttpHeaders(saved_event.getId()),
                HttpStatus.CREATED);
    }

    private String getCurrentUserIdFrom(Principal user) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) user;
        return ((GoodtimesUser) token.getPrincipal()).getId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable final String id) {
        eventsRepository.delete(id);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAll() {
        eventsRepository.deleteAll();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<GoodtimeEvent> findAll(Principal user) {
        List<String> eventIds = usersEventsRepository.findByUserId(
                getCurrentUserIdFrom(user)).stream().map(UserEvent::getEventId).collect(toList());
        return getEventsForIds(eventIds);
    }

    private List<GoodtimeEvent> getEventsForIds(List<String> eventIds) {
        Iterator<GoodtimeEvent> eventsIterator = eventsRepository.findAll(eventIds).iterator();
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(eventsIterator, Spliterator.ORDERED), false).collect(
                Collectors.<GoodtimeEvent>toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public GoodtimeEvent findOne(@PathVariable final String id) {
        return eventsRepository.findOne(id);
    }

    @RequestMapping(value = "/{id}/share", method = RequestMethod.PUT)
    public void share(@PathVariable("id") String eventId, @RequestBody ShareRequest shareRequest) {
        GoodtimesUser user = usersRepository.findByEmail(shareRequest.getEmail());
        usersEventsRepository.save(new UserEvent(user.getId(), eventId));
    }

}