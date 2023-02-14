package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Alumno;
import beans.Impartidor;
import conex.ConexPoolDB;

public class ImpartidoresDao {

	public static Impartidor getImpartidor(String id) {
		Impartidor imp = null;
		String sql = "SELECT * FROM impartidor WHERE id = ?";
		try {
			Connection con = ConexPoolDB.getDataSource().getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, id);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				imp = new Impartidor(rs.getInt("id"), rs.getString("apellido"), rs.getString("nombre")); 
			}
			
			rs.close();
			st.close();
			con.close();
		}catch (SQLException ex) {
            System.err.println("Error en metodo getImpartidor: " + ex);
        }
		
		
		return imp;
	}
}
