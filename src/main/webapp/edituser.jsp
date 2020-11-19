<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="it.contrader.dto.UserDTO" %>

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

<% //request.getSession().getAttribute("user"); %>

<body>
	<%@include file="css/header.jsp"%>

	<div class="navbar">
		<a href="/homeuser.jsp">Home</a> 
		<a href="/user/readurl">ShortURLs</a>
		<a class="active" href="/edituser.jsp">Edit</a>
		<a href="/user/logout" id="logout">Logout</a>
	</div>

	<div class="main">
		<h1>Modifica i tuoi dati</h1>
		
		<div class="url">
		<br><form action="/user/edituser" method="post" class="url">
			<div class="row">
				<div class="col-76">
				<label for ="username">Username</label>
					<input required	type="text"  name="username" placeholder="${user.getUsername()}"/>
					<input type= "hidden" name ="id" value="${user.getId()}"/>
					<label for ="password">Password</label>
					<input required	type="text"  name="password" placeholder="${user.getPassword()}"/>
				</div>
				<div class="col-26">
					<button type="submit" id="shorturlbutt">Modifica</button>
				</div>
			</div>
		</form>
		
		</div>
	</div>

	<%@ include file="css/footer.jsp"%>
</body>
</html>