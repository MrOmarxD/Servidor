package beans;

public class Cliente {
	private int idCliente;
	private String nombre;
	private String password;
	private String domicilio;
	private int codigopostal;
	private int telefono;
	private String email;
	
	// Constructores
	public Cliente(int idCliente, String nombre, String password, String domicilio, int codigopostal, int telefono,
			String email) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.password = password;
		this.domicilio = domicilio;
		this.codigopostal = codigopostal;
		this.telefono = telefono;
		this.email = email;
	}
	
	public Cliente() {
		
	}
	
	// get/set
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public int getCodigopostal() {
		return codigopostal;
	}

	public void setCodigopostal(int codigopostal) {
		this.codigopostal = codigopostal;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
