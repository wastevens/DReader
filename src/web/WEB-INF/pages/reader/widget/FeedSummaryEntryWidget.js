define([
	"dojo/_base/declare",
	"dijit/_WidgetBase",
	"dijit/_TemplatedMixin",
	"dojo/text!./templates/feedSummaryEntry.html",
	"dojo/dom-class"
], function(declare, _WidgetBase, _TemplatedMixin, template, domClass){
        return declare([_WidgetBase, _TemplatedMixin], {
            feedItemChannelName: "no channel",
			feedUnreadItemsTotal: 0,
			
    		templateString: template,
    		baseClass: "feedSummaryEntryWidget",
    		
            constructor : function (channelName) {
                this.feedItemChannelName = channelName;
        	},
            
            postCreate: function() {
			    this.inherited(arguments);
			    this.setFeedUnreadItemsTotal(this.feedUnreadItemsTotal);
			},
			
			incrementWith: function(unread) {
                if(unread) {
					this.setFeedUnreadItemsTotal(this.feedUnreadItemsTotal + 1);
                }
			},
			
			setFeedUnreadItemsTotal: function(total) {
				this.feedUnreadItemsTotal = total;
				this.feedUnreadNumberNode.innerHTML = total;
				if (total > 0) {
					domClass.remove(this.feedUnreadCountNode, "hidden");
					domClass.remove(this.feedNode, "read");
				} else {
					domClass.add(this.feedUnreadCountNode, "hidden");
					domClass.add(this.feedNode, "read");
				}
			},
			
			decrementUnreadItems: function() {
				this.setFeedUnreadItemsTotal(this.feedUnreadItemsTotal - 1);
			}
        });
});