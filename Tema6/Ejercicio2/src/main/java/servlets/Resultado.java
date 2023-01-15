package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Test;

@WebServlet(name="Resultado", urlPatterns = {"/Resultado", "/Resultado/*" })
public class Resultado extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        
        if(request.getParameter("respuesta") == null){
            response.sendRedirect("ProcesoPregunta/");
            session.setAttribute("error", "Seleccione una respueta");
        } else {
            int numeroPregunta=(int)(session.getAttribute("numeroPregunta"))+1;
            ArrayList<Integer> respuestas=(ArrayList<Integer>)session.getAttribute("respuestas");
            Test test = (Test)session.getAttribute("test");
            respuestas.add(Integer.parseInt(request.getParameter("respuesta")));
            long tiempo = System.currentTimeMillis()-(long)session.getAttribute("tiempo");
            long segundos = TimeUnit.MILLISECONDS.toSeconds(tiempo);
            long minutos = 0;
            if(segundos>60){
                minutos = segundos/60;
                segundos=segundos-(minutos*60);
            }

            int aciertos = test.comprobar(respuestas);
            String usuario = (String)session.getAttribute("nombre");
            int nPreguntas = (int)session.getAttribute("nPreguntas");

            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Resultado</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<p><strong>"+usuario+"</strong>, has acertado <strong>"+aciertos+"</strong> preguntas de un total de <strong>"+nPreguntas+"</strong></p>");
                out.println("<p>Tienes muy buenos conocimientos de cine</p>");
                if(minutos==0){
                    out.println("<p>Tiempo de respuesta: "+segundos+" segundo(s)</p>");
                } else {
                    out.println("<p>Tiempo de respuesta: "+minutos+" minuto(s) "+segundos+" segundo(s)</p>");
                }
                out.println("<a href='"+request.getContextPath()+"/index.html'>NUEVO INTENTO</a>");
                out.println("</body>");
                out.println("</html>");
            }
            session.invalidate();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
