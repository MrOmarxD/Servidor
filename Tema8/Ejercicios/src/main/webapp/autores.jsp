<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Autores</title>
</head>
<body>
	<%-- El parámetro libros se encuentran el ambito de sesión --%>
    <c:if test="${lstAutores == null}">
    	<jsp:forward page="ServletControladorAutores"/>
    </c:if>
    
	<h1>Lista de Autores</h1>
	<table>
		<tr>
			<th>Nombre</td>
			<th>Fecha de nacimiento</th>
			<th>Nacionalidad</th>
			<th>Ver libros</th>
		</tr>
		<c:forEach items="${lstAutores}" var="autor">
			<tr>
				<td>${autor.nombre}</td>
				<td>${autor.fechanac}</td>
				<td>${autor.nacionalidad}</td>
				<td><a href="ServletControladorAutores?idautor=${autor.idAutor}">Ver Libros</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<h1>Añadir Autor</h1>
	<form action="ServletControladorAutores" method="post">
		Nombre: <input type="text" name="nombre"><br>
        Fecha de nacimiento: <input type="date" name="fechanac"><br>
        Nacionalidad: <input type="text" name="ncionalidad"><br>
        <input type="submit" name="botAniadir" value="AÑADIR">
    </form>
    <p>${errorAniadir}</p>
    
    <c:if test="${nombreAutorSeleccionado != null}">
    	<h1>Libros de ${nombreAutorSeleccionado}</h1>
    	<ul>
    		<c:forEach items="${librosAutor}" var="libro">
    			<li>${libro.titulo}</li>
    		</c:forEach>
    	</ul>
    </c:if>

	<a href="index.jsp">Ir a la lista de libros</a>
</body>
</html>