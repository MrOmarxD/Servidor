package beans;

public class Imagen {
    private String ruta;
    private String nombre;
    private long tamanio;

    // Constructor
    public Imagen(String ruta, String nombre, long tamanio) {
        this.ruta = ruta;
        this.nombre = nombre;
        this.tamanio = tamanio;
    }
    
    // Metodo 
    public String tamanioDesglosado(){
        String msg = "";
        double bytes = tamanio%1024;
        msg = bytes+" bytes ";
        if(tamanio>1024){
            double Kb = tamanio/1024%1024;
            msg=Kb+" Kb "+msg;
        }
        if(tamanio>(1024*1024)){
            double Mb = tamanio/1024/1024%1024;
            msg=Mb+" Mb "+msg;
        }
        return msg;
    }
    
    // get/set
    public String getRuta() {
        return ruta;
    }

    public String getNombre() {
        return nombre;
    }

    public long getTamanyo() {
        return tamanio;
    }
}