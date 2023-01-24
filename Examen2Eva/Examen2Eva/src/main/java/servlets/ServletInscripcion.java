package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Actividad;
import beans.Alumno;
import daos.ActividadesDao;
import daos.ParticiparDao;

/**
 * Servlet implementation class ServletInscripcion
 */
public class ServletInscripcion extends HttpServlet {
	private ActividadesDao actividadesDao;
	private ParticiparDao participarDao;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        actividadesDao = new ActividadesDao();
        participarDao = new ParticiparDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Guardamos el alumno en una variable sesion alumno
		if(request.getAttribute("alumno")==null) {
        	request.getSession().setAttribute("alumno", request.getAttribute("alumno"));
		}
		
		// Guardamos en un arraylist las actividades del alumno
		request.getSession().setAttribute("lstActividadesParticipa", actividadesDao.obtenerActividadesParticipa((Alumno) request.getSession().getAttribute("alumno")));
		
		// Guardamos en un arraylist las actividades en las que no participa el alumno
		request.getSession().setAttribute("lstActividadesNoParticipa", actividadesDao.obtenerActividadesLibresNoParticipa((Alumno) request.getSession().getAttribute("alumno")));
		
		// Si no existe la lista de actividades que queremos participar la crea
        ArrayList<Actividad> lstApuntarActividad;
        if(request.getSession().getAttribute("lstApuntarActividad")==null)
        	lstApuntarActividad = new ArrayList<Actividad>();
        else
        	lstApuntarActividad = (ArrayList<Actividad>)request.getSession().getAttribute("lstApuntarActividad");
		
		// Si marca una actividad para apuntarse, este lo añade a la session de lstApuntarActividad
		if(request.getParameter("apuntarse")!=null){
			Actividad actividad = actividadesDao.getActividad(Integer.parseInt(request.getParameter("apuntarse")));
			
            if(!lstApuntarActividad.contains(actividad))
            	lstApuntarActividad.add(actividad);
            request.getSession().setAttribute("lstApuntarActividad", lstApuntarActividad);
		}
		
		// Si selecciona una actividad para anular la participacion, este 
		if(request.getParameter("anular")!=null){
			Actividad actividad = actividadesDao.getActividad(Integer.parseInt(request.getParameter("anular")));
			
            if(lstApuntarActividad.contains(actividad))
            	lstApuntarActividad.remove(actividad);
            request.getSession().setAttribute("lstApuntarActividad", lstApuntarActividad);
		}
		
		// Si decide grabar las participaciones
        if(request.getParameter("grabar")!=null){
        	participarDao.inscribir(lstApuntarActividad, (String) request.getSession().getAttribute("alumno"));
            request.getSession().removeAttribute("lstApuntarActividad");
            request.getSession().setAttribute("lstActividadesParticipa", actividadesDao.obtenerActividadesParticipa((Alumno) request.getSession().getAttribute("alumno")));        }
        
		// Redirige a alumnos.jsp
		request.getRequestDispatcher("alumno.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
