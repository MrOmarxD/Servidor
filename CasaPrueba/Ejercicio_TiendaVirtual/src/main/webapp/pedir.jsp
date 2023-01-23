<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pedir</title>
</head>
<body>
	<div class="container" style="margin: auto; width: 20em;">
		<h2 style="text-align: center;">PEDIDO</h2>
		<h2 style="text-align: center; background: LightYellow;">TUS DATOS</h2>
		<form action="ServletGrabarCompra" method="post">
			<table style="margin: auto; border: 1px solid LightYellow;">
				<tr>
					<td style="border: 1px solid LightYellow"><label>Nombre</label></td>
					<td style="border: 1px solid LightYellow"><input type="text" name="nombre" value="${cliente.getNombre()}" disabled></td>
				</tr>
				<tr>
					<td style="border: 1px solid LightYellow"><label>Zip</label></td>
					<td style="border: 1px solid LightYellow"><input type="text" name="zip" value="${cliente.getCodigoPostal()}"></td>
				</tr>
				<tr>
					<td style="border: 1px solid LightYellow"><label>Direccion</label></td>
					<td style="border: 1px solid LightYellow"><input type="text" name="direccion" value="${cliente.getDomicilio()}"></td>
				</tr>
				<tr>
					<td style="border: 1px solid LightYellow"><label>Teléfono</label></td>
					<td style="border: 1px solid LightYellow"><input type="tel" name="telefono" value="${cliente.getTelefono()}"></td>
				</tr>
				<tr>
					<td style="border: 1px solid LightYellow"><label>E-mail</label></td>
					<td style="border: 1px solid LightYellow"><input type="email" name="email" value="${cliente.getEmail()}"></td>
				</tr>
			</table>
			
			<h2 style="text-align: center; background: LightYellow">TU CARRO</h2>
			<table style="margin: auto; border: 1px solid LightYellow; margin-bottom: 15px">
				<tr>
					<th style="border: 1px solid LightYellow">Id</th>
					<th style="border: 1px solid LightYellow">Nombre</th>
					<th style="border: 1px solid LightYellow">Precio</th>
					<th style="border: 1px solid LightYellow">Num</th>
				</tr>
				
				<c:forEach items="${carroCompra.getCarro()}" var="carro">
					<tr>
						<td style="border: 1px solid LightYellow; padding: 10px"><c:out value="${carro.key}" /></td>
						<td style="border: 1px solid LightYellow; padding: 10px"><c:out value="${carro.value.item.getNombre()}" /></td>
						<td style="border: 1px solid LightYellow; padding: 10px"><c:out value="${carro.value.item.getPrecio()}" /></td>
						<td style="border: 1px solid LightYellow; padding: 10px"><c:out value="${carro.value.cantidad}" /></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="3" style="border: 1px solid LightYellow; padding: 10px"><strong>TOTAL</strong></td>
					<td colspan="3" style="border: 1px solid LightYellow; padding: 10px"><p style="color: red;"><c:out value="${carroCompra.total()}" />€</td>
				</tr>
			</table>
			
			<button type="submit" name="comprar">COMPRAR</button>
			<button type="reset" name="reset">RESET</button>
			<button type="submit" name="modificar">MODIFICAR COMPRA</button>
		</form>
	</div>
</body>
</html>