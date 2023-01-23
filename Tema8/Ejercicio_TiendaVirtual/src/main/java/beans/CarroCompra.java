package beans;

import java.util.ArrayList;
import java.util.HashMap;

public class CarroCompra {
	private HashMap<Integer, LineaPedido> carro = new HashMap<Integer, LineaPedido>();
	
	// Metodo aniadeLinea, añade lineas al atributo carro, en caso de que el idItem ya
	// se encuentra se le suma la cantidad
	public void aniadeLinea(LineaPedido linea) {
		int idItem = linea.getItem().getIdItem();
		if(carro.containsKey(idItem))
			linea.setCantidad(linea.getCantidad() + carro.get(idItem).getCantidad());
		
		carro.put(idItem, linea);
	}
	
	// Metodo borraLinea
	public void borraLinea(int iditem) {
		if(carro.containsKey(iditem))
			carro.remove(iditem);
	}
	
	// Metodo getLineaPedido, devuelve una LineaPedido pasandole un iditem
	public LineaPedido getLineaPedido(int iditem){
		LineaPedido linea = carro.get(iditem);
		return linea;
	}
	
	// Metodo getLineasPedido, devulve todas las LineaPedido que se encuentran en carro
	public ArrayList<LineaPedido> getLineasPedido(){
		ArrayList<LineaPedido> lstLineasPedido = new ArrayList<LineaPedido>();
		for(LineaPedido linea : carro.values()){
			lstLineasPedido.add(linea);
		}
		return lstLineasPedido;
	}
	
	// Metodo total, devuelve el precio total actual del carro
	public double total() {
		double total = 0;
		for(LineaPedido linea : carro.values()){
			total+=linea.getCantidad();
		}
		return total;
	}
	
	// Metodo removeAll que vacía el carro
	public void removeAll() {
		carro.clear();
	}
	
	// Metodo vacio, que devuelve si el carro está vacío o no
	public boolean vacio() {
		if(carro.size()>0)
			return false;
		return true;
	}
	
	// get / set
	public HashMap<Integer, LineaPedido> getCarro() {
		return carro;
	}

	public void setCarro(HashMap<Integer, LineaPedido> carro) {
		this.carro = carro;
	}
}
