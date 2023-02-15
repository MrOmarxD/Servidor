<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/estilos.css"/>
        <title>APLICACIÓN CHARLAS</title>
    </head>
    <body>
   		 <div id="header">
            <h1>APLICACIÓN CHARLAS</h1> <!-- Nombre de la página -->
        </div>
        <div id="menu">
        	<jsp:include page="menu.jsp"></jsp:include>
        </div>
        <div id="container">
        	<h2>Preparando charlas de hoy</h2>
        	<table>
        		<form method="post" action="ServletPrepararCharlas">
	        		<tr>
	        			<th>HORA</th>
	        			<th>TEMA</th>
	        			<th>SALA</th>
	        			<th>DESCUENTO</th>
	        		</tr>
	        		<c:forEach var="charla" items="${lstHorasCharlas}">
						<tr>
							<td><c:out value='${charla.hora}'/>:<c:out value='${charla.min}'/></td>
							<td><input type="text" id="tema" name="tema"/></td>
							<select id="sala" name="sala">
								<c:forEach var="sala" items="${lstSalasPreparadas}">
									<td><option value="<c:out value='${sala.id_sala}'/>"><c:out value='${sala.id_sala}'/> para <c:out value='${sala.capacidad}'/></option></td>
								</c:forEach>
							</select>
							<td><input type="checkbox" id="descuento" name="descuento" value="<c:out value='${charla.hora}'/>:<c:out value='${charla.min}'/>"/>Descuento</td>
						</tr>
						<button type="sumbit" name="grabar">Grabar Charla</button>
					</c:forEach>
				</form>
        	</table>
		</div>
	</body>
</html>