package servlets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletNuevaCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletNuevaCuenta() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ArrayList<String> arrListNombres=listaNombresProhibidos();
		ArrayList<String> errores=new ArrayList<String>();
		
		//Comprobar nombres prohibidos o vacío
		String nombre=request.getParameter("titular");
		if(nombre.equals("")||nombre.equals(" ")) {
			errores.add("TITULAR VACÍO - nombre válido requerido");
		}else {
			for(String s: arrListNombres) {
				if(nombre.toLowerCase().equals(s.toLowerCase())) {
					errores.add("TITULAR PROHIBIDO - "+s+" no puede crear cuentas");
					break;
				}
			}
		}
		
		//Comprobar saldo negativo
		Double saldo=Double.parseDouble(request.getParameter("saldoInicial"));
		if(saldo<0) {
			errores.add("SALDO NEGATIVO - se requiere saldo inicial positivo");
		}
		
		//GESTIONAR REDIRECCION EN BASE A ERRORES
		if(errores.size()==0) {
			response.sendRedirect(request.getContextPath()+"/movimientos.jsp");
		}else {
			response.sendRedirect(request.getContextPath()+"/nuevaCuenta.jsp");
		}
		
	}
	
	public ArrayList<String> listaNombresProhibidos() throws IOException {
		ArrayList<String> arrListNombres=new ArrayList<String>();
		
		BufferedReader br=new BufferedReader(new FileReader(this.getServletContext().getRealPath(getInitParameter("listaProhibidos"))));
		String linea=br.readLine();
		while(linea!=null) {
			arrListNombres.add(linea);
			linea=br.readLine();
		}	
		br.close();
		
		return arrListNombres;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
