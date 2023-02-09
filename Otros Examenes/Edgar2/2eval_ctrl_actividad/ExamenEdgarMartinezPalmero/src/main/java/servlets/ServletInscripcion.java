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
import beans.ActividadesInscritas;
import beans.ActividadesNoInscritas;
import beans.Alumno;
import daos.ActividadesDao;
import daos.ParticiparDao;

/**
 * Servlet implementation class ServletInscripcion
 */
public class ServletInscripcion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInscripcion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Si se guarda una nueva inscripción se entra aquí:
		if (request.getParameter("guardar") != null) {
			System.out.print("guardar");
			HashSet<Integer> nuevasInscripciones = new HashSet();
//			Se intenta obtener el hashset con las nuevas inscripciones
			if (request.getSession().getAttribute("nuevasInscripciones") != null) {
//				nuevasInscripciones = (ArrayList<Integer>) request.getSession().getAttribute("nuevasInscripciones");
				nuevasInscripciones = (HashSet<Integer>) request.getSession().getAttribute("nuevasInscripciones");
			}
//			Se agrega la nueva inscripcion al hashset y se guarda en sesión
			nuevasInscripciones.add(Integer.parseInt(request.getParameter("guardar")));
			request.getSession().setAttribute("nuevasInscripciones", nuevasInscripciones);
			response.sendRedirect("alumno.jsp");
		} else {
//			Si se borra una nueva inscripción se entra aquí:
			if (request.getParameter("borrar") != null) {
//				Si esta guardado en sesión el hashset con las inscripciones se entra aquí, sino no hace nada
				if (request.getSession().getAttribute("nuevasInscripciones") != null) {
					HashSet<Integer> nuevasInscripciones = (HashSet<Integer>) request.getSession().getAttribute("nuevasInscripciones");
					Integer idBorrar = Integer.parseInt(request.getParameter("borrar"));
					nuevasInscripciones.remove(idBorrar);
					request.getSession().setAttribute("nuevasInscripciones", nuevasInscripciones);
				}
				response.sendRedirect("alumno.jsp");
			} else {
//				Si se pulsa el boton para guardar las nuevas inscripciones entraremos aquí
				if (request.getParameter("guardarEnBBDD") != null) {
					HashSet<Integer> nuevasInscripciones = (HashSet<Integer>) request.getSession().getAttribute("nuevasInscripciones");
					ParticiparDao pd = new ParticiparDao();
					Alumno alumno = (Alumno) request.getSession().getAttribute("alumno");
					pd.guardarActividades(nuevasInscripciones, alumno);
					request.getSession().removeAttribute("nuevasInscripciones");
					guardarActividadesEnSesion(request);
					response.sendRedirect("alumno.jsp");
				} else {
					if (request.getSession().getAttribute("alumno") != null) {
						guardarActividadesEnSesion(request);
						request.getRequestDispatcher("alumno.jsp").forward(request, response);
					} else
						request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}
		}					
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void guardarActividadesEnSesion(HttpServletRequest request) throws ServletException {
		Alumno alumno = (Alumno) request.getSession().getAttribute("alumno");
		ActividadesDao ad = new ActividadesDao();
		ArrayList<ActividadesInscritas> actividadesInscrito = ad.actividadesAlumnoInscrito(alumno); 
		ArrayList<ActividadesNoInscritas> actividadesNoInscrito = ad.actividadesAlumnoNoInscrito(alumno); 
		
		request.getSession().setAttribute("actividadesInscrito", actividadesInscrito);
		request.getSession().setAttribute("actividadesNoInscrito", actividadesNoInscrito);
	}
	

}
