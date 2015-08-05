package com.goodtimes.users;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersEventsRepository extends MongoRepository<UserEvent, String> {

    List<UserEvent> findByUserId(String userId);

}
