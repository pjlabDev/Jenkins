<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consulta Director</title>
</head>
<body>

	Introduzca el nombre del director
	<br>
	<form action="CineController" method="POST">
	
		<input type="text" name="director">
		<p>${ message }</p>
		<input type="submit" name="action" value="Consultar">
	
	</form>
	
</body>
</html>