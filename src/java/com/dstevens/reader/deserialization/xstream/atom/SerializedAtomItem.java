package com.dstevens.reader.deserialization.xstream.atom;

import java.util.List;

import com.dstevens.reader.deserialization.DateFormatter;
import com.dstevens.reader.feeds.item.FeedItem;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("entry")
public class SerializedAtomItem {

    private String title;
    @XStreamImplicit
    private List<SerializedAtomLink> link;
    private String updated;
    private String summary;
    private String content;

    public FeedItem asItem(DateFormatter dateFormatter) {
        String contentToReturn = pickDescription();
        if (link.size() == 0)
            return new FeedItem(title, link.get(0).href, dateFormatter.formatFrom(updated), contentToReturn);
        return new FeedItem(title, findAlternateLink(link), dateFormatter.formatFrom(updated), contentToReturn);
    }

    private String pickDescription() {
        if (summary == null && content == null)
            return "";
        if (summary == null)
            return content;
        if (content == null)
            return summary;
        
        return summary.length() > content.length() ? summary : content;
    }

    private String findAlternateLink(List<SerializedAtomLink> links) {
        for (SerializedAtomLink serializedAtomLink : links) {
            if(serializedAtomLink.rel != null && serializedAtomLink.rel.equals("alternate")) {
                return serializedAtomLink.href;
            }
        }
        return links.get(0).href;
    }

}
