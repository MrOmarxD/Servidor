package bean;

import exceptions.BadBetException;

public class Apuesta {

	private String nombre;
	private int numAdministrador, numReintegro;
	private int[] numJugados;
	
	// Constructor
	public Apuesta(String nombre, int numAdministrador, int[]numJugados, int numReintegro) throws BadBetException {
		
		String errores="";
		
		
		if(numJugados.length!=6) {
			errores+="No se han recibido 6 números / ";
		}
		
		for (int i = 0; i < numJugados.length; i++) {
			for (int j = i+1; j < numJugados.length; j++) {
				if(numJugados[j]==numJugados[i]) {
					errores+="Existen números repetidos / ";
				}
			}
		}
		
		for(int i: numJugados) {
			if(i<1||i>49) {
				errores+="Números no comprenden rango 1-49 / ";
			}
		}
		
		
		if(numReintegro<0||numReintegro>9) {
			errores+="Reintegro no comprende rango 0-9 / ";
		}
		
		if(errores!="") {
			throw new BadBetException(errores.substring(0,errores.lastIndexOf('/')-1));
		}
		
		this.nombre=nombre;
		this.numAdministrador=numAdministrador;
		this.numJugados=numJugados;
		this.numReintegro=numReintegro;
	}
	
	// toSting
	public String toSting() {
		String apuesta="Números: ";
		for(int i:numJugados) {
			apuesta+=i+" - ";
		}
		apuesta=apuesta.substring(0, apuesta.lastIndexOf('-')-1);
		apuesta+= " Reintegro: "+numReintegro;
		return apuesta; 
	}
	
	// get / set
	public String getNombre() {
		return nombre;
	}

	public int getNumAdministrador() {
		return numAdministrador;
	}

	public int getNumReintegro() {
		return numReintegro;
	}

	public int[] getNumJugados() {
		return numJugados;
	}

}
