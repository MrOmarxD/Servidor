package beans;

import java.util.ArrayList;

public class AlmacenMatrices {
	
	private static ArrayList<int[][]> listaMatrices;
	private static int numMatricesTotal = 0;
	
	//Constructores
	public AlmacenMatrices() {
		numMatricesTotal++;
	}
	//get/set
	public static ArrayList<int[][]> getListaMatrices() {
		return listaMatrices;
	}

	public static void setListaMatrices(ArrayList<int[][]> listaMatrices) {
		AlmacenMatrices.listaMatrices = listaMatrices;
	}

	public static int getNumMatricesTotal() {
		return numMatricesTotal;
	}

	public static void setNumMatricesTotal(int numMatricesTotal) {
		AlmacenMatrices.numMatricesTotal = numMatricesTotal;
	}
}
