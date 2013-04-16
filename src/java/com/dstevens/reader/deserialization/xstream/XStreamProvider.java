package com.dstevens.reader.deserialization.xstream;

import org.springframework.stereotype.Service;

import com.dstevens.reader.deserialization.xstream.atom.SerializedAtomChannel;
import com.dstevens.reader.deserialization.xstream.rss.SerializedRssDocument;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.MapperWrapper;

@Service
public class XStreamProvider {

    private static XStream xstream = null;
    
    public XStream getXStream() {
        if (xstream == null) {
            xstream = new XStream() {
                @Override
                protected MapperWrapper wrapMapper(MapperWrapper next) {
                  return new MapperWrapper(next) {
                    @SuppressWarnings("rawtypes")
                    @Override
                    public boolean shouldSerializeMember(Class definedIn, String fieldName) {
                      if (definedIn == Object.class) {
                        return false;
                      }
                      return super.shouldSerializeMember(definedIn, fieldName);
                    }
                  };
                }
              };
        }
        xstream.processAnnotations(SerializedRssDocument.class);
        xstream.processAnnotations(SerializedAtomChannel.class);
        
        return xstream;
    }
    
}
