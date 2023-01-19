package servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Cliente;
import dao.ClienteDAO;
import dao.PedidoDAO;

/**
 * Servlet implementation class ServletLogin
 */
public class ServletLogin extends HttpServlet {
	private ClienteDAO clienteDAO;
	private PedidoDAO pedidoDAO;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        clienteDAO = new ClienteDAO();
        pedidoDAO = new PedidoDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Mira si existe session, sino es asi devuelve null
		HttpSession session = request.getSession(false);
		String mensajeError = "";
		// En caso de existir una session la invalida y crea una nueva
		if (session != null)
			session.invalidate();
		session = request.getSession(true);
		
		if (request.getParameter("login") != null) {
			String usuario = request.getParameter("usuario");
			String password = request.getParameter("password");
			
			if (usuario.equals("") || password.equals("")) {
				mensajeError = "Debes rellenar los campos de usuario y contraseña";
				request.setAttribute("mensajeError", mensajeError);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			else {
				Cliente cliente = clienteDAO.buscaCliente(usuario, password);
				
				if (cliente == null) {
					mensajeError = "No se encuentra en la Base de Datos";
					request.setAttribute("mensajeError", mensajeError);
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} else {
					request.getSession().setAttribute("cliente", cliente);
					request.getSession().setAttribute("lstItems", pedidoDAO.todosItems());
					request.getRequestDispatcher("tienda.jsp").forward(request, response);
				}
				
			}			
		}	
	}
}
