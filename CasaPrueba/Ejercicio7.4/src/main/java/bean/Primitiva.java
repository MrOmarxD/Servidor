package bean;

import java.util.ArrayList;
import java.util.Iterator;

import exceptions.BadBetException;

public class Primitiva {

	private Fecha fecha;
	private int[] numGanadores;
	private int numReintegroGanador;
	
	public Primitiva(Fecha fecha) {
		this.fecha=fecha;
		this.numGanadores=new int[6];
		
		ArrayList<Integer>arrListAux=new ArrayList<Integer>();
		for (int i = 0; i < numGanadores.length;) {
			int num=(int)(Math.random()*49)+1;
			if(!arrListAux.contains(num)) {
				arrListAux.add(num);
				i++;
			}
		}
		
		for (int i = 0; i < arrListAux.size(); i++) {
			numGanadores[i]=arrListAux.get(i);
		}
		
		burbujon();
		
		this.numReintegroGanador=(int)(Math.random()*10);
	}
	
	
	private void burbujon() {
		for (int i = 0; i < numGanadores.length-1; i++) {
			for (int j = i+1; j < numGanadores.length; j++) {
				if(numGanadores[i]>numGanadores[j]) {
					int aux=numGanadores[i];
					numGanadores[i]=numGanadores[j];
					numGanadores[j]=aux;
				}
			}
		}
	}
	
	private boolean numTocado(int num) {
		for(int i: numGanadores) {
			if(num==i) {
				return true;
			}
		}
		return false;
	}
	
	public String resultApuesta(Apuesta apuesta) {
		String resultado="Aciertos: ";
		
		for(int i:apuesta.getNumJugados()) {
			if(numTocado(i)) {
				resultado+=i+" ";
			}
		}
		if(resultado.equals("Aciertos: ")) {
			resultado="Sin aciertos ";
		}
		
		if(apuesta.getNumReintegro()==this.numReintegroGanador) {
			resultado+="REINTEGRO ACERTADO!";
		}else {
			resultado+="Sin reintegro";
		}
		
		return resultado;
	}
	
	private String verResultadoGanador() {
		String resultado="[PRIMITIVA GANADORA] Números: ";
		for(int i: numGanadores) {
			resultado+=i+" ";
		}
		resultado+="Reintegro: "+this.numReintegroGanador;
		return resultado;
	}
		
	
	public Fecha getFecha() {
		return fecha;
	}	
}
