package servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Cliente;
import dao.ClienteDAO;
import dao.KeysDAO;

/**
 * Servlet implementation class ServletRegistro
 */
public class ServletRegistro extends HttpServlet {
	private ClienteDAO bdCliente;
	private KeysDAO bdKeys;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bdCliente = new ClienteDAO();
        bdKeys = new KeysDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mensajeError = "";
		String mensaje = "";
		int maxRegistros = Integer.parseInt(this.getInitParameter("maxRegistros"));
		
		if (request.getParameter("registrarse") != null) {
			if (request.getParameter("usuario").equals("") || request.getParameter("pass").equals("") || request.getParameter("domicilio").equals("") || request.getParameter("zip").equals("") || request.getParameter("telefono").equals("") || request.getParameter("email").equals("")) {
				mensajeError = "Debes rellenar todos los campos";
				request.setAttribute("mensajeError", mensajeError);
				request.getRequestDispatcher("registro.jsp").forward(request, response);
			} else {
				Cliente cliente = new Cliente(bdKeys.siguienteId("clientes"), request.getParameter("usuario"), request.getParameter("password"), request.getParameter("domicilio"), Integer.parseInt(request.getParameter("zip")), Integer.parseInt(request.getParameter("telefono")), request.getParameter("email"));
				boolean existeCliente = bdCliente.buscaCliente(cliente.getNombre());
				
				if (existeCliente) {
					mensajeError = "No se pudo registrar usuario, este ya existe";
					request.setAttribute("mensajeError", mensajeError);
					request.getRequestDispatcher("registro.jsp").forward(request, response);
				} else {
					int cantRegistros = (int) request.getAttribute("cantRegistros");
					if(cantRegistros>=maxRegistros) {
						mensajeError = "No se pudo registrar usuario, ha llegado el cupo de registros.";
						request.setAttribute("mensajeError", mensajeError);
						request.getRequestDispatcher("registro.jsp").forward(request, response);
					}
					else {
						boolean exitoProceso = bdCliente.guardaCliente(cliente);
						if (exitoProceso) {
							cantRegistros++;
							mensaje = "Cliente registrado";
							request.setAttribute("mensaje", mensaje);
							request.getRequestDispatcher("login.jsp").forward(request, response);						
						}
					}
				}
			}
		}
	}

}
