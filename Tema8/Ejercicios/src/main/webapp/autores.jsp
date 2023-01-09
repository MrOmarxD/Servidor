<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Autores</title>
</head>
<body>
	<%-- El parámetro libros se encuentran el ambito de sesión --%>
    <c:if test="${autores == null}">
    	<jsp:forward page="ServletControladorAutores"/>
    </c:if>
    
	<h1>Lista de Autores</h1>
	<table>
		<tr>
			<td>Nombre</td>
			<td>Fecha de nacimiento</td>
			<td>Nacionalidad</td>
			<td>Ver libros</td>
		</tr>
		<c:forEach items="${autores}" var="autor">
			<tr>
				<td></td>
			</tr>
		</c:forEach>
	</table>

	<a href="index.jsp">Ir a la lista de libros</a>
</body>
</html>