<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%! int i; %>
<!-- A�adimos la cabecera -->
<c:import url="cabecera.jsp"/> 
<div class="container py-5 h-100">
    <div class="row justify-content-center align-items-center h-100">
     <div class="col-12 col-lg-9 col-xl-7">

	<form action="ServletLogin" method="post">
	  <!-- Email input -->
	  <div class="form-outline mb-4">
	  	<label class="form-label" for="email">Email</label>
	    <input type="email" id="email" name="email" class="form-control"/>
	  </div>
	
	  <!-- Password input -->
	  <div class="form-outline mb-4">
	  	<label class="form-label" for="password">Password</label>
	    <input type="password" id="password" class="form-control" name="password"/>
	  </div>
		<c:if test="${mensaje != null}">
			<label class="form-label" style="color: green">${mensaje}</label>
		</c:if>
	
	   <div class="col">
	     <!-- Simple link -->
		<a href="recuperarPass.jsp">�Olvidaste tu contrase�a?</a>
		<a href="registro.jsp">�No tienes cuenta? Registrate</a>
	   </div>
	  
	  <!-- Submit button -->
	  <button type="submit" class="btn btn-danger btn-block mb-4" name="login">Entrar</button>
	  <c:if test="${mensajeError != null}">
	  		<div>
			  <c:out value = "${mensajeError}"/>
			</div> 
	  </c:if>
	 
	</form>
	<c:if test="${strValidar != null}"> 
		<form action="ServletLogin" method="post">
		  <div class="form-outline mb-4">
		  	<label class="form-label" for="nombre">Email a validar</label>
		    <input type="email" id="nombre" name="email" class="form-control"/>
		  </div>
			<div class="form-outline mb-4">
			  	<label class="form-label" for="cadena">Cadena</label>
			    <input type="text" id="cadena" class="form-control" name="cadena"/>
			</div>
			<button type="submit" class="btn btn-danger btn-block mb-4" name="validar">VALIDAR</button>
		</form>
	</c:if>
  </div>
 </div>
</div>
<%session.invalidate();%>
<!-- A�adimos el footer -->
<c:import url="footer.jsp"/>
