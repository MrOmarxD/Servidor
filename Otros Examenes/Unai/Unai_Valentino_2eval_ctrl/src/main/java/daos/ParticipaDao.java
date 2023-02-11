package daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Actividad;
import beans.Alumno;
import conex.ConexPoolDB;

public class ParticipaDao {

	public static void inscribir (Alumno alu, Actividad acti) {
		String sql = "INSERT INTO participa VALUES(?, ?, ?)";
		try {
			Connection con = ConexPoolDB.getDataSource().getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, acti.getId());
			st.setString(2, alu.getDni());
			Date now = new Date(System.currentTimeMillis());
			st.setDate(3, now);
			st.executeUpdate();
			
			st.close();
			con.close();
		}catch (SQLException ex) {
            System.err.println("Error en metodo inscribir: " + ex);
        }
	}
}
