<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<jsp:include page="dojo.jsp" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<script src="https://login.persona.org/include.js"></script>
<title>Login to DReader</title>
</head>
<body>

<div id="loginDiv">Login with Persona!</div>

<script>
function verifyAssertion(assertion) {
	require(["dojo/request", "dojo/domReady!"], 
		function(request) {
			request.post("persona/auth", {data: {"assertion": assertion}}).
	        	then(function(data) {
	        		window.location.href = window.location.origin + "/DReader/" + data;  
	        	});
	    	});
};

navigator.id.watch({
  onlogin: function(assertion) {
    verifyAssertion(assertion);
  },
  onlogout: function() {
    // A user has logged out! Here you need to:
    // Tear down the user's session by redirecting the user or making a call to your backend.
    // Also, make sure loggedInUser will get set to null on the next page load.
    // (That's a literal JavaScript null. Not false, 0, or undefined. null.)
    //$.ajax({
    //  type: 'POST',
    //  url: '/auth/logout', // This is a URL on your website.
    //  success: function(res, status, xhr) { window.location.reload(); },
    //  error: function(xhr, status, err) { alert("Logout failure: " + err); }
    //});
  }
});
</script>

<script>
	var signinLink = document.getElementById('loginDiv');
	if (signinLink) {
	  signinLink.onclick = function() { navigator.id.request(); };
	}
</script>

</body>
</html>