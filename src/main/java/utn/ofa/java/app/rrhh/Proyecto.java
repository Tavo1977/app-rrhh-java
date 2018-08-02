/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.ofa.java.app.rrhh;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;


/**
 *
 * @author gmuller
 */
@Entity
public class Proyecto{
    @Id
    private Integer id;
    private String nombre;
    private String descripcion;
    private double presupuestoMaximo;

    
    public String ToString(){
        String texto = "El Proyecto nro " + id + " llamado " + nombre + " consiste en " + descripcion + " y cuenta con con un tope de " + presupuestoMaximo + " pesos" ;
        return texto;  
    }
        
    public boolean equals(Proyecto ProaC) {
        if (this == ProaC) return true;
        if (ProaC == null) return false;
        if (getClass() != ProaC.getClass()) return false;        
        if (!this.nombre.equals(ProaC.nombre)) return false;
        if (!this.descripcion.equals(ProaC.descripcion)) return false;
        if (this.presupuestoMaximo != ProaC.presupuestoMaximo) return false;
        return true;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPresupuestoMaximo() {
        return presupuestoMaximo;
    }

    public void setPresupuestoMaximo(double presupuestoMaximo) {
        this.presupuestoMaximo = presupuestoMaximo;
    }
       


}
