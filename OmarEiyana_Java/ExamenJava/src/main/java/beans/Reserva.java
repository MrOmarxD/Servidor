package beans;

public class Reserva {
	private String id_reserva;
	private int pagado;
	private String nombreCliente;
	
	// get / set
	public String getId_reserva() {
		return id_reserva;
	}
	public void setId_reserva(String id_reserva) {
		this.id_reserva = id_reserva;
	}
	public int getPagado() {
		return pagado;
	}
	public void setPagado(int pagado) {
		this.pagado = pagado;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
}
