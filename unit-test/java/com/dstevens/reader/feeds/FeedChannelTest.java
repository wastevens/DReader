package com.dstevens.reader.feeds;

import java.util.List;

import org.junit.Test;

import com.dstevens.reader.feeds.Feed;
import com.dstevens.reader.feeds.FeedDescription;
import com.dstevens.reader.feeds.item.FeedItem;
import com.dstevens.util.test.EqualityTester;

import static org.mockito.Mockito.mock;

import static com.dstevens.util.CollectionUtils.list;

public class FeedChannelTest {

    @Test
    public void testEquality() {
        FeedDescription description = mock(FeedDescription.class);
        FeedDescription otherDescription = mock(FeedDescription.class);
        
        FeedItem item = mock(FeedItem.class);
        FeedItem otherItem = mock(FeedItem.class);
        List<FeedItem> items = list(item);
        List<FeedItem> otherItems = list(otherItem);
        
        EqualityTester.testing(new Feed(description, items)).
                 assertEqualTo(new Feed(description, items)).
              assertNotEqualTo(new Feed(otherDescription, items)).
              assertNotEqualTo(new Feed(description, otherItems));
    }
}
