define([
	"dojo/_base/declare",
	"dijit/_WidgetBase",
	"dijit/_TemplatedMixin",
	"dojo/text!./templates/userFeedItem.html",
	"dojo/dom-class"
], function(declare, _WidgetBase, _TemplatedMixin, template, domClass){
        return declare([_WidgetBase, _TemplatedMixin], {
            feedItemChannelName: "no channel",
    		link: "no link",
    		title: "no title",
    		displayablePublicationDate: "no date",
    		description: "no content",
    		unread: false,
    		
    		templateString: template,
    		baseClass: "userFeedItemWidget",
    		
            constructor : function (entry) {
                this.feedItemChannelName = entry.channelDescription.title;
                this.link = entry.item.link;
                this.title = entry.item.title;
                this.displayablePublicationDate = entry.item.displayablePublicationDate;
                this.description = entry.item.description;
                this.unread = entry.unread;
        	},
            
            postCreate: function() {
    			var domNode = this.domNode;
  				
  				if(!this.unread) {
  					domClass.add(domNode, "read");
  				}
			    this.inherited(arguments);
			}
        });
});