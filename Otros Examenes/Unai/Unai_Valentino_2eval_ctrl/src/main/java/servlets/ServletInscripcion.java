package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Actividad;
import beans.Alumno;
import conex.ConexPoolDB;
import daos.ActividadesDao;
import daos.ParticipaDao;

/**
 * Servlet implementation class ServletInscripcion
 */
public class ServletInscripcion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ConexPoolDB db = new ConexPoolDB();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession ss = request.getSession();
		
		if(request.getParameter("apuntar") != null) {
			ArrayList<Actividad> arrInscripciones = new ArrayList<Actividad>();
			if(ss.getAttribute("arrInscripciones") != null) {
				arrInscripciones = (ArrayList<Actividad>) ss.getAttribute("arrInscripciones");
			}
			arrInscripciones.add(ActividadesDao.obtenerActividad(Integer.parseInt(request.getParameter("id"))));
			ss.setAttribute("arrInscripciones", arrInscripciones);
			request.getRequestDispatcher("alumno.jsp").include(request, response);
		}
		else if (request.getParameter("anular") != null) {
			ArrayList<Actividad> arrInscripciones = new ArrayList<Actividad>();
			if(ss.getAttribute("arrInscripciones") != null) {
				arrInscripciones = (ArrayList<Actividad>) ss.getAttribute("arrInscripciones");
			}
			arrInscripciones.remove(ActividadesDao.obtenerActividad(Integer.parseInt(request.getParameter("id"))));
			ss.setAttribute("arrInscripciones", arrInscripciones);
			request.getRequestDispatcher("alumno.jsp").include(request, response);
		}
		else if (request.getParameter("guardar") != null) {
			Alumno alu = (Alumno) ss.getAttribute("alumno");
			ArrayList<Actividad> arrInscripciones = (ArrayList<Actividad>) ss.getAttribute("arrInscripciones");
			for(Actividad acti: arrInscripciones) {
				ParticipaDao.inscribir(alu, acti);
			}
			ss.setAttribute("arrInscripciones", null);
			doPost(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ss = request.getSession();
		Alumno alu = (Alumno) ss.getAttribute("alumno");
		
		ArrayList<Actividad> arrActividades = ActividadesDao.obtenerActividadesParticipa(alu);
		ss.setAttribute("arrActividades", arrActividades);
		ArrayList<Actividad> arrActividadesLibres = ActividadesDao.obtenerActividadesLibresNoParticipa();
		for(int i=0;i<arrActividades.size();i++) {
			if(arrActividadesLibres.contains(arrActividades.get(i))) {
				arrActividadesLibres.remove(arrActividades.get(i));
				i--;
			}
			
		}
		ss.setAttribute("arrActividadesLibres", arrActividadesLibres);
		
		request.getRequestDispatcher("alumno.jsp").include(request, response);
	}

}
