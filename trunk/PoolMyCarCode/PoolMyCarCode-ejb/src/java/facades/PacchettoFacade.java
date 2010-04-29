/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import utenti.Viaggiatore;
import viaggi.Pacchetto;
import viaggi.Viaggio;

/**Oggetto che viene usato per scrivere l'indirizzo nel database
 * Questo oggetto contiene i metodi utili a scrivere un oggetto nel database. Viene usato per la persistenza di tale oggetto
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

    /** scrive nel database un pacchetto
     * scrive nel database un'oggetto di tipo pacchetto e i suoi relativi viaggi
     * @param pacchetto l'oggetto da scrivere
     */
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

    /** Seleziona i pacchetti che intercorrono tra i due parametri
     * Fa una query su database caricando e restituendo tutti i pacchetti che intercorrono tra data1 e data2
     * Questo metodo verrà usato per la ricerca dei viaggi
     * @param data1 la data da cui iniziare la ricerca
     * @param data2 la data in cui finire la ricerca
     * @return una lista di pacchetti contenti i viaggi corrispondenti alle features di ricerca
     */
    public List<Pacchetto> findDate(Calendar data1, Calendar data2) {
        Query q = em.createQuery("select object(o) from Pacchetto as o where ((o.inizio BETWEEN :d1 AND :d2) OR (o.fine BETWEEN :d1 AND :d2)) AND (o.fine >= :today) ORDER BY o.inizio,o.fine");
        Calendar today = new GregorianCalendar();
        q.setParameter("today", today);
        q.setParameter("d1", data1);
        q.setParameter("d2", data2);
        List<Pacchetto> l = q.getResultList();
        System.out.println("----------- viaggi trovati:" + l);
        return l;
    }

    /** legge i pacchetti relativi alla data nel parametro
     * esegue una query per caricando tutti i pacchetti che intercorrono nella data compresa nel parametro a partire dall'ora indicata nell'oggetto calendar.
     * Questo metodo verrà usato per la ricerca dei viaggi
     * @param dataOra l'oggetto che comprende data e ora relativa ai viaggi da cercare
     * @return una lista di pacchetti contenti i viaggi corrispondenti alle features di ricerca
     */
    public List<Pacchetto> findDataSingola(Calendar dataOra) {
        Query q = em.createQuery("select object(o) from Pacchetto as o where (:d1 BETWEEN o.inizio AND o.fine) AND (o.fine >= :today) ORDER BY o.inizio,o.fine");
        q.setParameter("d1", dataOra);
        Calendar today = new GregorianCalendar();
        q.setParameter("today", today);
        System.out.println("oggi è " + today);
        List<Pacchetto> l = q.getResultList();
        System.out.println("----------- d1:" + dataOra.getTime());
        System.out.println("----------- viaggi trovati:" + l);
        return l;
    }

    public List<Pacchetto> findDaAutista(Viaggiatore a) {
        Query q = em.createQuery("select object(o) from Pacchetto as o where o.autista = :a");
        q.setParameter("a", a);
        List<Pacchetto> l = q.getResultList();

        System.out.println("lista viaggi da autista: " + l);
        return l;
    }

    public Pacchetto findOne(Long id) {
        Query q = em.createQuery("select object(o) from Pacchetto as o where o.id = :i");
        q.setParameter("i", id);
        List<Pacchetto> l = q.getResultList();

        System.out.println("pacchetto: " + l);
        if (l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }
}
