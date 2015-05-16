package com.goodtimes.users;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface UsersRepository extends MongoRepository<User, BigInteger> {

}
