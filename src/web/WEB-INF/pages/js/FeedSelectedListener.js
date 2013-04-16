require(["dojo/_base/declare", "dojo/query", "dojo/dom-class", "dojo/on", "dojo/_base/lang", "dojo/domReady!"],
function(declare, query, domClass, on, lang) {
	return declare("FeedSelectedListener", null, {
		registeredUserFeedItemWidgets: [],
		lastShownFeedWidget: null,
		
		constructor: function() {},
		registerWidget: function(widget) {
  			this.registeredUserFeedItemWidgets.push(widget);
  		},
  	
		showSelectedFeed: function(widget) {
			var selectedTitle = widget.feedItemChannelName;

			if (this.lastShownFeedWidget != null) {
				domClass.add(this.lastShownFeedWidget.deleteFeedNode, "hidden");
			}
			this.lastShownFeedWidget = widget;
		
			if (selectedTitle.trim() == 'All Entries') {
				this.showAllFeeds();
				return;
			}
			
			domClass.remove(this.lastShownFeedWidget.deleteFeedNode, "hidden");
	    	var feedItems = this.registeredUserFeedItemWidgets;
    		feedItems.forEach(function(feedItem) {
				if (feedItem.feedItemChannelNode.innerHTML.trim() == selectedTitle.trim().replace("&", "&amp;")) {
					domClass.remove(feedItem.feedItemNode, "hidden");
				} else {
					domClass.add(feedItem.feedItemNode, "hidden");
				}
    		});
    	},
    	
    	showAllFeeds: function() {
    		var feedItems = this.registeredUserFeedItemWidgets;
    		feedItems.forEach(function(feedItem) {
    			domClass.remove(feedItem.feedItemNode, "hidden");
    		});
    	},
    	
  		listenTo: function(widget) {
  			on(widget, "click", lang.hitch(this, "showSelectedFeed", widget));
  		},
	});
});