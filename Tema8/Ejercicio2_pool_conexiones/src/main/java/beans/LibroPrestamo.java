package beans;

import java.util.Objects;

public class LibroPrestamo {
	private int idPrestamo;
    private int fecha;
    private String titulo;
    
    // Constructores
    public LibroPrestamo(int idPrestamo, int fecha, String titulo) {
		super();
		this.idPrestamo = idPrestamo;
		this.fecha = fecha;
		this.titulo = titulo;
	}
    
    public LibroPrestamo() {
    	
    }
    
    // get/set
    public int getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	// equals
	@Override
	public int hashCode() {
		return Objects.hash(fecha, idPrestamo, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LibroPrestamo other = (LibroPrestamo) obj;
		return fecha == other.fecha && idPrestamo == other.idPrestamo && Objects.equals(titulo, other.titulo);
	}
}
