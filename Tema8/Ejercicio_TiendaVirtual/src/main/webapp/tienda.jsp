<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tienda</title>
</head>
<body>
	<div class="container" style="margin: auto; width: 20em;">
		<h2 style="text-align: center; background: LightYellow;">LÁMINAS</h2>
		<form action="ServletAgregarLineaPedido" method="post">
			<table style="margin: auto; border: 1px solid LightYellow; margin-bottom: 15px">
				<tr>
					<th style="border: 1px solid LightYellow">Id</th>
					<th style="border: 1px solid LightYellow">Nombre</th>
					<th style="border: 1px solid LightYellow">Precio</th>
					<th style="border: 1px solid LightYellow">Cantidad</th>
					<th style="border: 1px solid LightYellow">Añadir</th>
				</tr>
				
				<c:forEach items="${lstItems}" var="item">
					<tr>
						<td style="border: 1px solid LightYellow; padding: 10px"><c:out value="${item.key}" /></td>
						<td style="border: 1px solid LightYellow; padding: 10px"><c:out value="${item.value.nombre}" /></td>
						<td style="border: 1px solid LightYellow; padding: 10px"><c:out value="${item.value.precio}" /></td>
						<td style="border: 1px solid LightYellow; padding: 10px"><input type="number" name="${item.key}" /></td>
						<td style="border: 1px solid LightYellow; padding: 10px"><button type="submit" name="aniadir" value="${item.key}">Añadir al carro</button></td>
					</tr>
				</c:forEach>
			</table>
			<button type="submit" name="ver">Ver cesta</button>
			<button type="submit" name="hacer">Hacer pedido</button>
			<button type="submit" name="pedidos">Mis pedidos</button>
		</form>
	</div>
</body>
</html>