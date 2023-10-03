package beans;

public class Usuario {

	private int id; 
	private String nombre;
    private String apellido;
    private String contrasena;
    private String imagen;
    private String descripcion;
    private String direccion;
    private int cp; 
    private String municipio;
    private String provincia;
    private String pais;
    private int telefono;  
	private String email;
    private int validado; 
    private String strValidado;

	/**
	 * Constructor Datos Vacio
	 */
	public Usuario() { 
		id = 0;
        nombre = new String();
		apellido = new String();
		contrasena = new String();
        imagen = new String();
        descripcion = new String();
        direccion = new String(); 
		cp = 00000;
        municipio = new String();
        provincia = new String();
        pais = new String(); 
		telefono = 000000000;
		email = new String(); 
		validado = 0;
        strValidado = new String();

	}

	public Usuario(String nombre, String apellido, String contrasena, String imagen, String descripcion, String direccion, int cp, String municipio, String provincia, String pais, int telefono, String email, int validado, String strValidado) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasena = contrasena;
		this.imagen = imagen;
        this.descripcion = descripcion;
        this.cp = cp;
        this.municipio = municipio;
        this.provincia = provincia;
        this.pais = pais;
        this.telefono = telefono;
        this.email = email;
        this.validado = validado;
        this.strValidado = strValidado; 
	}
	public Usuario(int id, String nombre, String apellido, String contrasena, String imagen, String descripcion, String direccion, int cp, String municipio, String provincia, String pais, int telefono, String email, int validado, String strValidado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasena = contrasena;
		this.imagen = imagen;
        this.descripcion = descripcion;
        this.cp = cp;
        this.municipio = municipio;
        this.provincia = provincia;
        this.pais = pais;
        this.telefono = telefono;
        this.email = email;
        this.validado = validado;
        this.strValidado = strValidado; 
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
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

	public int getValidado() {
		return validado;
	}

	public void setValidado(int validado) {
		this.validado = validado;
	}

	public String getStrValidado() {
		return strValidado;
	}

	public void setStrValidado(String strValidado) {
		this.strValidado = strValidado;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", contrasena=" + contrasena
				+ ", imagen=" + imagen + ", descripcion=" + descripcion + ", direccion=" + direccion + ", cp=" + cp
				+ ", municipio=" + municipio + ", provincia=" + provincia + ", pais=" + pais + ", telefono=" + telefono
				+ ", email=" + email + ", validado=" + validado + ", strValidado=" + strValidado + "]";
	}

	 

}
