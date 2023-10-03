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
	
	<!-- Mensaje de error por si no introducen ningun valor al añadir -->
	<c:if test="${mensajeError != null}">
	  		<p style="color: green"><c:out value = "${mensajeError}"/></p> 
	 </c:if>
	  
	<form action="ServletAgregarLineaPedidos" method="post">
		<table class="table">
			<tr>
		   		<th>Nombre</th>
		   		<th>Precio</th>
		   		<th>Cantidad</th>
		   		<th>Añadir</th>
		   	</tr>
			<c:forEach items="${lstProductos}" var="producto">
				<tr>
					<td><c:out value='${producto.nombre}'/></td>
					<td><c:out value='${producto.precio}'/></td>
					<td><input type="number" name="${producto.id}" ></td>
					<td>
						<button type="submit" class="btn btn-danger" name="aniadir" value="${producto.id}">Añadir</button>
						<button type="submit" class="btn btn-danger" name="detalles" value="${producto.id}">Más info</button>
					</td>
				</tr>
			</c:forEach>
		</table>
		<button type="submit" class="btn btn-danger" name="verCesta">Ver cesta</button>
    </form>
    
<!-- Aniadimos el footer -->
<c:import url="footer.jsp"/>