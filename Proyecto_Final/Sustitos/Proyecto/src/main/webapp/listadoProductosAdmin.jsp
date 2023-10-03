<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Aniadimos la cabecera -->
<c:import url="cabecera.jsp"/>

	<!-- Si no existe el array de productos lo crea-->
	<c:if test="${lstProductos == null}">
		<jsp:forward page="ServletProductos"/>
	</c:if>

	<!-- Lista de productos -->
	<h1>Productos</h1>
	  
	<form action="ServletAgregarLineaPedidos" method="post">
		<table class="table">
			<tr>
		   		<th>Nombre</th>
		   		<th>Precio</th>
		   		<th>Descripcion<th>
		   		<th>Borrar</th>
		   	</tr>
			<c:forEach items="${lstProductos}" var="producto">
				<tr>
					<td><c:out value='${producto.nombre}'/></td>
					<td><c:out value='${producto.precio}'/></td>
					<td><c:out value='${producto.descripcion}'/></td>
					<td>
						<button type="submit" class="btn btn-danger" name="borrarProd" value="${producto.id}">Borrar</button>
					</td>
				</tr>
			</c:forEach>
		</table>
		<a href="aniadirProducto.jsp" class="btn btn-danger">Añadir Producto</a>
    </form>
    
<!-- Aniadimos el footer -->
<c:import url="footer.jsp"/>