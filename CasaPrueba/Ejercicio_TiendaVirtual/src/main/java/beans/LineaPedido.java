package beans;

public class LineaPedido {
	private int idLineaPedido;
	private int cantidad;
	private Item item;
	private Pedido pedido;
	
	// get / set
	public int getIdLineaPedido() {
		return idLineaPedido;
	}
	public void setIdLineaPedido(int idLineaPedido) {
		this.idLineaPedido = idLineaPedido;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
