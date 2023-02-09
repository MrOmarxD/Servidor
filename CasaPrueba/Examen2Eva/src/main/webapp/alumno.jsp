<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF8">
		<title>LOGIN</title>
		<link rel="stylesheet" type="text/css" href="css/estilos.css"/>
	</head>
	<body>
		<div id="header">
            <h1>APLICACIÓN ACTIVIDADES</h1>
        </div>
        <div id=menu>
        	<h2>ALUMNOS</h2>
        </div>
        <div id="container">
        	<!-- Introduce el contenido de la pantalla de Alumnos -->
        	<!-- Actividades ya asignadas -->
			<table>
				<thead>
					<tr>
						<td colspan="3">ACTIVIDADES ASIGNADAS</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>ACTIVIDAD</th>
						<th>PRECIO</th>
						<th>IMPARTIDOR</th>
					</tr>
					<c:forEach var="actividad" items="${actividadesInscrito}">
						<tr>
							<td>${actividad.nombre }</td>
							<td>${actividad.costeMensual }</td>
							<td>${actividad.nombreImpartidor }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<!-- Actividades no asignadas -->
			<table>
				<thead>
					<tr>
						<td colspan="3">NUEVAS INSCRIPCIONES</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>ACTIVIDAD</th>
						<th>IMPARTIDOR</th>
						<th>APUNTARSE</th>
					</tr>
					<c:forEach var="actividad" items="${actividadesNoInscrito}">
					
						<tr>
							<td>${actividad.nombre }</td>
							<td>${actividad.nombreImpartidor }</td>
							<c:if test="${nuevasInscripciones.contains(actividad.id) }">
								<td class="lineaCabecera"><a href="ServletInscripcion?borrar=${actividad.id}">ANULAR</a></td>
								<c:set var="cont" value="${cont+1}"/>
							</c:if>
							<c:if test="${!nuevasInscripciones.contains(actividad.id) }">
								<td><a href="ServletInscripcion?guardar=${actividad.id}">APUNTARSE</a></td>
							</c:if>
						</tr>
					</c:forEach>
					<c:if test="${cont > 0 }">
						<tr>
							<c:if test="${cont > 1 }">
								<td colspan="3"><a href="ServletInscripcion?guardarEnBBDD">GUARDAR LAS ${cont } NUEVAS INSCRIPCIONES</a></td>
							</c:if>
							<c:if test="${cont == 1 }">
								<td colspan="3"><a href="ServletInscripcion?guardarEnBBDD">GUARDAR LA NUEVA INSCRIPCIÓN</a></td>
							</c:if>
						</tr>
					</c:if>
				</tbody>
			</table>
			
		</div>
	</body>
</html>