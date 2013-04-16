package com.dstevens.reader.feeds;

public class FeedDescription implements Comparable<FeedDescription> {

    private final String title;
    private final String link;
    private final String sourceUri;
    
    public FeedDescription(String title, String link, String sourceUri) {
        this.title = title;
        this.link = link;
        this.sourceUri = sourceUri;
    }

    public String getTitle() {
        return title;
    }
    
    public String getLink() {
        return link;
    }
    
    public String getSourceUri() {
        return sourceUri;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof FeedDescription) {
            FeedDescription that = (FeedDescription) o;
            return this.title.equals(that.title) &&
                   this.link.equals(that.link) &&
                   this.sourceUri.equals(that.sourceUri);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 17 + title.hashCode();
    }
    
    @Override
    public int compareTo(FeedDescription o) {
        return title.compareTo(o.title);
    }
    
    @Override
    public String toString() {
        return "FeedChannelDescription[title: '" + title +"', link: '" + link +"']";
    }
    
}
