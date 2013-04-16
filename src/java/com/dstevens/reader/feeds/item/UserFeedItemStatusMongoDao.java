package com.dstevens.reader.feeds.item;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dstevens.reader.persistence.mongo.DReaderDbProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

@Repository
public class UserFeedItemStatusMongoDao implements UserFeedItemStatusDao {

    private final UserFeedItemMongoTranslator translator;
    private final DReaderDbProvider dbProvider;

    @Autowired
    public UserFeedItemStatusMongoDao(UserFeedItemMongoTranslator translator,
                                      DReaderDbProvider dbProvider) {
        this.translator = translator;
        this.dbProvider = dbProvider;
    }
    
    @Override
    public UserFeedItemStatus getStatusFor(String userId, String link) {
        DBObject query = new BasicDBObject("ui", userId).append("link", link);
        DBObject statusFound = dbProvider.getUserFeedItems().findOne(query);
        return translator.from(statusFound);
    }

    @Override
    public UserFeedItemStatus getStatusFor(String feedItemId) {
        DBObject query = new BasicDBObject("_id", new ObjectId(feedItemId));
        DBObject statusFound = dbProvider.getUserFeedItems().findOne(query);
        return translator.from(statusFound);
    }
    
    @Override
    public void saveStatus(UserFeedItemStatus status) {
        DBObject newStatus = translator.from(status);
        DBCollection collection = dbProvider.getUserFeedItems();
        collection.save(newStatus);
    }
}
