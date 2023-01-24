package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Actividad;
import beans.Alumno;
import beans.Impartidor;
import conex.ConexPoolBD;

public class ActividadesDao {
	
	// Lista todas las actividades que participa el alumno pasado como parametro
	public ArrayList<Actividad> obtenerActividadesParticipa (Alumno alumno) {
		
		ArrayList<Actividad> arrActividades = new ArrayList<Actividad>();
	
		try {
			Connection con= ConexPoolBD.getDataSource().getConnection();
			String sql="SELECT id, impartidor_id, nombre, coste_mensual, capacidad "
					+ "FROM actividad, participa WHERE id = actividad_id AND "
					+ "alumno_dni ='" + alumno.getDni() + "'";
			Statement st= con.createStatement();
	
			ResultSet rs= st.executeQuery(sql);
		
			
			while(rs.next()) {
				Actividad actividad = new Actividad();
				actividad.setId(rs.getInt("id"));
				Impartidor impartidor = ImpartidoresDao.getImpartidor(rs.getString("impartidor_id"));
				actividad.setImpartidor(impartidor);
				actividad.setNombre(rs.getString("nombre"));
				actividad.setCoste_mensual(rs.getDouble("coste_mensual"));
				actividad.setCapacidad(rs.getInt("capacidad"));
				
				arrActividades.add(actividad);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrActividades;
	}
	
	
	// Metodo recibe un alumno y devuelve una lista con las actividades en las que él
	// puede matricularse. Un alumno puede matricularse en aquellas actividades en las
	// que NO está inscrito, cuyo nombre no coincide con ninguna de las actividades en
	// las que sí está inscrito, y con alguna plaza libre
	public ArrayList<Actividad> obtenerActividadesLibresNoParticipa (Alumno alumno) {
		
		ArrayList<Actividad> arrActividades = new ArrayList<Actividad>();
	
		try {
			Connection con= ConexPoolBD.getDataSource().getConnection();
			String sql="SELECT id, impartidor_id, nombre, coste_mensual, capacidad "
					+ "FROM actividad, participa 'participa1' WHERE id = participa1.actividad_id AND "
					+ "alumno_dni !='" + alumno.getDni() + "'  AND capacidad<(SELECT COUNT(*) "
					+ "FROM participa 'participa2' WHERE id = participa2.actividad_id)";
			Statement st= con.createStatement();
	
			ResultSet rs= st.executeQuery(sql);
		
			
			while(rs.next()) {
				Actividad actividad = new Actividad();
				actividad.setId(rs.getInt("id"));
				Impartidor impartidor = ImpartidoresDao.getImpartidor(rs.getString("impartidor_id"));
				actividad.setImpartidor(impartidor);
				actividad.setNombre(rs.getString("nombre"));
				actividad.setCoste_mensual(rs.getDouble("coste_mensual"));
				actividad.setCapacidad(rs.getInt("capacidad"));
				
				arrActividades.add(actividad);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrActividades;
	}
	
	//Metodo que devuelve un objeto Actividad si este existe pasandole como parametro una id
	public Actividad getActividad (Integer id) {
		Actividad actividad = null;
        try {
        	Connection con= ConexPoolBD.getDataSource().getConnection();
        	String sql = "SELECT * FROM actividad WHERE id = '" + id + "'";
        	Statement st= con.createStatement();
	
			ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
            	actividad = new Actividad();
            	actividad.setId(rs.getInt("id"));
				Impartidor impartidor = ImpartidoresDao.getImpartidor(rs.getString("impartidor_id"));
				actividad.setImpartidor(impartidor);
				actividad.setNombre(rs.getString("nombre"));
				actividad.setCoste_mensual(rs.getDouble("coste_mensual"));
				actividad.setCapacidad(rs.getInt("capacidad"));
            }
            rs.close();
            st.close();
            con.close();
        }
        catch (SQLException ex) {
            System.err.println("Error en metodo getActividad: " + ex);
            return null;
        }
		return actividad;
	}
}
