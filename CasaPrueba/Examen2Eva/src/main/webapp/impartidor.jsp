<%@page import="java.util.Iterator"%>
<%@page import="beans.Alumno"%>
<%@page import="beans.Actividad"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.Impartidor"%>
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
        	<h2>IMPARTIDORES</h2>
        </div>
        <div id="container">
			<!-- Introduce el contenido de la pantalla de Impartidores -->
			<%
				Impartidor impartidor = (Impartidor) session.getAttribute("impartidor");
				out.print("<p>" + impartidor.getApellido() + " " + impartidor.getNombre() + "</p><hr>");
			%>
			<table>
				<%
					if (session.getAttribute("actividadesImpartidor") != null) {
						
						for (Actividad actividad : (ArrayList<Actividad>) session.getAttribute("actividadesImpartidor")) {
							out.print("<tr>");
							out.print("<td>"+actividad.getNombre()+"</td>");
							out.print("<td> <a href='ServletAvisos?asistencia'"+actividad.getId()+">ASISTENCIA</a></td>");
							out.print("</tr>");
						}
						
					}
				
				%>
			</table>
			<table>
				<%
				/*
					if (session.getAttribute("alumnos") != null) {
						Iterator<Integer> it = (HashSet<Alumno>) session.getAttribute("alumnos");
						while (it.hasNext()) {
							out.print("<tr>");
							out.print("<td>"+actividad.getNombre()+"</td>");
							out.print("<td> <a href='ServletAvisos?asistencia'"+actividad.getId()+">ASISTENCIA</a></td>");
							out.print("</tr>");
						}
						
					}
				*/
				%>
			</table>
		</div>
	</body>
</html>