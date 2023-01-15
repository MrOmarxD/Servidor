package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Catalogo;

@WebServlet(name="Listalibros", urlPatterns = {"/Listalibros", "/Listalibros/*" })
public class Listalibros extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// Obtiene la variable sesión.
		HttpSession sesion = request.getSession();
		String libro_select = request.getParameter("libro_select");
		String limpiarLista = request.getParameter("limpiarLista");
		String agregar = request.getParameter("agregar");
		String error = "";
		ArrayList<String> librosAgregados = new ArrayList<String>();
		// Limpia la session
		if(limpiarLista!=null)
			sesion.invalidate();
		else {
			if(sesion.getAttribute("librosAgregados")!=null)
				librosAgregados = (ArrayList<String>) sesion.getAttribute("librosAgregados");
		}
		// En caso de pulsar agregar libro
		if(agregar!=null){
			if(librosAgregados.size()>0){
				if(!librosAgregados.contains(libro_select)) {
					librosAgregados.add(libro_select);
					sesion.setAttribute("librosAgregados", librosAgregados);
				}
				else {
					error = "Error ya has elegido "+libro_select;
				}
			}
			else {
				librosAgregados.add(libro_select);
				sesion.setAttribute("librosAgregados", librosAgregados);
			}
		}
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Ejercicio 0 - Sessiones servlet</title>");        
			out.println("</head>");
			out.println("<body>");
			out.println("<form method='get' action='Listalibros'>");
				out.println("<select name='libro_select'>");
				Catalogo catalogos = new Catalogo();
				String[] lista_libros = catalogos.getLISTA_LIBROS();
				for(String libro : lista_libros) {
					String src = "";
					if(libro_select.equals(libro) )
						src = "selected";
					out.println("<option value='"+libro+"' "+src+">"+libro+"</option>");
				}
				out.println("</select>");
				out.println("<p style='color: red;'>"+error+"</p>");
			    out.println("<input type='submit' name='agregar' value='AGREGAR'>");
			    out.println("<input type='submit' name='limpiarLista' value='Limpiar Lista'>");
			 out.println("</form>");
			 //Comprobamos que la session tiene librosAgregados o no
			 if(librosAgregados.size()<=0)
				 out.println("<p>No se han elegido libros</p>");
			 else {
				 out.println("<p>TU ELECCION:</p>");
				 out.println("<ul>");
				 for(String libro : librosAgregados) {
					 out.println("<li>"+libro+"</li>");
				 }
				 out.println("</ul>");
			 }
			 out.println("</body>");
			 out.println("</html>");
		 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
