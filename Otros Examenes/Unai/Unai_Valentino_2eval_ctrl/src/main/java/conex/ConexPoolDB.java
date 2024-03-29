package conex;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConexPoolDB {
	private static DataSource dataSource;
	
	public ConexPoolDB() {
		try {
			InitialContext ctx = new InitialContext();
			Context env = (Context) ctx.lookup("java:comp/env");
			// nombre del recurso en el context.xml
			dataSource = (DataSource) env.lookup("jdbc/poolActividadesDB");
		
		} catch (NamingException e) {
			e.printStackTrace();
			
		}
	}

	public static DataSource getDataSource() {
		return dataSource;
	}
	
	
}
