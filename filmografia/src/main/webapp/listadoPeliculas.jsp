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

	<table id="tabla" style="align:center" border="1">

		<tr>

			<td>Título</td>

		</tr>


		<c:forEach items="${listaPelis}" var="item">

			<tr>

				<td>${item.titulo}</td>

			</tr>

		</c:forEach>

	</table>
	
	<br>
	<br>
	<p>¿Desea consultar otro director?</p>
	<br>
	<a href="consultaDirector.jsp"><button>Si</button></a>
	<br>
	<br>
	<a href="directores.jsp"><button>Finalizar</button></a>

</body>
</html>