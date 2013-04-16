require(["dojo/_base/declare", "dojo/dom-class", "dojo/_base/xhr", "dojo/on", "dojo/_base/lang", "dojo/domReady!"],
function(declare, domClass, xhr, on, lang) {
	return declare("DeleteFeedListener", null, {
	
		constructor: function() {},
		
		deleteFeed: function(feedSummaryWidget) {
			xhr.post({
					url: "deleteFeed",
					timeout: 3000,
					content: {"feedUri": feedSummaryWidget.channelDescription.sourceUri },
					failOk: true,
					handle: function(data, ioArgs){
						window.location.reload();
       				},
			});
		},
		
		listenTo: function(widget, feedSummaryWidget) {
			on(widget, "click", lang.hitch(this, "deleteFeed", feedSummaryWidget));
		}
	});
});