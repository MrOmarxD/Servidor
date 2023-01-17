<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Primitiva</title>
</head>
<body>

<form action="ServletCheckFecha" method="post">
     <table cellpadding="3">
         <tr bgcolor="cccccc">
             <td bgcolor="cccccc" colspan="6">
                 <h3 align="center">PRIMITIVA</h3>
             </td>
         </tr>
         
         <c:choose>
	         <c:when test="${sessionScope.primitiva != null}"> 
	          <tr bgcolor="cccccc">
	             <td>Dia</td>
	             <td><input type="text" name="dia" value="${sessionScope.primitiva.getFecha().getDia()}"></td>
	             <td>Mes</td>
	             <td><input type="text" name="mes" value="${sessionScope.primitiva.getFecha().getMes()}"></td>
	             <td>Año</td>
	             <td><input type="text" name="ano" value="${sessionScope.primitiva.getFecha().getAno()}"></td>
	         </tr>
			</c:when>
			<c:otherwise>
    			<tr bgcolor="cccccc">
		             <td>Dia</td>
		             <td><input type="text" name="dia" value=""></td>
		             <td>Mes</td>
		             <td><input type="text" name="mes" value=""></td>
		             <td>Año</td>
		             <td><input type="text" name="ano" value=""></td>
         		</tr>
  			</c:otherwise>
 		</c:choose>
         
		
		<c:choose>
		  <c:when test="${sessionScope.errores != null}">
		    <tr bgcolor="cccccc" >
         		<td colspan="6" align="center" bgcolor="red">${sessionScope.errores}</td>
         	</tr>
		  </c:when>
		  <c:when test="${sessionScope.primitiva != null}">
		    <tr bgcolor="cccccc" >
         		<td colspan="6" align="center" bgcolor="cccccc">${sessionScope.primitiva.verResultadoGanador()}</td>
         	</tr>
         	<tr bgcolor="cccccc" >
         		<td colspan="6" align="center" bgcolor="cccccc"><a href="check_apuestas.jsp">COMPROBAR APUESTA</a></td>
         	</tr>
		  </c:when>
		  	<c:otherwise>
		  	<!-- NO DEBERIA SALIR NADA -->
  			</c:otherwise>
		</c:choose>
		
        
         
         <tr>
             <td colspan="6" bgcolor="222222">
                 <p align="center">
                     <input type="submit" name="crearCuentaCorriente" value="Crear Cuenta Corriente">
                 </p>
             </td>
         </tr>
     </table>
</form>


</body>
</html>