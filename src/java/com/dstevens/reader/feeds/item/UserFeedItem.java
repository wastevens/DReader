package com.dstevens.reader.feeds.item;

import com.dstevens.reader.feeds.FeedDescription;

public class UserFeedItem implements Comparable<UserFeedItem> {

    private final FeedDescription channelDescription;
    private final FeedItem item;
    private final UserFeedItemStatus status;

    public UserFeedItem(FeedDescription channelDescription, FeedItem item, UserFeedItemStatus status) {
        this.channelDescription = channelDescription;
        this.item = item;
        this.status = status;
    }

    public boolean isUnread() {
        return status.getStatus().equals(FeedItemStatus.UNREAD);
    }
    
    public String getId() {
        return status.getId();
    }
    
    public String getUserId() {
        return status.getUserId();
    }
    
    public FeedDescription getChannelDescription() {
        return channelDescription;
    }
    
    public FeedItem getItem() {
        return item;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof UserFeedItem) {
            UserFeedItem that = (UserFeedItem) o;
            return this.channelDescription.equals(that.channelDescription) &&
                   this.item.equals(that.item) &&
                   this.status.equals(that.status);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 23 + channelDescription.hashCode() + item.hashCode();
    }
    
    @Override
    public String toString() {
        return "UserFeedEntry[channelDescription: '" + channelDescription +"', item: '" + item + ", status: '" + status + "']";
    }
    
    @Override
    public int compareTo(UserFeedItem o) {
        if (item.compareTo(o.item) != 0) {
            return item.compareTo(o.item);
        }
        return channelDescription.compareTo(o.channelDescription);
    }
    
}
