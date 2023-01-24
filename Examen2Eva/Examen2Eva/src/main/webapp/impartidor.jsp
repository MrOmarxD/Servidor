<%@page import="beans.Impartidor"%>
<%@page import="beans.Actividad"%>
<%@ page language="java" content="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF8">
		<title>LOGIN</title>
		<link rel="stylesheet" type="text/css" href="css/estilos.css"/>
	</head>
	<body>
		<%
			if(session.getAttribute("impartidor")==null)
				
        %>
		<div id="header">
            <h1>APLICACIÓN ACTIVIDADES</h1>
        </div>
        <div id=menu>
        	<h2>IMPARTIDORES</h2>
        </div>
        <div id="container">
        	<p><%=impartidor.getApellido()%> <%=impartidor.getNombre()%></p>
        	<%
                for (Actividad actividad : lstActividades) {  
             %>
                	<p><%=actividad.getNombre()%><a href="ServletAvisos?asistencia=<%=actividad.getId()%>">ASISTENCIA</a></p>
             <% 
                 }
             %>
		</div>
	</body>
</html>