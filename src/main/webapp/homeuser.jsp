<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.UserDTO"
	import="it.contrader.dto.UrlTableDTO"%>
<!DOCTYPE html>
<html>
<head>

<%
	UrlTableDTO url = (UrlTableDTO)request.getAttribute("url"); 
	String urlShort = "";
	String urlError = "";
	if(url != null) {
		urlShort = url.getShortUrl();
		if(urlShort == null) {
			urlShort = "";
			urlError = "URL non valido!";
		}
	}
%>

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
		<form action="UrlServlet" method="post">
		  <div class="row">
		    <div class="col-26">
		      <label>Insert URL</label>
		    </div>
		    <div class="col-76">
		      <input type="text" id="input" name="url" placeholder="www.sample.com">
		    </div>
		  </div>
		<%-- <%=urlError?"<p>true</p>":"<p>false</p>"%> --%>
		  <div class="url"><a target="_blank" href=<%=urlShort%>><%=urlShort%></a></div>
		  <div class="urlerr"><%=urlError%></div>
		    <button type="submit">Convert</button>
		</form>
	</div>
</div>


<%@ include file="css/footer.jsp" %>

</body>
</html>