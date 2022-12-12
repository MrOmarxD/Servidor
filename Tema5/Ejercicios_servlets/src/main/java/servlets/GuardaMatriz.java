package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AlmacenMatrices;

@WebServlet({ "/GuardaMatriz", "/GuardaMatriz/*" })
public class GuardaMatriz extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

	// Valida si es que sea un numero o no este vacio
	
	 @Override
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
		 String botGuardar = request.getParameter("botGuardar");
		 if(botGuardar!=null) {
			 try {
				 int numfilas = Integer.parseInt(request.getParameter("numfilas"));
				 int numcolumnas = Integer.parseInt(request.getParameter("numcolumnas"));
				 for(int i = 1; i<=numfilas; i++) {
					 for(int j = 1; j<=numcolumnas; j++) {
						 Integer.parseInt(request.getParameter("celda"+j+"-"+i));
					}
				}
				out.println("<p>Tu matriz de "+numfilas+"x"+numcolumnas+" ha sido guardada</p>");
				out.println("<p>Hay un total de "+AlmacenMatrices.getNumMatricesTotal()+" matrices</p>");
				out.println("<a>INTRODUCE OTRA MATRIZ</a>");
				out.println("<a>VER MATRICES</a>");
			} catch (NumberFormatException e) {
				out.println("<p>Debe rellenar correctamente la matriz</p>");
			}
		 }
		 else
			 response.sendRedirect("/Ejercicios_servlets/IntroCeldas");
	 }
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 
	 }
}