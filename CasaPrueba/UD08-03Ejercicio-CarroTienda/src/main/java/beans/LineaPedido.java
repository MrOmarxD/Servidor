package beans;

public class LineaPedido {
	
	
	private int id;
	private int cantidad;
	private Pedido pedido;
	private Item item;
	
	public LineaPedido() {
		super();
		// TODO Auto-generated constructor stub
	}


	public LineaPedido(int id, int cantidad, Pedido pedido, Item item) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.pedido = pedido;
		this.item = item;
	}


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


	public Item getItem() {
		return item;
	}


	public void setItem(Item item) {
		this.item = item;
	}
	
	


}
