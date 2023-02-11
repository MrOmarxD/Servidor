<%@page import="java.sql.Date"%>
<%@page import="beans.Alumno"%>
<%@page import="java.util.HashMap"%>
<%@page import="beans.Actividad"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.Impartidor"%>
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
        	<h2>IMPARTIDORES</h2>
        </div>
        <div id="container">
        
        <%
        	HttpSession ss = request.getSession();
        	Impartidor imp = (Impartidor) ss.getAttribute("impartidor");
        	out.println("<p>"+imp.getApellido()+" "+imp.getNombre()+"</p>");
        %>
        <hr>
		<table>
			<%
				ArrayList<Actividad> arrActividadesImp = (ArrayList<Actividad>) ss.getAttribute("arrActividadesImp");
				for(Actividad acti: arrActividadesImp){
					if(request.getParameter("id") != null && Integer.parseInt(request.getParameter("id")) == acti.getId()){
						out.println("<tr style='background-color: grey'>");
					}
					else{
						out.println("<tr>");
					}
					out.println("<td>"+acti.getNombre()+"</td>");
					out.println("<td><a href='ServletAvisos?id="+acti.getId()+"'>ASISTENCIA</a></td>");
					out.println("</tr>");
				}
			%>
		</table>
		
		<%
			if(request.getParameter("id") != null){
				out.println("<form action='ServletAvisos' method='post'>");
				out.println("<table>");
				out.println("<tr>");
				out.println("<th>Nombre</th>");
				out.println("<th>Ultima asistencia</th>");
				out.println("<th>TIPO DE AVISO</th>");
				HashMap<Alumno, Date> mapAsistencias = (HashMap<Alumno, Date>) ss.getAttribute("mapAsistencias");
				for(Alumno alu: mapAsistencias.keySet()){
					out.println("</tr>");
					out.println("<td>"+alu.getNombre()+"</td>");
					out.println("<td>"+mapAsistencias.get(alu)+"</td>");
					out.println("<td><input type='radio' name='tipoAviso' value='email'>Email<input type='radio' name='tipoAviso' value='email'>Teléfono <button type='submit' value='"+alu.getDni()+"' name='avisar'>AVISAR</button></td>");
					out.println("<tr>");
				}
				
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
			}
			
			if(request.getParameter("enviado") != null){
				out.println("<p>Se ha enviado el mensaje</p>");
			}
		%>
		
		</div>
	</body>
</html>