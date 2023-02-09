package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Alumno;
import beans.Impartidor;
import daos.AlumnosDao;
import daos.ImpartidoresDao;

/**
 * Servlet implementation class ServletLogin
 */
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String botonAlumno=request.getParameter("btnLoginAlumno");
		String botonImpartidor=request.getParameter("btnLoginImpartidor");
		
		if(botonAlumno!=null && !botonAlumno.isBlank()) {
			
			Alumno alumno= loginAlumno(request.getParameter("usuario"), request.getParameter("password"));
			request.getSession().setAttribute("alumno", alumno);
			request.setAttribute("alumno", alumno);
			if(alumno!=null) {
				request.getRequestDispatcher("ServletInscripcion").include(request, response);
			}else {
				request.getRequestDispatcher("index.jsp").include(request, response);
			}
		}else if(botonImpartidor!=null && !botonImpartidor.isBlank()) {
			Impartidor impartidor= loginImpartidor(request.getParameter("usuario"), request.getParameter("password"));
			request.getSession().setAttribute("impartidor", impartidor);
			request.setAttribute("impartidor", impartidor);
			if(impartidor!=null) {
				request.getRequestDispatcher("ServletAvisos").include(request, response);
			}else {
				request.getRequestDispatcher("index.jsp").include(request, response);
			}
		}else {
			request.getSession().setAttribute("error", "El usuario o la contrase�a no son correctas, vuelva a intentarlo");
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
		
	}
	 
	

	/**
	 * M�todo loginAlumno, destinado al login de alumnos: recibe un dni y una clave y devuelve el Alumno correspondiente a dicha 
	 * combinaci�n. Los alumnos se loguean usando como usuario su dni y como password las 3 primeras letras de sus apellidos concatenadas 
	 * min�scula (Marta G�mez Aguirre tiene la clave �gomagu�)
	 * @param dni
	 * @param password
	 * @return
	 */
	
	private Alumno loginAlumno(String dni, String password) throws ServletException {

		String passwordAlumno;
		AlumnosDao ad = new AlumnosDao();
		Alumno alumno= ad.getAlumno(dni);
		if(alumno!=null) {
			String[] apellidos=alumno.getApellidos().toLowerCase().split(" ");
			passwordAlumno=apellidos[0].substring(0,3)+apellidos[1].substring(0,3);
		
			if(passwordAlumno.compareTo(password)==0) {
				return alumno;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
	

	/**
	 * M�todo loginImpartidor, destinado al login de profesores. Los impartidores se loguean tomando como usuario la clave primaria de la tabla 
	 * impartidores, y como contrase�a �damocles". Devuelve el impartidor correspondiente a dicho id.
	 * 
	 * @param usuario
	 * @param password
	 * @return
	 */
	
	
	private Impartidor loginImpartidor(String usuario, String password) throws ServletException {

		String passwordImpartidor="damocles";
		ImpartidoresDao id = new ImpartidoresDao();
		Impartidor impartidor=id.getImpartidor(usuario);
		if(impartidor!=null) {
		
			if(passwordImpartidor.compareTo(password)==0) {
				return impartidor;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
	

}
