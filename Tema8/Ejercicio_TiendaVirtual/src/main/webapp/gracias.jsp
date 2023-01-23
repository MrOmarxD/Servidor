<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gracias</title>
</head>
<body>
	<div class="container" style="margin: auto; width: 20em;">
		<h2 style="text-align: center;">GRACIAS POR REALIZAR SU PEDIDO</h2>
		<h2 style="text-align: center; background: LightYellow;">Estos son los datos de su pedido</h2>
		<form action="ServletGrabarCompra" method="post">
			<table style="margin: auto; border: 1px solid LightYellow;">
				<tr>
					<td style="border: 1px solid LightYellow"><label>Nombre</label></td>
					<td style="border: 1px solid LightYellow"><c:out value="${cliente.getNombre()}" /></td>
				</tr>
				<tr>
					<td style="border: 1px solid LightYellow"><label>Zip</label></td>
					<td style="border: 1px solid LightYellow"><c:out value="${cliente.getCodigoPostal()}" /></td>
				</tr>
				<tr>
					<td style="border: 1px solid LightYellow"><label>Direccion</label></td>
					<td style="border: 1px solid LightYellow"><c:out value="${cliente.getDomicilio()}" /></td>
				</tr>
				<tr>
					<td style="border: 1px solid LightYellow"><label>Teléfono</label></td>
					<td style="border: 1px solid LightYellow"><c:out value="${cliente.getTelefono()}" /></td>
				</tr>
				<tr>
					<td style="border: 1px solid LightYellow"><label>E-mail</label></td>
					<td style="border: 1px solid LightYellow"><c:out value="${cliente.getEmail()}" /></td>
				</tr>
			</table>
			
			<h2 style="text-align: center; background: LightYellow">Se enviará a la mayor brevedad posible el siguiente pedido:</h2>
			<table style="margin: auto; border: 1px solid LightYellow;">
				<tr>
					<th style="border: 1px solid LightYellow">nombre</th>
					<th style="border: 1px solid LightYellow">precio</th>
					<th style="border: 1px solid LightYellow">num</th>
				</tr>
				
				<c:forEach items="${carroCompra.getCarro()}" var="carro">
					<tr>
						<td style="border: 1px solid LightYellow; padding: 10px"><c:out value="${carro.value.item.getNombre()}" /></td>
						<td style="border: 1px solid LightYellow; padding: 10px"><c:out value="${carro.value.item.getPrecio()}" /></td>
						<td style="border: 1px solid LightYellow; padding: 10px"><c:out value="${carro.value.cantidad}" /></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="3" style="border: 1px solid LightYellow; padding: 10px"><p style="color: red;"><c:out value="${carroCompra.total()}" />€</td>
				</tr>
			</table>
			
			<a href="login.jsp">SALIR</a>
			<a href="tienda.jsp">VOLVER A LA TIENDA</a>
		</form>
	</div>
</body>
</html>