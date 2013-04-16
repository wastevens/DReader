package com.dstevens.persistence.mongo;

import java.net.UnknownHostException;

import org.springframework.stereotype.Service;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

@Service
public class MongoDbProvider {

    private static MongoClient mongoClient;
    private static DB dreaderDb;
    
    private DB getDB() {
        try {
            if (mongoClient == null) {
                mongoClient = new MongoClient();
            }
            if (dreaderDb == null) {
                dreaderDb = mongoClient.getDB("dreader");
            }
            return dreaderDb;
        } catch (UnknownHostException e) {
            throw new IllegalStateException("Unknown host when trying to get db provider", e);
        }
    }
    
    public DBCollection getCollection(String collection) {
        return getDB().getCollection(collection);
    }
    
}
