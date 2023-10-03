<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sustín</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/12417bf0f7.js" crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Sustitos</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
      	<c:choose>
       		<c:when test="${usuario != null}">
	       		<li class="nav-item">
	          		<a class="nav-link active" aria-current="page" href="perfil.jsp">${usuario.nombre}</a>
	        	</li>
	        	<c:if test="${!esAdmin.equals('si')}">
		        	<li class="nav-item">
	          			<a class="nav-link" href="listadoCesta.jsp">Carrito</a>
	        		</li>
	        	</c:if>
		        <li class="nav-item">
		          <a class="nav-link" href="ServletLogin?cerrar">Cerrar Sesion</a>
		        </li>
       		</c:when>
       		<c:otherwise>
       			<li class="nav-item">
	          		<a class="nav-link " aria-current="page" href="login.jsp">Iniciar Sesion</a>
	        	</li>
	        	<li class="nav-item">
	          		<a class="nav-link " aria-current="page" href="registro.jsp">Crear Cuenta</a>
	        	</li>
	        </c:otherwise>
       	</c:choose>
       	<c:if test="${esAdmin.equals('si')}">
	       	<li class="nav-item dropdown">
	          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	            Administrar
	          </a>
	          <ul class="dropdown-menu">
	            <li><a class="dropdown-item" href="listadoProveedores.jsp">Proveedores</a></li>
	            <li><a class="dropdown-item" href="listadoUsuarios.jsp">Usuarios</a></li>
	            <li><hr class="dropdown-divider"></li>
	            <li><a class="dropdown-item" href="listadoProductosAdmin.jsp">Productos</a></li>
	          </ul>
	        </li>
       	</c:if>
      </ul>
      <form class="d-flex">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-danger" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>