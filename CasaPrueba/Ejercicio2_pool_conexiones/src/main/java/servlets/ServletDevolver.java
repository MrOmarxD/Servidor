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

import beans.LibroPrestamo;
import dao.GestorBD;

/**
 * Servlet implementation class ServletDevolver
 */
@WebServlet(name = "ServletDevolver", urlPatterns = {"/ServletDevolver"})
public class ServletDevolver extends HttpServlet {
	private GestorBD bd;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bd = new GestorBD();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Si no existe la lista de libros prestados la crea con el metodo listarLibrosPrestados del gestor de bd
        if(request.getSession().getAttribute("lstLibrosPrestados")==null) {
        	request.getSession().setAttribute("lstLibrosPrestados", bd.listarLibrosPrestados());
        }
        
        // Si no existe la lista de libros que queremos devolver la crea
        ArrayList<LibroPrestamo> devoluciones;
        if(request.getSession().getAttribute("lstDevolucionesMarcadas")==null)
            devoluciones = new ArrayList<LibroPrestamo>();
        else
            devoluciones = (ArrayList<LibroPrestamo>)request.getSession().getAttribute("lstDevolucionesMarcadas");
		
		// Si marca un prestamo para delvolver, este lo añade a la session de lstDevolucionesMarcadas
		if(request.getParameter("marcar")!=null){
			LibroPrestamo libroPrestamo = bd.buscarLibroPrestamo(Integer.parseInt(request.getParameter("marcar")));
			
            if(!devoluciones.contains(libroPrestamo))
            	devoluciones.add(libroPrestamo);
            request.getSession().setAttribute("lstDevolucionesMarcadas", devoluciones);
		}
		
		// Si selecciona un prestamo para revertir la devolucion, este 
		if(request.getParameter("revertir")!=null){
			LibroPrestamo libroPrestamo = bd.buscarLibroPrestamo(Integer.parseInt(request.getParameter("revertir")));
			
            if(devoluciones.contains(libroPrestamo))
            	devoluciones.remove(libroPrestamo);
            request.getSession().setAttribute("lstDevolucionesMarcadas", devoluciones);
		}
		
		// Si decide grabar las devoluciones
        if(request.getParameter("grabar")!=null){
            bd.devolver(devoluciones);
            request.getSession().removeAttribute("lstDevolucionesMarcadas");
            request.getSession().setAttribute("lstLibrosPrestados", bd.listarLibrosPrestados());
        }
     		
		// Te redirige a devoluciones.jsp
        request.getRequestDispatcher("devoluciones.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
