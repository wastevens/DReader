package com.dstevens.reader.feeds;

import org.junit.Test;

import com.dstevens.reader.feeds.source.UserFeedSource;
import com.dstevens.util.test.EqualityTester;

public class UserFeedDefinitionTest {

    @Test
    public void testEquals() {
        String userIdentifier = "identifier";
        String uriFeed = "uri";
        String otherUserIdentifier = "other identifier";
        String otherUriFeed = "other uri";
        
        EqualityTester.testing(new UserFeedSource(userIdentifier, uriFeed)).
                 assertEqualTo(new UserFeedSource(userIdentifier, uriFeed)).
              assertNotEqualTo(new UserFeedSource(otherUserIdentifier, uriFeed)).
              assertNotEqualTo(new UserFeedSource(userIdentifier, otherUriFeed)).
              assertNotEqualTo("Not a UserFeedDefinitionTest");
    }
    
}
