package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.sql.DataSource;

import beans.Actividad;
import beans.ActividadesInscritas;
import beans.ActividadesNoInscritas;
import beans.Alumno;
import beans.Impartidor;
import conex.ConexPoolBD;

public class ActividadesDao {
	private ConexPoolBD bdConex;
	private DataSource ds;
	private Connection con;
		
	public ActividadesDao() throws ServletException {
		bdConex = new ConexPoolBD();
		con = bdConex.getCon();
		ds = bdConex.getDs();
	}
	
	/*
	public ArrayList<Actividad> obtenerActividadesParticipa(Alumno al) throws ServletException {
		ArrayList<Actividad> arr = new ArrayList();
		try {
			con = ds.getConnection();
			String sql = "SELECT * FROM ACTIVIDAD WHERE id in (SELECT ACTIVIDAD_ID FROM PARTICIPA WHERE ALUMNO_DNI = ?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, al.getDni());
			
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				ImpartidoresDao id = new ImpartidoresDao();
				Impartidor impartidor = id.getImpartidor(rs.getInt("impartidor_id")+"");
				arr.add(new Actividad(rs.getInt("id"), impartidor, 
						rs.getString("nombre"), rs.getFloat("coste_mensual"), rs.getInt("capacidad")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (arr.size() > 0) 
			return arr;
		return null;
	}
	*/
	
	public ArrayList<ActividadesInscritas> actividadesAlumnoInscrito(Alumno al) throws ServletException {
		ArrayList<ActividadesInscritas> arr = new ArrayList();
		try {
			con = ds.getConnection();
			String sql = "SELECT actividad.id, actividad.nombre, actividad.coste_mensual, impartidor.nombre "
					+ "FROM ACTIVIDAD, IMPARTIDOR WHERE actividad.id "
					+ "in (SELECT ACTIVIDAD_ID FROM PARTICIPA WHERE ALUMNO_DNI = ?) AND impartidor.id = impartidor_id";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, al.getDni());
			
			ResultSet rs = pst.executeQuery();
			while (rs.next())
				arr.add(new ActividadesInscritas(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getFloat(3)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (arr.size() > 0) 
			return arr;
		return null;
	}
	
	public ArrayList<ActividadesNoInscritas> actividadesAlumnoNoInscrito(Alumno al) throws ServletException {
		ArrayList<ActividadesNoInscritas> arr = new ArrayList();
		try {
			con = ds.getConnection();
			String sql = "SELECT actividad.id, actividad.nombre, impartidor.nombre "
					+ "FROM ACTIVIDAD, IMPARTIDOR WHERE actividad.id "
					+ "NOT in (SELECT ACTIVIDAD_ID FROM PARTICIPA WHERE ALUMNO_DNI = ?) AND impartidor.id = impartidor_id;";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, al.getDni());
			
			ResultSet rs = pst.executeQuery();
			while (rs.next()) 
				arr.add(new ActividadesNoInscritas(rs.getInt(1), rs.getString(2), rs.getString(3)));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (arr.size() > 0) 
			return arr;
		return null;
	}
	
	public ArrayList<Actividad> actividadesImpartidor(Impartidor impartidor) {
		ArrayList<Actividad> arrActividades = new ArrayList<Actividad>();
		try {
			con = ds.getConnection();
			String sql = "SELECT * FROM ACTIVIDAD where impartidor_id=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, impartidor.getId()+"");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) 
				arrActividades.add(new Actividad(rs.getInt(1), impartidor, rs.getString(3), rs.getFloat(4), rs.getInt(5)));
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrActividades;
	}
	
	public HashSet<Alumno> mapaAsistenciaActividad(String idActividad) {
		HashSet<Alumno> alumnos = new HashSet();
		try {
			con = ds.getConnection();
			String sql = "SELECT * FROM ALUMNO WHERE DNI IN (SELECT ALUMNO_DNI FROM PARTICIPA WHERE ACTIVIDAD_ID = ?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, idActividad);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) 
				alumnos.add(new Alumno(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alumnos;
	}
	
	/*
	public ArrayList<Actividad> obtenerActividadesLibresNoParticipa(Alumno al) throws ServletException {
		ArrayList<Actividad> arr = new ArrayList();
		try {
			con = ds.getConnection();
			String sql = "SELECT * FROM ACTIVIDAD WHERE id not in (SELECT ACTIVIDAD_ID FROM PARTICIPA WHERE ALUMNO_DNI = ?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, al.getDni());
			
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				ImpartidoresDao id = new ImpartidoresDao();
				Impartidor impartidor = id.getImpartidor(rs.getInt("impartidor_id")+"");
				arr.add(new Actividad(rs.getInt("id"), impartidor, 
						rs.getString("nombre"), rs.getFloat("coste_mensual"), rs.getInt("capacidad")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (arr.size() > 0) 
			return arr;
		return null;
	}
	*/
}
