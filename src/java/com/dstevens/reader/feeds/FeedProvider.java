package com.dstevens.reader.feeds;

import java.text.ParseException;

import com.dstevens.reader.deserialization.DateFormatter;

public interface FeedProvider {

    Feed asFeed(DateFormatter dateFormatter, String sourceUri) throws ParseException;
    
}
