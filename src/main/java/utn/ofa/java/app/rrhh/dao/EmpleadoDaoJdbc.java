/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.ofa.java.app.rrhh.dao;
import utn.ofa.java.app.rrhh.Empleado;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utn.ofa.java.app.rrhh.Efectivo;
import utn.ofa.java.app.rrhh.Contratados;
/**
 *
 * @author gustavo
 */
public class EmpleadoDaoJdbc implements EmpleadoDao{
    
    private final String INSERT_EMPLEADO = "INSERT INTO EMPLEADOS (NOMBRE, CORREO, CUIL, "
    + "FECHA_INGRESO, HS_TRABAJADAS, SUELDO_BASICO, "
    + "COMISIONES, HS_MINIMAS, COSTO_HORA, TIPO_EMPLEADO) "
    + "VALUES (?,?,?,?,?,?,?,?,?,?)";

     private final String UPDATE_EMPLEADO = "UPDATE EMPLEADOS SET NOMBRE = ?, CORREO = ?, CUIL = ?, "
    + "FECHA_INGRESO = ?, HS_TRABAJADAS = ?, SUELDO_BASICO = ?, "
    + "COMISIONES = ?, HS_MINIMAS = ?, COSTO_HORA = ?, TIPO_EMPLEADO = ? "
    + "WHERE ID = ? ";
    
     private final String BUSCAR_EMPLEADO = "SELECT NOMBRE, CORREO, CUIL, "
    + "FECHA_INGRESO, HS_TRABAJADAS, SUELDO_BASICO, "
    + "COMISIONES, HS_MINIMAS, COSTO_HORA, TIPO_EMPLEADO FROM EMPLEADOS "
    + "WHERE ID = ? "; 
     
    private final String BUSCAR_TODOS = "SELECT ID, NOMBRE, CORREO, CUIL, "
    + "FECHA_INGRESO, HS_TRABAJADAS, SUELDO_BASICO, "
    + "COMISIONES, HS_MINIMAS, COSTO_HORA, TIPO_EMPLEADO FROM EMPLEADOS ";
              
