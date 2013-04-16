package com.dstevens.reader.feeds.item;

import org.springframework.stereotype.Repository;

import com.dstevens.util.DReaderCache;
import com.dstevens.util.Pair;

@Repository
public class UserFeedItemStatusCache extends DReaderCache<Pair<String, String>, UserFeedItemStatus>{
}
