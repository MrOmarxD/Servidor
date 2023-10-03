package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Categoria;
import conex.ConexPoolBD;

public class CategoriaDAO {

	
	// M�todo buscaCategoriaPorId devuelve la categoria cuyo id se recibe como parametro o null
	public Categoria buscaCategoriaPorId(int idcategoria) {
		Categoria categoria = new Categoria();
		String sql = "SELECT * FROM categoria WHERE id = '" + idcategoria + "'";
        try {
        	Connection con = ConexPoolBD.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	categoria.setId(idcategoria);
            	categoria.setNombre(rs.getString("nombre"));
            }
            rs.close();
            st.close();
            con.close();
        }
        catch (SQLException ex) {
            System.err.println("Error en metodo buscaCategoriaPorId: " + ex);
        }
		return categoria;
	}
}
