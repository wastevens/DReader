package com.dstevens.reader.feeds;

import org.junit.Test;

import com.dstevens.util.test.EqualityTester;

import static org.junit.Assert.assertEquals;

public class FeedDescriptionTest {

    @Test
    public void testEquals() {
        String title = "some title";
        String link = "some link";
        String sourceUri = "source uri";
        String otherTitle = "some other title";
        String otherLink = "some other link";
        String otherSourceUri = "other source uri";
        
        EqualityTester.testing(new FeedDescription(title, link, sourceUri)).
            assertEqualTo(new FeedDescription(title, link, sourceUri)).
            assertNotEqualTo(new FeedDescription(otherTitle, link, sourceUri)).
            assertNotEqualTo(new FeedDescription(title, otherLink, sourceUri)).
            assertNotEqualTo(new FeedDescription(title, link, otherSourceUri)).
            assertNotEqualTo("Not a FeedDescriptionTest");
    }
    
    @Test
    public void testComparable() {
        String sourceUri = "source uri";
        String titleA = "a title";
        String titleB = "b title";
        String titleC = "c title";
        String linkA = "a link";
        String linkB = "b link";
        
        assertEquals(0, new FeedDescription(titleA, linkA, sourceUri).compareTo(new FeedDescription(titleA, linkB, sourceUri)));
        assertEquals(0, new FeedDescription(titleB, linkA, sourceUri).compareTo(new FeedDescription(titleB, linkB, sourceUri)));
        assertEquals(0, new FeedDescription(titleC, linkA, sourceUri).compareTo(new FeedDescription(titleC, linkB, sourceUri)));
        
        assertEquals(-1, new FeedDescription(titleA, linkA, sourceUri).compareTo(new FeedDescription(titleB, linkA, sourceUri)));
        assertEquals(-1, new FeedDescription(titleA, linkB, sourceUri).compareTo(new FeedDescription(titleB, linkB, sourceUri)));
        assertEquals(-1, new FeedDescription(titleA, linkA, sourceUri).compareTo(new FeedDescription(titleB, linkB, sourceUri)));
        assertEquals(-1, new FeedDescription(titleA, linkB, sourceUri).compareTo(new FeedDescription(titleB, linkA, sourceUri)));
        
        assertEquals(1, new FeedDescription(titleB, linkA, sourceUri).compareTo(new FeedDescription(titleA, linkA, sourceUri)));
        assertEquals(1, new FeedDescription(titleB, linkB, sourceUri).compareTo(new FeedDescription(titleA, linkB, sourceUri)));
        assertEquals(1, new FeedDescription(titleB, linkA, sourceUri).compareTo(new FeedDescription(titleA, linkB, sourceUri)));
        assertEquals(1, new FeedDescription(titleB, linkB, sourceUri).compareTo(new FeedDescription(titleA, linkA, sourceUri)));
        
        assertEquals(-1, new FeedDescription(titleA, linkA, sourceUri).compareTo(new FeedDescription(titleB, linkA, sourceUri)));
        assertEquals(-2, new FeedDescription(titleA, linkA, sourceUri).compareTo(new FeedDescription(titleC, linkA, sourceUri)));
        assertEquals(-1, new FeedDescription(titleB, linkA, sourceUri).compareTo(new FeedDescription(titleC, linkA, sourceUri)));
        assertEquals(1, new FeedDescription(titleB, linkA, sourceUri).compareTo(new FeedDescription(titleA, linkA, sourceUri)));
        assertEquals(2, new FeedDescription(titleC, linkA, sourceUri).compareTo(new FeedDescription(titleA, linkA, sourceUri)));
        assertEquals(1, new FeedDescription(titleC, linkA, sourceUri).compareTo(new FeedDescription(titleB, linkA, sourceUri)));
    }
    
}
