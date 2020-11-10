<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="it.contrader.dto.UrlTableDTO"%>
<!DOCTYPE html>
<html>
<head>

<%
	UrlTableDTO url = (UrlTableDTO)request.getAttribute("url"); 
	String urlShort = "";
	if(url != null){
		urlShort = url.getShortUrl();
	}
%>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 	<form class="shortUrl" action="Test" method="post">
		<label for="url">Url: </label>	
		<input type="text" id="input" name="url" placeholder="Insert url">
		<button type="submit" value="test" name="pulsante">Generate ShortURL</button>
		<p> Res: <%=urlShort%></p>
	</form>
</body>
</html>