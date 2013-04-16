package com.dstevens.reader.feeds;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

import com.dstevens.reader.feeds.item.FeedItem;
import com.dstevens.util.test.EqualityTester;

import static org.junit.Assert.assertEquals;

import static com.dstevens.util.CollectionUtils.list;

import static com.dstevens.util.test.DateUtils.dateFor;

public class FeedItemTest {

    @Test
    public void testEquality() {
        String title = "title";
        String link = "link";
        String description = "description";
        Date publicationDate = new Date(1000);
        Date otherDate = new Date(1001);
        Date nullPublicationDate = null;
        
        EqualityTester.testing(new FeedItem(title, link, publicationDate, description)).
                 assertEqualTo(new FeedItem(title, link, publicationDate, description)).
              assertNotEqualTo(new FeedItem("other" + title, link, publicationDate, description)).
              assertNotEqualTo(new FeedItem(title,"other" + link, publicationDate, description)).
              assertNotEqualTo(new FeedItem(title, link, nullPublicationDate, description)).
              assertNotEqualTo(new FeedItem(title, link, otherDate, description));
        
        EqualityTester.testing(new FeedItem(title, link, nullPublicationDate, description)).
            assertEqualTo(new FeedItem(title, link, nullPublicationDate, description)).
            assertNotEqualTo(new FeedItem(title, link, publicationDate, description));
    }
    
    @Test
    public void testDisplayablePublicationDateReturnsHourOnlyIfItemWasPublishedToday() {
        String title = "title";
        String link = "link";
        String description = "description";
        DateTime twoHoursAgo = DateTime.now().minusHours(2);
        FeedItem feedItem = new FeedItem(title, link, twoHoursAgo.toDate(), description);
        
        assertEquals(new SimpleDateFormat("h:mm a").format(twoHoursAgo.toDate()), feedItem.getDisplayablePublicationDate());
    }
    
    @Test
    public void testDisplayablePublicationDateReturnsMonthDayAndYearOnlyIfItemWasPublishedBeforeToday() {
        String title = "title";
        String link = "link";
        String description = "description";
        DateTime today = DateTime.now();
        Date yesterday = dateFor(today.year().get(), today.monthOfYear().get(), today.dayOfMonth().get() -1);
        FeedItem feedItem = new FeedItem(title, link, yesterday, description);
        
        assertEquals(new SimpleDateFormat("MMM d, yyyy").format(yesterday), feedItem.getDisplayablePublicationDate());
    }
    
    @Test
    public void testDisplayablePublicationDateReturnsEmptyStringForNullPublicationDate() {
        String title = "title";
        String link = "link";
        String description = "description";
        FeedItem feedItem = new FeedItem(title, link, null, description);
        
        assertEquals("", feedItem.getDisplayablePublicationDate());
    }
    
    @Test
    public void testComparable() {
        String title = "title";
        String linkA = "a link";
        String linkB = "b link";
        String description = "description";
        Date date1 = dateFor(2004, 5, 8);
        Date date2 = dateFor(2004, 5, 7);
        Date date3 = dateFor(2004, 5, 6);
        Date noDate = null;
        
        List<FeedItem> items = list(new FeedItem(title, linkA, date2, description), 
                                    new FeedItem(title, linkB, noDate, description), 
                                    new FeedItem(title, linkA, date1, description), 
                                    new FeedItem(title, linkA, noDate, description), 
                                    new FeedItem(title, linkA, date3, description));
        
        Collections.sort(items);
        assertEquals(list(new FeedItem(title, linkA, noDate, description), 
                          new FeedItem(title, linkB, noDate, description),
                          new FeedItem(title, linkA, date1, description), 
                          new FeedItem(title, linkA, date2, description), 
                          new FeedItem(title, linkA, date3, description)), 
                     items);
    }
}
