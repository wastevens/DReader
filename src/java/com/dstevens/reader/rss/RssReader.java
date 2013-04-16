package com.dstevens.reader.rss;

import java.io.IOException;
import java.text.ParseException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.reader.deserialization.DateFormatter;
import com.dstevens.reader.deserialization.xstream.XStreamProvider;
import com.dstevens.reader.feeds.Feed;
import com.dstevens.reader.feeds.FeedProvider;
import com.dstevens.reader.stream.StreamReader;
import com.thoughtworks.xstream.XStream;

@Service
public class RssReader {

    private final StreamReader streamReader;
    private final XStreamProvider xStreamProvider;
    private final DateFormatter dateFormatter;
    private final RssReaderCache cache;

    @Autowired
    public RssReader(StreamReader streamReader, XStreamProvider xStreamProvider, DateFormatter dateFormatter, RssReaderCache cache) {
        this.streamReader = streamReader;
        this.xStreamProvider = xStreamProvider;
        this.dateFormatter = dateFormatter;
        this.cache = cache;
    }
    
    public Feed read(String uri) throws ClientProtocolException, IOException, ParseException {
        if (cache.has(uri)) {
            return cache.get(uri);
        }
        XStream stream = xStreamProvider.getXStream();
        
        String readStreamFrom = streamReader.readStreamFrom(uri);
        try {
            Feed feed = ((FeedProvider) stream.fromXML(readStreamFrom)).asFeed(dateFormatter, uri);
            return cache.put(uri, feed);
        } catch(Exception e) {
            System.out.println("Error reading feed from " + uri);
            e.printStackTrace(System.out);
            throw new RuntimeException(e);
        }
    }
    
}
