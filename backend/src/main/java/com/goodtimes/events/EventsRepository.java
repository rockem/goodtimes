package com.goodtimes.events;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface EventsRepository extends MongoRepository<GoodtimeEvent, BigInteger> {

}
