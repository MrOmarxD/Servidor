package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import beans.Actividad;
import conex.ConexPoolBD;

public class ParticiparDao {

	// Metodo que inscribe a los alumnos en la tabla participar las tuplas que se encuentra en la session
	public void inscribir(ArrayList<Actividad> lstApuntarActividad, String dni) {
		try{    
			Connection con= ConexPoolBD.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO participa(actividad_id, alumno_dni, ultima_asistencia) VALUES(?, ?, ?)");
            for (Actividad actividad : lstApuntarActividad) {
                ps.setInt(1, actividad.getId());
                ps.setString(2, dni);
                SimpleDateFormat formateador = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                ps.setString(3, formateador.format(date));
                ps.executeUpdate();
            }
            ps.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo inscribir: " + ex);
        }
	}
}
