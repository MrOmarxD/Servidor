<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${libros == null}">
		<jsp:forward page="ServletControlador"/>
    </c:if>
	<table>
		<ol>
			<c:forEach items="${lstLibrosPrestados}" var="libroPrestado">
				<tr>
					<li>
						<td> días prestado</td>
						<td><a href="ServletDevolver?idPrestamo=${libroPrestado.idPrestamo}">MARCAR DEVOLUCION</a></td>
					</li>
				</tr>
			</c:forEach>
		</ol>
		<tr>
			<td colspan="2">
				<c:if test="${lstDevolucionesMarcadas != null}">
					<a>GRABAR DEVOLUCIONES(${lstDevolucionesMarcadas.lenght} libros)</a>
    			</c:if>
    		</td>
		</tr>
	</table>
</body>
</html>