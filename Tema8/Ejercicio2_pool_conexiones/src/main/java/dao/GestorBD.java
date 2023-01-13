/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import beans.LibroPrestamo;

/**
 *
 * @author Omar
 */
public class GestorBD {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USER = "root";
    private static final String PASS = "";
    private static BasicDataSource dataSource;

    public GestorBD() {
        //Creamos el pool de conexiones
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASS);
        //Indicamos el tamaño del pool de conexiones
        dataSource.setInitialSize(50);
    }
    
    // Metodo listarLibrosPrestados, devuelve arraylist con objetos LibroPrestamo
    public ArrayList<LibroPrestamo> listarLibrosPrestados(){
    	ArrayList<LibroPrestamo> lstLibrosPrestados = new ArrayList<LibroPrestamo>();
        String sql = "SELECT prestamo.id as id, titulo, DATEDIFF(CURRENT_DATE,fecha) as fecha FROM libro, prestamo WHERE libro.id=prestamo.idlibro order by fecha DESC";
        try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	LibroPrestamo libroPrestado = new LibroPrestamo();
            	libroPrestado.setIdPrestamo(rs.getInt("id"));
            	libroPrestado.setFecha(rs.getInt("fecha"));
            	libroPrestado.setTitulo(rs.getString("titulo"));
                lstLibrosPrestados.add(libroPrestado);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo listarLibrosPrestados: " + ex);
        }
        return lstLibrosPrestados;
    }
    
    // Metodo buscarLibroPrestamo, con la idPrestamos te devuelve un objeto LibroPrestamo
    public LibroPrestamo buscarLibroPrestamo(int idPrestamo) {
    	LibroPrestamo libroprestamo = new LibroPrestamo();
        String sql = "SELECT prestamo.id as id, titulo, DATEDIFF(CURRENT_DATE,fecha) as fecha FROM libro, prestamo WHERE libro.id=prestamo.idlibro AND prestamo.id = '" + idPrestamo + "' order by fecha DESC";
        try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	libroprestamo.setIdPrestamo(rs.getInt("id"));
            	libroprestamo.setFecha(rs.getInt("fecha"));
            	libroprestamo.setTitulo(rs.getString("titulo"));
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo buscarLibroPrestamo: " + ex);
        }
    	return libroprestamo;
    }
    
    // Metodo devolver, elimina de la tabla prestamos las tuplas que se encuentra en la session
    public void devolver(ArrayList<LibroPrestamo> devoluciones){
        try{    
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM prestamo WHERE id=?");
            for (LibroPrestamo devolucion : devoluciones) {
                ps.setInt(1, devolucion.getIdPrestamo()); 
                ps.executeUpdate();
            }
            ps.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo devolver: " + ex);
        }
        
    }
}
