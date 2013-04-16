package com.dstevens.reader.deserialization.xstream.atom;

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

public class SerializedAtomChannelTest {

    @Mock private DateFormatter dateFormatter;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testXStreamAnnotations() throws ParseException {
        XStream stream = new XStream();
        stream.processAnnotations(SerializedAtomChannel.class);
        
        String feedTitle = "some feed title";
        String feedLinkHref = "some feed link";
        String feedLink = "<link href=\""+feedLinkHref+"\"></link>";
        String itemTitle = "some item title";
        String itemTitleValue = "<title>"+itemTitle+"</title>";
        String itemLinkHref = "some item link";
        String itemLink = "<link href=\""+itemLinkHref+"\"></link>";
        String itemDescription = "some item description";
        String itemDescriptionValue = "<summary>" + itemDescription + "</summary>";
        String publicationDate = "publication date";
        String publicationUpdate = "<updated>"+publicationDate+"</updated>";
        Date publicationTimestamp = new Date(1000);
        when(dateFormatter.formatFrom(publicationDate)).thenReturn(publicationTimestamp);
        
        String xmlForSerializedAtomItem = 
            "<entry>" + 
            itemTitleValue + 
            itemLink + 
            itemDescriptionValue +
            publicationUpdate +
            "</entry>";
        
        String xmlForSerializedAtomFeed = 
            "<feed>" +
            "<title>" + feedTitle + "</title>" +
            feedLink +
            xmlForSerializedAtomItem +
            "</feed>";
        
        SerializedAtomChannel fromXML = (SerializedAtomChannel) stream.fromXML(xmlForSerializedAtomFeed);
        
        String sourceUri = "source uri";
        assertEquals(new Feed(new FeedDescription(feedTitle, feedLinkHref, sourceUri), 
                list(new FeedItem(itemTitle, itemLinkHref, publicationTimestamp, itemDescription))), fromXML.asFeed(dateFormatter, sourceUri));
    }
}
