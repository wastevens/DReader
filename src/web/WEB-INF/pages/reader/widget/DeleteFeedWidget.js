define([
	"dojo/_base/declare",
	"dijit/_WidgetBase",
	"dijit/_TemplatedMixin",
	"dojo/text!./templates/deleteFeed.html",
	"dojo/dom-class"
], function(declare, _WidgetBase, _TemplatedMixin, template, domClass){
        return declare([_WidgetBase, _TemplatedMixin], {
    		templateString: template,
    		baseClass: "deleteFeedWidget",
    		
            constructor : function () {
        	},
            
            postCreate: function() {
			}
        });
});