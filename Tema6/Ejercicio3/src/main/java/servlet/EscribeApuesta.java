package servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EscribeApuesta
 */
@WebServlet(name="EscribeApuesta", urlPatterns = {"/EscribeApuesta", "/EscribeApuesta/*" })
public class EscribeApuesta extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String fichero = this.getInitParameter("fichero");
        try {
            String ruta = this.getServletContext().getRealPath(fichero);
            File myObj = new File(ruta);
            Scanner myReader = new Scanner(myObj,"UTF-8");
            ArrayList<String> partidos=new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                partidos.add(data);
            }
            myReader.close();
            this.getServletContext().setAttribute("partidos", partidos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession(true);
        
        String nombre="";
        ArrayList<String> partidos = null;
        if(this.getServletContext().getAttribute("partidos")!=null){
            partidos = (ArrayList<String>)this.getServletContext().getAttribute("partidos");
        }
        
        if(request.getParameter("enviar")!=null){
            nombre = request.getParameter("nombre");
            session.setAttribute("nombre", nombre);
        }
        
        if(session.getAttribute("nombre")!=null){
            nombre=(String) session.getAttribute("nombre");
        }
        
        
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EscribeApuestas</title>"); 
            out.println("<meta http-equiv='Content-Type' content='text/html;charset=UTF-8'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>"+nombre+", escribe tu apuesta:</h1>");
            out.println("<form action='ProcesaApuesta' method='POST'>");
            out.println("<table>");
            if(partidos!=null){
                int cont = 0;
                for (String partido : partidos) {
                    String[] equipos=partido.split(":");
                    out.println("<tr>");
                    out.println("<td>"+equipos[0]+"</td>");
                    out.println("<td>"+equipos[1]+"</td>");
                    if(session.getAttribute("partido"+cont)!=null){
                        out.println("<td><select name='"+cont+"'>");
                        out.println("<option value=''></option>");
                        if(session.getAttribute("partido"+cont).equals("1")){
                            out.println("<option value='1' selected>1</option>");
                        } else {
                            out.println("<option value='1'>1</option>");
                        }
                        if(session.getAttribute("partido"+cont).equals("X")){
                            out.println("<option value='X' selected>X</option>");
                        } else {
                            out.println("<option value='X'>X</option>");
                        }
                        if(session.getAttribute("partido"+cont).equals("2")){
                            out.println("<option value='2' selected>2</option>");
                        } else {
                            out.println("<option value='2'>2</option>");
                        }
                        out.println("</select></td>");
                        if(session.getAttribute("partido"+cont).equals("")){
                            out.println("<td style='color:red'>Debes rellenar este campo</td>");
                        }
                        out.println("</td>");
                    } else {
                        out.println("<td><select name='"+cont+"'>"
                            + "<option value=''></option>"
                            + "<option value='1'>1</option>"
                            + "<option value='X'>X</option>"
                            + "<option value='2'>2</option>"
                            + "</td>");
                    }
                    out.println("</tr>");
                    cont++;
                }
            }
            out.println("</table>");
            out.println("<input type='submit' value='GUARDAR APUESTA' name='enviarApuesta'>");
            out.println("</form>");
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
