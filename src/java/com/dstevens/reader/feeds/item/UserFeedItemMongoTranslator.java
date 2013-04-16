package com.dstevens.reader.feeds.item;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class UserFeedItemMongoTranslator {

    public UserFeedItemStatus from(DBObject statusFound) {
        if (statusFound == null) {
            return null;
        }
        String id = ((ObjectId) statusFound.get("_id")).toString();
        String userId = (String) statusFound.get("ui");
        String link = (String) statusFound.get("link");
        Integer key = (Integer) statusFound.get("status");
        
        FeedItemStatus status = FeedItemStatus.statusFor(key);
        return new UserFeedItemStatus(id, userId, link, status);
    }

    public DBObject from(UserFeedItemStatus status) {
        BasicDBObject dbObject = new BasicDBObject("ui", status.getUserId()).
                                            append("link", status.getLink()).
                                            append("status", status.getStatus().ordinal());
        
        if (status.getId() != null) {
            return dbObject.append("_id", new ObjectId(status.getId()));
        }
        return dbObject;
    }

}
