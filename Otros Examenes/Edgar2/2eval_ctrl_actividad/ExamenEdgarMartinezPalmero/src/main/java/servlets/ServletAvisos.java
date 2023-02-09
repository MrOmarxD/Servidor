package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Actividad;
import beans.Alumno;
import beans.Impartidor;
import daos.ActividadesDao;
import daos.ImpartidoresDao;

/**
 * Servlet implementation class ServletAvisos
 */
public class ServletAvisos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAvisos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getAttribute("asistencia") != null) {
			ActividadesDao ad = new ActividadesDao();
			HashSet<Alumno> alumnos = ad.mapaAsistenciaActividad((String) request.getAttribute("asistencia"));
			request.getSession().setAttribute("alumnos", alumnos);
		}
		
		request.getRequestDispatcher("impartidor.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ActividadesDao ad = new ActividadesDao();
		Impartidor impartidor = (Impartidor) request.getSession().getAttribute("impartidor");
		ArrayList<Actividad> actividades = (ArrayList<Actividad>) ad.actividadesImpartidor(impartidor);
		request.getSession().setAttribute("actividadesImpartidor", actividades);
		request.getRequestDispatcher("impartidor.jsp").forward(request, response);
	}

}
