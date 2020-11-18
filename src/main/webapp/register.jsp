<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Sample Register page">
<meta name="author" content="Vittorio Valent">

<title>Register</title>

<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/css/vittoriostyle.css" rel="stylesheet">
</head>


<body class="text-center">
	<form class="login" action="/user/register" method="post">
		<h1>REGISTRAZIONE</h1>

		<label for="inputUser" class="sr-only">Crea il tuo username</label> 
		<input type="text" name="username" id="inputUser" class="form-control" placeholder="Username" required autofocus>

		<label for="inputPassword" class="sr-only">Crea la tua password</label> 
		<input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required> 

		<input type="hidden" name="richiesta" value="login">
		<button class="btn btn-lg btn-primary btn-block" type="submit">Registrati</button>

	</form>

</body>
</html>