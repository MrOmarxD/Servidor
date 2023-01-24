package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Alumno;
import conex.ConexPoolBD;

public class AlumnosDao {

	
	// Método que comprueba si existe el dni introducido por el usuario buscando en la
	// tabla de Alumnos por el dni.
	public static Alumno getAlumno(String dni) {
		Alumno alumno = null;
        try {
        	Connection con= ConexPoolBD.getDataSource().getConnection();
        	String sql = "SELECT * FROM alumno WHERE dni = '" + dni + "'";
        	Statement st= con.createStatement();
	
			ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
            	alumno = new Alumno();
            	alumno.setDni(rs.getString("dni"));
            	alumno.setApellidos(rs.getString("apellidos"));
            	alumno.setNombre(rs.getString("nombre"));
            	alumno.setTelefono(rs.getString("telefono"));
            	alumno.setEmail(rs.getString("email"));
            }
            rs.close();
            st.close();
            con.close();
        }
        catch (SQLException ex) {
            System.err.println("Error en metodo getAlumno: " + ex);
            return null;
        }
		return alumno;
	}
}
