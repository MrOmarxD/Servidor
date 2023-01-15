<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Ilegales</h1>
	<form action="${pageContext.request.contextPath}/ServletIlegales">
		<input type='text' name='nombre'>
		<input type='submit' name='add' value='enviar'>
	</form>
	<ul>
		<c:forEach items="${usuarios}" var="nombre" varStatus="index">
		<li>${nombre} <a href='${pageContext.request.contextPath}/ServletIlegales?eliminar=${index.index}'>X</a></li>
		</c:forEach>
	</ul>
</body>
</html>