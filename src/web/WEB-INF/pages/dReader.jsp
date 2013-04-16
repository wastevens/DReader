<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>DReader</title>
<link rel="stylesheet" type="text/css" href="<c:url value='css/style.css'/>"/>
<jsp:include page="dojo.jsp" />
<jsp:include page="feedEntries.jsp" />
</head>
<body>

<div id="addFeedBlock" style="float: left; width: 20%; height: 100%">
  <div style="height: 10%">
    <jsp:include page="addFeedSection.jsp" />
  </div>
  <div id="feedSummary" style="border-top-style:solid; border-top-width:thin; border-top-color:DarkGray; height: 90%; overflow-x: hidden; overflow-y: auto;">
  Feed summary
  </div>
</div>

<div id="displayFeed" style="float: left; width: 80%; height: 100%; overflow-x: hidden; overflow-y: auto;">
	<div id="loading">
	Loading... please wait.
	</div>
</div>

</body>
</html>