package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ClienteDAO;
import daos.KeysDAO;

/**
 * Servlet implementation class ServletLogin
 */
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//DAO  con metodos y constructo no estaticos
	private ClienteDAO clienteDao;
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        clienteDao= new ClienteDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//utilización de DAO con metodo static
		int id=KeysDAO.siguienteId("clientes");
		System.out.println("El siguiente ID de la tabla clientes es: "+id);
		
		//Utilizando un DAO con instanciación de objeto DAO
		boolean existeCliente=clienteDao.buscaCliente("Juan Gil");
		System.out.println("¿Exite el cliente Juan Gil?: "+existeCliente);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
