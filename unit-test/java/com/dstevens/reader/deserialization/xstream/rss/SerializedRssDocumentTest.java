package com.dstevens.reader.deserialization.xstream.rss;

import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dstevens.reader.deserialization.DateFormatter;
import com.dstevens.reader.feeds.Feed;
import com.dstevens.reader.feeds.FeedDescription;
import com.dstevens.reader.feeds.item.FeedItem;
import com.thoughtworks.xstream.XStream;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import static com.dstevens.util.CollectionUtils.list;

public class SerializedRssDocumentTest {
    
    @Mock private DateFormatter dateFormatter;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testXStreamAnnotations() throws ParseException {
        XStream stream = new XStream();
        stream.processAnnotations(SerializedRssDocument.class);
        
        String channelTitle = "some channel title";
        String channelLink = "some channel link";
        String itemTitle = "some item title";
        String itemLink = "some item link";
        String itemDescription = "some item description";
        String publicationDate = "publication date";
        Date publicationTimestamp = new Date(1000);
        when(dateFormatter.formatFrom(publicationDate)).thenReturn(publicationTimestamp);
        
        String xmlForSerializedRssItem = "<item><title>" + itemTitle + "</title><link>" + itemLink + "</link><pubDate>" + publicationDate + "</pubDate><description>" + itemDescription + "</description></item>";
        String xmlForSerializedRssChannel = "<channel><title>" + channelTitle +"</title><link>" + channelLink +"</link>" + xmlForSerializedRssItem + "</channel>";
        
        SerializedRssDocument fromXML = (SerializedRssDocument) stream.fromXML("<rss>" + xmlForSerializedRssChannel + "</rss>");
        
        String sourceUri = "source uri";
        
        assertEquals(new Feed(new FeedDescription(channelTitle, channelLink, sourceUri), list(new FeedItem(itemTitle, itemLink, publicationTimestamp, itemDescription))), 
                     fromXML.asFeed(dateFormatter, sourceUri));
    }
    
}
