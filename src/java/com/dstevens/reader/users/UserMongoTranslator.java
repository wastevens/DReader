package com.dstevens.reader.users;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class UserMongoTranslator {

    public DBObject from(User user) {
        return new BasicDBObject("email",  user.getEmail());
    }
    
    public DBObject from(String email) {
        return new BasicDBObject("email",  email);
    }

    public DBObject fromId(String userId) {
        return new BasicDBObject("_id", new ObjectId(userId));
    }
    
    public User from(DBObject dbObject) {
        if (dbObject == null) {
            return null;
        }
        String id = ((ObjectId) dbObject.get("_id")).toString();
        String email = (String) dbObject.get("email");
        return new User(id, email);
    }
}
