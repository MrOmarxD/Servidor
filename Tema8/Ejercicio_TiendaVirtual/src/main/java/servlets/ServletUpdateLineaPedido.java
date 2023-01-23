package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CarroCompra;
import beans.LineaPedido;

/**
 * Servlet implementation class ServletUpdateLineaPedido
 */
public class ServletUpdateLineaPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CarroCompra carroCompra = (CarroCompra) session.getAttribute("carroCompra");
		String accion = (String) session.getAttribute("accion");
		int idItemTratado = Integer.parseInt((String) session.getAttribute("itemTratado"));
		
		if (accion.equals("borrar")) {
			carroCompra.borraLinea(idItemTratado);
		} else {
			int cantidadNueva = Integer.parseInt((String) session.getAttribute("cantidadNueva"));
			LineaPedido lineaPedido = carroCompra.getLineaPedido(idItemTratado);
			lineaPedido.setCantidad(cantidadNueva);
		}
		request.getRequestDispatcher("listar_cesta.jsp").forward(request, response);
	}

}
