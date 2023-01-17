package servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletPrepararProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ServletPrepararProductos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session= request.getSession(false);
		//Crear sesion
		if(request.getSession()!=null && request.getSession().getAttribute("mapaProductos")==null) {
			session= request.getSession(true);
			HashMap<String, Integer> leerFichero=leerFichero();
			
			if(request.getParameter("categoria")!=null) {
				try {
					Integer categoria = Integer.parseInt(request.getParameter("categoria"));
					session.setAttribute("mapaProductos", guardarProductos(categoria));	
				}catch(NumberFormatException e) {
					session.setAttribute("mapaProductos", leerFichero);	
				}
			}else {
				session.setAttribute("mapaProductos", leerFichero);
			}
			
		}else {
			session.setAttribute("mapaProductos", leerFichero());
		}		
		response.sendRedirect(request.getContextPath()+"/compra.jsp");

	}
	
	
	private HashMap<String, Integer> guardarProductos(Integer categoria) throws IOException{
		HashMap<String, Integer> productos=leerFichero();
		HashMap<String, Integer> mapa=new HashMap<String, Integer>();
		
		for(String i:productos.keySet()) {
			if(productos.get(i)==categoria) {
				mapa.put(i, productos.get(i));
			}
		}	
		return mapa;
	}
	
	private HashMap<String, Integer> leerFichero() throws IOException{
		HashMap<String, Integer> mapa=new HashMap<String, Integer>();
		BufferedReader br=new BufferedReader(new FileReader(this.getServletContext().getRealPath(getInitParameter("nomFich"))));
		String linea=br.readLine();
		while(linea!=null) {
			String[] partes=linea.split(";");	
			mapa.put(partes[1],Integer.parseInt(partes[0]));
			linea=br.readLine();
		}	
		br.close();
		
		return mapa;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
