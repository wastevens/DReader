package com.dstevens.reader.stream;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Service;

@Service
public class HttpClientFactory {

    public HttpClient getClient() {
        return new DefaultHttpClient();
    }
    
}
