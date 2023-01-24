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
		<c:if test="${alumno == null}">
			<jsp:forward page="ServletInscripcion"/>
	    </c:if>
		<div id="header">
            <h1>APLICACIÓN ACTIVIDADES</h1>
        </div>
        <div id=menu>
        	<h2>ALUMNOS</h2>
        </div>
        <div id="container">
        	<p>SOCIO:${alumno.nombre} ${alumno.apellidos}</p>
			<table>
				<tr>
					<th colspan=3>ACTIVIDADES ASIGNADAS</th>
				</tr>
				<tr>
					<td class="lineaCabecera">ACTIVIDAD</td>
					<td class="lineaCabecera">PRECIO</td>
					<td class="lineaCabecera">IMPARTIDOR</td>
				</tr>
				<c:forEach items="${lstActividadesParticipa}" var="actividad">
					<tr>
						<td>${actividad.nombre}</td>
						<td>${actividad.coste_mensual}</td>
						<td>${actividad.impartidor}</td>
					</tr>
				</c:forEach>
			</table>
			
			<table>
				<tr>
					<th colspan=3>NUEVAS INSCRIPCIONES</th>
				</tr>
				<tr>
					<td class="lineaCabecera">ACTIVIDAD</td>
					<td class="lineaCabecera">IMPARTIDOR</td>
					<td class="lineaCabecera">APUNTARSE</td>
				</tr>
				<c:forEach items="${lstActividadesNoParticipa}" var="actividad">
					<tr>
						<td>${actividad.nombre}</td>
						<td>${actividad.impartidor}</td>
						<td>
							<c:choose>
                   				<c:when test="${lstApuntarActividad.contains(actividad)}">
                      				<a href="ServletInscripcion?anular=${actividad.id}" style="color:red;">ANULAR</a>
                   				</c:when>
                   				<c:otherwise>
                       				<a href="ServletInscripcion?apuntarse=${actividad.id}">APUNTARSE</a>
                   				</c:otherwise>
               				</c:choose>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="2">
						<c:if test="${lstApuntarActividad.size()>0}">
				            <a href="ServletInscripcion?grabar">GUARDAR LAS </a> (${lstApuntarActividad.size()} NUEVAS INSCRIPCIONES)
			        	</c:if>
	    			</td>
				</tr>
			</table>
			
        </div>
	</body>
</html>