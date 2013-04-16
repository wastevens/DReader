package com.dstevens.reader.feeds.source;

public class UserFeedSource {

    private String userIdentifier;
    private String feedUri;
    
    public UserFeedSource(String userIdentifier, String feedUri) {
        this.userIdentifier = userIdentifier;
        this.feedUri = feedUri;
    }
    
    public String getUserIdentifier() {
        return userIdentifier;
    }
    
    public String getFeedUri() {
        return feedUri;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof UserFeedSource) {
            UserFeedSource that = (UserFeedSource) o;
            return this.userIdentifier.equals(that.userIdentifier) &&
                   this.feedUri.equals(that.feedUri);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return (17 * userIdentifier.hashCode()) + (23 * feedUri.hashCode());
    }
    
    @Override
    public String toString() {
        return "UserFeedSource[userIdentifier: '" + userIdentifier + "', feedUri: '" + feedUri + "']";
    }
    
}
