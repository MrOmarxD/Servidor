package servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PedidoDAO;

/**
 * Servlet implementation class ServletAgregarLineaPedido
 */
@WebServlet("/ServletAgregarLineaPedido")
public class ServletAgregarLineaPedido extends HttpServlet {
	private PedidoDAO pedidoDAO;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        pedidoDAO = new PedidoDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("aniadir") != null) {
			
		}
		else {
			if (request.getParameter("botVerCesta") != null)
				request.getRequestDispatcher("listar_cesta.jsp").forward(request, response);
			if (request.getParameter("botHacerPedido") != null)
				request.getRequestDispatcher("pedir.jsp").forward(request, response);
			if (request.getParameter("botMisPedidos") != null)
				request.getRequestDispatcher("ServletListarPedidos").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("aniadir") != null) {
			
		}
		else {
			if (request.getParameter("botVerCesta") != null)
				request.getRequestDispatcher("listar_cesta.jsp").forward(request, response);
			if (request.getParameter("botHacerPedido") != null)
				request.getRequestDispatcher("pedir.jsp").forward(request, response);
			if (request.getParameter("botMisPedidos") != null)
				request.getRequestDispatcher("ServletListarPedidos").forward(request, response);
		}
	}

}
