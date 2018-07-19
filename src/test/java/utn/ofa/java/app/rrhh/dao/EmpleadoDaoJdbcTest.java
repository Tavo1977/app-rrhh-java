/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.ofa.java.app.rrhh.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import utn.ofa.java.app.rrhh.Contratados;
import utn.ofa.java.app.rrhh.Efectivo;
import utn.ofa.java.app.rrhh.Empleado;

/**
 *
 * @author gustavo
 */
public class EmpleadoDaoJdbcTest {
    
    EmpleadoDaoJdbc empleadoDaoJdbc;
    
    public EmpleadoDaoJdbcTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
    }
    
    //controlo el buscar y el insert de efectivo
    @Test
    public void testCrear() {
       System.out.println("crear");
       empleadoDaoJdbc = new EmpleadoDaoJdbc();
       //creo objeto empleado para llamar al metodo crear
       Efectivo empleado = new Efectivo();
       java.util.Date fechaActual = new java.util.Date();       
       empleado.setId(2);
       empleado.setNombre("JUAN" );
       empleado.setCorreoElectronico("JUAN@PEPE.COM");
       empleado.setCuil("21257131123");
       empleado.setFechaIngreso(fechaActual);
       empleado.setHorasTrabajadas(40);
       empleado.setCanMInHorObl(40);
       empleado.setComisiones(2000.0);
       empleado.setSueldoBasico(30000.0);       
       System.out.println(empleado.getNombre() + "Crear2:");        
       empleadoDaoJdbc.crear(empleado);
       System.out.println(empleado.getNombre() + "Crear3:");
       Empleado empleado2 =  empleadoDaoJdbc.buscarPorId(2);
       assertEquals(empleado.getNombre(), empleado2.getNombre());
   }
    
       //controlo el buscar y el insert de contratado
    @Test
    public void testCrearCon() {
       System.out.println("crear COntratado");
       empleadoDaoJdbc = new EmpleadoDaoJdbc();
       //creo objeto empleado para llamar al metodo crear
       Contratados empleado = new Contratados();
       java.util.Date fechaActual = new java.util.Date();       
       empleado.setId(2);//cualquiera total grava el que sigue
       empleado.setNombre("JUAN" );
       empleado.setCorreoElectronico("JUAN@PEPEC.COM");
       empleado.setCuil("21257131123");
       empleado.setFechaIngreso(fechaActual);
       empleado.setHorasTrabajadas(40);
       empleado.setMonPorHor(250);
       System.out.println(empleado.getNombre() + "Crear2C:");        
       empleadoDaoJdbc.crear(empleado);
       System.out.println(empleado.getNombre() + "Crear3C:");
       Empleado empleado2 =  empleadoDaoJdbc.buscarPorId(2);
       assertEquals(empleado.getNombre(), empleado2.getNombre());
   }
    
     //controlo el eliminar y el buscar por id
    @Test
    public void testEliminar() {
       empleadoDaoJdbc = new EmpleadoDaoJdbc();
       System.out.println("Eliminar empleado");
       System.out.println(  "Crear2C:");  
       int posicion = 1;
       for(int i=1 ;i<200;i++){
           Empleado empleadox =  empleadoDaoJdbc.buscarPorId(i);
           if (empleadox != null){
               posicion = i;
               i = 101;
           }                   
       }
       System.out.println("Eliminar empleado posicion:" + posicion);
       Empleado empleado1 =  empleadoDaoJdbc.buscarPorId(posicion);
       System.out.println("antes:" + empleado1.getNombre());
       empleadoDaoJdbc.eliminar(posicion);       
       Empleado empleado2 =  empleadoDaoJdbc.buscarPorId(posicion);
      // System.out.println("antes:" + empleado1.getNombre() + " despues " + empleado2.getNombre());
       assertNull(empleado2);
   }
    
}
