package com.dstevens.reader.feeds.source;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserFeedSourceRepository {

    private final UserFeedSourceDao dao;
    private final UserFeedSourceCache cache;

    @Autowired
    public UserFeedSourceRepository(UserFeedSourceDao dao, UserFeedSourceCache cache) {
        this.dao = dao;
        this.cache = cache;
    }
    
    public List<UserFeedSource> getFeedsFor(String userIdentifier) {
        if (cache.has(userIdentifier)) {
            return cache.get(userIdentifier);
        }
        return cache.put(userIdentifier, dao.getDefinitionsFor(userIdentifier));
    }

    public void saveFeed(UserFeedSource userFeedDefinition) {
        String userIdentifier = userFeedDefinition.getUserIdentifier();
        cache.evict(userIdentifier);
        dao.save(userFeedDefinition);
        cache.put(userIdentifier, dao.getDefinitionsFor(userIdentifier));
    }
    
    public void deleteFeed(String userIdentifier, String uri) {
        cache.evict(userIdentifier);
        dao.delete(userIdentifier, uri);
        cache.put(userIdentifier, dao.getDefinitionsFor(userIdentifier));
    }
    
}
