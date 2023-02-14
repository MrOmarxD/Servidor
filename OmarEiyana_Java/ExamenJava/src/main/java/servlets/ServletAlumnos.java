package servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.AlumnosDao;

/**
 * Servlet implementation class ServletAlumnos
 */
public class ServletAlumnos extends HttpServlet {
	private AlumnosDao alumnosDAO;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        alumnosDAO = new AlumnosDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Guardamos el alumno en una variable sesion alumno
		if(request.getSession().getAttribute("alumno") == null) {
        	request.getSession().setAttribute("alumno", alumnosDAO.lstAlumno());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
