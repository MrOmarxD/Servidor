<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- Aniadimos la cabecera -->
<c:import url="cabecera.jsp"/>

<!-- Aniadimos menu lateral 
<c:import url="menuLateral.jsp"/>-->

	<!-- Si no existe el array de Proveedores lo crea-->
	<c:if test="${proveedores == null}">
		<jsp:forward page="ServletProveedores"/>
	</c:if>

	<!-- Lista de proveedores -->
	<div class="container">
		<h1>Proveedores</h1>
		<table class="table">
			<tr>
		   		<th>Nombre Empresa</th>
		   		<th>Categoría</th>
		   		<th>Mas datos</th>
		   	</tr>
		  
			<c:forEach items="${proveedores}" var="proveedor">
				<tr>
					<td><c:out value='${proveedor.value.nombre}'/></td>
					<td><c:out value='${proveedor.value.categoria}'/></td>
					<td><a class="btn btn-danger" href="ServletProveedores?claveProveedor=${proveedor.key}">Mas información</a></td>
				</tr>
			</c:forEach>
		</table>
	   </div>
<!-- Aniadimos el footer -->
<c:import url="footer.jsp"/>