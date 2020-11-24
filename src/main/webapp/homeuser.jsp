<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="it.contrader.dto.UrlDTO" import="it.contrader.dto.UserDTO" %>

<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Homepage for User">
<meta name="author" content="Vittorio Valent">

<title>Home</title>

<!-- Custom styles for this template -->
<link href="/css/vittoriostyle.css" rel="stylesheet">
</head>

<%
	UrlDTO url = (UrlDTO) request.getSession().getAttribute("shortUrl");
%>

<body>
	<%@include file="css/header.jsp"%>

	<div class="navbar">
		<a class="active" href="/homeuser.jsp">Home</a> 
		<a href="/user/readurl">ShortURLs</a>
		<a href="/edituser.jsp">Edit</a>
		<a href="/user/logout" id="logout">Logout</a>
	</div>

	<div class="main">
		<h1>Welcome ${user.getUsername()}</h1>
		
		<div class="url">
		<br><form action="/user/create" method="post" class="url">
			<div class="row">
				<div class="col-76">
					<input 	required pattern="(http(s)?:\/\/.)?(www\.)?[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*)|[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b([-a-zA-Z0-9()@:%_\+.~#?&//=]*)" 
							title="www.sample.com"
							type="text" id="shorturl" name="url" placeholder="Insert URL"/>
				</div>
				<div class="col-26">
					<button type="submit" id="shorturlbutt">Generate</button>
				</div>
			</div>
		</form>
		<form action="/user/redirect" method="post" class="url"> 
			<div class="row">
				<div class="col-76">
				<% if(url.getShorturl().equals("err")) {%>
					<div class="urlErr">URL non valido o già utilizzato</div>
				<% }  else {%>
					<input type="text" id="shorturl" name="shortUrl" value="<%=url.getShorturl()%>" placeholder="iShort.ly/sample">
				<% }%>
				</div>
				<div class="col-26">
					<button type="submit" id="shorturlbutt" name="pulsante" value="urlLong">Go</button>
				</div>
			</div>
		</form><br>
		</div>
	</div>

	<%@ include file="css/footer.jsp"%>
</body>
</html>