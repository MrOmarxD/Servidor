<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- Añadimos la cabecera -->
<c:import url="cabecera.jsp"/>	
<section class=" gradient-custom">
  <div class="container py-5 h-95">
    <div class="row justify-content-center align-items-center h-100">
      <div class="col-12 col-lg-9 col-xl-7">
        <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
          <div class="card-body p-4 p-md-5"> 
             <div class="card mb-3">
			  <img src="https://i.imgur.com/WUGxiYh.jpeg" class="card-img-top" alt="userIMG">
			  <div class="card-body">
			    <h5 class="card-title" style="text-transform: capitalize">${fn:toLowerCase(usuario.nombre)}</h5>
			    <p class="card-text">Este es tu perfil de usuario para consultar información privada pueda pulsar el siguiente boton.</p>
			    <!-- Button trigger modal -->
			    <form action="ServletPerfil" method="post">
				<button type="submit" name="mostrar" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
				  Mostrar
				</button>
				</form>
				<!-- Modal -->
				<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        ...
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
				        <button type="button" class="btn btn-primary">Save changes</button>
				      </div>
				    </div>
				  </div>
				</div>
			    <p class="card-text"><small class="text-muted">Ultima actualización hace 3 mins</small></p>
			  </div>
			</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section> 
<!-- Añadimos el footer -->
<c:import url="footer.jsp"/>







