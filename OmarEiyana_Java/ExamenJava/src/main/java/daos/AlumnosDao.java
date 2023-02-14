package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public ArrayList<Alumno> lstAlumno() throws ServletException {
		ArrayList<Alumno> arr = new ArrayList<Alumno>();
		try {
			con = ds.getConnection();
			String sql = "SELECT * FROM alumno";
			PreparedStatement pst = con.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Alumno al = new Alumno();
				al.setDni(rs.getString("dni"));
				al.setApellidos(rs.getString("apellidos"));
				al.setNombre(rs.getString("nombre"));
				al.setEmail(rs.getString("email"));
				al.setTelefono(rs.getString("telefono"));
				arr.add(al);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (arr.size() > 0) 
			return arr;
		return null;
	}
}
