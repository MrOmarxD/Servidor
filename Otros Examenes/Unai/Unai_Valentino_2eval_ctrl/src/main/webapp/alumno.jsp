<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        	<p>SOCIO: <c:out value="${alumno.getNombre()}"></c:out> <c:out value="${alumno.getApellidos()}"></c:out></p>
        	
        	<table>
        		<caption>ACTIVIDADES ASIGNADAS</caption>
        		<tr>
        			<th>ACTIVIDAD</th>
        			<th>PRECIO</th>
        			<th>IMPARTIDOR</th>
        		</tr>
	        	<c:forEach items="${arrActividades}" var="actividad">
	        		<tr>
	        			<td><c:out value="${actividad.getNombre()}"></c:out></td>
	        			<td><c:out value="${actividad.getCoste_mensual()}"></c:out>&euro;</td>
	        			<td><c:out value="${actividad.getImpartidor().getNombre()}"></c:out> <c:out value="${actividad.getImpartidor().getApellido()}"></c:out></td>
	        		</tr>
	        	</c:forEach>
        	</table>
        	<table>
        		<caption>NUEVAS INSCRIPCIONES</caption>
        		<tr>
        			<th>ACTIVIDAD</th>
        			<th>IMPARTIDOR</th>
        			<th>APUNTARSE</th>
        		</tr>
	        	<c:forEach items="${arrActividadesLibres}" var="actividadLibre">
	        		<c:choose>
		        		<c:when test="${arrInscripciones != null && arrInscripciones.contains(actividadLibre)}">
		        			<tr style="background-color: grey">
		        		</c:when>
	        			<c:otherwise>
			        		<tr>
	        			</c:otherwise>
	        		</c:choose>
	        			<td><c:out value="${actividadLibre.getNombre()}"></c:out></td>
	        			<td><c:out value="${actividadLibre.getImpartidor().getNombre()}"></c:out> <c:out value="${actividadLibre.getImpartidor().getApellido()}"></c:out></td>
	        			<c:choose>
	        				<c:when test="${arrInscripciones.contains(actividadLibre)}">
	        					<td><a href="ServletInscripcion?anular=true&id=${actividadLibre.getId()}">ANULAR</a></td>
	        				</c:when>
	        				<c:otherwise>
			        			<td><a href="ServletInscripcion?apuntar=true&id=${actividadLibre.getId()}">APUNTARSE</a></td>
	        				</c:otherwise>
	        			</c:choose>
	        		</tr>
	        	</c:forEach>
        	</table>
        	
        	<c:if test="${arrInscripciones != null && arrInscripciones.size() > 0}">
        		<a href="ServletInscripcion?guardar=true">GUARDAR LAS <c:out value="${arrInscripciones.size()}"></c:out> NUEVAS INSCRIPCIONES</a>
        	</c:if>
        	
			
		</div>
	</body>
</html>