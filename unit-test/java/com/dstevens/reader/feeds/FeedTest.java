package com.dstevens.reader.feeds;

import java.util.List;

import org.junit.Test;

import com.dstevens.reader.feeds.item.FeedItem;
import com.dstevens.util.test.EqualityTester;

import static com.dstevens.util.CollectionUtils.list;

import static org.mockito.Mockito.mock;

public class FeedTest {

    @Test
    public void testEquals() {
        FeedDescription description = mock(FeedDescription.class);
        FeedDescription otherDescription = mock(FeedDescription.class);
        List<FeedItem> items = list(mock(FeedItem.class), mock(FeedItem.class));
        List<FeedItem> otherItems = list(mock(FeedItem.class), mock(FeedItem.class));
        
        EqualityTester.testing(new Feed(description, items)).
            assertEqualTo(new Feed(description, items)).
            assertNotEqualTo(new Feed(otherDescription, items)).
            assertNotEqualTo(new Feed(description, otherItems)).
            assertNotEqualTo("Not a FeedTest");
    }
    
}
