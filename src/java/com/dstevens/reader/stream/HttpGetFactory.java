package com.dstevens.reader.stream;

import org.apache.http.client.methods.HttpGet;
import org.springframework.stereotype.Service;

@Service
public class HttpGetFactory {

    public HttpGet forUri(String uri) {
        return new HttpGet(uri);
    }
    
}
