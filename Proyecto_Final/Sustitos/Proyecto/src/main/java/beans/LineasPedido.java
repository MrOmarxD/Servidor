package beans;

public class LineasPedido {
	private int id;
	private int cantidad;
	private Pedido pedido;
	private Producto producto;
	
	// Constructores
	public LineasPedido(int id, int cantidad, Pedido pedido, Producto producto) {
		this.id = id;
		this.cantidad = cantidad;
		this.pedido = pedido;
		this.producto = producto;
	}

	public LineasPedido() {
		
	}
	
	// get / set
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "LineasPedido [id=" + id + ", cantidad=" + cantidad + ", pedido=" + pedido + ", producto=" + producto
				+ "]";
	}
	
}
