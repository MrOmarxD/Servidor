package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Producto;
import conex.ConexPoolBD;

public class ProductoDAO {
	private ConexPoolBD gestorBDXml = new ConexPoolBD();
	private CategoriaDAO categoriaDao;
	
	// Metodo que lista todos los productos que hay
	public ArrayList<Producto> lstProductos() {
		categoriaDao = new CategoriaDAO();
		ArrayList<Producto> lstproductos = new ArrayList<Producto>();
		 try{                  		         
			 	Connection con = gestorBDXml.getDataSource().getConnection(); 
	        	String sql = "SELECT * FROM producto";
	        	Statement st = con.createStatement();
	            ResultSet rs = st.executeQuery(sql);            
	            while(rs.next()) { 
	            	Producto p = new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"), rs.getDouble("precio"), categoriaDao.buscaCategoriaPorId(rs.getInt("idCategoria")));
	            	lstproductos.add(p);
	            }
	            rs.close();
	            st.close();
	            con.close();
	         }
	         catch (Exception e)  {
	             System.err.println("Error en lstProductos: " + e);
	         } 
		 return lstproductos;
	}
	
	// Metodo que elimina un producto pasado por id
	public void borrarProducto (int id){
		try{    
			Connection con = gestorBDXml.getDataSource().getConnection(); 
            PreparedStatement ps = con.prepareStatement("DELETE FROM producto WHERE id=?");
            ps.setInt(1, id); 
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo borrarProducto: " + ex);
        }
	}
}
