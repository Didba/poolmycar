/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utenti.CommentoViaggiatore;

/**
 *
 * @author berto
 */
@Stateless
public class CommentoViaggiatoreFacade implements CommentoViaggiatoreFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(CommentoViaggiatore commentoViaggiatore) {
        em.persist(commentoViaggiatore);
    }

    public void edit(CommentoViaggiatore commentoViaggiatore) {
        em.merge(commentoViaggiatore);
    }

    public void remove(CommentoViaggiatore commentoViaggiatore) {
        em.remove(em.merge(commentoViaggiatore));
    }

    public CommentoViaggiatore find(Object id) {
        return em.find(CommentoViaggiatore.class, id);
    }

    public List<CommentoViaggiatore> findAll() {
        return em.createQuery("select object(o) from CommentoViaggiatore as o").getResultList();
    }

}
