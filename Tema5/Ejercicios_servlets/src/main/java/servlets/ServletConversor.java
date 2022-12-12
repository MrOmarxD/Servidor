package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletConversor
 */
@WebServlet({ "/ServletConversor", "/conversion/*" })
public class ServletConversor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConversor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
        //Obtener datos del formulario
        request.setCharacterEncoding("utf-8");
		String botonFaCel = request.getParameter("fah-cel");
		String gradosCels = request.getParameter("gradosCels");
		String gradosFahr = request.getParameter("gradosFahr");
		
        //Crear respuesta
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head><title>Resultado</title></head>");
            out.println("<body>");
            out.println("<h1>Resultado de la conversion:</h1>");
            
            // Sin Cambios
            /*if(botonFaCel!=null){
    			if(gradosFahr.isEmpty())
    	            out.println("<p>ERROR: Debes indicar los grados Fahrenheit</p>");
    			else {
    	            out.println("<p>Valor en celsius: "+((Double.parseDouble(gradosFahr)-32)*5/9)+"</p>");
    	            out.println("<p>Valor en fahrenheit:"+gradosFahr+"</p>");
    			}
    		}
            else {
            	if(gradosCels.isEmpty())
    	            out.println("<p>ERROR: Debes indicar los grados Celsius</p>");
    			else {
    	            out.println("<p>Valor en celsius: "+gradosCels+"</p>");
    	            out.println("<p>Valor en fahrenheit: "+(32+Double.parseDouble(gradosCels)*9/5)+"</p>");
    			}
            }*/
            
            ConversionCF ccf = null;
            if(botonFaCel!=null){
    			if(gradosFahr.isEmpty())
    	            out.println("<p>ERROR: Debes indicar los grados Fahrenheit</p>");
    			else
    				ccf = new ConversionCF(Double.parseDouble(gradosFahr), 'f');
    		}
            else {
            	if(gradosCels.isEmpty())
    	            out.println("<p>ERROR: Debes indicar los grados Celsius</p>");
    			else
    				ccf = new ConversionCF(Double.parseDouble(gradosCels), 'c');
            }
            if(ccf!=null) {
            		out.println("<p>Valor en celsius: "+ccf.getCelsius()+"</p>");
            		out.println("<p>Valor en fahrenheit: "+ccf.getFahrenheit()+"</p>");
            }
            out.println("<a href='"+request.getContextPath()+"/conversorCF.html'>Enlace para volver al formulario</a>");
            out.println("</body>");
            out.println("</html>");
        } finally {
        }
		/*response.sendRedirect(request.getContextPath()+"/conversorCF.html");*/
	}
}
