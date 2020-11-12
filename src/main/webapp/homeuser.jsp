<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.UserDTO"
	import="it.contrader.dto.UrlTableDTO"%>
<!DOCTYPE html>
<html>
<head>

<%
	Object i = request.getAttribute("url"); //oggetto istanziato a 1 per evitare url=null ad ingresso homeuser (tutto cominciò da LoginServlet "case: USER"..)
	String urlShort = "";
	boolean urlErr = false;
	if(i != null && i.equals(1)) {
	} else {
		UrlTableDTO url = (UrlTableDTO)request.getAttribute("url"); //vero get per ottenere url

		//verifica su url per messaggio di errore
		if(url != null) {
			urlShort = url.getShortUrl();
			if(urlShort == null) {
				urlErr = true;
				urlShort = "";
			}
		} else {
			urlErr = true;
		}
	} //TODO(unsolvable) rimuovere messaggio di errore al refresh della pagina
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
	
	<div class="url">
		<br><form action="UrlServlet" method="post" class="url">
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
		<form action="RedirectServlet" method="post" class="url"> <%-- <%=urlError?"<p>true</p>":"<p>false</p>"%> --%>
			<div class="row">
				<div class="col-76">
				<%if(urlErr==false){%><input type="text" name="keywords" value="<%=urlShort%>" placeholder="iShort.ly/sample"/><%}%>
				<%if(urlErr==true){%><div class="urlErr">URL non valido o già utilizzato</div><%}%>
				</div>
				<div class="col-26">
					<button class="url" type="submit" name="pulsante" value="urlLong">Go</button>
				</div>
			</div>
		</form><br>
	</div>
</div>


<%@ include file="css/footer.jsp" %>

</body>
</html>