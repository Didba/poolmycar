/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import viaggi.Viaggio;

/**
 *
 * @author berto
 */
@Stateless
public class ViaggioFacade implements ViaggioFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(Viaggio viaggio) {
        em.persist(viaggio);
    }

    public void edit(Viaggio viaggio) {
        em.merge(viaggio);
    }

    public void remove(Viaggio viaggio) {
        em.remove(em.merge(viaggio));
    }

    public Viaggio find(Object id) {
        return em.find(Viaggio.class, id);
    }

    public List<Viaggio> findAll() {
        return em.createQuery("select object(o) from Viaggio as o").getResultList();
    }

}
