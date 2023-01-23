package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conex.ConexPoolBD;

public class KeysDAO {
	
	
	public static int siguienteId(String nombreTabla) {
		
		int id=0;
		
		try {
			Connection con= ConexPoolBD.getDataSource().getConnection();
			String sql="SELECT MAX(ID) AS ID FROM "+nombreTabla;
			Statement pst= con.createStatement();
			
			
			ResultSet rs= pst.executeQuery(sql);
			
			while(rs.next()) {
				id=Integer.parseInt(rs.getString("ID"));
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id+1;
	}
	

}
