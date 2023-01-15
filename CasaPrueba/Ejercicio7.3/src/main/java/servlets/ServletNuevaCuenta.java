package servlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Cuenta;

/**
 * Servlet implementation class ServletNuevaCuenta
 */
@WebServlet(name="ServletNuevaCuenta", urlPatterns = {"/ServletNuevaCuenta", "/ServletNuevaCuenta/*" })
public class ServletNuevaCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
        super.init();
        ServletContext context=getServletContext();
        ArrayList<String> nombres;
        if(context.getAttribute("usuarios")!=null){
            nombres=(ArrayList<String>)context.getAttribute("usuarios");
        }else {
            nombres=new ArrayList<>();
        }
        nombres.add("admin");
        nombres.add("omar");
        context.setAttribute("usuarios", nombres);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session=request.getSession(true);
        String fichero="errores.txt", error="";
        ArrayList<String> errores=new ArrayList<>();
        ArrayList<String> nombres=(ArrayList<String>)request.getServletContext().getAttribute("usuarios");
        Cuenta cuenta;
        if(session.getAttribute("cuenta")==null){
            cuenta=new Cuenta();
        } else {
            cuenta=(Cuenta)session.getAttribute("cuenta");
        }
        try {
            String ruta=this.getServletContext().getRealPath("txt/"+fichero);
            File myObj = new File(ruta);
            Scanner myReader = new Scanner(myObj,"UTF-8");
            while (myReader.hasNextLine()) {
                errores.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(request.getParameter("enviar")!=null){
            if(request.getParameter("titular").equals("")){
                error+=errores.get(0)+"<br>";
            } else if(nombres.contains(request.getParameter("titular"))){
                error+=errores.get(2)+"<br>";
            } else {
                cuenta.setTitular(request.getParameter("titular"));
            }
            System.out.println(request.getParameter("saldo"));
            if(request.getParameter("saldo").equals("")){
                cuenta.setSaldo(0);
            }else if(Integer.parseInt(request.getParameter("saldo"))<0){
                error+=errores.get(1)+"<br>";
            } else {
                cuenta.setSaldo(Integer.parseInt(request.getParameter("saldo")));
            }
            session.setAttribute("error", error);
            session.setAttribute("cuenta", cuenta);
            if(error!=""){
                response.sendRedirect(request.getContextPath() + "/nuevacuenta.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/movimientos.jsp");
            }
        }else if(request.getParameter("gastar")!=null){
            if(request.getParameter("cantidad").equals("")){
                error+=errores.get(3)+"<br>";
            }else if(Integer.parseInt(request.getParameter("cantidad"))<0){
                error+=errores.get(4)+"<br>";
            } else {
                boolean gastado=cuenta.gastar(Integer.parseInt(request.getParameter("cantidad")));
                if(!gastado){
                    error+=errores.get(1)+"<br>";
                }
            }
            session.setAttribute("error", error);
            session.setAttribute("cuenta", cuenta);
            response.sendRedirect(request.getContextPath() + "/movimientos.jsp");
        }else if(request.getParameter("ingresar")!=null){
            if(request.getParameter("cantidad").equals("")){
                error+=errores.get(3)+"<br>";
            }else if(Integer.parseInt(request.getParameter("cantidad"))<0){
                error+=errores.get(4)+"<br>";
            } else {
                cuenta.ingresar(Integer.parseInt(request.getParameter("cantidad")));
            }
            session.setAttribute("error", error);
            session.setAttribute("cuenta", cuenta);
            response.sendRedirect(request.getContextPath() + "/movimientos.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/nuevacuenta.jsp");
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
		processRequest(request, response);;
	}

}
