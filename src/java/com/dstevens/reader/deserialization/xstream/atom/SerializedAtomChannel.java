package com.dstevens.reader.deserialization.xstream.atom;

import java.text.ParseException;
import java.util.List;

import com.dstevens.reader.deserialization.DateFormatter;
import com.dstevens.reader.feeds.Feed;
import com.dstevens.reader.feeds.FeedDescription;
import com.dstevens.reader.feeds.FeedProvider;
import com.dstevens.reader.feeds.item.FeedItem;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import static com.dstevens.util.CollectionUtils.list;

@XStreamAlias("feed")
public class SerializedAtomChannel implements FeedProvider {

    private String title;
    
    @XStreamImplicit
    private List<SerializedAtomLink> link;
    
    @XStreamImplicit(itemFieldName="entry")
    private List<SerializedAtomItem> atomItems;
    
    @Override
    public String toString() {
        return "SerializedAtomChannel[title:" + title + ", link: " + link + ", atomItems: " + atomItems + "]";
    }

    public Feed asFeed(DateFormatter dateFormatter, String sourceUri) throws ParseException {
        List<FeedItem> items = list();
        for (SerializedAtomItem  item : atomItems) {
            items.add(item.asItem(dateFormatter));
        }
        return new Feed(new FeedDescription(title, link.get(0).href, sourceUri), items);
    }

}
