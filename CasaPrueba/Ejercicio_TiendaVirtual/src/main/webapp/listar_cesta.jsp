<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listar cesta</title>
</head>
<body>
	<div class="container" style="margin: auto; width: 20em">
		<h2 style="text-align: center; background: LightYellow;">LISTADO CARRO</h2>
		<form action="ServletAgregarLineaPedido" method="post">
			<table style="margin: auto; border: 1px solid LightYellow;">
				<tr>
					<th style="border: 1px solid LightYellow">Id</th>
					<th style="border: 1px solid LightYellow">Nombre</th>
					<th style="border: 1px solid LightYellow">Precio</th>
					<th style="border: 1px solid LightYellow">Num</th>
					<th style="border: 1px solid LightYellow">CAMBIAR</th>
				</tr>
				
				<c:forEach items="${carroCompra.getCarro()}" var="carro">
					<tr>
						<td style="border: 1px solid LightYellow; padding: 10px"><c:out value="${carro.key}" /></td>
						<td style="border: 1px solid LightYellow; padding: 10px"><c:out value="${carro.value.item.getNombre()}" /></td>
						<td style="border: 1px solid LightYellow; padding: 10px"><c:out value="${carro.value.item.getPrecio()}" /></td>
						<td style="border: 1px solid LightYellow; padding: 10px"><input type="text" name="${carro.key}" value="${carro.value.cantidad}" /></td>
						<td style="border: 1px solid LightYellow; padding: 10px">
							<button type="submit" name="borrar" value="${carro.key}">BORRAR ITEM</button>
							<button type="submit" name="cambiar" value="${carro.key}">CAMBIAR CANTIDAD</button>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="3" style="border: 1px solid LightYellow; padding: 10px"><strong>TOTAL</strong></td>
					<td colspan="3" style="border: 1px solid LightYellow; padding: 10px"><p style="color: red;"><c:out value="${carroCompra.total()}" />€</td>
				</tr>
			</table>
			<button type="submit" name="vaciar">VACIAR CESTA</button>
			<button type="submit" name="continuar" >CONTINUAR LA COMPRA</button>
			<button type="submit" name="hacer_pedido">HACER PEDIDO</button>
		</form>
	</div>
</body>
</html>