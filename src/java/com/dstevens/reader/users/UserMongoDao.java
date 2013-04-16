package com.dstevens.reader.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dstevens.reader.persistence.mongo.DReaderDbProvider;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

@Repository
public class UserMongoDao implements UserDao {

    private final UserMongoTranslator translator;
    private final DReaderDbProvider dbProvider;

    @Autowired
    public UserMongoDao(UserMongoTranslator translator,
                        DReaderDbProvider dbProvider) {
        this.translator = translator;
        this.dbProvider = dbProvider;
    }

    @Override
    public void save(User user) {
        DBObject newFeed = translator.from(user);
        DBCollection collection = dbProvider.getUsers();
        boolean upsert = true;
        boolean touchAllMatches = false;
        collection.update(newFeed, newFeed, upsert, touchAllMatches);
    }

    @Override
    public User findBy(String email) {
        return findBy(translator.from(email));
    }
    
    @Override
    public User findById(String userId) {
        return findBy(translator.fromId(userId));
    }

    private User findBy(DBObject userEmail) {
        return translator.from(dbProvider.getUsers().findOne(userEmail));
    }
}
