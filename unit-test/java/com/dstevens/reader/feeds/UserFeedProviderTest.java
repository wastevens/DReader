package com.dstevens.reader.feeds;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dstevens.reader.feeds.item.FeedItem;
import com.dstevens.reader.feeds.item.UserFeedItem;
import com.dstevens.reader.feeds.item.UserFeedItemProvider;
import com.dstevens.reader.feeds.source.UserFeedSource;
import com.dstevens.reader.feeds.source.UserFeedSourceRepository;
import com.dstevens.reader.rss.RssReader;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import static com.dstevens.util.CollectionUtils.list;

public class UserFeedProviderTest {


    private static final String USER_IDENTIFIER = "some user id";
    private static final String FEED_URI_1 = "feed uri 1";
    private static final String FEED_URI_2 = "feed uri 2";
    private static final String FEED_URI_3 = "feed uri 3";
    
    @Mock private UserFeedSourceRepository repository;
    @Mock private RssReader reader;
    @Mock private UserFeedItemProvider userFeedItemProvider;
    @Mock private UserFeedSource source1;
    @Mock private UserFeedSource source2;
    @Mock private UserFeedSource source3;
    @Mock private Feed feed1;
    @Mock private Feed feed2;
    @Mock private Feed feed3;
    @Mock private FeedDescription description1;
    @Mock private FeedDescription description2;
    @Mock private FeedDescription description3;
    @Mock private FeedItem item1;
    @Mock private FeedItem item2;
    @Mock private FeedItem item3;
    @Mock private FeedItem item4;
    @Mock private FeedItem item5;
    @Mock private FeedItem item6;
    @Mock private UserFeedItem userFeedItem1;
    @Mock private UserFeedItem userFeedItem2;
    @Mock private UserFeedItem userFeedItem3;
    @Mock private UserFeedItem userFeedItem4;
    @Mock private UserFeedItem userFeedItem5;
    @Mock private UserFeedItem userFeedItem6;
    
    private UserFeedProvider provider;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        when(source1.getFeedUri()).thenReturn(FEED_URI_1);
        when(source2.getFeedUri()).thenReturn(FEED_URI_2);
        when(source3.getFeedUri()).thenReturn(FEED_URI_3);
        
        when(userFeedItem1.compareTo(userFeedItem2)).thenReturn(-1);
        when(userFeedItem1.compareTo(userFeedItem3)).thenReturn(-1);
        when(userFeedItem1.compareTo(userFeedItem4)).thenReturn(-1);
        when(userFeedItem1.compareTo(userFeedItem5)).thenReturn(-1);
        when(userFeedItem1.compareTo(userFeedItem6)).thenReturn(-1);
        
        when(userFeedItem2.compareTo(userFeedItem3)).thenReturn(-1);
        when(userFeedItem2.compareTo(userFeedItem4)).thenReturn(-1);
        when(userFeedItem2.compareTo(userFeedItem5)).thenReturn(-1);
        when(userFeedItem2.compareTo(userFeedItem6)).thenReturn(-1);
        
        when(userFeedItem3.compareTo(userFeedItem4)).thenReturn(-1);
        when(userFeedItem3.compareTo(userFeedItem5)).thenReturn(-1);
        when(userFeedItem3.compareTo(userFeedItem6)).thenReturn(-1);
        
        when(userFeedItem4.compareTo(userFeedItem5)).thenReturn(-1);
        when(userFeedItem4.compareTo(userFeedItem6)).thenReturn(-1);
        
        when(userFeedItem5.compareTo(userFeedItem6)).thenReturn(-1);
        
        when(userFeedItem6.compareTo(userFeedItem5)).thenReturn(1);
        when(userFeedItem6.compareTo(userFeedItem4)).thenReturn(1);
        when(userFeedItem6.compareTo(userFeedItem3)).thenReturn(1);
        when(userFeedItem6.compareTo(userFeedItem2)).thenReturn(1);
        when(userFeedItem6.compareTo(userFeedItem1)).thenReturn(1);
        
        when(userFeedItem5.compareTo(userFeedItem4)).thenReturn(1);
        when(userFeedItem5.compareTo(userFeedItem3)).thenReturn(1);
        when(userFeedItem5.compareTo(userFeedItem2)).thenReturn(1);
        when(userFeedItem5.compareTo(userFeedItem1)).thenReturn(1);
        
        when(userFeedItem4.compareTo(userFeedItem3)).thenReturn(1);
        when(userFeedItem4.compareTo(userFeedItem2)).thenReturn(1);
        when(userFeedItem4.compareTo(userFeedItem1)).thenReturn(1);
        
        when(userFeedItem3.compareTo(userFeedItem2)).thenReturn(1);
        when(userFeedItem3.compareTo(userFeedItem1)).thenReturn(1);
        
        when(userFeedItem2.compareTo(userFeedItem1)).thenReturn(1);
        
