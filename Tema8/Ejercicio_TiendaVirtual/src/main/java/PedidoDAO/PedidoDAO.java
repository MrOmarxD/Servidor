package PedidoDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import beans.Item;
import beans.Pedido;
import conex.BDConex;

public class PedidoDAO {
	private BDConex bd = new BDConex();
	
	// Método todosItems que devuelve un HashMap con todos los ítems en venta de la base de datos
	public HashMap<Integer, Item> todosItems() {
		HashMap<Integer, Item> lstItems = new HashMap<Integer, Item>();
		String sql = "SELECT * FROM items";
        Connection con;
        try {
            con = bd.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	Item item = new Item();
            	item.setIdItem(rs.getInt("id"));
            	item.setNombre(rs.getString("nombre"));
            	item.setPrecio(rs.getDouble("precio"));
            	lstItems.put(item.getIdItem(), item);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BDConex.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		return lstItems;
	}
	
	// Método buscaItemPorId devuelve el item cuyo id se recibe como parámetro o null
	public Item buscaItemPorId(int iditem) {
		Item item = new Item();
		String sql = "SELECT * FROM items WHERE id = '" + iditem + "'";
        try {
            Connection con = bd.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	item.setIdItem(iditem);
            	item.setNombre(rs.getString("nombre"));
            	item.setPrecio(rs.getDouble("precio"));
            }
            rs.close();
            st.close();
            con.close();
        }
        catch (SQLException ex) {
            System.err.println("Error en metodo buscaItemPorId: " + ex);
        }
		return item;
	}
	
	// Método guardaPedido que recibe un pedido y lo almacena en la bd
	public void guardaPedido(Pedido p) {
		
	}
}
