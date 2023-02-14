package servlets;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Actividad;
import beans.Alumno;
import beans.Impartidor;
import conex.ConexPoolDB;
import daos.ActividadesDao;
import daos.AlumnosDao;

public class ServletAvisos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ConexPoolDB db = new ConexPoolDB();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession ss = request.getSession();
		Impartidor imp = (Impartidor) ss.getAttribute("impartidor");
		if(request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			HashMap<Alumno, Date> mapAsistencias = ActividadesDao.mapaAsistenciaActividad(id); 

			ss.setAttribute("mapAsistencias", mapAsistencias);
			response.sendRedirect("impartidor.jsp?id="+id);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ss = request.getSession();
		Impartidor imp = (Impartidor) ss.getAttribute("impartidor");
		
		if(request.getParameter("avisar") != null) {
			HashMap<Alumno, Date> mapAsistencias = (HashMap<Alumno, Date>) ss.getAttribute("mapAsistencias");
			BufferedWriter bw =  new BufferedWriter(new FileWriter(getServletContext().getRealPath("avisos.txt")));
			String linea = request.getParameter("avisar") + " - " + mapAsistencias.get(AlumnosDao.getAlumno(request.getParameter("avisar"))) + " - " + request.getParameter("tipoAviso");
			
			bw.write(linea);
			bw.close();
			
			response.sendRedirect("impartidor.jsp?enviado=true");
			
		}
		else {
			ArrayList<Actividad> arrActividadesImp = ActividadesDao.actividadesImpartidor(imp);
			ss.setAttribute("arrActividadesImp", arrActividadesImp);
			request.getRequestDispatcher("impartidor.jsp").include(request, response);
		}
		
	}

}
