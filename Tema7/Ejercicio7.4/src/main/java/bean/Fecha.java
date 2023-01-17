package bean;

public class Fecha {
	
	private int dia, mes, ano;
	
	// Constructor
	public Fecha(int dia, int mes, int ano) {
		this.dia=dia;
		this.mes=mes;
		this.ano=ano;
	}
	
	// Metodo valida fecha 
	public boolean correcta() {
		boolean validoFecha=false;

        if (dia<1 || dia>31) {
            validoFecha=true;
        }

        if (mes<1 || mes>12) {
            validoFecha=true;
        }

        if (mes==2 && dia==29 && ano % 400 == 0 || (ano % 4 == 0 && ano % 100 != 0) ) {
            validoFecha=true;
            }

        return validoFecha;
	}
	
	// toString
	public String toString() {
		return dia+"/"+mes+"/"+ano;
	}
	
	// get / set
	public int getDia() {
		return dia;
	}

	public int getMes() {
		return mes;
	}

	public int getAno() {
		return ano;
	}	
}
