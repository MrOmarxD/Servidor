package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.sql.DataSource;

import beans.Actividad;
import beans.Alumno;
import beans.Impartidor;
import conex.ConexPoolBD;

public class ImpartidoresDao {

	private ConexPoolBD bdConex;
	private DataSource ds;
	private Connection con;
		
	public ImpartidoresDao() throws ServletException {
		bdConex = new ConexPoolBD();
		con = bdConex.getCon();
		ds = bdConex.getDs();
	}
	
	public Impartidor getImpartidor(String id) {
		try {
			con = ds.getConnection();
			String sql = "SELECT * FROM IMPARTIDOR where id=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, id+"");
			
			ResultSet rs = pst.executeQuery();
			if (rs.next())
				return new Impartidor(Integer.parseInt(id), rs.getString("apellido"), rs.getString("nombre"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