    private final String DELETE_EMPLEADO = "DELETE FROM EMPLEADOS  "
    + "WHERE ID = ? ";
     
    
    @Override
    public void crear(Empleado e) {
        //System.out.println("1");
        Connection conn = ConexionJDBC.getConexion();
        //System.out.println("2");
        try(PreparedStatement ps = conn.prepareStatement(INSERT_EMPLEADO)){
        //    System.out.println("3" + e.getNombre());
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getCorreoElectronico());
            ps.setString(3, e.getCuil());
            //ps.setDate(4, new Date(e.getFechaIngreso().getTime()));
            ps.setDate(4, new Date(e.getFechaIngreso().getTime()));
           // ps.setDate(4, '1991-01-29');
            ps.setInt(5, e.getHorasTrabajadas());
          //  System.out.println("3.1" + e.esEfectivo() + e.esContratado());
            if(e.esEfectivo()){
              //  System.out.println("4");
                Efectivo empEf = (Efectivo) e;
                ps.setDouble(6, empEf.getSueldoBasico());
                ps.setDouble(7, empEf.getComisiones());
                ps.setInt(8, empEf.getCanMInHorObl());
                ps.setDouble(9, 0.0);
                ps.setInt(10, 1);
            } else if (e.esContratado()){
             //   System.out.println("5");
                Contratados c = (Contratados) e;
                ps.setDouble(6, 0);
                ps.setDouble(7, 0);
                ps.setInt(8, 0);                
                ps.setDouble(9, c.getMonPorHor());
                ps.setInt(10, 2);
            }
          //  System.out.println("6");
            int filasInsertadas = ps.executeUpdate();
        }catch(SQLException ex){
            
          //  System.out.println("8");
            ex.printStackTrace();
        }
       // System.out.println("7");
        ConexionJDBC.liberarConexion();
    }
   
    @Override
    public void actualizar(Empleado e) {
        Connection conn = ConexionJDBC.getConexion();
        try(PreparedStatement ps = conn.prepareStatement(UPDATE_EMPLEADO)){
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getCorreoElectronico());
            ps.setString(3, e.getCuil());
            ps.setDate(4, new Date(e.getFechaIngreso().getTime()));
            ps.setInt(5, e.getHorasTrabajadas());
            if(e.esEfectivo()){
                Efectivo empEf = (Efectivo) e;
                ps.setDouble(6, empEf.getSueldoBasico());
                ps.setDouble(7, empEf.getComisiones());
                ps.setInt(8, empEf.getCanMInHorObl());
                ps.setDouble(9, 0.0);
                ps.setInt(10, 1);
            } else if (e.esContratado()){
                Contratados c = (Contratados) e;
                ps.setDouble(6, 0);
                ps.setDouble(7,0);
                ps.setInt(8, 0);               
                ps.setDouble(9, c.getMonPorHor());
                ps.setInt(10, 2);
            }
            ps.setInt(11, e.getId());
            int filasModificadas = ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        ConexionJDBC.liberarConexion(); 
    }

    @Override
    public void eliminar(Integer id) {
        Connection conn = ConexionJDBC.getConexion();
        try(PreparedStatement ps = conn.prepareStatement(DELETE_EMPLEADO)){
            ps.setInt(1, id);
            int filasEliminadas = ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        ConexionJDBC.liberarConexion();         
    } 

    @Override
    public Empleado buscarPorId(Integer id) {
        Connection conn = ConexionJDBC.getConexion();
        Empleado empBuscado = null;
        try(PreparedStatement ps = conn.prepareStatement(BUSCAR_EMPLEADO)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){ 
                    if (rs.getInt("TIPO_EMPLEADO") == 1){
                        //Efectivo empBuscado;
                        empBuscado = new Efectivo();
                        empBuscado.setId(id);
                        empBuscado.setNombre(rs.getString("NOMBRE") );
                        empBuscado.setCorreoElectronico(rs.getString("CORREO"));
                        empBuscado.setCuil(rs.getString("CUIL"));
                        empBuscado.setFechaIngreso(rs.getDate("FECHA_INGRESO"));                        
                        empBuscado.setHorasTrabajadas(rs.getInt("HS_TRABAJADAS"));
                      //  empBuscado.setSueldoBasico(rs.getFloat("SUELDO_BASICO"));
                      //  empBuscado.setComisiones(rs.getFloat("COMISIONES"));
                      //  empBuscado.setCanMInHorObl(rs.getInt("HS_MINIMAS"));    
                    } else if(rs.getInt("TIPO_EMPLEADO") == 2){
                        //Contratados empBuscado;
                        empBuscado = new Contratados();
                        empBuscado.setId(id);
                        empBuscado.setNombre(rs.getString("NOMBRE") );
                        empBuscado.setCorreoElectronico(rs.getString("CORREO"));
                        empBuscado.setCuil(rs.getString("CUIL"));
                        empBuscado.setFechaIngreso(rs.getDate("FECHA_INGRESO"));                        
                        empBuscado.setHorasTrabajadas(rs.getInt("HS_TRABAJADAS"));
                       // empBuscado.setMonPorHor(rs.getInt("COSTO_HORA"));
                    }
                 }
            }
           // int filasEliminadas = ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        ConexionJDBC.liberarConexion(); 
        return empBuscado;
    }  
    

    @Override
    public List<Empleado> buscarTodos() {
        Connection conn = ConexionJDBC.getConexion();
        List<Empleado> empBuscados = new ArrayList<>(1000);
        try(PreparedStatement ps = conn.prepareStatement(BUSCAR_TODOS)){
          //  ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){ 
                    if (rs.getInt("TIPO_EMPLEADO") == 1){
                        //Efectivo empBuscado;
                        Empleado empBuscado = null;
                        empBuscado = new Efectivo();
                        empBuscado.setId(rs.getInt("ID"));
                        empBuscado.setNombre(rs.getString("NOMBRE") );
                        empBuscado.setCorreoElectronico(rs.getString("CORREO"));
                        empBuscado.setCuil(rs.getString("CUIL"));
                        empBuscado.setFechaIngreso(rs.getDate("FECHA_INGRESO"));                        
                        empBuscado.setHorasTrabajadas(rs.getInt("HS_TRABAJADAS"));
                        empBuscados.add(empBuscado);
                      
                        //  empBuscado.setSueldoBasico(rs.getFloat("SUELDO_BASICO"));
                      //  empBuscado.setComisiones(rs.getFloat("COMISIONES"));
                      //  empBuscado.setCanMInHorObl(rs.getInt("HS_MINIMAS"));    
                    } else if(rs.getInt("TIPO_EMPLEADO") == 2){
                        //Contratados empBuscado;
                        Empleado empBuscado = null;
                        empBuscado = new Contratados();
                        empBuscado.setId(rs.getInt("ID"));
                        empBuscado.setNombre(rs.getString("NOMBRE") );
                        empBuscado.setCorreoElectronico(rs.getString("CORREO"));
                        empBuscado.setCuil(rs.getString("CUIL"));
                        empBuscado.setFechaIngreso(rs.getDate("FECHA_INGRESO"));                        
                        empBuscado.setHorasTrabajadas(rs.getInt("HS_TRABAJADAS"));
                        empBuscados.add(empBuscado);
                       // empBuscado.setMonPorHor(rs.getInt("COSTO_HORA"));
                    }
                 }
            }
           // int filasEliminadas = ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        ConexionJDBC.liberarConexion(); 
        return empBuscados;
    }   
    
}
