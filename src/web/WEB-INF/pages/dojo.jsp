<script>
	// We're specifying our Dojo Configuration this way,
	// as it's a bit more complex and easier to type out
	// than a data-dojo-config string
	 var dojoConfig = (function(){
		var base = location.href.split("/");
		base.pop();
		base = base.join("/");
		return {
			async: true,
			isDebug: true,
			packages: [{
				name: "reader",
				location: base + "/reader"
			}]
		};
	})();
</script>
<script src="//ajax.googleapis.com/ajax/libs/dojo/1.8.3/dojo/dojo.js"></script>