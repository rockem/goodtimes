package com.goodtimes.repository;

import com.goodtimes.entity.GoodtimeEvent;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rockem on 16/2/15.
 */
public interface EventsRepository extends CrudRepository<GoodtimeEvent, Long> {

    //GoodtimeEvent save(GoodtimeEvent event);
}
