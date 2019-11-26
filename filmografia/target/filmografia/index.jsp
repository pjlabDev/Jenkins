<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pagina principal</title>
</head>
<body>
	
	<h2>Bienvenido a la página principal</h2>
	
	<br>
	
	<form action="CineController" method="POST">
	
		<input type="text" name="director">
		<input type="submit" name="action" value="Consultar directores">
	
	</form>

</body>
</html>