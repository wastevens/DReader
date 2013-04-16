<script src="js/ContentRevealListener.js" /></script>
<script src="js/MarkAsReadListener.js" /></script>
<script src="js/FeedSelectedListener.js" /></script>
<script src="js/DeleteFeedListener.js" /></script>
<script>
require(["dojo/request", "dojo/dom", "dojo/_base/array", "reader/widget/UserFeedItemWidget", "reader/widget/FeedSummaryWidget", "dojo/dom-construct", "dojo/domReady!"], 
  function(request, dom, arrayUtil, UserFeedItemWidget, FeedSummaryWidget, domConstruct){
  	var displayFeedContainer = dom.byId("displayFeed");
  	var feedSummaryContainer = dom.byId("feedSummary");
  	
  	var contentRevealListener = new ContentRevealListener();
  	var markAsReadListener = new MarkAsReadListener();
  	
  	var feedSummaryWidget = new FeedSummaryWidget();
  	
  	request.post("feeds", {handleAs: "json"}).then(
  		function(userFeed){
  			arrayUtil.forEach(userFeed.entries, function(entry){
  	  			var widget = new UserFeedItemWidget(entry).placeAt(displayFeedContainer);
  	  			
  	  			feedSummaryWidget.addItem(widget);
  	  			contentRevealListener.listenTo(widget);
  	  			markAsReadListener.listenTo(widget, feedSummaryWidget);
  	  			
			});
			domConstruct.destroy(dom.byId("loading"));
			feedSummaryWidget.placeAt(feedSummaryContainer);
	});
});
</script>