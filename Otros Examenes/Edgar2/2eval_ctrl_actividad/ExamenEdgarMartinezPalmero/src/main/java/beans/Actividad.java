package beans;

public class Actividad {

	private Integer id;
	private Impartidor impartidor;
	private String nombre;
	private Float coste_mensual;
	private Integer capacidad;

	/**
	 * Constructor Datos Vacio
	 */
	public Actividad() {
		id = 0;
		impartidor = new Impartidor();
		nombre = new String();
		coste_mensual = 0.0f;
		capacidad = 0;
	}

	/**
	 * @param id
	 * @param impartidor_id
	 * @param nombre
	 * @param coste_mensual
	 * @param capacidad
	 */
	public Actividad(Integer id, Impartidor impartidor, String nombre, Float coste_mensual, Integer capacidad) {
		super();
		this.id = id;
		this.impartidor = impartidor;
		this.nombre = nombre;
		this.coste_mensual = coste_mensual;
		this.capacidad = capacidad;
	}

	/**
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public Impartidor getImpartidor() {
		return impartidor;
	}

	/**
	 * @param impartidor_id
	 */
	public void setImpartidor(Impartidor impartidor) {
		this.impartidor = impartidor;
	}

	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return
	 */
	public Float getCoste_mensual() {
		return coste_mensual;
	}

	/**
	 * @param coste_mensual
	 */
	public void setCoste_mensual(Float coste_mensual) {
		this.coste_mensual = coste_mensual;
	}

	/**
	 * @return
	 */
	public Integer getCapacidad() {
		return capacidad;
	}

	/**
	 * @param capacidad
	 */
	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	@Override
	public String toString() {
		return "Actividad [id=" + id + ", impartidor_id=" + impartidor.toString() + ", nombre=" + nombre
				+ ", coste_mensual=" + coste_mensual + ", capacidad=" + capacidad + "]";
	}

}
