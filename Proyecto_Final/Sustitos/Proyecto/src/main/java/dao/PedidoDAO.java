package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import beans.Producto;
import conex.ConexPoolBD;

public class PedidoDAO {
	private CategoriaDAO catDao;
	
	// Metodo buscaProductoPorId devuelve el producto cuyo id se recibe como parametro o null
	public Producto buscaProductoPorId(int idproducto) {
		Producto producto = new Producto();
		String sql = "SELECT * FROM producto WHERE id = '" + idproducto + "'";
        try {
        	Connection con = ConexPoolBD.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	producto.setId(idproducto);
            	producto.setNombre(rs.getString("nombre"));
            	producto.setDescripcion(rs.getString("descripcion"));
            	producto.setPrecio(rs.getDouble("precio"));
            	catDao = new CategoriaDAO();
            	producto.setCat(catDao.buscaCategoriaPorId(rs.getInt("categoria")));
            }
            rs.close();
            st.close();
            con.close();
        }
        catch (SQLException ex) {
            System.err.println("Error en metodo buscaProductoPorId: " + ex);
        }
		return producto;
	}
}
