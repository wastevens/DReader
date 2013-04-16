package com.dstevens.reader.feeds.item;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;

public class FeedItem implements Comparable<FeedItem>{

    private final String title;
    private final String link;
    private final Date publicationDate;
    private final String description;
    
    public FeedItem(String title, String link, Date publicationDate, String description) {
        this.title = title;
        this.link = link;
        this.publicationDate = publicationDate;
        this.description = description;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getLink() {
        return link;
    }
    
    public Date getPublicationDate() {
        return publicationDate;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getDisplayablePublicationDate() {
        if (publicationDate == null) {
            return "";
        }
        
        if (new DateTime(publicationDate).getDayOfMonth() != DateTime.now().getDayOfMonth()) {
            return new SimpleDateFormat("MMM d, yyyy").format(publicationDate);
        }
        return new SimpleDateFormat("h:mm a").format(publicationDate);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FeedItem) {
            FeedItem that = (FeedItem) obj;
            return this.title.equals(that.title) &&
                   this.link.equals(that.link) &&
                   ((this.publicationDate != null && that.publicationDate != null && this.publicationDate.equals(that.publicationDate)) ||
                    (this.publicationDate == null && that.publicationDate == null));
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return 17 + title.hashCode();
    }
    
    @Override
    public String toString() {
        return "FeedItem[title: '" + title +
                     "', link: '" + link +
                     "', publicationDate: '" + publicationDate + 
                     "']";
    }

    @Override
    public int compareTo(FeedItem o) {
        if (publicationDate == null && o.publicationDate == null) {
            return link.compareTo(o.link);
        }
        if (publicationDate == null) {
            return -1;
        }
        if (o.publicationDate == null) {
            return 1;
        }
        if (o.publicationDate.compareTo(publicationDate) != 0) {
            return o.publicationDate.compareTo(publicationDate);
        }
        return o.hashCode() - hashCode();
    }
    
}
