package com.dstevens.reader.stream;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.util.InputStreamReader;

@Service
public class StreamReader {

    private final HttpClientFactory clientFactory;
    private final HttpGetFactory getFactory;
    private final InputStreamReader streamReader;

    @Autowired
    public StreamReader(HttpClientFactory clientFactory, HttpGetFactory getFactory, InputStreamReader streamReader) {
        this.clientFactory = clientFactory;
        this.getFactory = getFactory;
        this.streamReader = streamReader;
    }
    
    public String readStreamFrom(String uri) throws ClientProtocolException, IOException {
        HttpClient client = clientFactory.getClient();
        HttpEntity responseEntity = client.execute(getFactory.forUri(uri)).getEntity();
        if (responseEntity != null) {
            return streamReader.from(responseEntity.getContent());
        }
        throw new IllegalArgumentException("No response entity found for " + uri);
    }
    
}
