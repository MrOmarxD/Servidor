package beans;

public class Proveedor {
	private String nombre;
    private String encargado;
    private String categoria;
    private int telefono;
    private String email;
    
    // get / set
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEncargado() {
		return encargado;
	}
	public void setEncargado(String encargado) {
		this.encargado = encargado;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
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
	
	@Override
	public String toString() {
		return nombre+";"+encargado+";"+categoria+";"+telefono+";"+email;
	}
}
