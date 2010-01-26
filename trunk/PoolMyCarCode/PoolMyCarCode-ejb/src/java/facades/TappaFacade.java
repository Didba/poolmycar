/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import viaggi.Tappa;

/**
 *
 * @author berto
 */
@Stateless
public class TappaFacade implements TappaFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(Tappa tappa) {
        em.persist(tappa);
    }

    public void edit(Tappa tappa) {
        em.merge(tappa);
    }

    public void remove(Tappa tappa) {
        em.remove(em.merge(tappa));
    }

    public Tappa find(Object id) {
        return em.find(Tappa.class, id);
    }

    public List<Tappa> findAll() {
        return em.createQuery("select object(o) from Tappa as o").getResultList();
    }

}
