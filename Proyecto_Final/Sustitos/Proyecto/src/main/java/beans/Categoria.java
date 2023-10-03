package beans;

public class Categoria {
	private Integer id;
	private String nombre;
	
	/**
	 * Constructor Datos Vacio
	 */
	public Categoria() {
		id = 0;
		nombre = new String();
	}
	
	public Categoria(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return nombre;
	}
	
	
}
