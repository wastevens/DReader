package com.dstevens.reader.deserialization.xstream.atom;

import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dstevens.reader.deserialization.DateFormatter;
import com.dstevens.reader.feeds.item.FeedItem;
import com.thoughtworks.xstream.XStream;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

public class SerializedAtomItemTest {

    @Mock private DateFormatter dateFormatter;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testXStreamAnnotations() throws ParseException {
        XStream stream = new XStream();
        stream.processAnnotations(SerializedAtomItem.class);
        
        String itemTitle = "some item title";
        String itemLinkHref = "some item link";
        String publicationDate = "publication date";
        String itemSummary = "some item summary";
        Date publicationTimestamp = new Date(1000);
        when(dateFormatter.formatFrom(publicationDate)).thenReturn(publicationTimestamp);
        
        String xmlForSerializedRssItem = 
        "<entry>" + 
            "<title>"+itemTitle+"</title>" + 
            "<link href=\""+itemLinkHref+"\"></link>" + 
            "<summary>"+itemSummary+"</summary>" + 
            "<updated>"+publicationDate+"</updated>" +
        "</entry>";
        
        SerializedAtomItem fromXML = (SerializedAtomItem) stream.fromXML(xmlForSerializedRssItem);
        
        assertEquals(new FeedItem(itemTitle, itemLinkHref, publicationTimestamp, itemSummary), fromXML.asItem(dateFormatter));
    }
}
