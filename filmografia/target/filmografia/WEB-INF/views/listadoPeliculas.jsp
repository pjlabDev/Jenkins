<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listado de peliculas</title>
</head>
<body>

	<h2>Listado de peliculas del director: ${director}</h2>

	<br>

	<table id="tabla" border="1">

		<tr>

			<td>Titulo</td>

		</tr>


		<c:forEach items="${listaPelis}" var="item">

			<tr>

				<td>${info.titulo}</td>

			</tr>

		</c:forEach>

	</table>

</body>
</html>