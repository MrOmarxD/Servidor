package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import beans.Usuario;
import conex.ConexPoolBD; 

public class UsuarioDAO {
	private ConexPoolBD gestorBDXml = new ConexPoolBD();
	
	// Metodo que lista los usuarios validados
	public ArrayList<Usuario> lstUsuariosValidados() {
		ArrayList<Usuario> lstusuarios = new ArrayList<Usuario>();
		 try{                  		         
			 	Connection con = gestorBDXml.getDataSource().getConnection(); 
	        	String sql = "SELECT * FROM usuario WHERE validado = '" + 1 + "'";
	        	Statement st = con.createStatement();
	            ResultSet rs = st.executeQuery(sql);            
	            while(rs.next()) { 
	            	Usuario u = new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("contrasena"), rs.getString("imagen"), rs.getString("descripcion"), rs.getString("direccion"), rs.getInt("cp"), rs.getString("municipio"), rs.getString("provincia"), rs.getString("pais"), rs.getInt("telefono"), rs.getString("email"), rs.getInt("validado"), rs.getString("strValidado"));
	            	lstusuarios.add(u);
	            }
	            rs.close();
	            st.close();
	            con.close();
	         }
	         catch (Exception e)  {
	             System.err.println("Error en lstUsuariosValidados: " + e);
	         } 
		 return lstusuarios;
	}
	
	// Metodo que lista los usuarios no validados
	public ArrayList<Usuario> lstUsuariosNoValidados() {
		ArrayList<Usuario> lstusuarios = new ArrayList<Usuario>();
		 try{                  		         
			 	Connection con = gestorBDXml.getDataSource().getConnection(); 
	        	String sql = "SELECT * FROM usuario WHERE validado = '" + 0 + "'";
	        	Statement st = con.createStatement();
	            ResultSet rs = st.executeQuery(sql);            
	            while(rs.next()) { 
	            	Usuario u = new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("contrasena"), rs.getString("imagen"), rs.getString("descripcion"), rs.getString("direccion"), rs.getInt("cp"), rs.getString("municipio"), rs.getString("provincia"), rs.getString("pais"), rs.getInt("telefono"), rs.getString("email"), rs.getInt("validado"), rs.getString("strValidado"));
	            	lstusuarios.add(u);
	            }
	            rs.close();
	            st.close(); 
	            con.close();
	         }
	         catch (Exception e)  {
	             System.err.println("Error en lstUsuariosNoValidados: " + e);
	         } 
		 return lstusuarios;
	}
		
	// Metodo que busca un usuario en la BD por su email y contraseña
	public Usuario buscaUsuario(String email, String password) { 
		Usuario u = null;
		 try{                  		         
			 	Connection con = gestorBDXml.getDataSource().getConnection(); 
	        	String sql = "SELECT * FROM usuario WHERE email = '" + email + "' AND contrasena = '"+ password +"'";
	        	Statement st = con.createStatement();
	            ResultSet rs = st.executeQuery(sql);            
	            if(rs.next()) { 
	            	u = new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("contrasena"), rs.getString("imagen"), rs.getString("descripcion"), rs.getString("direccion"), rs.getInt("cp"), rs.getString("municipio"), rs.getString("provincia"), rs.getString("pais"), rs.getInt("telefono"), rs.getString("email"), rs.getInt("validado"), rs.getString("strValidado"));
	            }
	            rs.close();
	            st.close();
	            con.close();
	         }
	         catch (Exception e)  {
	             System.err.println("Error en buscaUsuario(Email, password): " + e);
	         } 
		 return u;
	}
	
	// Metodo que valida un usuario 
	public boolean validarCadena(String email, String cadena) { 
		int result = 0;
		String sql = "UPDATE usuario SET validado = 1 WHERE email = ? AND strValidado = ?";
		 try{                  		 
			 	Connection con = gestorBDXml.getDataSource().getConnection();
			 	PreparedStatement ps = con.prepareStatement (sql);
			 	ps.setString(1, email); 
			 	ps.setString(2, cadena); 
			 	result = ps.executeUpdate();   		
				ps.close();
				con.close();
				
	         }
	         catch (Exception e)  {
	             System.err.println("Error en validarCadena(nombre, cadena): " + e);
	         } 
		if (result > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
	    //La dirección de correo de envío
	    String remitente = "sustitosexpres@gmail.com";
	    //La clave de aplicación obtenida según se explica en este artículo:
	    String claveemail = "aaaSasa2";

	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
	    props.put("mail.smtp.user", remitente);
	    props.put("mail.smtp.clave", claveemail);    //La clave de la cuenta
	    props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
	    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
	    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress(remitente));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));   //Se podrían añadir varios de la misma manera
	        message.setSubject(asunto);
	        message.setText(cuerpo);
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", remitente, claveemail);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   //Si se produce un error
	    }
	  }
	public boolean insertarUsuario(Usuario u) {
		int n = 0; 
		try{                  		         
		 	Connection con = gestorBDXml.getDataSource().getConnection(); 
		 	String sql ="INSERT INTO usuario(nombre,apellido,contrasena,imagen,descripcion,direccion,cp,municipio,provincia,pais,telefono,email,validado,strValidado) VALUES( ? , ?,  ? ,? , ?,  ?,  ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement (sql);   // Se precompila la consulta

			ps.setString(1, u.getNombre());
			ps.setString(2, u.getApellido());
			ps.setString(3,	u.getContrasena());
			ps.setString(4, u.getImagen());
			ps.setString(5, u.getDescripcion());
			ps.setString(6, u.getDireccion());
			ps.setInt(7, u.getCp());
			ps.setString(8, u.getMunicipio() );
			ps.setString(9, u.getProvincia() );
			ps.setString(10, u.getPais() );
			ps.setInt(11, u.getTelefono() );
			ps.setString(12, u.getEmail() );
			ps.setInt(13, u.getValidado() );
			ps.setString(14, u.getStrValidado() );
			n = ps.executeUpdate();   		
			ps.close();
			con.close();
         }
         catch (Exception e)  {
             System.err.println("Error en insertarUsuario: " + e);
         }
		if (n > 0) {
			return true;
		}else {
			return false;
		}
	}
}
 