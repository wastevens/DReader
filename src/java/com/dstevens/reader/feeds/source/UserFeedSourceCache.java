package com.dstevens.reader.feeds.source;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dstevens.util.DReaderCache;

@Repository
public class UserFeedSourceCache extends DReaderCache<String, List<UserFeedSource>> {

}
