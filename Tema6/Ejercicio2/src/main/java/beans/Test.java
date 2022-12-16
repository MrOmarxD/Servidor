package beans;

import java.util.ArrayList;

public class Test {
	
	private int numPreguntas;
	private ArrayList<Pregunta> ltsPreguntas;
	private final static Pregunta[]  preguntas= new Pregunta[]{
			new Pregunta("DIRECTOR DE 'Psicosis'", "Era britanico", new String[]{"Alfred Hitchcock", "Orson Welles", "Stanley Kubrick"}, 0),
			new Pregunta("PELICULA MAS OSCARIZADA", "11 oscar en 1959", new String[]{"Amadeus", "Benhur", "West Side Story", "El Padrino II"}, 1),
			new Pregunta(" ¿Quién dirigió Reservoir Dogs?", "Adivina", new String[]{"Quentin Tarantino", "Christopher Nolan"}, 0)
	};
	
	// Constructor
	public Test(int cant){
		
	}
}