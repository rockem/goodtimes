package com.goodtimes.events;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

/**
 * Created by rockem on 16/2/15.
 */
public interface EventsRepository extends MongoRepository<GoodtimeEvent, BigInteger> {

}
