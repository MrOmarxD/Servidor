package beans;

public class Cliente {

	
	private int id;
	private String nombre;
	private String password;
	private String domicilio;	
	private String codigopostal;
	private String telefono	;
	private String email;
	
	
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cliente(int id, String nombre, String password, String domicilio, String codigopostal, String telefono,
			String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.domicilio = domicilio;
		this.codigopostal = codigopostal;
		this.telefono = telefono;
		this.email = email;
	}
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
	public String getCodigopostal() {
		return codigopostal;
	}
	public void setCodigopostal(String codigopostal) {
		this.codigopostal = codigopostal;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
