package com.dstevens.reader.feeds.item;

public class UserFeedItemStatus {

    private final String id;
    private final String userId;
    private final String link;
    
    private FeedItemStatus status;

    public UserFeedItemStatus(String id, String userId, String link, FeedItemStatus status) {
        this.id = id;
        this.userId = userId;
        this.link = link;
        this.status = status;
    }

    public String getId() {
        return id;
    }
    
    public FeedItemStatus getStatus() {
        return status;
    }

    public String getUserId() {
        return userId;
    }

    public String getLink() {
        return link;
    }

    public void markAsRead() {
        this.status = FeedItemStatus.READ;
    }
    
    @Override
    public String toString() {
        return "UserFeedItemStatus[id: " + id+ ", userId: " + userId +", link: " + link +", status: " + status +"]";
    }

}
