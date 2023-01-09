package servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GestorBD;

/**
 * Servlet implementation class ServletControladorAutores
 */
@WebServlet(name = "ServletControladorAutores", urlPatterns = {"/ServletControladorAutores"})
public class ServletControladorAutores extends HttpServlet {
	 private GestorBD bd;
	    
	    @Override
	    public void init(ServletConfig config) throws ServletException {
	        super.init(config);
	        bd = new GestorBD();
	    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("libros", bd.libros());
        request.getSession().setAttribute("autores", bd.lstAutores());
        request.getRequestDispatcher("autores.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
