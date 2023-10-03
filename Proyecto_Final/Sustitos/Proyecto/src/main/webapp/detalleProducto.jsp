<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Aniadimos la cabecera -->
<c:import url="cabecera.jsp"/>

	<!-- Lista de productos -->
	<h1> Detalles <c:out value='${producto.nombre}'/></h1>
	  
	<table class="table">
		<tr>
	   		<th>Nombre</th>
	   		<th>Descripcion</th>
	   		<th>Precio</th>
	   		<th>Categoría</th>
	   	</tr>
		<tr>
			<td><c:out value='${producto.nombre}'/></td>
			<td><c:out value='${producto.descripcion}'/></td>
			<td><c:out value='${producto.precio}'/>$</td>
			<td><c:out value='${producto.cat}'/></td>
		</tr>
	</table>
	<a href="listadoProductos.jsp" class="btn btn-danger">Seguir Comprando</a>
    
<!-- Aniadimos el footer -->
<c:import url="footer.jsp"/>