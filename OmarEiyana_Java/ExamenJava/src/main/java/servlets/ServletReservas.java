package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Reserva;
import daos.CharlasDao;

/**
 * Servlet implementation class ServletReservas
 */
public class ServletReservas extends HttpServlet {
	private CharlasDao bdCharlas;
    
	 @Override
  public void init(ServletConfig config) throws ServletException {
      super.init(config);
      bdCharlas = new CharlasDao();
  }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// carga un array con los charlas de la BBDD si no existe
		if(request.getSession().getAttribute("lstCharlas") == null) {
			request.getSession().setAttribute("lstCharlas", bdCharlas.obtenerCharlas());
		}
		// carga un array con las reservas de una charla determinada
		if(request.getAttribute("idCharla") != null) {
			HashMap<String, ArrayList<Reserva>> mapaCharlasReserva = bdCharlas.mapaCharlasReserva();
			request.getSession().setAttribute("lstReservas", mapaCharlasReserva.get(request.getAttribute("idCharla")));
		}
        request.getRequestDispatcher("reservas.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
