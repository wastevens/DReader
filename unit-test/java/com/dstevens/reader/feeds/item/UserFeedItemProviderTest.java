package com.dstevens.reader.feeds.item;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dstevens.reader.feeds.FeedDescription;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import static com.dstevens.util.test.DateUtils.dateFor;

public class UserFeedItemProviderTest {

    private static final String USER_IDENTIFIER = "some user id";
    
    @Mock private UserFeedItemStatusRepository repository;
    
    private UserFeedItemProvider userFeedItemProvider;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        userFeedItemProvider = new UserFeedItemProvider(repository);
    }
    
    @Test
    public void testFromFeedItemForUser() {
        FeedDescription feedDescription = new FeedDescription("feed title", "feed link", "source uri");
        
        String title = "title";
        String link = "link";
        Date publicationDate = dateFor(2013, 4, 5);
        String description = "description";
        FeedItem feedItem = new FeedItem(title, link, publicationDate, description);
        
        UserFeedItemStatus status = new UserFeedItemStatus("id", USER_IDENTIFIER, link, FeedItemStatus.READ);
        when(repository.getStatusFor(USER_IDENTIFIER, feedItem)).thenReturn(status);
        
        UserFeedItem userFeedItem = userFeedItemProvider.fromFeedItem(USER_IDENTIFIER, feedDescription, feedItem);
        
        assertEquals(new UserFeedItem(feedDescription, feedItem, status), userFeedItem);
    }
    
}
