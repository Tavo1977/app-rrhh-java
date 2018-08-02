/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.ofa.java.app.rrhh.dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *
 * @author gmuller
 */
public class ConexionJPA {
   private static EntityManagerFactory _entityManagerFactory = null;
  
   private static void inicializar(){
        if(_entityManagerFactory==null){
             System.out.println("crea el entiti manager app-rrhh-PU");
            //_entityManagerFactory = Persistence.createEntityManagerFactory("app-rrhh-PU");
            _entityManagerFactory = Persistence.createEntityManagerFactory("apprrhhpu");
        }
    } 
    
   public static EntityManager get(){
    inicializar();
    return _entityManagerFactory.createEntityManager();
    }   
}
