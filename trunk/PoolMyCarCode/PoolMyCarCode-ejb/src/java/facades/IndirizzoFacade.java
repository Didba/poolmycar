/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utenti.Indirizzo;

/**
 *
 * @author berto
 */
@Stateless
public class IndirizzoFacade implements IndirizzoFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(Indirizzo indirizzo) {
        em.persist(indirizzo);
    }

    public void edit(Indirizzo indirizzo) {
        em.merge(indirizzo);
    }

    public void remove(Indirizzo indirizzo) {
        em.remove(em.merge(indirizzo));
    }

    public Indirizzo find(Object id) {
        return em.find(Indirizzo.class, id);
    }

    public List<Indirizzo> findAll() {
        return em.createQuery("select object(o) from Indirizzo as o").getResultList();
    }

}
