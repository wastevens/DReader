require(["dojo/_base/declare", "dojo/dom-class", "dojo/_base/xhr", "dojo/on", "dojo/_base/lang", "dojo/domReady!"],
function(declare, domClass, xhr, on, lang) {
	return declare("MarkAsReadListener", null, {
	
		constructor: function() {},
		
		markAsRead: function(widget, feedSummaryWidget) {
			var contentId = widget.id;
			var nodeToMarkAsRead = widget.feedItemNode;
			if (!domClass.contains(nodeToMarkAsRead, "read")) {
				domClass.add(nodeToMarkAsRead, "read");
				xhr.post({
					url: "markAsRead",
					timeout: 3000,
					failOk: true,
					content: {"feedItemId": nodeToMarkAsRead.id },
				});
				feedSummaryWidget.decrementUnreadFor(widget.channelDescription.title);
			}
		},
		listenTo: function(widget, feedSummaryWidget) {
			on(widget, "click", lang.hitch(this, "markAsRead", widget, feedSummaryWidget));
		}
	});
});