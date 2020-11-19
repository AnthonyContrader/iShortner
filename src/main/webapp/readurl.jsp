<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO" import="it.contrader.dto.ShortUrlDTO" import="it.contrader.dto.ServerDTO" import="java.util.List"%>
	
	
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="User Read URl">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Read User</title>
</head>
<body>
	<%@ include file="./css/header.jsp"%>
	<div class="navbar">
		<a href="/homeuser.jsp">Home</a>
		<a class="active" href="/user/readurl">ShortURLs</a>
		<a href="/edituser.jsp">Edit</a>
		<a href="/user/logout" id="logout">Logout</a>
	</div>
	<br>

	<div class="main">
		<%
			List<ShortUrlDTO> listUrl = (List<ShortUrlDTO>) request.getSession().getAttribute("urlDto");
		%>

	<h2>I tuoi ShortURLs</h2>

		<table class="info">
			<tr>
				<th>#</th>
				<th>Url originale</th>
				<th>url generato</th>
			</tr>
			<% for (int i=0; i<listUrl.size(); i++) { %>
			<tr>
				<td><%=i + 1%></td>
				<td><%=listUrl.get(i).getLongurl()%></td>
				<td><%=listUrl.get(i).getShorturl()%></td>
			</tr>
			<% } %>
		</table>
		
		

		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br> <br> <br>


	</div>

	<%@ include file="./css/footer.jsp"%>
</body>
</html>