require(["dojo/_base/declare", "dojo/dom-class", "dojo/on", "dojo/_base/lang", "dojo/domReady!"],
function(declare, domClass, on, lang) {
	return declare("ContentRevealListener", null, {
		previousNodeRevealed: null,
		
		constructor: function() {},
		revealContent: function(userFeedItem) {
			var nodeToReveal = userFeedItem.feedItemContentNode;
			domClass.remove(nodeToReveal, "hidden");
			
		    if(this.previousNodeRevealed) {
				var nodeToHide = this.previousNodeRevealed.feedItemContentNode;
				domClass.add(nodeToHide, "hidden");
		    }
		    if (this.previousNodeRevealed != userFeedItem) {
				this.previousNodeRevealed = userFeedItem;
			} else {
				this.previousNodeRevealed = null;
			}
		},
		listenTo: function(userFeedItem) {
			on(userFeedItem, "click", lang.hitch(this, "revealContent", userFeedItem));
		}
	});
});