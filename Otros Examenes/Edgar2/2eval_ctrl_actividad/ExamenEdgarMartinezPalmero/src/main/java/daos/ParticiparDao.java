package daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.sql.DataSource;

import beans.Alumno;
import beans.Impartidor;
import conex.ConexPoolBD;

public class ParticiparDao {

	private ConexPoolBD bdConex;
	private DataSource ds;
	private Connection con;
		
	public ParticiparDao() throws ServletException {
		bdConex = new ConexPoolBD();
		con = bdConex.getCon();
		ds = bdConex.getDs();
	}
	
	public void guardarActividades(HashSet<Integer> idActividades, Alumno alumno) {
		String sql = "INSERT INTO participa(actividad_id, alumno_dni, ultima_asistencia)"
				+ "VALUES(?, ?, ?)";
        try {
            Connection con = ds.getConnection();
            PreparedStatement st = con.prepareStatement(sql);
            
            Iterator<Integer> it = idActividades.iterator();
            st.setString(2, alumno.getDni());
            while(it.hasNext()) {
            	st.setInt(1, it.next());
            	st.setDate(3, new Date(System.currentTimeMillis()));
            	st.executeUpdate();
            }
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo guardarActividades: " + ex);
        }
	}

}
