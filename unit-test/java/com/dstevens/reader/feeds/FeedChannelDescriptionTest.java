package com.dstevens.reader.feeds;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.dstevens.util.test.EqualityTester;

import static org.junit.Assert.assertEquals;

import static com.dstevens.util.CollectionUtils.list;

public class FeedChannelDescriptionTest {

    @Test
    public void testEquals() {
        String title = "title";
        String link = "link";
        String sourceUri = "sourceUri";
        
        EqualityTester.testing(new FeedDescription(title, link, sourceUri)).
                 assertEqualTo(new FeedDescription(title, link, sourceUri)).
              assertNotEqualTo(new FeedDescription("other" + title, link, sourceUri)).
              assertNotEqualTo(new FeedDescription(title, "other" + link, sourceUri)).
              assertNotEqualTo(new FeedDescription(title, "other" + link, "other" + sourceUri)).
              assertNotEqualTo("Not a FeedChannelDescription");
    }
    
    @Test
    public void testCompare() {
        String link = "link doesn't matter";
        String sourceUri = "neither does source uri";
        String title1 = "A";
        String title2 = "B";
        String title3 = "C";
        FeedDescription feed1 = new FeedDescription(title1, link, sourceUri);
        FeedDescription feed2 = new FeedDescription(title2, link, sourceUri);
        FeedDescription feed3 = new FeedDescription(title3, link, sourceUri);
        
        List<FeedDescription> feeds = list(feed2, feed3, feed1);
        Collections.sort(feeds);
        assertEquals(list(feed1, feed2, feed3), feeds);
    }

}
