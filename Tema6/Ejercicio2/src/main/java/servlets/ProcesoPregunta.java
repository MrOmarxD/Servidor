package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.Pregunta;
import beans.Test;

@WebServlet(name="ProcesoPregunta", urlPatterns = {"/ProcesoPregunta", "/ProcesoPregunta/*" })
public class ProcesoPregunta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession(true);
        
        if(session.getAttribute("tiempo")==null){
            session.setAttribute("tiempo", System.currentTimeMillis());
        }
        
        if(request.getParameter("siguiente")!=null && session.getAttribute("numeroPregunta")!=null){
            int numeroPregunta=(int)(session.getAttribute("numeroPregunta"))+1;
            ArrayList<Integer> respuestas = (ArrayList<Integer>)session.getAttribute("respuestas");
            Test test = (Test)session.getAttribute("test");
            if(request.getParameter("respuesta")!=null){
                respuestas.add(Integer.parseInt(request.getParameter("respuesta")));
            }
            if(respuestas.size()!=numeroPregunta){
                numeroPregunta--;
                session.setAttribute("error","Seleccione una respueta");
            }
            session.setAttribute("respuestas", respuestas);
            session.setAttribute("numeroPregunta", numeroPregunta);
        }

        if(request.getParameter("enviar")!=null){
            String nombre = request.getParameter("nombre");
            int nPreguntas = 0;
            if(Integer.parseInt(request.getParameter("nPreguntas")) > Test.getPreguntas().length){
                nPreguntas = Test.getPreguntas().length;
            } else {
                nPreguntas = Integer.parseInt(request.getParameter("nPreguntas"));
            }
            
            boolean pista=false;
            if(request.getParameter("pista")!=null){
                pista=true;
            }
            int numeroPregunta=0;
            Test test = new Test(nPreguntas);
            ArrayList<Integer> respuestas = new ArrayList<>();
            
            session.setAttribute("respuestas", respuestas);
            session.setAttribute("nombre", nombre);
            session.setAttribute("nPreguntas", nPreguntas);
            session.setAttribute("pista", pista);
            session.setAttribute("numeroPregunta", numeroPregunta);
            session.setAttribute("test", test);
        }
        Test test = (Test)session.getAttribute("test");
        int numeroPregunta = (int)session.getAttribute("numeroPregunta");
        Pregunta pregunta = test.getLtsPreguntas().get(numeroPregunta);
        String enunciado=pregunta.getEnunciado();
        String[] respuestas=pregunta.getRespuestas();
        String pista = null;
        if(session.getAttribute("pista")==Boolean.TRUE){
            pista=pregunta.getPista();
        }
        
        String error = null;
        if(session.getAttribute("error")!=null || request.getParameter("respuesta")==null){
            error = (String)session.getAttribute("error");
        }
        session.setAttribute("error","");
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProcesoPregunta</title>");            
            out.println("</head>");
            out.println("<body>");
            if(error!=null){
                out.println("<p style='color: red'>"+error+"</p>");
            }
            out.println("<h3>"+enunciado+"</h3>");
            if(numeroPregunta==(int)session.getAttribute("nPreguntas")-1){
                out.println("<form method='POST' action='Resultado/'>");
            } else {
                out.println("<form method='POST' action='ProcesoPregunta/'>");
            }
            for (int i = 0; i < respuestas.length; i++) {
                out.println("<input type='radio' name='respuesta' id='"+i+"' value='"+i+"'><label for='"+i+"'>"+respuestas[i]+"</label><br>");
            }
            if(numeroPregunta==(int)session.getAttribute("nPreguntas")-1){
                out.println("<input type='submit' value='FIN' name='siguiente'>");
            } else {
                out.println("<input type='submit' value='SIGUIENTE' name='siguiente'><br><br>");
            }
            out.println("</form>");
            if(pista!=null){
                out.println("<cite>* PISTA: "+pista+"</cite>");
            }
            out.println("</body>");
            out.println("</html>");
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
