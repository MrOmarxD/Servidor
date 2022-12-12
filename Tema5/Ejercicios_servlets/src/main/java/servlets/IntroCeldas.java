package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/IntroCeldas", "/introCeldas/*" })
public class IntroCeldas extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

	// Dibujar matriz
	private void dibujarMatriz(PrintWriter out, int numfilas, int numcolumnas, boolean fondoGris){
		String fondo= "";
		if(fondoGris)
			fondo = "style='bagroung-color: gray'";
		out.println("<form action='/Ejercicios_servlets/GuardaMatriz' method='get' "+fondo+">");
		for(int i = 1; i<=numfilas; i++) {
			for(int j = 1; j<=numcolumnas; j++) {
				out.println("<input type='text' name='celda"+j+"-"+i+"'>");
			}
			out.println("<br>");
		}
		out.println("<input type='hidden' name='numfilas' value='"+numfilas+"'>");
		out.println("<input type='hidden' name='numcolumnas' value='"+numcolumnas+"'>");
		out.println("<input type='submit' name='botGuardar' value='Guardar matriz'>");
		out.println("<input type='submit' name='botRestablecer' value='Restablecer'>");
		out.println("</form>");
    }
	
	 @Override
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
		 int numfilas = Integer.parseInt(request.getParameter("numfilas"));
		 int numcolumnas = Integer.parseInt(request.getParameter("numcolumnas"));
		 String fondoGris = request.getParameter("fondoGris");
		 if(numfilas<=0 || numcolumnas<=0){
			 response.sendRedirect(request.getContextPath()+"/indexCeldas.html");
		 }
		 response.setContentType("text/html;charset=UTF-8");
		 try (PrintWriter out = response.getWriter()) {
			 out.println("<!DOCTYPE html>");
			 out.println("<html>");
			 out.println("<head>");
			 out.println("<title>Ejercicio 3 - Matrices</title>");        
			 out.println("</head>");
			 out.println("<body>");
			 out.println("<h1>INTRODUCE VALORES</h1>");
			 if(fondoGris!=null)
				 dibujarMatriz(out, numfilas, numcolumnas, true);
			 else
				 dibujarMatriz(out, numfilas, numcolumnas, false);
			 out.println("</body>");
			 out.println("</html>");
		 }
	 }
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 String botGuardar = request.getParameter("botGuardar");
		 if(botGuardar!=null) {
			 
		 }
		 else
			 doGet(request, response);
	 }
}
