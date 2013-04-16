package com.dstevens.reader.deserialization.xstream.atom;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("link")
public class SerializedAtomLink {
    
    @XStreamAsAttribute
    public String href;
    
    @XStreamAsAttribute
    public String rel;

}
