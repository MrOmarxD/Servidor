package beans;

import java.util.Arrays;

public class Apuesta {
	
	private String apostante;
    private int administracion, reintegro;
    private int[] numeros;

    // Constructor
    public Apuesta(String apostante, int administracion, int reintegro, int[] numeros) throws BadBetException {
        //Tamaño array numeros de 6
        if(numeros.length!=6){
            throw new BadBetException("No hay 6 numeros");
        }
        
        //Numeros repetidos
        int[] aux=numeros;
        Arrays.sort(aux);
        int ant=0;
        for (int numero : aux) {
            if(numero==ant){
                throw new BadBetException("Numero "+numero+" repetido");
            }
        }
        
        //Limites 1-49
        if(aux[0]<1){
            throw new BadBetException("Numero "+aux[0]+" es menor a 1");
        }
        if(aux[aux.length-1]>49){
            throw new BadBetException("Numero "+aux[0]+" es menor a 1");
        }
        
        //Reintegro entre 0 y 9
        if(reintegro<0){
            throw new BadBetException("Reintegro "+reintegro+" es menor a 0");
        } else if(reintegro>9){
            throw new BadBetException("Reintegro "+reintegro+" es mayor a 9");
        }
        
        this.apostante = apostante;
        this.administracion = administracion;
        this.reintegro = reintegro;
        this.numeros = numeros;
    }
  
    // Metodo toString
    @Override
    public String toString() {
        return "Apuesta{" + "apostante=" + apostante + ", administracion=" + administracion + ", reintegro=" + reintegro + ", numeros=" + numeros + '}';
    }
}
