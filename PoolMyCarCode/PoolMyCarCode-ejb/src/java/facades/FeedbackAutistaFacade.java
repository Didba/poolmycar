/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utenti.FeedbackAutista;

/**
 *
 * @author berto
 */
@Stateless
public class FeedbackAutistaFacade implements FeedbackAutistaFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(FeedbackAutista feedbackAutista) {
        em.persist(feedbackAutista);
    }

    public void edit(FeedbackAutista feedbackAutista) {
        em.merge(feedbackAutista);
    }

    public void remove(FeedbackAutista feedbackAutista) {
        em.remove(em.merge(feedbackAutista));
    }

    public FeedbackAutista find(Object id) {
        return em.find(FeedbackAutista.class, id);
    }

    public List<FeedbackAutista> findAll() {
        return em.createQuery("select object(o) from FeedbackAutista as o").getResultList();
    }

}
