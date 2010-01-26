/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import viaggi.Richiesta;

/**
 *
 * @author berto
 */
@Stateless
public class RichiestaFacade implements RichiestaFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(Richiesta richiesta) {
        em.persist(richiesta);
    }

    public void edit(Richiesta richiesta) {
        em.merge(richiesta);
    }

    public void remove(Richiesta richiesta) {
        em.remove(em.merge(richiesta));
    }

    public Richiesta find(Object id) {
        return em.find(Richiesta.class, id);
    }

    public List<Richiesta> findAll() {
        return em.createQuery("select object(o) from Richiesta as o").getResultList();
    }

}
