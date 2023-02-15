

package servlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.HoraCharla;
import daos.CharlasDao;


public class ServletPrepararCharlas extends HttpServlet {
	private CharlasDao bdCharlas;
    
	 @Override
   public void init(ServletConfig config) throws ServletException {
       super.init(config);
       bdCharlas = new CharlasDao();
   }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession(true);
    	//Recogemos el nombre del fichero de horas de apertura y cierre del web.xml
        String fichero = this.getInitParameter("fichero");
        // Recorremos el fichero recogiendo los datos por lineas
        try {
            String ruta = this.getServletContext().getRealPath(fichero);
            File myObj = new File(ruta);
            Scanner myReader = new Scanner(myObj,"UTF-8");
            ArrayList<String> lineas = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                lineas.add(data);
            }
            myReader.close();
            // Separamos las horas y min de apertura
            String[] partes = lineas.get(0).split(":");
            session.setAttribute("hora", partes[0]);
            session.setAttribute("minutos", partes[1]);
            
         // Separamos las horas y min de cierre
          partes = lineas.get(1).split(":");
          session.setAttribute("horaCierre", partes[0]);
          session.setAttribute("minutosCierre", partes[1]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		request.getRequestDispatcher("horarioscharlas.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	// Si se pulsa el boton Organizar ponencias
    	if(request.getAttribute("submit1") != null) {
			int hora = 0;
			int min = 0;
			int intervalo = 0;
			HttpSession session = request.getSession(true);
			// Validamos la informacion que se le pasa en los input
			// Recogemos la informacion
			try {
				hora = Integer.parseInt(request.getParameter("horaini"));
		    	min = Integer.parseInt(request.getParameter("minutosini"));
		    	intervalo = Integer.parseInt(request.getParameter("intervalo"));
		    	// Validar intervalo
		    	if(intervalo <1) {
		    		request.setAttribute("error", "Intervalo introducido incorrecto");
		    		request.getRequestDispatcher("horarioscharlas.jsp").include(request, response);
		    	}
		    	// Validar horas
		    	if(hora<1 || hora>23) {
		    		request.setAttribute("error", "Hora introducida incorrecto");
		    		request.getRequestDispatcher("horarioscharlas.jsp").include(request, response);
		    	}
		    	// Validar minutos
		    	if(min<1 || min>59) {
		    		request.setAttribute("error", "Minutos introducida incorrecto");
		    		request.getRequestDispatcher("horarioscharlas.jsp").include(request, response);
		    	}
			} catch (NumberFormatException e) {
				request.setAttribute("error", "Debes de rellenar todos los campos con números");
				request.getRequestDispatcher("horarioscharlas.jsp").include(request, response);
			}
			
			// Llegados a este punto no hay errores
			//lista de horasCharlas posibles
			ArrayList<HoraCharla> lstHorasCharlas = new ArrayList<HoraCharla>();
			
			int inicioMin = hora*60+min;
			int finMin = (int)session.getAttribute("horaCierre")*60+(int)session.getAttribute("minutosCierre");
			for(int i = inicioMin; i<=finMin; i = i+intervalo){
				HoraCharla hc = new HoraCharla();
				hc.setHora(i/60);
				hc.setMin(i%60);
				lstHorasCharlas.add(hc);
		    }
			session.setAttribute("lstHorasCharlas", lstHorasCharlas);
			session.setAttribute("lstSalasPreparadas", bdCharlas.obtenerSalasPreparadas());
			request.getRequestDispatcher("charlas.jsp").include(request, response);
		 }
    	// Si se pulsa el boton grabar charla
    	if(request.getAttribute("grabar") != null) {
    		
    	}
    }
    
}

