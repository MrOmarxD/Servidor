package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.sql.DataSource;

import beans.Alumno;
import conex.ConexPoolBD;

public class AlumnosDao {
	private ConexPoolBD bdConex;
	private DataSource ds;
	private Connection con;
		
	public AlumnosDao() throws ServletException {
		bdConex = new ConexPoolBD();
		con = bdConex.getCon();
		ds = bdConex.getDs();
	}
	
	public Alumno getAlumno(String dni) {
		try {
			con = ds.getConnection();
			String sql = "SELECT * FROM ALUMNO where DNI=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, dni);
			
			ResultSet rs = pst.executeQuery();
			if (rs.next())
				return new Alumno(dni, rs.getString("apellidos"), rs.getString("nombre"), rs.getString("telefono"), rs.getString("email"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
