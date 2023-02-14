<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF8">
		<title>Alumnos</title>
	</head>
	<body>
		<c:if test="${alumno == null}">
			<jsp:forward page="ServletAlumnos"/>
	    </c:if>
	    
	    <table>
			<tr>
				<th colspan=3>Alumnos</th>
			</tr>
			<tr>
				<td>DNI</td>
				<td>APELLIDO</td>
				<td>NOMBRE</td>
			</tr>
			<c:forEach items="${alumno}" var="alumno">
				<tr>
					<td>${alumno.dni}</td>
					<td>${alumno.nombre}</td>
					<td>${alumno.apellidos}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>