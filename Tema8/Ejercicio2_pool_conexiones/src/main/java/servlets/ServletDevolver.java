package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		// Si marca una prestamo para delvolver, este lo añade a la session de lstDevoluciones
		if(request.getParameter("marcar")!=null){
			int idPrestamo = Integer.parseInt(request.getParameter("marcar"));
            ArrayList<Integer> lstlibrosprestados;
            if(request.getSession().getAttribute("lstLibrosPrestados")==null)
            	lstlibrosprestados = new ArrayList<Integer>();
            else
            	lstlibrosprestados = (ArrayList<Integer>)request.getSession().getAttribute("lstLibrosPrestados");
            if(!lstlibrosprestados.contains(idPrestamo))
            	lstlibrosprestados.add(idPrestamo);
            request.getSession().setAttribute("lstLibrosPrestados", lstlibrosprestados);
        }
		
		// Si selecciona un prestamo para revertir la devolucion, este 
		if(request.getParameter("revertir")!=null){
			int idPrestamo = Integer.parseInt(request.getParameter("revertir"));
            ArrayList<Integer> devoluciones;
            if(request.getSession().getAttribute("lstDevolucionesMarcadas")==null)
                devoluciones=new ArrayList<Integer>();
           else
                devoluciones=(ArrayList<Integer>)request.getSession().getAttribute("lstDevolucionesMarcadas");
            devoluciones.remove(Integer.valueOf(idPrestamo));
            request.getSession().setAttribute("lstDevolucionesMarcadas", devoluciones);
        }
		
		// Si decide grabar las devoluciones
        if(request.getParameter("grabar")!=null){
            ArrayList<Integer> devoluciones;
            if(request.getSession().getAttribute("lstDevolucionesMarcadas")==null)
                devoluciones = new ArrayList<Integer>();
            else
                devoluciones=(ArrayList<Integer>)request.getSession().getAttribute("lstDevolucionesMarcadas");
            bd.devolver(devoluciones);
            request.getSession().removeAttribute("lstDevolucionesMarcadas");
            request.getSession().removeAttribute("lstLibrosPrestados");
        }
		
		// Si no existe la lista de libros prestados la crea con el metodo listarLibrosPrestados del gestor de bd
		if(request.getSession().getAttribute("lstLibrosPrestados")==null)
			request.getSession().setAttribute("lstLibrosPrestados", bd.listarLibrosPrestados());
		
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
