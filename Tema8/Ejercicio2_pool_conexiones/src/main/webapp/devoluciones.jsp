<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Prestamos y Devoluciones</title>
</head>
<body>
	<c:if test="${lstLibrosPrestados == null}">
		<jsp:forward page="ServletDevolver"/>
    </c:if>
	<table>
		<ol>
			<c:forEach items="${lstLibrosPrestados}" var="libroPrestado">
				<tr>
					<li>
						<td> ${libroPrestado.titulo}, ${libroPrestado.fecha} dias prestado</td>
						<td>
							<c:choose>
                        		<c:when test="${lstDevolucionesMarcadas.contains(libroPrestado.id)}">
                           			<a href="ServletDevolver?revertir=${libroPrestado.id}" style="color:red;">REVERTIR DEVOLUCION</a>
                        		</c:when>
                        		<c:otherwise>
                            		<a href="ServletDevolver?marcar=${libroPrestado.id}">MARCAR DEVOLUCION</a>
                        		</c:otherwise>
                    		</c:choose>
						</td>
					</li>
				</tr>
			</c:forEach>
		</ol>
		<tr>
			<td colspan="2">
				<c:if test="${lstDevolucionesMarcadas.size()>0}">
		            <a href="ServletDevolver?grabar">GRABAR DEVOLUCIONES</a> (${lstDevolucionesMarcadas.size()} libros)
	        	</c:if>
    		</td>
		</tr>
	</table>
</body>
</html>