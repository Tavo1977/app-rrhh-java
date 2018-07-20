/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.ofa.java.app.rrhh.dao;

import java.util.List;
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
       empleado.setId(1);//no usa al alta
       empleado.setNombre("JUAN" );
       empleado.setCorreoElectronico("JUAN@PEPE.COM");
       empleado.setCuil("21257131123");
       empleado.setFechaIngreso(fechaActual);
       empleado.setHorasTrabajadas(40);
       empleado.setCanMInHorObl(40);
       empleado.setComisiones(2000.0);
       empleado.setSueldoBasico(30000.0);       
       //System.out.println(empleado.getNombre() + "Crear2:");        
       empleadoDaoJdbc.crear(empleado);
       
       List<Empleado> testBuscados = empleadoDaoJdbc.buscarTodos();
       int idmax = 1;
       for (Empleado empleadoItem: testBuscados) {
            int idnuevo = empleadoItem.getId() ;
            if(idnuevo > idmax){
                idmax = idnuevo;
            }
        }// el id maximo es el que inserte recien si funciono crear
       Empleado empleado2 =  empleadoDaoJdbc.buscarPorId(idmax);       
       //System.out.println(empleado.getNombre() + "Crear3:");
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
       empleado.setId(1);//cualquiera total grava el que sigue
       empleado.setNombre("JUAN" );
       empleado.setCorreoElectronico("JUAN@PEPEC.COM");
       empleado.setCuil("21257131123");
       empleado.setFechaIngreso(fechaActual);
       empleado.setHorasTrabajadas(40);
       empleado.setMonPorHor(250);
       //System.out.println(empleado.getNombre() + "Crear2C:");        
       empleadoDaoJdbc.crear(empleado);
       
        List<Empleado> testBuscados = empleadoDaoJdbc.buscarTodos();
       int idmax = 1;
       for (Empleado empleadoItem: testBuscados) {
            int idnuevo = empleadoItem.getId() ;
            if(idnuevo > idmax){
                idmax = idnuevo;
            }
        }// el id maximo es el que inserte recien si funciono crear
       
       Empleado empleado2 =  empleadoDaoJdbc.buscarPorId(idmax);
       assertEquals(empleado.getNombre(), empleado2.getNombre());
   }
    
  
  
    
    //controlo el actualizar
    
    @Test
    public void testActualizar() {
       System.out.println("Actualizar");
       empleadoDaoJdbc = new EmpleadoDaoJdbc();
       //creo objeto empleado para llamar al metodo crear
       Efectivo empleado = new Efectivo();
       int posicion = 1;
       for(int i=5 ;i<200;i++){
           Empleado empleadox =  empleadoDaoJdbc.buscarPorId(i);
            if (empleadox != null){              
                if ("JUAN".equals(empleadox.getNombre()) && empleadox.esEfectivo()){
                    posicion = i;
                    i = 201;//para uqe salga, ojo si cambio enel for camiar aca  tb
                } else {
                }  
            }
       }// recupera el primer indice de algun juan qu eno sea de los primeros 5
       java.util.Date fechaActual = new java.util.Date();       
       empleado.setId(posicion);
       empleado.setNombre("JUAN Actual" );
       empleado.setCorreoElectronico("JUAN@PEPE.COM");
       empleado.setCuil("21257131123");
       empleado.setFechaIngreso(fechaActual);
       empleado.setHorasTrabajadas(40);
       empleado.setCanMInHorObl(40);
       empleado.setComisiones(2000.0);
       empleado.setSueldoBasico(30000.0);       
       System.out.println(empleado.getNombre() + "Actualizar el: " + posicion);        
       empleadoDaoJdbc.actualizar(empleado); 
      // System.out.println(empleado.getNombre() + "Actualizar3:");
       Empleado empleado2 =  empleadoDaoJdbc.buscarPorId(posicion);
       assertEquals(empleado.getNombre(), empleado2.getNombre());
   }
  
       //controlo el eliminar y el buscar por id, no los 5 primeros    
    @Test
    public void testEliminar() {
       empleadoDaoJdbc = new EmpleadoDaoJdbc();
       System.out.println("Eliminar empleado");
       int posicion = 1;
       for(int i=5 ;i<200;i++){
           Empleado empleadox =  empleadoDaoJdbc.buscarPorId(i);
           if (empleadox != null){
               posicion = i;
               i = 201;//ojo que corresponda con el for  
           }                   
       }//recupera para eliminar el primero comenzando del 5
       System.out.println("Eliminar empleado posicion: " + posicion);
       Empleado empleado1 =  empleadoDaoJdbc.buscarPorId(posicion);
       System.out.println("antes: " + empleado1.getNombre());
       empleadoDaoJdbc.eliminar(posicion);       
       Empleado empleado2 =  empleadoDaoJdbc.buscarPorId(posicion);
      // System.out.println("antes:" + empleado1.getNombre() + " despues " + empleado2.getNombre());
       assertNull(empleado2);
   }
  
    
       //controlo el buscar lista de todos
    @Test
    public void testTodos() {
       empleadoDaoJdbc = new EmpleadoDaoJdbc();
       System.out.println("Buscar todos los empleado");
       List<Empleado> testBuscados = empleadoDaoJdbc.buscarTodos();
       //System.out.println("Listado:" + testBuscados.toString());
       int cant = 1;
       for (Empleado empleado2: testBuscados) {
    //código para acceder a cada campo del Item.
        cant +=1;
        System.out.println(empleado2.getId() + " - "+ empleado2.getNombre());
        }
       assertTrue(cant > 1);
   }
}
