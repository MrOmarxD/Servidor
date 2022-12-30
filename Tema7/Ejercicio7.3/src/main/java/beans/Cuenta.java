package beans;

public class Cuenta {
	
	private String titular;
    private float saldo;
    
    // Metodo gastar devuelve si se ha podido retirar dinero, si es asi lo resta del saldo
    public boolean gastar(float cantidad){
        if(this.saldo < cantidad){
            this.saldo-= cantidad;
            return true;
        }
        return false;
    }
    
    // Metodo ingresar aumneta el saldo la cantidad de que se especifique
    public void ingresar(float cantidad){
        this.saldo+= cantidad;
    }
    
    // Get/Set
    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
