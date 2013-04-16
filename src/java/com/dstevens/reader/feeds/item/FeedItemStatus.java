package com.dstevens.reader.feeds.item;

public enum FeedItemStatus {

    UNREAD,
    READ;
    
    public static FeedItemStatus statusFor(Integer key) {
        return values()[key];
    }
    
}
