<script src="https://login.persona.org/include.js"></script>

<script>
function logout() {
	require(["dojo/request", "dojo/domReady!"], 
		function(request) {
			request.post("persona/logout").
	        	then(function(data) {
	        		window.location.href = window.location.origin + "/DReader/" + data;  
	        	});
	    	});
};

navigator.id.watch({
  onlogin: function(assertion) {
  },
  onlogout: function() {
    logout();
  }
});
</script>

<div id="signOutDiv" onclick="navigator.id.logout();">Sign out</div>

<script>
function addFeed(formId) {
	require(["dojo/_base/xhr", "dojo/domReady!"],
	function(xhr) {
		xhr.post({
			url: "addFeed",
			timeout: 3000,
			form: formId,
			failOk: true,
			handle: function(data, ioArgs){
				window.location.reload();
			},
		});
	});
};
</script>

<form id="addFeedForm">
  <label for="feedUri">Feed Uri: </label><input type="text" name="feedUri">
  <br><input type="submit" value="Feed me!" onclick="addFeed('addFeedForm'); return false;">
</form>