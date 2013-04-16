package com.dstevens.reader.feeds;

import java.util.List;

import com.dstevens.reader.feeds.item.FeedItem;

public class Feed {

    private FeedDescription description;
    
    private List<FeedItem> items;
    
    public Feed(FeedDescription description, List<FeedItem> items) {
        this.description = description;
        this.items = items;
    }
    
    public String getTitle() {
        return description.getTitle();
    }
    
    public FeedDescription getDescription() {
        return description;
    }
    
    public List<FeedItem> getItems() {
        return items;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Feed) {
            Feed that = (Feed) obj;
            return this.description.equals(that.description) &&
                   this.items.equals(that.items);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return 13 + description.hashCode();
    }
    
    @Override
    public String toString() {
        return "Feed[description: '" + description +"', items: '" + items + "']";
    }
}
