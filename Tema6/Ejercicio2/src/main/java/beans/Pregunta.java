package beans;

public class Pregunta {

	private String enunciado;
	private String pista;
	private String[] respuestas;
	private int respuestaCorrecta;
	
	// Constructor
	public Pregunta(String enunciado, String pista, String[] respuestas, int respuestaCorrecta) {
		this.enunciado = enunciado;
		this.pista = pista;
		this.respuestas = respuestas;
		this.respuestaCorrecta = respuestaCorrecta;
	}

	
	//get/set
	public String getEnunciado() {
		return enunciado;
	}

	public String getPista() {
		return pista;
	}

	public String[] getRespuestas() {
		return respuestas;
	}

	public int getRespuestaCorrecta() {
		return respuestaCorrecta;
	}
}
