/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utenti.Viaggiatore;

/**
 *
 * @author berto
 */
@Stateless
public class ViaggiatoreFacade implements ViaggiatoreFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(Viaggiatore viaggiatore) {
        em.persist(viaggiatore);
    }

    public void edit(Viaggiatore viaggiatore) {
        em.merge(viaggiatore);
    }

    public void remove(Viaggiatore viaggiatore) {
        em.remove(em.merge(viaggiatore));
    }

    public Viaggiatore find(Object id) {
        return em.find(Viaggiatore.class, id);
    }

    public List<Viaggiatore> findAll() {
        return em.createQuery("select object(o) from Viaggiatore as o").getResultList();
    }

}
