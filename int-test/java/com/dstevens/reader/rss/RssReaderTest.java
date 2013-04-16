package com.dstevens.reader.rss;

import java.io.IOException;
import java.text.ParseException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.junit.Test;

import com.dstevens.reader.deserialization.DateFormatter;
import com.dstevens.reader.deserialization.xstream.XStreamProvider;
import com.dstevens.reader.stream.HttpClientFactory;
import com.dstevens.reader.stream.HttpGetFactory;
import com.dstevens.reader.stream.StreamReader;
import com.dstevens.util.InputStreamReader;

public class RssReaderTest {

    private RssReader rssReader;
    private XStreamProvider xStreamProvider;
    private RssReaderCache cache;
    
    @Before
    public void setUp() {
        HttpClientFactory clientFactory = new HttpClientFactory();
        HttpGetFactory getFactory = new HttpGetFactory();
        InputStreamReader inputStreamReader = new InputStreamReader();
        StreamReader streamReader = new StreamReader(clientFactory, getFactory, inputStreamReader);
        xStreamProvider = new XStreamProvider();
        DateFormatter dateFormatter = new DateFormatter();
        cache = new RssReaderCache();
        
        rssReader = new RssReader(streamReader, xStreamProvider, dateFormatter, cache);
    }
    
    @Test
    public void testForOOTS() throws ClientProtocolException, IOException, ParseException {
        rssReader.read("http://www.giantitp.com/comics/oots.rss");
    }
    
    @Test
    public void testForSP() throws ClientProtocolException, IOException, ParseException {
        rssReader.read("http://www.somethingpositive.net/sp.xml");
    }
    
}
