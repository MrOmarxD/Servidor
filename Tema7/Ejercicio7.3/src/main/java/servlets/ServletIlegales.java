package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletIlegales
 */
@WebServlet(name="ServletIlegales", urlPatterns = {"/ServletIlegales", "/ServletIlegales/*" })
public class ServletIlegales extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context=getServletContext();
        ArrayList<String> nombres;
        if(context.getAttribute("usuarios")!=null){
            nombres=(ArrayList<String>)context.getAttribute("usuarios");
        }else {
            nombres=new ArrayList<>();
        }
        if(request.getParameter("eliminar")!=null){
            nombres.remove(Integer.parseInt(request.getParameter("eliminar")));
        }
        if(request.getParameter("add")!=null){
            nombres.add(request.getParameter("nombre"));
        }     
        context.setAttribute("usuarios", nombres);
        response.sendRedirect(request.getContextPath() + "/ilegales.jsp");
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
