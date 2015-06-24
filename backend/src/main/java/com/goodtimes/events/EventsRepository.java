package com.goodtimes.events;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface EventsRepository extends MongoRepository<GoodtimeEvent, BigInteger> {

    List<GoodtimeEvent> findAllByUserId(BigInteger userId);
}