        provider = new UserFeedProvider(repository, reader, userFeedItemProvider);
    }
    
    @Test
    public void testForUserWhenNoUserFeedSourcesAreFound() {
        List<UserFeedSource> noUserFeedSources = list();
        when(repository.getFeedsFor(USER_IDENTIFIER)).thenReturn(noUserFeedSources);
        
        List<UserFeedItem> noEntries = list();
        assertEquals(new UserFeed(noEntries), provider.forUser(USER_IDENTIFIER));
    }
    
    @Test
    public void testForUserWhenUserFeedSourcesAreFoundButCannotBeRead() throws ClientProtocolException, IOException, ParseException {
        List<UserFeedSource> userFeedSources = list(source1, source2, source3);
        when(repository.getFeedsFor(USER_IDENTIFIER)).thenReturn(userFeedSources);
        when(reader.read(FEED_URI_1)).thenThrow(new RuntimeException("Failed to read"));
        when(reader.read(FEED_URI_2)).thenThrow(new RuntimeException("Failed to read"));
        when(reader.read(FEED_URI_3)).thenThrow(new RuntimeException("Failed to read"));
        
        List<UserFeedItem> noEntries = list();
        assertEquals(new UserFeed(noEntries), provider.forUser(USER_IDENTIFIER));
    }
    
    @Test
    public void testForUserWhenUserFeedSourcesAreFoundAndCanBeRead() throws ClientProtocolException, IOException, ParseException {
        List<UserFeedSource> userFeedSources = list(source1, source2, source3);
        when(repository.getFeedsFor(USER_IDENTIFIER)).thenReturn(userFeedSources);
        when(reader.read(FEED_URI_1)).thenReturn(feed1);
        when(reader.read(FEED_URI_2)).thenReturn(feed2);
        when(reader.read(FEED_URI_3)).thenReturn(feed3);
        when(feed1.getDescription()).thenReturn(description1);
        when(feed2.getDescription()).thenReturn(description2);
        when(feed3.getDescription()).thenReturn(description3);
        when(feed1.getItems()).thenReturn(list(item5, item3));
        when(feed2.getItems()).thenReturn(list(item1, item6, item2));
        when(feed3.getItems()).thenReturn(list(item4));
        when(userFeedItemProvider.fromFeedItem(USER_IDENTIFIER, description1, item5)).thenReturn(userFeedItem5);
        when(userFeedItemProvider.fromFeedItem(USER_IDENTIFIER, description1, item3)).thenReturn(userFeedItem3);
        when(userFeedItemProvider.fromFeedItem(USER_IDENTIFIER, description2, item1)).thenReturn(userFeedItem1);
        when(userFeedItemProvider.fromFeedItem(USER_IDENTIFIER, description2, item6)).thenReturn(userFeedItem6);
        when(userFeedItemProvider.fromFeedItem(USER_IDENTIFIER, description2, item2)).thenReturn(userFeedItem2);
        when(userFeedItemProvider.fromFeedItem(USER_IDENTIFIER, description3, item4)).thenReturn(userFeedItem4);
        
        List<UserFeedItem> entries = list(userFeedItem1, userFeedItem2, userFeedItem3, userFeedItem4, userFeedItem5, userFeedItem6);
        UserFeed forUser = provider.forUser(USER_IDENTIFIER);
        assertEquals(new UserFeed(entries), forUser);
    }
    
    @Test
    public void testForUserWhenUserFeedSourcesAreFoundAndOnlySomeCanBeRead() throws ClientProtocolException, IOException, ParseException {
        List<UserFeedSource> userFeedSources = list(source1, source2, source3);
        when(repository.getFeedsFor(USER_IDENTIFIER)).thenReturn(userFeedSources);
        when(reader.read(FEED_URI_1)).thenReturn(feed1);
        when(reader.read(FEED_URI_2)).thenThrow(new RuntimeException("Failed to read"));
        when(reader.read(FEED_URI_3)).thenReturn(feed3);
        when(feed1.getDescription()).thenReturn(description1);
        when(feed3.getDescription()).thenReturn(description3);
        when(feed1.getItems()).thenReturn(list(item5, item3));
        when(feed3.getItems()).thenReturn(list(item4));
        when(userFeedItemProvider.fromFeedItem(USER_IDENTIFIER, description1, item5)).thenReturn(userFeedItem5);
        when(userFeedItemProvider.fromFeedItem(USER_IDENTIFIER, description1, item3)).thenReturn(userFeedItem3);
        when(userFeedItemProvider.fromFeedItem(USER_IDENTIFIER, description3, item4)).thenReturn(userFeedItem4);
        
        List<UserFeedItem> entries = list(userFeedItem3, userFeedItem4, userFeedItem5);
        UserFeed forUser = provider.forUser(USER_IDENTIFIER);
        assertEquals(new UserFeed(entries), forUser);
    }
    
}
