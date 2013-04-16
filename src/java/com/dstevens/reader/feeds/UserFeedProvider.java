package com.dstevens.reader.feeds;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.reader.feeds.item.FeedItem;
import com.dstevens.reader.feeds.item.UserFeedItem;
import com.dstevens.reader.feeds.item.UserFeedItemProvider;
import com.dstevens.reader.feeds.source.UserFeedSource;
import com.dstevens.reader.feeds.source.UserFeedSourceRepository;
import com.dstevens.reader.rss.RssReader;

import static com.dstevens.util.CollectionUtils.list;

@Service
public class UserFeedProvider {

    private final RssReader reader;
    private final UserFeedSourceRepository repository;
    private final UserFeedItemProvider userFeedItemProvider;

    @Autowired
    public UserFeedProvider(UserFeedSourceRepository repository, RssReader reader, UserFeedItemProvider userFeedItemProvider) {
        this.repository = repository;
        this.reader = reader;
        this.userFeedItemProvider = userFeedItemProvider;
    }
    
    public UserFeed forUser(String userIdentifier) {
        List<Feed> feeds = feedForUser(userIdentifier);
        List<UserFeedItem> userFeedItems = list();
        for (Feed feed : feeds) {
            for (FeedItem feedItem : feed.getItems()) {
                userFeedItems.add(userFeedItemProvider.fromFeedItem(userIdentifier, feed.getDescription(), feedItem));
            }
        }
        Collections.sort(userFeedItems);
        return new UserFeed(userFeedItems);
    }

    public List<Feed> feedForUser(String userIdentifier) {
        List<Feed> feeds = list();
        List<UserFeedSource> feedSources = repository.getFeedsFor(userIdentifier);
        for (UserFeedSource source : feedSources) {
            try {
                feeds.add(reader.read(source.getFeedUri()));
            } catch(Exception e) {
                //Failed to read from one of the user's feed URIs; skip it and move on.
            }
        }
        return feeds;
    }
}
