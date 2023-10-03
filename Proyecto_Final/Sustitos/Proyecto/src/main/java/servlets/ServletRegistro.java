package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Usuario;
import dao.UsuarioDAO;

/**
 * Servlet implementation class ServletRegistro
 */
@WebServlet("/ServletRegistro")
public class ServletRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDAO udao = new UsuarioDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegistro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("registrarse") != null) {
//			Map<String, String> params = new HashMap<String, String>();
//			params.put("nombre", request.getParameter("nombre")); 
//			params.put("ciudad", request.getParameter("ciudad")); 
//			params.put("apellidos", request.getParameter("apellidos")); 
//			params.put("pais", request.getParameter("pais")); 
//			params.put("descripcion", request.getParameter("descripcion")); 
//			params.put("email", request.getParameter("email")); 
//			params.put("direccion", request.getParameter("direccion")); 
//			params.put("password", request.getParameter("password")); 
//			params.put("cp", request.getParameter("cp"));  
//			params.put("telefono", request.getParameter("telefono"));  
//			params.put("municipio", request.getParameter("municipio"));  
//			params.put("imagen", request.getParameter("imagen"));   
//			// Validamos de que nada sea nulo
//			for (Map.Entry<String, String> entry : params.entrySet()) {
//		        System.out.println(entry.getKey() + ":" + entry.getValue());
//		        if (entry.getValue() == null) {
//		        	params.put(entry.getKey(), "");
//				}
//		    } 
			
			
			Usuario u = new Usuario(request.getParameter("nombre"), request.getParameter("apellidos"), request.getParameter("password"),
					request.getParameter("imagen"), request.getParameter("descripcion"), request.getParameter("direccion"), Integer.parseInt(request.getParameter("cp")), request.getParameter("municipio"), request.getParameter("provincia"), request.getParameter("pais") , Integer.parseInt(request.getParameter("telefono")), request.getParameter("email"), 0, "AAAA");
			boolean todoBn = udao.insertarUsuario(u);
			if (todoBn == true) {
				request.getSession().setAttribute("mensaje", "Ingresa la cadena de verificación que te ha llegado en el email.");
				request.getSession().setAttribute("strValidar", "AAAA");
				response.sendRedirect("login.jsp");  
			}else {
				request.getSession().setAttribute("mensaje", "ERROR: Contacte con el servicio tecnico.");
				response.sendRedirect("login.jsp");  
			}
		}
	}

}
