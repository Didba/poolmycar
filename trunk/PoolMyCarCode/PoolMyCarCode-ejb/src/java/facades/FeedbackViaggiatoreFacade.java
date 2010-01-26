/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utenti.FeedbackViaggiatore;

/**
 *
 * @author berto
 */
@Stateless
public class FeedbackViaggiatoreFacade implements FeedbackViaggiatoreFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(FeedbackViaggiatore feedbackViaggiatore) {
        em.persist(feedbackViaggiatore);
    }

    public void edit(FeedbackViaggiatore feedbackViaggiatore) {
        em.merge(feedbackViaggiatore);
    }

    public void remove(FeedbackViaggiatore feedbackViaggiatore) {
        em.remove(em.merge(feedbackViaggiatore));
    }

    public FeedbackViaggiatore find(Object id) {
        return em.find(FeedbackViaggiatore.class, id);
    }

    public List<FeedbackViaggiatore> findAll() {
        return em.createQuery("select object(o) from FeedbackViaggiatore as o").getResultList();
    }

}
