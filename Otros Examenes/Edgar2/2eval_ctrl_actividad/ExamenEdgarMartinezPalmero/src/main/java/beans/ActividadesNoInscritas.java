package beans;

public class ActividadesNoInscritas {
	private int id;
	private String nombre, nombreImpartidor;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombreImpartidor() {
		return nombreImpartidor;
	}
	public void setNombreImpartidor(String nombreImpartidor) {
		this.nombreImpartidor = nombreImpartidor;
	}

	public ActividadesNoInscritas(int id, String nombre, String nombreImpartidor) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nombreImpartidor = nombreImpartidor;
	}
	
	
	
	
}
