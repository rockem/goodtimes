package com.goodtimes.users;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface UsersRepository extends MongoRepository<GoodtimesUser, BigInteger> {

    GoodtimesUser findByUsername(String username);

    List<GoodtimesUser> removeByUsername(String username);

}
