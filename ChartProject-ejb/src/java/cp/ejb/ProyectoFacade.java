/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cp.ejb;

import cp.entity.Proyecto;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author shiba
 */
@Stateless
public class ProyectoFacade extends AbstractFacade<Proyecto> {

    @PersistenceContext(unitName = "ChartProject-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProyectoFacade() {
        super(Proyecto.class);
    }
    
     public Proyecto findByIdProyecto (BigDecimal idProyecto) {
        Query q;
        
       //q = em.createQuery(
       //      "select c from Customer c where c.state= :state and c.zip.zipCode=:cp");
       q = em.createNamedQuery("Proyecto.findByIdProyecto");
       q.setParameter("idProyecto", idProyecto);
       return (Proyecto)q.getSingleResult();
    }  
    
}
