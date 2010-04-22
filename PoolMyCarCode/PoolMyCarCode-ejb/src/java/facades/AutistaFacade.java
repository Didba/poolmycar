/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utenti.Autista;

/**
 *
 * @author berto
 */
@Stateless
public class AutistaFacade implements AutistaFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(Autista autista) {
        em.persist(autista);
    }

    public void edit(Autista autista) {
        em.merge(autista);
    }

    public void remove(Autista autista) {
        em.remove(em.merge(autista));
    }

    public Autista find(Object id) {
        return em.find(Autista.class, id);
    }

    public List<Autista> findAll() {
        return em.createQuery("select object(o) from Autista as o").getResultList();
    }




}
