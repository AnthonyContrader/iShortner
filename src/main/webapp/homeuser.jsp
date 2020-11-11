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
	//String urlLong = "";
	String urlError = "";
	if(url != null) {
		urlShort = url.getShortUrl();
		//urlLong = url.getLongUrl();
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
		     <input pattern="(http(s)?:\/\/.)?(www\.)?[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*)|[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b([-a-zA-Z0-9()@:%_\+.~#?&//=]*)" type="text" id="input" name="url" placeholder="www.sample.com">
		    </div>
		  </div>
		<%-- <%=urlError?"<p>true</p>":"<p>false</p>"%> --%>
		    <button type="submit">Convert</button>
		</form>
		<br><br>
		<form action="RedirectServlet" method="post">
			<div class="row">
				<div class="col-26">
					<button type="submit" name="pulsante" value="urlLong">Go</button>
				</div>
				<div class="col-76">
					<input type="text" name="keywords" value="<%=urlShort%>"/>
				</div>
			</div>
        </form>
	</div>
</div>


<%@ include file="css/footer.jsp" %>

</body>
</html>