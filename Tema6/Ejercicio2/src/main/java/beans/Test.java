package beans;

import java.util.ArrayList;

public class Test {
	
	private int numPreguntas;
	private ArrayList<Pregunta> ltsPreguntas;
	private final static Pregunta[]  PREGUNTAS = new Pregunta[]{
			new Pregunta("DIRECTOR DE 'Psicosis'", "Era britanico", new String[]{"Alfred Hitchcock", "Orson Welles", "Stanley Kubrick"}, 0),
			new Pregunta("PELICULA MAS OSCARIZADA", "11 oscar en 1959", new String[]{"Amadeus", "Benhur", "West Side Story", "El Padrino II"}, 1),
			new Pregunta(" ¿Quién dirigió Reservoir Dogs?", "Adivina", new String[]{"Quentin Tarantino", "Christopher Nolan"}, 0)
	};
	
	// Constructor
	public Test(int cant) {
        if(cant > PREGUNTAS.length){
        	cant = PREGUNTAS.length;
            for (int i = 0; i < PREGUNTAS.length; i++) {
            	ltsPreguntas.add(PREGUNTAS[i]);
            }
        } else {
            this.numPreguntas = cant;
            ArrayList<Integer> idicesDePreguntas = new ArrayList<>();
            ltsPreguntas = new ArrayList<Pregunta>();
            for (int i = 0; i < cant;) {
                int num = (int)(Math.random()*cant);
                if(!idicesDePreguntas.contains(num)){
                	idicesDePreguntas.add(num);
                    ltsPreguntas.add(PREGUNTAS[num]);
                    i++;
                }
            }
        }
    }
    
	// Comprobar las respustas del usuario, devuelve num de aciertos
    public int comprobar(ArrayList<Integer> respuestas){
        int i = 0;
        int aciertos = 0;
        for (Pregunta pregunta: ltsPreguntas) {
            if(pregunta.getRespuestaCorrecta() == respuestas.get(i)){
            	aciertos++;
            }
            i++;
        }
        return aciertos;
    }
    
    // get/set
    public int getNumPreguntas() {
		return numPreguntas;
	}

	public ArrayList<Pregunta> getLtsPreguntas() {
		return ltsPreguntas;
	}
	
	public static Pregunta[] getPreguntas() {
		return PREGUNTAS;
	}
}