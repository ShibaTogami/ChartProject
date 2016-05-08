/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cp.ejb;

import cp.entity.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author nacho
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "ChartProject-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
     public Usuario getUsuarioPorNickname(String usuario) {
        Usuario salida=null;
        Query q = em.createNamedQuery("Usuario.findByNickname");
        q.setParameter("nickname", usuario);
        try
        {
            salida = (Usuario)q.getSingleResult();
        } catch (NoResultException excepcion)
        {
            salida=null;
        }
                
        return salida;
    }
}
