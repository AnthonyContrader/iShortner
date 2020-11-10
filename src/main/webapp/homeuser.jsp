<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home User</title>
<link href="css/vittoriostyle.css" rel="stylesheet">
</head>
<body>
<%@include file="css/header.jsp"%>


<div class="navbar">
  <a class="active" href="homeuser.jsp">Home</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>

<div class="main">
	<h1>Welcome ${user.getUsername()}</h1>
	
	<div align="center">
		<form action="Test" method="post">
		  <div class="row">
		    <div class="col-26">
		      <label>Insert URL</label>
		    </div>
		    <div class="col-76">
		      <input type="text" id="input" name="url" placeholder="Insert url">
		    </div>
		  </div>
		      <button class="2" type="submit" >Convert</button>
		</form>
	</div>
</div>


<%@ include file="css/footer.jsp" %>

</body>
</html>