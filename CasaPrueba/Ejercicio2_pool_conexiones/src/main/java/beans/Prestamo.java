package beans;

/**
 *
 * @author Omar
 */
public class Prestamo {
    private int idPrestamo;
    private String fecha;
    private int idlibro;

    // Constructores
    public Prestamo(int idPrestamo, String fecha, int idlibro) {
        this.idPrestamo = idPrestamo;
        this.fecha = fecha;
        this.idlibro = idlibro;
    }

    public Prestamo(String fecha, int idlibro) {
        this.fecha = fecha;
        this.idlibro = idlibro;
    }

    public Prestamo() {
    }
    
    // get/set
    public int getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getIdlibro() {
		return idlibro;
	}

	public void setIdlibro(int idlibro) {
		this.idlibro = idlibro;
	}
}
