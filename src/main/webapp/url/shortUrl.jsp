<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Short URL Generator</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>

<div class="main">


<div align="center">
<form action="" method="post">
  <div class="row">
    <div class="col-26">
      <label>Insert URL</label>
    </div>
    <div class="col-76">
      <input type="text" placeholder="http://example.com">
    </div>
    <div class="col-26">
      <label>Generated Short URL</label>
    </div>
  </div>
      <button type="submit" >Convert</button>
</form>
</div>

</div>

<%@ include file="../css/footer.jsp" %>
</body>
</html>