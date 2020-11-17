<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="it.contrader.dto.ShortUrlDTO" %>

<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Homepage for Admin ">
<meta name="author" content="Vittorio Valent">

<title>Home</title>

<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/css/vittoriostyle.css" rel="stylesheet">
</head>

<% //ShortUrlDTO url = (ShortUrlDTO) request.getSession().getAttribute("shortUrl"); %>
<body>
	<%@include file="css/header.jsp"%>


	<div class="navbar">
		<a class="active" href="/homeuser.jsp">Home</a> 
		<a href="/user/logout" id="logout">Logout</a>
	</div>

	<div class="main">
		<h1>Welcome ${user.getUsername()}</h1>
		
		<div class="url">
		<br><form action="/user/create" method="post" class="url">
			<div class="row">
				<div class="col-76">
					<input 	required="required" pattern="(http(s)?:\/\/.)?(www\.)?[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*)|[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b([-a-zA-Z0-9()@:%_\+.~#?&//=]*)" 
							title="www.sample.com"
							type="text" id="input" name="url" placeholder="Insert URL"/>
				</div>
				<div class="col-26">
					<button class="url" type="submit">Generate</button>
				</div>
			</div>
		</form>
		<form action="" method="post" class="url"> 
			<div class="row">
				<div class="col-76">
					<input type="text" name="shortUrl" value="">
				</div>
				<div class="col-26">
					<button class="url" type="submit" name="pulsante" value="urlLong">Go</button>
				</div>
			</div>
		</form><br>
	</div>


	</div>


	<%@ include file="css/footer.jsp"%>

</body>
</html>