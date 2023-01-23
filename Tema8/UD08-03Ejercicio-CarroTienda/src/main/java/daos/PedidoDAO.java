package daos;

import java.util.Map;

import beans.Item;
import beans.LineaPedido;
import beans.Pedido;

public class PedidoDAO {
	
	
	/**
	 * Método: que devuelve un HashMap con todos los ítems en venta de la base de datos
	 * @return
	 */
	public Map todosItems() {
		return null;
		
	}
	
	/**
	 * Método: Devuelve el item cuyo id se recibe como parámetro o null
	 * @param iditem
	 * @return
	 */
	public Item buscaItemPorId(int iditem) {
		return null;
		
	}
	
	
	
	/**
	 * Método: que recibe un pedido y lo almacena en la bd (Debe utilizar consultas precompiladas)
	 * @param p
	 */
	public void guardaPedido(Pedido pedido) {
		
	}
	
	
	/**
	 * Método: que recibe una línea de pedido y la almacena en la bd (Debe utilizar consultas precompiladas)
	 * @param l
	 */
	public void guardaLineaPedido (LineaPedido lineaPedido) {
		
	}
	
	

}
