package beans;

public class ActividadesInscritas {
	private int id;
	private String nombre, nombreImpartidor;
	private Float costeMensual;
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
	public Float getCosteMensual() {
		return costeMensual;
	}
	public void setCosteMensual(Float costeMensual) {
		this.costeMensual = costeMensual;
	}
	public ActividadesInscritas(int id, String nombre, String nombreImpartidor, Float costeMensual) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nombreImpartidor = nombreImpartidor;
		this.costeMensual = costeMensual;
	}
	
	
	
	
}
