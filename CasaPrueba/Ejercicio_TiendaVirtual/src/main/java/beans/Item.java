package beans;

public class Item {
	private int idItem;
	private String nombre;
	private double precio;
	
	// Constructores
	public Item(int idItem, String nombre, double precio) {
		super();
		this.idItem = idItem;
		this.nombre = nombre;
		this.precio = precio;
	}
	
	public Item() {
		
	}

	// get/set
	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}	
}
