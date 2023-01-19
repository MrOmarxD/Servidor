package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conex.BDConex;

public class KeysDAO {
	private BDConex bd = new BDConex();
	
	// Metodo siguienteId que reciba un nombre de tabla y nos devuelva el siguiente id 
	// disponible en dicha tabla (1 si aún no tiene tuplas, 1+ del mayor id
	// existente en caso contrario)
	public int siguienteId(String tabla) {
		int maxId = 0;
		String sql = "SELECT MAX(id) 'id' FROM '" + tabla + "'";
        try {
            Connection con = bd.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            		maxId = rs.getInt("id");
            }
            rs.close();
            st.close();
            con.close();
        }
        catch (SQLException ex) {
            System.err.println("Error en metodo siguienteId: " + ex);
        }
        if(maxId == 0)
        	return 1;
		return maxId;
	}
}
