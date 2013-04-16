package com.dstevens.reader.feeds.item;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.dstevens.reader.feeds.FeedDescription;
import com.dstevens.util.test.EqualityTester;

import static org.junit.Assert.assertEquals;

import static com.dstevens.util.CollectionUtils.list;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserFeedItemTest {

    @Test
    public void testEquals() {
        FeedDescription channelDescription = mock(FeedDescription.class);
        FeedItem feedItem = mock(FeedItem.class);
        UserFeedItemStatus status = mock(UserFeedItemStatus.class);
        FeedDescription otherChannelDescription = mock(FeedDescription.class);
        FeedItem otherFeedItem = mock(FeedItem.class);
        UserFeedItemStatus otherStatus = mock(UserFeedItemStatus.class);
        
        EqualityTester.testing(new UserFeedItem(channelDescription, feedItem, status)).
                 assertEqualTo(new UserFeedItem(channelDescription, feedItem, status)).
              assertNotEqualTo(new UserFeedItem(otherChannelDescription, feedItem, status)).
              assertNotEqualTo(new UserFeedItem(channelDescription, otherFeedItem, status)).
              assertNotEqualTo(new UserFeedItem(channelDescription, feedItem, otherStatus)).
              assertNotEqualTo("Not a UserFeedItemTest");
    }
    
    @Test
    public void testComparable() {
        FeedDescription channelDescriptionA = mock(FeedDescription.class, "channel A");
        FeedDescription channelDescriptionB = mock(FeedDescription.class, "channel B");
        FeedItem feedItemA = mock(FeedItem.class, "item A");
        FeedItem feedItemB = mock(FeedItem.class, "item B");
        FeedItem feedItemC = mock(FeedItem.class, "item C");
        UserFeedItemStatus status = mock(UserFeedItemStatus.class, "status");
        
        when(feedItemA.compareTo(feedItemB)).thenReturn(-1);
        when(feedItemA.compareTo(feedItemC)).thenReturn(-1);
        when(feedItemB.compareTo(feedItemC)).thenReturn(-1);
        
        when(feedItemC.compareTo(feedItemA)).thenReturn(1);
        when(feedItemC.compareTo(feedItemB)).thenReturn(1);
        when(feedItemB.compareTo(feedItemA)).thenReturn(1);
        
        when(feedItemA.compareTo(feedItemA)).thenReturn(0);
        when(feedItemB.compareTo(feedItemB)).thenReturn(0);
        when(feedItemC.compareTo(feedItemC)).thenReturn(0);
        
        when(channelDescriptionA.compareTo(channelDescriptionB)).thenReturn(-1);
        when(channelDescriptionB.compareTo(channelDescriptionA)).thenReturn(1);
        
        List<UserFeedItem> userFeedItems = 
            list(new UserFeedItem(channelDescriptionB, feedItemB, status),
                 new UserFeedItem(channelDescriptionA, feedItemC, status),
                 new UserFeedItem(channelDescriptionB, feedItemA, status),
                 new UserFeedItem(channelDescriptionA, feedItemA, status),
                 new UserFeedItem(channelDescriptionB, feedItemC, status),
                 new UserFeedItem(channelDescriptionA, feedItemB, status));
        
        Collections.sort(userFeedItems);
        
        List<UserFeedItem> sortedItems = 
               list(new UserFeedItem(channelDescriptionA, feedItemA, status),
                    new UserFeedItem(channelDescriptionB, feedItemA, status),
                    new UserFeedItem(channelDescriptionA, feedItemB, status),
                    new UserFeedItem(channelDescriptionB, feedItemB, status),
                    new UserFeedItem(channelDescriptionA, feedItemC, status),
                    new UserFeedItem(channelDescriptionB, feedItemC, status));
        
        assertEquals(sortedItems, userFeedItems);
    }
    
}
