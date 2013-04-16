package com.dstevens.reader.feeds.source;

import java.util.List;

interface UserFeedSourceDao {

    List<UserFeedSource> getDefinitionsFor(String userIdentifier);

    void save(UserFeedSource userFeedDefinition);
    
    void delete(String userId, String uri);
}
