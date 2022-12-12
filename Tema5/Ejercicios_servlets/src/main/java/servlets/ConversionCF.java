package servlets;

public class ConversionCF {
	
	private double celsius;
	private double fahrenheit;
	
	//Constructores
	public ConversionCF(double temperatura, char tipo) {
		if(tipo=='c') {
			this.celsius = temperatura;
			this.fahrenheit = 32+temperatura*9/5;
		}
		else {
			this.celsius = (temperatura-32)*5/9;
			this.fahrenheit = temperatura;
		}
	}

	//get/set
	public double getCelsius() {
		return celsius;
	}

	public double getFahrenheit() {
		return fahrenheit;
	}
}
