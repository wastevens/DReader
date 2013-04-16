package com.dstevens.reader.feeds;

import java.util.List;

import org.junit.Test;

import com.dstevens.reader.feeds.item.UserFeedItem;
import com.dstevens.util.test.EqualityTester;

import static org.mockito.Mockito.mock;

import static com.dstevens.util.CollectionUtils.list;

public class UserFeedTest {

    @Test
    public void testEquals() {
        List<UserFeedItem> entries = list(mock(UserFeedItem.class), mock(UserFeedItem.class));
        List<UserFeedItem> otherEntries = list(mock(UserFeedItem.class), mock(UserFeedItem.class));
        
        EqualityTester.testing(new UserFeed(entries)).
            assertEqualTo(new UserFeed(entries)).
            assertNotEqualTo(new UserFeed(otherEntries)).
            assertNotEqualTo("Not a UserFeedTest");
    }
    
}
