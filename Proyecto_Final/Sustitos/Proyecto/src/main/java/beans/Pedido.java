package beans;

import java.util.Date;

public class Pedido {
	private Integer id;
	private double total;
	private Date fecha;
	private Usuario user;
	
	// Construcores
	public Pedido(int id, double total, Date fecha, Usuario user) {
		this.id = id;
		this.total = total;
		this.fecha = fecha;
		this.user = user;
	}
	public Pedido() {
		
	}

	// get / set
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", total=" + total + ", fecha=" + fecha + ", user=" + user + "]";
	}
	
	
}
