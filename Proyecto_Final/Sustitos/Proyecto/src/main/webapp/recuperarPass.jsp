<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!-- Añadimos la cabecera -->
<c:import url="cabecera.jsp"/>	
<section class=" gradient-custom">
  <div class="container py-5 h-95">
    <div class="row justify-content-center align-items-center h-100">
      <div class="col-12 col-lg-9 col-xl-7">
        <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
          <div class="card-body p-4 p-md-5">
            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Recuperar Contraseña</h3> 
            
            <form action="ServletRecuperarPass" method="post">

              <div class="row">
                <div class="col-md-6 mb-4">

                  <div class="form-outline">
                  	<label class="form-label" for="nombre">Email *</label>
                    <input type="email" id="nombre" name="email" class="form-control form-control-lg" required />
                    
                  </div>

                </div>
                <div class="col-md-6 mb-4">

                  <div class="form-outline">
                  	<label class="form-label" for="ciudad">Ultima contraseña *</label>
                    <input type="password" id="ciudad" name="contrasena" class="form-control form-control-lg" required/>
                  </div>

                </div>
              </div>
               <c:if test="${mensaje != null}">
					<label class="form-label" style="color: green">${mensaje}</label>
				</c:if>

              <div class="mt-4 pt-2">
              	<button class="btn btn-danger btn-lg" type="submit" name="registrarse">Recuperar</button>
              </div>

            </form> 
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
<%session.invalidate();%>
<!-- Añadimos el footer -->
<c:import url="footer.jsp"/>







