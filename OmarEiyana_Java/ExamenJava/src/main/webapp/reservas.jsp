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
        
        	<!-- Si no existe el array de charlas grabadas lo crea-->
			<c:if test="${lstCharlas == null}">
				<jsp:forward page="ServletReservas"/>
			</c:if>
			
        	<h2>lista de Charlas grabadas</h2>
        	<table>
        		<tr>
        			<th>CHARLA</th>
        			<th>SALA</th>
        			<th>TARIFA</th>
        			<th>RESERVAS</th>
        		</tr>
        		<c:forEach items="${lstCharlas}" var="charla">
					<tr>
						<td><c:out value='${charla.tema}'/></td>
						<td><c:out value='${charla.id_sala}'/></td>
						<td><c:out value='${charla.tarifa}'/></td>
						<c:if test="${charla.reservas>0}">
							<td>
								<a href="ServletReserva?idCharla="+<c:out value='${charla.id_charla}'/>><c:out value='${charla.tarifa}'/> RESERVAS</a>
								<c:if test="${lstReservas!=null}">
									<c:forEach items="${lstReservas}" var="reserva">
										<p><c:out value='${reserva.nombreCliente}'/> (Reserva Pagada)</p>
									</c:forEach>
								</c:if>
							</td>
						</c:if>
						<c:if test="${charla.reservas <=0}">
							<td>Sin reservas</td>
						</c:if>
					</tr>
				</c:forEach>
        	</table>
		</div>
	</body>
</html>