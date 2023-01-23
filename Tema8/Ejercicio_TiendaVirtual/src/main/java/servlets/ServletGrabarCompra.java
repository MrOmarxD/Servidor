package servlets;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CarroCompra;
import beans.Cliente;
import beans.Pedido;
import dao.ClienteDAO;
import dao.KeysDAO;
import dao.PedidoDAO;

/**
 * Servlet implementation class ServletGrabarCompra
 */
@WebServlet("/ServletGrabarCompra")
public class ServletGrabarCompra extends HttpServlet {
	private ClienteDAO bdCliente;
	private KeysDAO bdKeys;
	private PedidoDAO bdPedido;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bdCliente = new ClienteDAO();
        bdKeys = new KeysDAO();
        bdPedido = new PedidoDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CarroCompra carroCompra = (CarroCompra) session.getAttribute("carroCompra");
		
		if (request.getParameter("modificar") != null) {
			request.getRequestDispatcher("tienda.jsp").forward(request, response);
		}
		
		if (request.getParameter("comprar") != null) {
			if (carroCompra.getCarro().size() == 0) {
				request.getRequestDispatcher("tienda.jsp").forward(request, response);
			} else {
				Cliente cliente = (Cliente) session.getAttribute("cliente");
				cliente.setCodigoPostal(request.getParameter("zip"));
				cliente.setDomicilio(request.getParameter("direccion"));
				cliente.setTelefono(request.getParameter("telefono"));
				cliente.setEmail(request.getParameter("email"));
				boolean clienteActualizado = bdCliente.actualizaCliente(cliente);
				
				Pedido pedido = new Pedido();
				pedido.setIdPedido(bdKeys.siguienteId("pedidos"));
				pedido.setTotal(carroCompra.total());
				Date date = new Date();
				pedido.setFecha(date);
				pedido.setCliente(cliente);
				bdPedido.guardaPedido(pedido);
				
				Set<Integer> keys = carroCompra.getCarro().keySet();
				for (Integer key : keys) {
					carroCompra.getCarro().get(key).setIdLineaPedido(bdKeys.siguienteId("lineaspedido"));
					bdPedido.guardaLineaPedido(carroCompra.getCarro().get(key));
				}
			}
			
			request.getRequestDispatcher("gracias.jsp").forward(request, response);
		}
	}

}
