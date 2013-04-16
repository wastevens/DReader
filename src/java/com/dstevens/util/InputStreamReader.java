package com.dstevens.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

@Service
public class InputStreamReader {

    public String from(InputStream inputStream) throws IOException {
        try {
            StringWriter writer = new StringWriter();
            IOUtils.copy(inputStream, writer, "UTF-8");
            return writer.toString();
        } finally {
            inputStream.close();
        }
    }
    
}
