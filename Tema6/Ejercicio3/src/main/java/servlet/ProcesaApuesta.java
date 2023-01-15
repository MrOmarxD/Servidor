package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ProcesaApuesta
 */
@WebServlet(name="ProcesaApuesta", urlPatterns = {"/ProcesaApuesta", "/ProcesaApuesta/*" })
public class ProcesaApuesta extends HttpServlet {
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession(true);
        
        ArrayList<String> partidos=null;
        if(this.getServletContext().getAttribute("partidos")!=null){
            partidos=(ArrayList<String>)this.getServletContext().getAttribute("partidos");
        }
        
        if(request.getParameter("enviarApuesta")!=null){
            for (int i = 0; i < partidos.size(); i++) {
                String valor=request.getParameter(""+i);
                session.setAttribute("partido"+i, valor);
            }
            for (int i = 0; i < partidos.size(); i++) {
                String valor=request.getParameter(""+i);
                if(valor.equals("")){
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/EscribeApuesta");
                    dispatcher.forward(request, response);
                }
            }
            ArrayList<String> apuestas=new ArrayList<>();
        }
        
        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProcesaApuesta</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Apuesta guardada</h1>");
            int cont=0;
            for (String partido : partidos) {
                out.println("<p>"+partido+" "+request.getParameter(""+cont)+"</p>");
                cont++;
            }
            out.println("<a href='EscribeApuesta'>REVISAR LA APUESTA</a>");
            out.println("</body>");
            out.println("</html>");
        }
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
