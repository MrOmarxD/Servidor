package servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/ServletFormOpinion", "/formOpinion/*" })
public class ServletFormOpinion extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

	 @Override
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
		 response.setContentType("text/html;charset=UTF-8");
		 try (PrintWriter out = response.getWriter()) {
			 out.println("<!DOCTYPE html>");
			 out.println("<html>");
			 out.println("<head>");
			 out.println("<title>Ejercicio 2 - Formulario opinion</title>");        
			 out.println("</head>");
			 out.println("<body>");
			 if(request.getAttribute("error") != null)
				 out.println("<p style='color:red;'>Debes de escribir un nombre y eleguir una opinion</p>");
			 out.println("<form method='post'>");
			 	out.println("Nombre:<input type='text' name='nombre'><br>");
			    out.println("Apellidos:<input type='text' name='apellidos'><br>");
			    out.println("<p>Opinión que le ha merecido este sitio web</p>");
			    out.println("<input type='radio' name='opinion' value='B'>Buena<br>");
			    out.println("<input type='radio' name='opinion' value='R'>Regular<br>");
			    out.println("<input type='radio' name='opinion' value='M'>Mala<br>");
			    out.println("Comentarios<br><textarea name='comentarios' rows='5' cols='40'></textarea>");
			    out.println("<p>Opinión que le ha merecido este sitio web</p>");
			    String nombreFichero = getServletContext().getRealPath("ficheros/secciones.txt");
			    BufferedReader br = new BufferedReader(new FileReader(nombreFichero));
			    String linea = br.readLine();
			    while(linea != null) {
			    	out.println("<input type='checkbox' name='seccionesFav' value='"+linea+"'>"+linea+"<br>");
			    	linea = br.readLine();
			    }
			    br.close();
			    out.println("<input type='submit' name='enviar' value='Enviar opinion'>");
			 out.println("</form>");
			 out.println("</body>");
			 out.println("</html>");
		 }
	 }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String opinion = request.getParameter("opinion");
		String[] seccionesFav = request.getParameterValues("seccionesFav");
		if(nombre!=null && opinion!=null){
			if(opinion.equals("B") && seccionesFav.length>0) {
				String texto = nombre+" "+apellidos+":";
				for (String seccion : seccionesFav) {
					texto+= seccion+",";
				}
				texto = texto.substring(0, texto.length()-1);
				String nombreFichero = getServletContext().getRealPath("ficheros/seccionesfavoritas.txt");
				BufferedWriter bw = new BufferedWriter(new FileWriter(nombreFichero));
				bw.write(texto+"\n");
				bw.close();
			}
		}
		else {
			request.setAttribute("error", "error");
		}
		doGet(request, response);
    }
}
