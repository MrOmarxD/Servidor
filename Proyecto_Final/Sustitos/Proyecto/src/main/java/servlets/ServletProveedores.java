package servlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Proveedor;

/**
 * Servlet implementation class ProcesaApuesta
 */
public class ServletProveedores extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
        String fichero = this.getInitParameter("fichero");
        HashMap<String,Proveedor> lstProveedores = null;
        try {
            String ruta = this.getServletContext().getRealPath(fichero);
            File myObj = new File(ruta);
            Scanner myReader = new Scanner(myObj,"UTF-8");
            ArrayList<String> proveedores = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                proveedores.add(data);
            }
            myReader.close();
           
            lstProveedores = new HashMap<String,Proveedor>();
           
            for(String proveedor: proveedores){
            	String[] partes = proveedor.split(";");
                Proveedor p = new Proveedor();
                p.setNombre(partes[0]);
                p.setEncargado(partes[1]);
                p.setCategoria(partes[2]);
                p.setTelefono(Integer.parseInt(partes[3]));
                p.setEmail(partes[4]);
                lstProveedores.put(p.getNombre(),p);
            }
            this.getServletContext().setAttribute("proveedores", lstProveedores);
            session.setAttribute("proveedores", lstProveedores);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(request.getParameter("claveProveedor")!= null) {
        	request.setAttribute("proveedor", lstProveedores.get(request.getParameter("claveProveedor")));
        	session.setAttribute("proveedor", lstProveedores.get(request.getParameter("claveProveedor")));
        	request.getRequestDispatcher("detalleProveedor.jsp").forward(request, response);
        }
        request.getRequestDispatcher("listadoProveedores.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		//Al pulsar el boton volver nos redirige a listado de proveedores 
		if(request.getParameter("volver")!= null) {
			request.getRequestDispatcher("listadoProveedores.jsp").forward(request, response);
		}else {
		// Esto nos hara cuando se pulse el boton de guardar cambios
			
			Proveedor proveedorNuevo = (Proveedor) session.getAttribute("proveedor");
		
			//Si el encargado ha sido actualizado lo cambiamos
			if(!request.getParameter("encargado").equals("")) {
				System.out.println(request.getParameter("encargado"));
				String encargado=request.getParameter("encargado");
				proveedorNuevo.setEncargado(encargado);
			}
			
			// Si la categoria ha sido actualizada la cambiamos
			if(!request.getParameter("categoria").equals("")) {
				proveedorNuevo.setCategoria(request.getParameter("categoria"));
			}
			
			// Si el telefono ha sido actualizado lo cambiamos
			if(!request.getParameter("telefono").equals("")) {
				int telefono=Integer.valueOf(request.getParameter("telefono"));
				proveedorNuevo.setTelefono(telefono);
			}
			
			// Si el email ha sido actualizado lo cambiamos
			if(!request.getParameter("email").equals("")) {
				proveedorNuevo.setEmail(request.getParameter("email"));
			}
			
			// Actualizamos el mapa de proveedores
			HashMap<String,Proveedor> lstProveedores = (HashMap<String, Proveedor>) session.getAttribute("proveedores");
			lstProveedores.put(request.getParameter("claveProveedor"), proveedorNuevo);
			session.setAttribute("proveedores", lstProveedores);
			
			// Actualizamos el fichero de proveedores
			String fichero = this.getInitParameter("fichero");
			
			PrintWriter pw = new PrintWriter(new FileWriter(fichero));//No se actualiza por que necesitamos modificar la ruta
			for(Proveedor p : lstProveedores.values()) {
				pw.println(p.toString());
			}
			
			pw.close();
			
			// Redirigimos a la pantalla de listado de proveedores
			request.getRequestDispatcher("listadoProveedores.jsp").forward(request, response);
		}
	}
}
