<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro</title>
</head>
<body>
	<div class="container" style="margin: auto; width: 20em;">
		<h2 style="text-align: center; background: LightYellow; color: red">Registro</h2>
		<form action="ServletRegistro" method="post">
			<table style="margin: auto; border: 1px solid LightYellow;">
				<tr>
					<td style="border: 1px solid LightYellow"><label>Usuario</label></td>
					<td style="border: 1px solid LightYellow"><input type="text" name="usuario"></td>
				</tr>
				<tr>
					<td style="border: 1px solid LightYellow"><label>Password</label></td>
					<td style="border: 1px solid LightYellow"><input type="password" name="password"></td>
				</tr>
				<tr>
					<td style="border: 1px solid LightYellow"><label>Domicilio</label></td>
					<td style="border: 1px solid LightYellow"><input type="text" name="domicilio"></td>
				</tr>
				<tr>
					<td style="border: 1px solid LightYellow"><label>Zip</label></td>
					<td style="border: 1px solid LightYellow"><input type="text" name="zip"></td>
				</tr>
				<tr>
					<td style="border: 1px solid LightYellow"><label>Teléfono</label></td>
					<td style="border: 1px solid LightYellow"><input type="tel" name="telefono"></td>
				</tr>
				<tr>
					<td style="border: 1px solid LightYellow"><label>E-mail</label></td>
					<td style="border: 1px solid LightYellow"><input type="email" name="email"></td>
				</tr>
			</table>
			<button type="submit" name="registrarse">Registrarse</button>
			<button type="reset" name="reset">Reset</button>
		</form>
		<c:if test="${mensajeError != null}">
			<script type='text/javascript'>alert("${mensajeError}");</script>
		</c:if>
	</div>
</body>
</html>