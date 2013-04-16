package com.dstevens.reader.feeds.item;

public interface UserFeedItemStatusDao {

    UserFeedItemStatus getStatusFor(String userId, String link);

    UserFeedItemStatus getStatusFor(String feedItemId);
    
    void saveStatus(UserFeedItemStatus unread);


}
