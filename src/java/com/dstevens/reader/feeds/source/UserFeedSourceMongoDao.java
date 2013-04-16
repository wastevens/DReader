package com.dstevens.reader.feeds.source;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dstevens.reader.persistence.mongo.DReaderDbProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import static com.dstevens.util.CollectionUtils.list;

@Repository
public class UserFeedSourceMongoDao implements UserFeedSourceDao {

    private final UserFeedSourceMongoTranslator translator;
    private final DReaderDbProvider dbProvider;

    @Autowired
    public UserFeedSourceMongoDao(UserFeedSourceMongoTranslator translator, DReaderDbProvider dbProvider) {
        this.translator = translator;
        this.dbProvider = dbProvider;
    }
    
    @Override
    public List<UserFeedSource> getDefinitionsFor(String userIdentifier) {
        List<UserFeedSource> definitions = list();
        DBObject query = new BasicDBObject("ui", userIdentifier);
        DBCollection collection = dbProvider.getFeeds();
        DBCursor results = collection.find(query);
        try {
            while(results.hasNext()) {
                definitions.add(translator.from(results.next()));
            }
        } finally {
            results.close();
        }
        return definitions;
    }

    @Override
    public void save(UserFeedSource userFeedDefinition) {
        DBObject newFeed = translator.from(userFeedDefinition);
        DBCollection collection = dbProvider.getFeeds();
        boolean upsert = true;
        boolean touchAllMatches = false;
        collection.update(newFeed, newFeed, upsert, touchAllMatches);
    }

    @Override
    public void delete(String userIdentifier, String uri) {
        DBObject feedToDelete = new BasicDBObject("ui", userIdentifier).append("uri", uri);
        DBCollection collection = dbProvider.getFeeds();
        collection.remove(feedToDelete);
    }
}
