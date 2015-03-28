package com.goodtimes.events;

import com.goodtimes.events.GoodtimeEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by rockem on 16/2/15.
 */
public interface EventsRepository extends MongoRepository<GoodtimeEvent, Long> {

}
