package com.dstevens.reader.feeds;

import java.util.List;

import com.dstevens.reader.feeds.item.UserFeedItem;

public class UserFeed {
    
    private final List<UserFeedItem> entries;
    
    public UserFeed(List<UserFeedItem> entries) {
        this.entries = entries;
    }
    
    public List<UserFeedItem> getEntries() {
        return entries;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof UserFeed) {
            UserFeed that = (UserFeed) o;
            return this.entries.equals(that.entries);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return entries.hashCode();
    }
    
    @Override
    public String toString() {
        return "UserFeed[entries: '"+entries+"']";
    }
}
