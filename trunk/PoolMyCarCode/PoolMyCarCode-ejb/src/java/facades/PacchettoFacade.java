/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import viaggi.Pacchetto;
import viaggi.Viaggio;

/**
 *
 * @author berto
 */
@Stateless
public class PacchettoFacade implements PacchettoFacadeLocal {

    @EJB
    private BachecaFacadeLocal bachecaFacade;
    @EJB
    private ViaggioFacadeLocal viaggioFacade;
    @PersistenceContext
    private EntityManager em;

    public void create(Pacchetto pacchetto) {
        bachecaFacade.create(pacchetto.getBacheca());
        for (Viaggio v : pacchetto.getViaggi()) {
            viaggioFacade.create(v);
        }
        em.persist(pacchetto);
    }

    public void edit(Pacchetto pacchetto) {
        em.merge(pacchetto);
    }

    public void remove(Pacchetto pacchetto) {
        em.remove(em.merge(pacchetto));
    }

    public Pacchetto find(Object id) {
        return em.find(Pacchetto.class, id);
    }

    public List<Pacchetto> findAll() {
        return em.createQuery("select object(o) from Pacchetto as o").getResultList();
    }

    public List<Pacchetto> findDate(Date data1, Date data2) {
        Query q = em.createQuery("select object(o) from Pacchetto as o where (o.inizio BETWEEN :d1 AND :d2) OR (o.fine BETWEEN :d1 AND :d2)");
        Calendar c1 = new GregorianCalendar();
        c1.setTime(data1);
        Calendar c2 = new GregorianCalendar();
        c2.setTime(data2);
        q.setParameter("d1", c1);
        q.setParameter("d2", c2);
        List<Pacchetto> l = q.getResultList();
        System.out.println("----------- viaggi trovati:" + l);
        return l;
    }

    public List<Pacchetto> findDataSingola(Date dataOra) {
        Query q = em.createQuery("select object(o) from Pacchetto as o where (o.inizio BETWEEN :d1 AND :d2) OR (o.fine BETWEEN :d1 AND :d2)");
        Calendar c1 = new GregorianCalendar();
        c1.setTime(dataOra);
        Calendar c2 = (Calendar) c1.clone();
        c2.set(Calendar.MINUTE, 59);
        q.setParameter("d1", c1);
        q.setParameter("d2", c2);
        List<Pacchetto> l = q.getResultList();
        System.out.println("----------- viaggi trovati:" + l);
        return l;
    }
}
