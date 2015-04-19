package com.goodtimes.users;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;

public interface UsersRepository extends MongoRepository<GoodtimesUser, BigInteger> {

    GoodtimesUser findByUsername(String username);

    List<GoodtimesUser> removeByUsername(String username);
}
