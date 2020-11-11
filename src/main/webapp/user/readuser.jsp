<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO"
    import="it.contrader.dto.UrlTableDTO"
    import="it.contrader.dto.ServerDTO"
    import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Read User</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a class="active"  href="UserServlet?mode=userlist">Users</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>

<div class="main">

<%
UserDTO u = (UserDTO) request.getAttribute("dto");
List<UrlTableDTO> listUrl = (List<UrlTableDTO>) request.getAttribute("urlDTO");
List<ServerDTO> listServer = (List<ServerDTO>) request.getAttribute("serverDTO");
%>

<h2>Informazioni sull'URL dell'utente: <%=u.getUsername()%></h2>


<table class="tableInfo">
	<tr> 
		<th>#</th>
		<th>URL originale</th>
		<th>URL generato</th>
		<th>Paese</th>
		<th>Browser</th>
		<th>Data</th>
		
	</tr>
	<%
			for (int i=0; i<listUrl.size(); i++) {
		%>
		<tr>
			<td><%=listUrl.get(i).getId()%></td>
			<td><%=listUrl.get(i).getLongUrl()%></td>
			<td><%=listUrl.get(i).getShortUrl()%></td>
			<td><%=listServer.get(i).getPosizione()%></td>
			<td><%=listServer.get(i).getTipologia()%></td>
			<td><%=listServer.get(i).getData()%></td>

		</tr>
		<%
			}
		%>
</table>

<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>


</div>

<%@ include file="../css/footer.jsp" %>
</body>
</html>