/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.ofa.java.app.rrhh.dao;

import java.util.ArrayList;
import java.util.List;
import utn.ofa.java.app.rrhh.Proyecto;
import javax.persistence.EntityManager;
/**
 *
 * @author gmuller
 */
public class ProyectoDaoJPA implements ProyectoDAO{

    private EntityManager em;
    
    @Override
    public void crear(Proyecto e) {
        this.em = ConexionJPA.get();
        try {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
            System.out.println("OJOJOJOJOJO persistió y comitió");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("OJOJOJOJOJO NO funciona persist....rollbackea");
            em.getTransaction().rollback();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void actualizar(Proyecto e) {
        this.em = ConexionJPA.get();
        try {
            em.getTransaction().begin();
            em.merge(e);
            em.getTransaction().commit();
            System.out.println("OJOJOJOJOJO mergeo y comitió");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("OJOJOJOJOJO NO funciona mergeo....rollbackea");
            em.getTransaction().rollback();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void eliminar(Proyecto e) {
        this.em = ConexionJPA.get();
        try {
            System.out.println("entor a eliminar 1");
            em.getTransaction().begin();
            System.out.println("entor a eliminar 2");
            
            Proyecto prye;
            prye = em.getReference(Proyecto.class, e.getId());
            prye.getId();
            em.remove(prye);
            
            System.out.println("entor a eliminar 3");
            em.getTransaction().commit();
            System.out.println("OJOJOJOJOJO elimino y comitió");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("OJOJOJOJOJO NO funciona delete....rollbackea");
            em.getTransaction().rollback();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Proyecto buscarPorId(Integer id) {
        this.em = ConexionJPA.get();       
        return em.find(Proyecto.class, id);
    } 
    

    @Override
    public List<Proyecto> buscarTodos() {
        this.em = ConexionJPA.get();
        List<Proyecto> resultado= new ArrayList<Proyecto>();
        try {
            em.getTransaction().begin();
            resultado = this.em.createQuery("SELECT p FROM Proyecto p").getResultList();
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return resultado;
    }
    
}
