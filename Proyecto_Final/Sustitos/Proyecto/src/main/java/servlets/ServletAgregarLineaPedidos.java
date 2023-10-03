package servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CarroCompra;
import beans.LineasPedido;
import beans.Pedido;
import beans.Producto;
import beans.Usuario;
import dao.PedidoDAO;
import dao.ProductoDAO;

/**
 * Servlet implementation class ServletAgregarLineaPedidos
 */
public class ServletAgregarLineaPedidos extends HttpServlet {
	private PedidoDAO bdPedido;
	private ProductoDAO bdProductos;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bdPedido = new PedidoDAO();
        bdProductos = new ProductoDAO();
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
		CarroCompra carroCompra;
		
		if (session.getAttribute("carroCompra") == null) {
			carroCompra = new CarroCompra();
			session.setAttribute("carroCompra", carroCompra);
		} else {
			carroCompra = (CarroCompra) session.getAttribute("carroCompra");
		}
		
		if (request.getParameter("verCesta") != null) {
			request.getRequestDispatcher("listadoCesta.jsp").forward(request, response);
		}
		
		
		if (request.getParameter("aniadir") != null) {
			Producto producto = bdPedido.buscaProductoPorId(Integer.parseInt(request.getParameter("aniadir")));
			try {
				int cant = Integer.parseInt(request.getParameter("" + producto.getId()));
				Pedido pedido = new Pedido();
				double importe = producto.getPrecio() * cant;
				pedido.setTotal(importe);
				Date date = new Date();
				pedido.setFecha(date);
				Usuario cliente = (Usuario) request.getSession().getAttribute("cliente");
				pedido.setUser(cliente);
				
				LineasPedido lineaPedido = new LineasPedido();
				lineaPedido.setCantidad(cant);
				lineaPedido.setProducto(producto);
				lineaPedido.setPedido(pedido);
				carroCompra.aniadeLinea(lineaPedido);
				session.setAttribute("carroCompra", carroCompra);
				
			}catch (NumberFormatException e)  {
				request.setAttribute("mensajeError", "Debes introducir una cantida");
	        } 
			request.getRequestDispatcher("listadoProductos.jsp").forward(request, response);
		}
		
		if (request.getParameter("detalles") != null) {
			Producto producto = bdPedido.buscaProductoPorId(Integer.parseInt(request.getParameter("detalles")));
			session.setAttribute("producto", producto);
			request.getRequestDispatcher("detalleProducto.jsp").forward(request, response);
		}
		
		if (request.getParameter("vaciar") != null) {
			request.getRequestDispatcher("ServletVaciarCesta").forward(request, response);
		}
		
		if (request.getParameter("continuar") != null) {
			request.getRequestDispatcher("listadoProductos.jsp").forward(request, response);
		}
		
		/*if (request.getParameter("hacer_pedido") != null) {
			request.getRequestDispatcher("pedir.jsp").forward(request, response);
		}*/
		
		if (request.getParameter("borrar") != null) {
			carroCompra = (CarroCompra) session.getAttribute("carroCompra");
			carroCompra.borraLinea(Integer.parseInt(request.getParameter("borrar")));
			request.getRequestDispatcher("listadoCesta.jsp").forward(request, response);
		}
		
		if (request.getParameter("borrarProd") != null) {
			bdProductos.borrarProducto(Integer.parseInt(request.getParameter("borrarProd")));
			request.getSession().setAttribute("lstProductos", bdProductos.lstProductos());
			request.getRequestDispatcher("listadoProductosAdmin.jsp").forward(request, response);
		}
	}

}
