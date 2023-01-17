package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Cliente;
import conex.BDConex;

public class ClienteDAO {
	private BDConex bd = new BDConex();
	
	// Método buscaCliente Busca en la BD el cliente con el nombre de usuario y password
	// recibidos como parámetros y lo devuelve como Cliente (null si no existe)
	public Cliente buscaCliente (String nombre, String password) {
		Cliente cliente = new Cliente();
		String sql = "SELECT * FROM clientes WHERE nombre = '" + nombre + "' AND password = '" + password + "'";
        try {
            Connection con = bd.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	cliente.setIdCliente(rs.getInt("id"));
            	cliente.setNombre(nombre);
            	cliente.setPassword(password);
            	cliente.setDomicilio(rs.getString("domicilio"));
            	cliente.setCodigopostal(rs.getInt("codigopostal"));
            	cliente.setTelefono(rs.getInt("telefono"));
            	cliente.setEmail(rs.getString("email"));
            }
            rs.close();
            st.close();
            con.close();
        }
        catch (SQLException ex) {
            System.err.println("Error en metodo buscaCliente: " + ex);
        }
		return cliente;
	}
	
	// Método buscaCliente que devuelve si existe o no un cliente con el nombre indicado.
	public boolean buscaCliente(String nombre) {
		int cant = 0;
		String sql = "SELECT count(*) 'cant' FROM clientes WHERE nombre = '" + nombre + "'";
        try {
        	Connection con = bd.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	cant = rs.getInt("cant");
            }
            rs.close();
            st.close();
            con.close();
        }
        catch (SQLException ex) {
            System.err.println("Error en metodo buscaCliente: " + ex);
        }
        if(cant>0)
        	return true;
       return false;
	}
	
	// Método guardaCliente guarda un nuevo cliente en la BD, con la información del
	// parámetro. Devuelve si la operación ha tenido éxito
	public boolean guardaCliente(Cliente c) {
		boolean guardado = false;
		String sql = "INSERT INTO clientes (id, nombre, password, domicilio, codigopostal,"
				+ " telefono, email) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
        	Connection con = bd.getDataSource().getConnection();
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, c.getIdCliente());
            st.setString(2, c.getNombre());  
            st.setString(3, c.getPassword());  
            st.setString(4, c.getDomicilio());  
            st.setInt(5, c.getCodigopostal());  
            st.setInt(6, c.getTelefono());  
            st.setString(7, c.getEmail());
            
            st.executeUpdate();
            guardado = true;
        } catch (SQLException ex) {
            System.err.println("Error en metodo guardaCliente: " + ex);
        }
		return guardado;
	}
	
	// Método actualizaCliente que actualiza el cliente de id contenido en c,
	// con el resto de atributos contenidos en c
	public boolean actualizaCliente(Cliente c) {
		boolean actualizado = false;
		String sql="UPDATE clientes SET nombre='"+c.getNombre()+"' AND "
				+ "password='"+c.getPassword()+"' AND domicilio='"+c.getDomicilio()+"' "
				+ "codigopostal='"+c.getCodigopostal()+"' telefono='"+c.getTelefono()+"' "
				+ "email='"+c.getEmail()+"' WHERE id = '"+c.getIdCliente()+"'";
		try {
        	Connection con = bd.getDataSource().getConnection();
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            st.executeUpdate();
            actualizado = true;
        } catch (SQLException ex) {
            System.err.println("Error en metodo actualizaCliente: " + ex);
        }
		return actualizado;
	}
}
