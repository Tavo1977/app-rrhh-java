/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.ofa.java.app.rrhh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author gustavo
 */
public class ConexionJDBC {
    private static final String USUARIO = "root";
    private static final String PASSWORD = "Fantaman101";
    private static final String URL_CONEXION = "jdbc:mysql://localhost:3306/app-rrhh";
    //Connection conn = null;
    private static Connection _CONEXION;
    public static Connection getConexion() {
       // System.out.println("CONECTA1");
      // if (_CONEXION != null) {
            try {
               //  System.out.println("CONECTA2");
              _CONEXION = DriverManager.getConnection(URL_CONEXION, USUARIO, PASSWORD);  
            } catch (SQLException ex) {   
              //  System.out.println("CONECTA3");
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
            // System.out.println("Error al conectar con: " + URL_CONEXION + " - " + USUARIO + " - " + PASSWORD);
            }
        //}
      //  System.out.println("CONECTA4" + _CONEXION.toString());
        return _CONEXION;
    }
    public static void liberarConexion() {
        if (_CONEXION != null) {
            try {
             _CONEXION.close();
             _CONEXION = null;   
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexion");
             //   Logger.getLogger(AlumnoDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

