package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Alumno;
import conex.ConexPoolDB;

public class AlumnosDao {

	public static Alumno getAlumno(String dni) {
		Alumno alu = null;
		String sql = "SELECT * FROM alumno WHERE dni = ?";
		try {
			Connection con = ConexPoolDB.getDataSource().getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, dni);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				alu = new Alumno(rs.getString("dni"), rs.getString("apellidos"), rs.getString("nombre"), rs.getString("telefono"), rs.getString("email"));
			}
			
			rs.close();
			st.close();
			con.close();
		}catch (SQLException ex) {
            System.err.println("Error en metodo getAlumno: " + ex);
        }
		
		return alu;
	}
}
