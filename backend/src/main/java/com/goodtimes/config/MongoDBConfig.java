package com.goodtimes.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import java.util.Arrays;

@Configuration
public class MongoDBConfig extends AbstractMongoConfiguration {

    @Value("${mongo.url}")
    private String url;

    @Value("${mongo.db}")
    private String databaseName;

    @Value("${mongo.username}")
    private String username;

    @Value("${mongo.password}")
    private String password;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(url);
    }

    @Override
    protected UserCredentials getUserCredentials() {
        return new UserCredentials(username, password);
    }
}
