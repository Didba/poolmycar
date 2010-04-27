/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import viaggi.Tappa;
import viaggi.Viaggio;

/**
 *
 * @author berto
 */
@Stateless
public class ViaggioFacade implements ViaggioFacadeLocal {

    @EJB
    private TappaFacadeLocal tappaFacade;
    @PersistenceContext
    private EntityManager em;

    /** scrive nel database un viaggio
     * scrive nel database un'oggetto di tipo Viaggio e i suoi relativi dati, creando le i vari oggetti di tipo Tappa che lo compongono
     * @param viaggio l'oggetto da scrivere
     */
    public void create(Viaggio viaggio) {
        tappaFacade.create(viaggio.getPartenza());
        tappaFacade.create(viaggio.getArrivo());
        for (Tappa t : viaggio.getTappeIntermedie()) {
            tappaFacade.create(t);
        }
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
