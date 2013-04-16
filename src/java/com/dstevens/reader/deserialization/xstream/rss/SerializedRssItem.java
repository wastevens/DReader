package com.dstevens.reader.deserialization.xstream.rss;

import java.text.ParseException;

import com.dstevens.reader.deserialization.DateFormatter;
import com.dstevens.reader.feeds.item.FeedItem;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("item")
public class SerializedRssItem {

    private String title;
    private String link;
    private String pubDate;
    private String description;
    
    @SuppressWarnings("unused") //part of the rss standard, but I'm ignoring this for now
    private String guid;
    
    public String getPubDate() {
        return pubDate;
    }
    
    public FeedItem asItem(DateFormatter dateFormatter) throws ParseException {
        return new FeedItem(title, link, dateFormatter.formatFrom(pubDate), description);
    }
    
    @Override
    public String toString() {
        return "SerializedRssItem[title:" + title + ", link: " + link + "]";
    }
    
}
