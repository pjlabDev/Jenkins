<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Log In</title>
</head>
<body>

	<form action="CineController" method="POST">
	
		<input type="text" name="director">
		<input type="password" name="password">
		<p>${ message }</p>
		<input type="submit" name="action" value="login">
	
	</form>

</body>
</html>