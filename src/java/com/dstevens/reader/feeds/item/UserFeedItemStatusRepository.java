package com.dstevens.reader.feeds.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dstevens.util.Pair;

import static com.dstevens.util.Pair.pair;

@Repository
public class UserFeedItemStatusRepository {

    private final UserFeedItemStatusDao dao;
    private final UserFeedItemStatusCache cache;

    @Autowired
    public UserFeedItemStatusRepository(UserFeedItemStatusDao dao, UserFeedItemStatusCache cache) {
        this.dao = dao;
        this.cache = cache;
    }
    
    public UserFeedItemStatus getStatusFor(String userId, FeedItem item) {
        Pair<String, String> cacheKey = pair(userId, item.getLink());
        if (cache.has(cacheKey)) {
            return cache.get(cacheKey);
        }
        
        UserFeedItemStatus status = dao.getStatusFor(userId, item.getLink());
        if (status != null) {
            return cache.put(cacheKey, status);
        }
        
        createUnread(userId, item);
        return cache.put(cacheKey, dao.getStatusFor(userId, item.getLink()));
    }

    private void createUnread(String userId, FeedItem item) {
        dao.saveStatus(new UserFeedItemStatus(null, userId, item.getLink(), FeedItemStatus.UNREAD));
    }
    
    public void markAsRead(String feedItemId) {
        UserFeedItemStatus status = dao.getStatusFor(feedItemId);
        status.markAsRead();
        dao.saveStatus(status);
        cache.put(pair(status.getUserId(), status.getLink()), status);
    }
}
