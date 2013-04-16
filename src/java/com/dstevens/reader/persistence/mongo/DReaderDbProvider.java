package com.dstevens.reader.persistence.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.persistence.mongo.MongoDbProvider;
import com.mongodb.DBCollection;

@Service
public class DReaderDbProvider {

    private final MongoDbProvider provider;

    @Autowired
    public DReaderDbProvider(MongoDbProvider provider) {
        this.provider = provider;
    }
    
    public DBCollection getUserFeedItems() {
        return provider.getCollection("userFeedItems");
    }
    
    public DBCollection getFeeds() {
        return provider.getCollection("feeds");
    }
    
    public DBCollection getUsers() {
        return provider.getCollection("users");
    }
    
}
