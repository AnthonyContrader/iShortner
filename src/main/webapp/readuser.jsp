<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO" import="it.contrader.dto.ShortUrlDTO"  import="java.util.List"%>
	
	
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
		<a href="/homeadmin.jsp">Home</a> <a class="active"
			href="/user/getall">Users</a> <a href="/user/logout" id="logout">Logout</a>
	</div>
	<br>

	<div class="main">
		<%
			UserDTO u = (UserDTO) request.getSession().getAttribute("dto");
			List<ShortUrlDTO> listUrl = (List<ShortUrlDTO>) request.getSession().getAttribute("urlDto");
		%>


		<table>
			<tr>
				<th>ID</th>
				<th>Username</th>
				<th>Password</th>
				<th>Usertype</th>
			</tr>
			<tr>
				<td><%=u.getId()%></td>
				<td><%=u.getUsername()%></td>
				<td><%=u.getPassword()%></td>
				<td><%=u.getUsertype()%></td>
			</tr>
		</table>
		
		<table>
			<tr>
				<th>#</th>
				<th>Url originale</th>
				<th>url generato</th>
				
			</tr>
			<% 
			for (int i= 0; i<listUrl.size(); i++) {
				%>
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