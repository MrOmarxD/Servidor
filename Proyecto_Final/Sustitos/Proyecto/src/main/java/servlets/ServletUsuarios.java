package servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsuarioDAO;

/**
 * Servlet implementation class servletUsuarios
 */
public class ServletUsuarios extends HttpServlet {
	private UsuarioDAO bdUsuario;
       
	 @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bdUsuario = new UsuarioDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("lstUsuValidado") == null) {
			request.getSession().setAttribute("lstUsuValidado", bdUsuario.lstUsuariosValidados());
			request.getSession().setAttribute("lstUsuNoValidado", bdUsuario.lstUsuariosNoValidados());
		}
        request.getRequestDispatcher("listadoUsuarios.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
