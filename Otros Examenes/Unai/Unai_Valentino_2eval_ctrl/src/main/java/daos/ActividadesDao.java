package daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import beans.Actividad;
import beans.Alumno;
import beans.Impartidor;
import conex.ConexPoolDB;

public class ActividadesDao {

	public static Actividad obtenerActividad (int id) {
		Actividad acti = null;
		String sql = "SELECT * FROM actividad WHERE id = ?";
		try {
			Connection con = ConexPoolDB.getDataSource().getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				acti = new Actividad();
				acti.setId(rs.getInt("id"));
				acti.setImpartidor(ImpartidoresDao.getImpartidor(rs.getString("impartidor_id")));
				acti.setNombre(rs.getString("nombre"));
				acti.setCoste_mensual(rs.getDouble("coste_mensual"));
				acti.setCapacidad(rs.getInt("capacidad"));
			}
			
			rs.close();
			st.close();
			con.close();
		}catch (SQLException ex) {
            System.err.println("Error en metodo obtenerActividad: " + ex);
        }
		return acti;
	}
	
	public static ArrayList<Actividad> obtenerActividadesParticipa (Alumno alu) {
		ArrayList<Actividad> arrActividad = new ArrayList<Actividad>();
		String sql = "SELECT actividad_id FROM participa WHERE alumno_dni = ?";
		try {
			Connection con = ConexPoolDB.getDataSource().getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, alu.getDni());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Actividad acti = obtenerActividad(rs.getInt("actividad_id"));
				arrActividad.add(acti);
			}
			
			rs.close();
			st.close();
			con.close();
		}catch (SQLException ex) {
            System.err.println("Error en metodo obtenerActividadesParticipa: " + ex);
        }
		
		return arrActividad;
	}
	
	public static ArrayList<Actividad> obtenerActividadesLibresNoParticipa(){
		ArrayList<Actividad> arrActividad = new ArrayList<Actividad>();
		String sql = "SELECT * FROM actividad";
		try {
			Connection con = ConexPoolDB.getDataSource().getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				int capacidad = rs.getInt("capacidad");
				
				if(actividadLibre(id, capacidad)) {
					Actividad acti = new Actividad();
					acti.setId(id);
					acti.setImpartidor(ImpartidoresDao.getImpartidor(rs.getString("impartidor_id")));
					acti.setNombre(rs.getString("nombre"));
					acti.setCoste_mensual(rs.getDouble("coste_mensual"));
					acti.setCapacidad(capacidad);
					arrActividad.add(acti);
				}
				
			}
			
			rs.close();
			st.close();
			con.close();
		}catch (SQLException ex) {
            System.err.println("Error en metodo obtenerActividadesLibresNoParticipa: " + ex);
        }
		
		return arrActividad;
	}
	
	public static boolean actividadLibre(int id, int cap) {
		boolean libre = false;
		String sql = "SELECT count(*) FROM participa WHERE actividad_id = ?";
		try {
			Connection con = ConexPoolDB.getDataSource().getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				if(rs.getInt("count(*)") < cap) {
					libre = true;
				}
			}
			
			rs.close();
			st.close();
			con.close();
		}catch (SQLException ex) {
            System.err.println("Error en metodo actividadLibre: " + ex);
        }
		
		return libre;
	}
	
	public static ArrayList<Actividad> actividadesImpartidor (Impartidor imp) {
		ArrayList<Actividad> arrActividad = new ArrayList<Actividad>();
		String sql = "SELECT * FROM actividad WHERE impartidor_id = ?";
		try {
			Connection con = ConexPoolDB.getDataSource().getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, imp.getId());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Actividad acti = new Actividad();
				acti.setId(rs.getInt("id"));
				acti.setImpartidor(ImpartidoresDao.getImpartidor(rs.getString("impartidor_id")));
				acti.setNombre(rs.getString("nombre"));
				acti.setCoste_mensual(rs.getDouble("coste_mensual"));
				acti.setCapacidad(rs.getInt("capacidad"));
				arrActividad.add(acti);
			}
			
			rs.close();
			st.close();
			con.close();
		}catch (SQLException ex) {
            System.err.println("Error en metodo actividadesImpartidor: " + ex);
        }
		return arrActividad;
	}
	
	public static HashMap<Alumno, Date> mapaAsistenciaActividad (int id){
		HashMap<Alumno, Date> mapAsistencias = new HashMap<Alumno, Date>();
		String sql = "SELECT * FROM participa WHERE actividad_id = ?";
		try {
			Connection con = ConexPoolDB.getDataSource().getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				mapAsistencias.put(AlumnosDao.getAlumno(rs.getString("alumno_dni")), rs.getDate("ultima_asistencia"));
			}
			
			rs.close();
			st.close();
			con.close();
		}catch (SQLException ex) {
            System.err.println("Error en metodo mapaAsistenciaActividad: " + ex);
        }
		return mapAsistencias;
	}
}
