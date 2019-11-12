<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="laboral.Empleado" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Salario empleado</title>
</head>
<body>
		
		
		
	<h3 align="center">El salario del empleado ${dniEmp} es:</h3>
				<br>
				<br>
	 
		
			<h4 align="center">${salarioEmp}</h4>
    
     <br>
     <br>
     
     <a href="index.html"><button>Volver</button></a>
     
</body>
</html>