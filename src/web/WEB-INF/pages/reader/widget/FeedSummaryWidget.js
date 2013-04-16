define([
	"dojo/_base/declare",
	"dijit/_WidgetBase",
	"dijit/_TemplatedMixin",
	"dojo/text!./templates/feedSummary.html",
	"dojo/dom-class",
	"reader/widget/FeedSummaryEntryWidget",
	"reader/widget/DeleteFeedWidget"	
], function(declare, _WidgetBase, _TemplatedMixin, template, domClass, FeedSummaryEntryWidget, DeleteFeedWidget){
        return declare([_WidgetBase, _TemplatedMixin], {
			feedSummaryAll: null,
			feedSummaryEntries: {},
			totalTitle: 'All Entries',
    		feedSelectedListener: null,
    		deleteFeedListener: null,
    		
    		templateString: template,
    		baseClass: "feedSummaryWidget",
    		
            constructor : function () {
            	this.feedSelectedListener = new FeedSelectedListener();
            	this.deleteFeedListener = new DeleteFeedListener();
        	},
            
            postCreate: function() {
            	this.feedSummaryAll = new FeedSummaryEntryWidget({feedItemChannelName: this.totalTitle}).placeAt(this.feedNodes);
				this.feedSelectedListener.listenTo(this.feedSummaryAll);
				
			    this.inherited(arguments);
			},
			
			addItem: function(widget) {
				var title = widget.channelDescription.title;
				if (!(title in this.feedSummaryEntries)) {
					this.feedSummaryEntries[title] = new FeedSummaryEntryWidget(title, widget.unread).placeAt(this.feedNodes);
					var deleteFeedWidget = new DeleteFeedWidget().placeAt(this.feedSummaryEntries[title].deleteFeedNode);
					this.feedSelectedListener.listenTo(this.feedSummaryEntries[title]);
					this.deleteFeedListener.listenTo(deleteFeedWidget, widget);
				} 
				this.feedSummaryEntries[title].incrementWith(widget.unread);
				this.feedSummaryAll.incrementWith(widget.unread);
				this.feedSelectedListener.registerWidget(widget);
			},
			
			decrementUnreadFor: function(feedTitle) {
				this.feedSummaryAll.decrementUnreadItems();
				this.feedSummaryEntries[feedTitle].decrementUnreadItems();
			}
        });
});