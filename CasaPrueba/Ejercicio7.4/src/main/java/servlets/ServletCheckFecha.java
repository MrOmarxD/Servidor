package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Fecha;
import bean.Primitiva;

@WebServlet("/ServletCheckFecha")
public class ServletCheckFecha extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletCheckFecha() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(true);
		//Vaciar si existe
		if(request.getAttribute("errores") != null) {
			session.removeAttribute("errores");
		}
		
		String errores="";
		if(request.getParameter("dia")!=null && request.getParameter("mes")!=null && request.getParameter("ano")!=null) {
			try {
				Fecha fecha=new Fecha(Integer.parseInt(request.getParameter("dia")), Integer.parseInt(request.getParameter("mes")), Integer.parseInt(request.getParameter("ano")));
				if(fecha.correcta()) {
					session.removeAttribute("errores");
					Primitiva primitiva = new Primitiva(fecha);
					session.setAttribute("primitiva", primitiva);
					response.sendRedirect(request.getContextPath()+"/crear_primitiva.jsp");
				}else {
					errores+="La fecha no es válida";
				}
				
			} catch (NumberFormatException e) {
				errores+="Hay campos no numéricos o incorrectos";
			}
		}else {
			errores+="Hay campos vacíos";
		}
		
		if(errores!="") {
			session.setAttribute("errores", errores);
			response.sendRedirect(request.getContextPath()+"/crear_primitiva.jsp");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
