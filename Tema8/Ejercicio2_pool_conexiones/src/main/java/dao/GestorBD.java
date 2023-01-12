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
import java.util.Map;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import beans.Prestamo;

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
    
    // Metodo listarLibrosPrestados, devuelve una lista con mapas que contienen la informacion de todos los prestamos
    public ArrayList<Map> listarLibrosPrestados(){
        ArrayList<Map> lstLibrosPrestados = new ArrayList<Map>();
        String sql = "SELECT prestamo.id as id, titulo, DATEDIFF(CURRENT_DATE,fecha) as fecha FROM libro, prestamo WHERE libro.id=prestamo.idlibro order by fecha DESC";
        try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Map libroPrestado = new HashMap();
                libroPrestado.put("titulo", rs.getString("titulo"));
                libroPrestado.put("fecha", rs.getInt("fecha"));
                libroPrestado.put("id", rs.getInt("id"));
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
    
    // Metodo devolver, elimina de la tabla prestamos las tuplas que se encuentra en la session
    public void devolver(ArrayList<Integer> devoluciones){
        try{    
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM prestamo WHERE id=?");
            for (Integer devolucion : devoluciones) {
                ps.setInt(1, devolucion); 
                ps.executeUpdate();
            }
            ps.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo devolver: " + ex);
        }
        
    }
}
