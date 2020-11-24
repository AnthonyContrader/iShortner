<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO" import="it.contrader.dto.ShortUrlDTO" import="it.contrader.dto.ServerDTO" import="java.util.List"%>
	
	
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="User Read">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Read User</title>
</head>
<body>
	<%@ include file="./css/header.jsp"%>
	<div class="navbar">
		<a href="/homeadmin.jsp">Home</a>
		<a class="active" href="/user/getall">Users</a>
		<a href="/user/logout" id="logout">Logout</a>
	</div>
	<br>

	<div class="main">
		<%
			UserDTO u = (UserDTO) request.getSession().getAttribute("dto");
			List<UrlDTO> listUrl = (List<UrlDTO>) request.getSession().getAttribute("urlDto");
			List<ServerDTO> listServer = (List<ServerDTO>) request.getSession().getAttribute("server");
		%>

	<h2>Dati dell'utente "<%=u.getUsername()%>"</h2>

	<% if(listUrl.size() == 0) {%>
		<h3>Nessun dato!</h3>
	<% } else { %>
		<table class="info">
			<tr>
				<th>#</th>
				<th>Url originale</th>
				<th>url generato</th>
				<th>Posizione</th>
				<th>Browser</th>
				<th>Data</th>
			</tr>
			<% for (int i=0; i<listUrl.size(); i++) { %>
			<tr>
				<td><%=i + 1%></td>
				<td><%=listUrl.get(i).getLongurl()%></td>
				<td><%=listUrl.get(i).getShorturl()%></td>
				<td><%=listServer.get(i).getPosizione()%></td>
				<td><%=listServer.get(i).getTipologia()%></td>
				<td><%=listServer.get(i).getData()%></td>
			</tr>
			<% } %>
		</table>
	<% } %>

		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br> <br> <br>


	</div>

	<%@ include file="./css/footer.jsp"%>
</body>
</html>