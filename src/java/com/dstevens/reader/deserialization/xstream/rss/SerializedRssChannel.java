package com.dstevens.reader.deserialization.xstream.rss;

import java.text.ParseException;
import java.util.List;

import com.dstevens.reader.deserialization.DateFormatter;
import com.dstevens.reader.feeds.Feed;
import com.dstevens.reader.feeds.FeedProvider;
import com.dstevens.reader.feeds.FeedDescription;
import com.dstevens.reader.feeds.item.FeedItem;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import static com.dstevens.util.CollectionUtils.list;

@XStreamAlias("channel")
public class SerializedRssChannel implements FeedProvider {

    private String title;
    private String link;
    
    @XStreamImplicit(itemFieldName="item")
    private List<SerializedRssItem> rssItems;
    
    @Override
    public String toString() {
        return "SerializedRssChannel[title:" + title + ", link: " + link + ", rssItems: " + rssItems + "]";
    }

    public Feed asFeed(DateFormatter dateFormatter, String sourceUri) throws ParseException {
        List<FeedItem> items = list();
        for (SerializedRssItem rssItem : rssItems) {
            if (rssItem.getPubDate() != null) {
                items.add(rssItem.asItem(dateFormatter));
            }
        }
        if (items.isEmpty() && !rssItems.isEmpty()) {
            items.add(rssItems.get(0).asItem(dateFormatter));
        }
        return new Feed(new FeedDescription(title, link, sourceUri), items);
    }
    
}
