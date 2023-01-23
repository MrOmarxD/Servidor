package beans;

import java.util.HashMap;

public class CarroCompra {
	
	private HashMap carro= new HashMap();

	public HashMap getCarro() {
		return carro;
	}

	public void setCarro(HashMap carro) {
		this.carro = carro;
	}

	
	
	public void aniadeLinea(LineaPedido linea) {
		
	}

	public void borraLinea (int iditem) {
		
	}
	public LineaPedido getLineaPedido(int iditem) {
		return null;
		
	}
//	public Collection<> getLineasPedido(){
//		
//	}
	public double total() {
		return 0;
		
	}
	public void removeAll() {
		
		carro.clear();
	}
	public boolean vacio() {
		
		if(carro.isEmpty())
			return true;	
		else
			return false;
		
	}
	

}
