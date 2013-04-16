package com.dstevens.reader.feeds.source;

import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class UserFeedSourceMongoTranslator {

    public DBObject from(UserFeedSource definition) {
        return new BasicDBObject("ui",  definition.getUserIdentifier()).append("uri", definition.getFeedUri());
    }
    
    public UserFeedSource from(DBObject dbObject) {
        return new UserFeedSource(dbObject.get("ui").toString(), dbObject.get("uri").toString());
    }
    
}
