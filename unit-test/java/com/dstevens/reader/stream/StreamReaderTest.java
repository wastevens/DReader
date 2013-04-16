package com.dstevens.reader.stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dstevens.util.InputStreamReader;

public class StreamReaderTest {

    @Mock private HttpClientFactory clientFactory;
    @Mock private HttpGetFactory getFactory;
    @Mock private InputStreamReader streamReader;
    
    @Mock private HttpClient client;
    @Mock private HttpGet getRequest;
    @Mock private HttpResponse getResponse;
    @Mock private HttpEntity responseEntity;
    
    private StreamReader reader;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        reader = new StreamReader(clientFactory, getFactory, streamReader);
    }
    
    @Test
    public void testReadStreamFromUri() throws ClientProtocolException, IOException {
        String uri = "some uri";
        when(clientFactory.getClient()).thenReturn(client);
        when(getFactory.forUri(uri)).thenReturn(getRequest);
        when(client.execute(getRequest)).thenReturn(getResponse);
        when(getResponse.getEntity()).thenReturn(responseEntity);
        String responseFromUri = "response from uri";
        ByteArrayInputStream stream = new ByteArrayInputStream(responseFromUri.getBytes());
        when(responseEntity.getContent()).thenReturn(stream);
        when(streamReader.from(stream)).thenReturn(responseFromUri);
        
        assertEquals(responseFromUri, reader.readStreamFrom(uri));
    }
    
    @Test
    public void testReadStreamWhenThereIsNoResponseEntity() throws ClientProtocolException, IOException {
        String uri = "some uri";
        when(clientFactory.getClient()).thenReturn(client);
        when(getFactory.forUri(uri)).thenReturn(getRequest);
        when(client.execute(getRequest)).thenReturn(getResponse);
        HttpEntity noResponseEntity = null;
        when(getResponse.getEntity()).thenReturn(noResponseEntity);

        try {
            reader.readStreamFrom(uri);
            fail("Expected exception");
        } catch(IllegalArgumentException e) {
            assertEquals("No response entity found for " + uri, e.getMessage());
        } catch(Exception e) {
            fail("Expected IllegalArguementException");
        }
    }
    
}
