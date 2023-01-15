package bean;

import java.util.ArrayList;

public class AlmacenPalabras {
	private final static ArrayList<String> ARRALMACEN = new ArrayList<String>(){{
        add("Hola");
        add("Adios");
        add("mesara");
        add("Calcetin");
        add("Monitor");
        add("Auriculares");
        add("Alfombrilla");
        add("Hipopotamo");
        add("Cables");
        add("Raton");
        add("Teclado");
        add("Machupichu");
        add("Medusa");
        add("Alioli");
    }}; 

    // Metodo que devuelve una palabra aleatoria del arraylist de la clase
    public static String dameUnaPalabra(){
        int numRandom = (int)(Math.random()*ARRALMACEN.size());
        String palabra = ARRALMACEN.get(numRandom).toUpperCase();
        
        return palabra;
    }
}
