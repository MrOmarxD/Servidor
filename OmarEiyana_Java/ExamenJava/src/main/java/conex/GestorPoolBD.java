package conex;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

public class GestorPoolBD {
	private DataSource ds;
	private Connection con;
	
	public GestorPoolBD() throws ServletException {
		try {
			InitialContext ctx = new InitialContext();
			Context env = (Context) ctx.lookup("java:comp/env");
			// Nombre del recurso en el context.xml
			ds = (DataSource) env.lookup("jdbc/poolCharlasDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public DataSource getDs() {
		return ds;
	}

	public Connection getCon() {
		return con;
	}
}
