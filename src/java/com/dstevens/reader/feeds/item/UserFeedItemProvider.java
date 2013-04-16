package com.dstevens.reader.feeds.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.reader.feeds.FeedDescription;

@Service
public class UserFeedItemProvider {

    private final UserFeedItemStatusRepository statusRepository;

    @Autowired
    public UserFeedItemProvider(UserFeedItemStatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }
    
    public UserFeedItem fromFeedItem(String userIdentifier, FeedDescription feedDescription, FeedItem feedItem) {
        UserFeedItemStatus status = statusRepository.getStatusFor(userIdentifier, feedItem);
        return new UserFeedItem(feedDescription, feedItem, status);
    }
    
}
