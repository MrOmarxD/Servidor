package daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.sql.Statement;

import beans.Charla;
import beans.HoraCharla;
import beans.Reserva;
import beans.Sala;
import conex.GestorPoolBD;

public class CharlasDao {
	private GestorPoolBD bdConex;
	private DataSource ds;
	private Connection con;
		
	public CharlasDao() throws ServletException {
		bdConex = new GestorPoolBD();
		con = bdConex.getCon();
		ds = bdConex.getDs();
	}
	
	// Metodo que devuelve una lista de todas las salas preparadas
	public ArrayList<Sala> obtenerSalasPreparadas() throws ServletException {
		ArrayList<Sala> arr = new ArrayList<Sala>();
		try {
			con = ds.getConnection();
			String sql = "SELECT * FROM sala WHERE preparada = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, 1);
			
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Sala sala = new Sala();
				sala.setCapacidad(rs.getInt("capacidad"));
				sala.setId_sala(rs.getString("id"));
				arr.add(sala);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (arr.size() > 0) 
			return arr;
		return null;
	}
	
	// Metodo que graba en la BBDD si es posible una charla sin id
	public void grabarCharla(Charla c) {
		if(numCharlasHoy(c.getId_sala())>2)
			return;
		String sql = "INSERT INTO charla(tema, dia, hora, minutos, tarifa, sala)"
				+ "VALUES(?, ?, ?, ?, ?, ?)";
        try {
            Connection con = ds.getConnection();
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            HoraCharla hc = c.getHoracharla();
            
            st.setString(1, c.getTema());
            st.setDate(2, (Date) hc.getFecha());
            st.setInt(3, hc.getHora());
            st.setInt(4, hc.getMin());
            st.setDouble(5, c.getTarifa());
            st.setString(6, c.getId_sala());
            
            st.executeUpdate();
            
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo grabarCharla: " + ex);
        }
	}
	
	// Metodo devuelve el numero de charlas que tiene este día una sala
	public int numCharlasHoy(String id_sala) {
		int num = 0;
		try {
			con = ds.getConnection();
			String sql = "SELECT COUNT(*) FROM charla WHERE sala = ? AND dia = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, id_sala);
			pst.setDate(2, new Date(System.currentTimeMillis()));
			
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				num = rs.getInt("COUNT(*)");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	// Metodo que devuelve una lista con las salas de la BBDD
	public ArrayList<Charla> obtenerCharlas() throws ServletException {
		ArrayList<Charla> arr = new ArrayList<Charla>();
		try {
			con = ds.getConnection();
			String sql = "SELECT * FROM charla)";
			PreparedStatement pst = con.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Charla c = new Charla();
				c.setId_charla(rs.getString("id"));
				c.setTema(rs.getString("tema"));
				HoraCharla hc = new HoraCharla();
				hc.setFecha(rs.getDate("dia"));
				hc.setHora(rs.getInt("hora"));
				hc.setMin(rs.getInt("minutos"));
				c.setHoracharla(hc);
				c.setTarifa(rs.getDouble("tarifa"));
				c.setId_sala(rs.getString("sala"));
				c.setReservas(numReservasCharla(rs.getString("id")));
				
				arr.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (arr.size() > 0) 
			return arr;
		return null;
	}
	
	// Metodo devuelve el numero de reservas que tiene una charla
	public int numReservasCharla(String id_charla) {
		int num = 0;
		try {
			con = ds.getConnection();
			String sql = "SELECT COUNT(*) 'cant' FROM reserva WHERE charla = ?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, id_charla);
			
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				if(rs.getInt("cant") > 0)
					num = rs.getInt("cant");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	// Metodo getMapaReservas clave charla y valor un arraylist de sus reservas
	public HashMap<String, ArrayList<Reserva>> mapaCharlasReserva() throws ServletException {
		HashMap<String, ArrayList<Reserva>> mapaCharlasReserva = new HashMap<String, ArrayList<Reserva>>();
		ArrayList<Charla> lstCharlas = obtenerCharlas();
		for(Charla c: lstCharlas) {
			mapaCharlasReserva.put(c.getId_charla(),obtenerReservasCharla(c.getId_charla()));
		}
		return mapaCharlasReserva;
	}
	
	// Metodo que devuelve una lista con las reservas de una charla
		public ArrayList<Reserva> obtenerReservasCharla(String id_charla) throws ServletException {
			ArrayList<Reserva> arr = new ArrayList<Reserva>();
			try {
				con = ds.getConnection();
				String sql = "SELECT * FROM reserva WHERE charla = ?)";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, id_charla);
				
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					Reserva r = new Reserva();
					r.setId_reserva(rs.getString("id"));
					r.setNombreCliente("dni");
					r.setPagado(rs.getInt("pagado"));
					
					arr.add(r);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if (arr.size() > 0) 
				return arr;
			return null;
		}
}
