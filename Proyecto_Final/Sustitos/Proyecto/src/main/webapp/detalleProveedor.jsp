<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!-- Aniadimos la cabecera -->
<c:import url="cabecera.jsp"/>


	<!-- Detalles proveedor -->
	
	<div class="container">
		<h1><c:out value='${proveedor.nombre}'/></h1>
		<form action="ServletProveedores" method="post">
			<table class="table">
				<tr>
			   		<th>Encargado</th>
			   		<th>Categoría</th>
		            <th>Telefono</th>
		            <th>Email</th>
			   	</tr>
			   	<tr>
					<td><c:out value='${proveedor.encargado}'/><input type="text" name="encargado"></input></td>
					<td><c:out value='${proveedor.categoria}'/><input type="text" name="categoria"></input></td>
					<td><c:out value='${proveedor.telefono}'/><input type="text" name="telefono"></input></td>
					<td><c:out value='${proveedor.email}'/><input type="text" name="email"></input></td>
				</tr>
			</table>
			<button type="sumbit" name="guardar" class="btn btn-danger mb-1">Guardar cambios</button>
			<button type="sumbit" name="volver" class="btn btn-danger mb-1">Volver a la pagina anterior</button>
			<input type="hidden" name="claveProveedor" value="${proveedor.nombre}"></input>
		</form>
    </div>
<!-- Aniadimos el footer -->
<c:import url="footer.jsp"/>