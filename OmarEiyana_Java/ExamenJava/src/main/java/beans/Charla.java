package beans;

public class Charla {
	private String id_charla;
	private HoraCharla horacharla;
	private String tema;
	private String id_sala;
	private Double tarifa;
	private int reservas;
	
	// get / set
	public String getId_charla() {
		return id_charla;
	}
	public void setId_charla(String id_charla) {
		this.id_charla = id_charla;
	}
	public HoraCharla getHoracharla() {
		return horacharla;
	}
	public void setHoracharla(HoraCharla horacharla) {
		this.horacharla = horacharla;
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	public String getId_sala() {
		return id_sala;
	}
	public void setId_sala(String id_sala) {
		this.id_sala = id_sala;
	}
	public Double getTarifa() {
		return tarifa;
	}
	public void setTarifa(Double tarifa) {
		this.tarifa = tarifa;
	}
	public int getReservas() {
		return reservas;
	}
	public void setReservas(int reservas) {
		this.reservas = reservas;
	}
}
