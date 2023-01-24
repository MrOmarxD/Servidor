package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Impartidor;
import conex.ConexPoolBD;

public class ImpartidoresDao {

	
	// Método que comprueba si existe el impartidor en la tabla de Impartidores en base
	// al id que introduce el usuario por pantalla.
	public static Impartidor getImpartidor(String id) {
		Impartidor impartidor = null;
        try {
        	Connection con= ConexPoolBD.getDataSource().getConnection();
        	String sql = "SELECT * FROM impartidor WHERE id = '" + id + "'";
        	Statement st= con.createStatement();
	
			ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
            	impartidor = new Impartidor();
            	impartidor.setId(rs.getInt("id"));
            	impartidor.setApellido(rs.getString("apellido"));
            	impartidor.setNombre(rs.getString("nombre"));
            }
            rs.close();
            st.close();
            con.close();
        }
        catch (SQLException ex) {
            System.err.println("Error en metodo getImpartidor: " + ex);
            return null;
        }
		return impartidor;
	}
}
