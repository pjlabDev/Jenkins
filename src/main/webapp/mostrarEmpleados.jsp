<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="laboral.Empleado" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Empleados BD</title>
<link rel="stylesheet" type="text/css" href="style.css"/>
</head>
<body>
	
	
	<table id="tabla" border="1">
	
		<tr>
			
			<td>Nombre</td>
			<td>DNI</td>
			<td>Sexo</td>
			<td>Categoria</td>
			<td>Años</td>
		
		</tr>
			
			
			<c:forEach items="${listaEmp}" var="info">
				
				<tr>
				
					<td>${info.nombre }</td>
					<td>${info.dni }</td>
					<td>${info.sexo }</td>
					<td>${info.categoria }</td>
					<td>${info.anyos }</td>
				
				</tr>
				
			</c:forEach>
			
			
		<%-- 	<%List<String> emp = (ArrayList<String>) request.getAttribute("listaEmp"); %>
			
			<%for(String empleados : emp){ %>
			
				<%String info[] = empleados.split("-"); %>
			<tr>
				<td><%=info[0] %></td>
				<td><%=info[1] %></td>
				<td><%=info[2] %></td>
				<td><%=info[3] %></td>
				<td><%=info[4] %></td>
			</tr>
			
			<%} %> --%>
			
			
	</table>
	
	<a href="index.html"><button>Volver</button></a>
	
	
</body>
</html>