package com.dstevens.reader.rss;

import org.springframework.stereotype.Repository;

import com.dstevens.reader.feeds.Feed;
import com.dstevens.util.TimedDReaderCache;

@Repository
public class RssReaderCache extends TimedDReaderCache<String, Feed>{

    private static final long KEEP_ALIVE = 1000 * 60 * 5;

    public RssReaderCache() {
        super(KEEP_ALIVE);
    }

}
