/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.ofa.java.app.rrhh.dao;
import utn.ofa.java.app.rrhh.Proyecto;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gmuller
 */
public class ProyectoDaoJPATest {
    ProyectoDAO proyectoDao;
    public ProyectoDaoJPATest() {
    }
    
    @Before
    public void setUp() {
        proyectoDao = new ProyectoDaoJPA();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
    }
    
    /*
    @Test
    public void testCrearProyecto() {
        List<Proyecto> lista = proyectoDao.buscarTodos();
        int proyectos = lista.size();
        System.out.println("OJOJOJOJOJO " + proyectos + " es la Cantidad");
        Proyecto pry = new Proyecto();
        pry.setId(86);
        pry.setNombre("Proyecto 1");
        pry.setDescripcion("Descripcion proyecto 1");
        pry.setPresupuestoMaximo(999.0);
        proyectoDao.crear(pry);
        lista = proyectoDao.buscarTodos();
        int proyectosMas1 = lista.size();
        assertEquals(proyectos+1, proyectosMas1);
       // assertEquals(1, 1);
    }
    */

    /*
    @Test
    public void testEliminarProyecto() {
        List<Proyecto> lista = proyectoDao.buscarTodos();
        int proyectos = lista.size();
        System.out.println("OJOJOJO elim " + proyectos + " es la Cantidad antes");
        Proyecto pry2 = new Proyecto();
        pry2.setId(88);
        pry2.setNombre("Proyecto 1");
        pry2.setDescripcion("Descripcion proyecto 1");
        pry2.setPresupuestoMaximo(999.0);
       // pry2 = em.getReference(Proyecto.class, 88);
        proyectoDao.eliminar(pry2);
        lista = proyectoDao.buscarTodos();
        int proyectosMenos1 = lista.size();
        assertEquals(proyectos-1, proyectosMenos1);
       // assertEquals(1, 1);
    }
    */
    
    
    @Test
    public void testBuscarProyecto() {
        Proyecto pry2 = proyectoDao.buscarPorId(90);  
         System.out.println("Busco el proyecto 90 con nombre: " + pry2.getNombre());
        assertEquals(pry2.getNombre(), "Proyecto 1");      
    }
        

     @Test
    public void testActualizarProyecto() {
       // List<Proyecto> lista = proyectoDao.buscarTodos();
       // int proyectos = lista.size();
       // System.out.println("OJOJOJOJOJO " + proyectos + " es la Cantidad");
        Proyecto pry2 = proyectoDao.buscarPorId(90);
        String descr1 = pry2.getDescripcion();
        String descr1a = descr1 + "A"; // le agrego una A
        pry2.setDescripcion(descr1a);
        proyectoDao.actualizar(pry2);
        
        Proyecto pry3 = proyectoDao.buscarPorId(90);
        String descr2 = pry3.getDescripcion();
        
        Boolean bandera;
        if(descr1.equals(descr2)){
            bandera = false; // no actualizo
        }else{
             bandera = true; // si actualizó
        }
        assertTrue(bandera);        
       // assertEquals(1, 1);
    }
    
}
