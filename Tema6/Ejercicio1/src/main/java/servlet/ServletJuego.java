package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.AlmacenPalabras;

/**
 * Servlet implementation class ServletJuego
 */
@WebServlet(name="ServletJuego", urlPatterns = {"/ServletJuego", "/ServletJuego/*" })
public class ServletJuego extends HttpServlet {
	private String palabraSecreta;
    private ArrayList<String> letrasPalabraSecreta;
    private int vidas;
    private ArrayList<Integer> posiciones;
    private boolean hasGanado;
    private boolean hasPerdido;
        
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession sesion = request.getSession(true);
        
        //si el juego no tiene una palabra secreta es que no hay ning�n juego empezado
        if(sesion.getAttribute("palabraSecreta") == null){
            cargarJuegoNuevo(sesion);   
        }
        
        cargarVariablesDesdeSesion(sesion, palabraSecreta, letrasPalabraSecreta, vidas, posiciones, hasGanado, hasPerdido);
        
       
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ServletJuego</title>");            
            out.println("</head>");
            out.println("<body>");
            
                if((boolean)sesion.getAttribute("hasGanado")){
                    out.println("<h1>Has ganado la partida.</h1>");
                      
                    cargarJuegoNuevo(sesion);   
                    cargarVariablesDesdeSesion(sesion, palabraSecreta, letrasPalabraSecreta, vidas, posiciones, hasGanado, hasPerdido);
                
                }else if((boolean)sesion.getAttribute("hasPerdido")){
                    out.println("<h1>Has perdido la partida. La palabra era: " + palabraSecreta + "</h1>");
                    
                    cargarJuegoNuevo(sesion);   
                    cargarVariablesDesdeSesion(sesion, palabraSecreta, letrasPalabraSecreta, vidas, posiciones, hasGanado, hasPerdido);
                }
            
                out.println("<table>");
                out.println("<tr>");
                    for(int i = 0; i < palabraSecreta.length(); i++) {
                        if( posiciones.contains(i) ){
                            out.println("<td style='border:solid 1px;'>" + palabraSecreta.charAt(i) + "<td>");
                        }else{
                            out.println("<td style='border:solid 1px;'><a href='" + request.getContextPath() + "/ServletComprobar?posicion=" + i + "'>Ver</a><td>");

                        }
                    }
                out.println("<tr>");
                out.println("</table>");
                out.println("<p>" + vidas + " vidas restantes</p>");

                out.println("<form action='ServletComprobar' method='POST'>");
                    out.println("<label for='respuesta'>Tu respuesta: </label>");
                    out.println("<input type='text' name='respuesta' id='respuesta'>");
                    
                    out.println("<button type='submit' name='butComprobar'>Comprobar</button>");
                out.println("</form>");
            
        
            out.println("</body>");
            out.println("</html>");
            
        }
    }


    private void cargarJuegoNuevo(HttpSession sesion){
        //Inicializar par�metros del juego:
        String palabraSecreta = AlmacenPalabras.dameUnaPalabra();
        sesion.setAttribute("palabraSecreta", palabraSecreta);
        
        ArrayList<String>letrasPalabraSecreta = new ArrayList<String>();
        sesion.setAttribute("letrasPalabraSecreta", letrasPalabraSecreta);

        int vidas = (int)palabraSecreta.length()/2;
        sesion.setAttribute("vidas", vidas);
        
        ArrayList<Integer> posiciones = new ArrayList<Integer>();
        sesion.setAttribute("posiciones", posiciones);

        sesion.setAttribute("hasGanado", false);
        
        sesion.setAttribute("hasPerdido", false);  
    }
   
    private void cargarVariablesDesdeSesion(HttpSession sesion, String palabraSecreta, ArrayList<String> letrasPalabraSecreta, 
            int vidas, ArrayList<Integer> posiciones, boolean hasGanado, boolean hasPerdido){
        
        this.palabraSecreta = (String)sesion.getAttribute("palabraSecreta");
        this.letrasPalabraSecreta = (ArrayList<String>)sesion.getAttribute("letrasPalabraSecreta");
        this.vidas = (int)sesion.getAttribute("vidas");
        this.posiciones = (ArrayList<Integer>)sesion.getAttribute("posiciones");
        this.hasGanado = (boolean)sesion.getAttribute("hasGanado");
        this.hasPerdido = (boolean)sesion.getAttribute("hasPerdido");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
