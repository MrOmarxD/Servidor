<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Añadimos la cabecera -->
<c:import url="cabecera.jsp"/>

	<!-- Si no existe el array de productos lo crea-->
	<c:if test="${lstUsuValidado == null}">
		<jsp:forward page="ServletUsuarios"/>
	</c:if>

	<!-- Lista de usuario validados -->
	<h1>Usuarios Validados</h1>
	<table class="table">
		<tr>
			<th>Imagen</th>
	   		<th>Email</th>
	   		<th>Nombre</th>
	   		<th class="hidden"></th>
	   	</tr>
	  
		<c:forEach items="${lstUsuValidado}" var="usuario">
			<tr>
				<!-- <td><img src="<c:out value='${usuario.imagen}'/>"> </td> -->
				<td><c:out value="${usuario.email}"/> </td>
				<td><c:out value="${usuario.nombre}"/> <c:out value="${usuario.apellido}"/> </td>
				<td> <a class="btn btn-danger" href="ServletBorrar?usuario=${usuario.id}">Borrar</a></td>
			</tr>
		</c:forEach>
	     
	</table>
	
	<!-- Lista de usuario no validados -->
	<h1>Usuarios No Validados</h1>
	<table class="table">
		<tr>
			<th>Imagen</th>
	   		<th>Email</th>
	   		<th>Nombre</th>
	   		<th class="hidden"></th>
	   	</tr>
	  
		<c:forEach items="${lstUsuNoValidado}" var="usuario">
			<tr>
				<td><img src="<c:out value='${usuario.imagen}'/>"> </td>
				<td><c:out value="${usuario.email}"/> </td>
				<td><c:out value="${usuario.nombre}"/> <c:out value="${usuario.apellido}"/> </td>
				<td> <a class="btn btn-danger" href="ServletBorrar?usuario=${usuario.id}">Borrar</a></td>
			</tr>
		</c:forEach>
	     
	</table>

<!-- Añadimos el footer -->
<c:import url="footer.jsp"/>