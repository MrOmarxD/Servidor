package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Autor;
import beans.Libro;
import dao.GestorBD;

/**
 * Servlet implementation class ServletControladorAutores
 */
@WebServlet(name = "ServletControladorAutores", urlPatterns = {"/ServletControladorAutores"})
public class ServletControladorAutores extends HttpServlet {
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
        request.getSession().setAttribute("lstAutores", bd.lstAutores());
        String idAutor = (String) request.getAttribute("idautor");
        if(idAutor != null) {
        	LinkedHashMap<Integer, String> autores = bd.autores();
        	request.getSession().setAttribute("nombreAutorSeleccionado", autores.get(idAutor));
        	request.getSession().setAttribute("librosAutor", bd.lstLibrosAutor(idAutor));
        }
        request.getRequestDispatcher("autores.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Para evitar problemas con caracteres especiales
        request.setCharacterEncoding("UTF-8");
        if(request.getParameter("botAniadir") == null){
            doGet(request, response);
        }else{
            if(request.getParameter("nombre").equals("") || 
                    request.getParameter("fechanac").equals("") || 
                    request.getParameter("nacionalidad").equals("")){
                request.setAttribute("errorAniadir", "Hay que rellenar todos los datos");
            }else{
                //Recuperamos los datos del formulario y creamos un objeto de tipo autor.
                //Este objeto no tendrá ID hasta que se inserte en la base de datos
                String nombre = request.getParameter("nombre");
                Date fechanac = null;
				try {
					fechanac = new SimpleDateFormat("yyyy/MM/dd").parse(request.getParameter("fechanac"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
                String nacionalidad = request.getParameter("nacionalidad");
                
                Autor autor = new Autor(nombre, fechanac, nacionalidad);
                
                //Si el autor ya existe no se inserta en la base de datos
                boolean existe = bd.existeAutor(autor);
                if(existe){
                    request.setAttribute("errorAniadir", "El autor " 
                            + autor.getNombre() + " ya existe");
                }else{
                    int id = bd.aniadirAutor(autor);
                    
                    if(id == -1){
                        request.setAttribute("errorAniadir", "No se ha podido añadir el autor");
                    }
                }
            }
            request.getRequestDispatcher("autores.jsp").forward(request, response);
        }
    }

}
