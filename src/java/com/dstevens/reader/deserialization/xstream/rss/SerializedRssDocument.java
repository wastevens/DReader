package com.dstevens.reader.deserialization.xstream.rss;

import java.text.ParseException;

import com.dstevens.reader.deserialization.DateFormatter;
import com.dstevens.reader.feeds.Feed;
import com.dstevens.reader.feeds.FeedProvider;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("rss")
public class SerializedRssDocument implements FeedProvider {

    private SerializedRssChannel channel;
    
    @Override
    public String toString() {
        return "SerializedRssDocument[channel:" + channel + "]";
    }

    @Override
    public Feed asFeed(DateFormatter dateFormatter, String sourceUri) throws ParseException {
        return channel.asFeed(dateFormatter, sourceUri);
    }
    
}
