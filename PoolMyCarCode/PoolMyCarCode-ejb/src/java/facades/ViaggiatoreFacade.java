/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utenti.Viaggiatore;

/**Oggetto che viene usato per scrivere un viaggiatore nel database
 * Questo oggetto contiene i metodi utili a scrivere un oggetto nel database. Viene usato per la persistenza di tale oggetto
 * @author berto
 */
@Stateless
public class ViaggiatoreFacade implements ViaggiatoreFacadeLocal {

    @PersistenceContext
    private EntityManager em;

    public void create(Viaggiatore viaggiatore) {
        em.persist(viaggiatore);
    }

    public void edit(Viaggiatore viaggiatore) {
        em.merge(viaggiatore);
    }

    public void remove(Viaggiatore viaggiatore) {
        em.remove(em.merge(viaggiatore));
    }

    public Viaggiatore find(Object id) {
        return em.find(Viaggiatore.class, id);
    }

    

    public List<Viaggiatore> findAll() {
        return em.createQuery("select object(o) from Viaggiatore as o").getResultList();
    }

    /**Esegue la login
     * Esegue la query sul database e carica l'oggetto o di tipo Viaggiatore tale che o.login=usr
     * @param usr login dell'utente
     * @return puntatore all'oggetto Viaggiatore relativo all'input
     */
    public Viaggiatore findLogin(String usr){
        Viaggiatore viaggiatore=null;

        List<Viaggiatore> viaggiatori=null;
                
        viaggiatori=em.createQuery("SELECT object(o) FROM Viaggiatore o WHERE o.login = '"+usr+"'").getResultList();
        
        if(viaggiatori.size()>=1){
            viaggiatore=viaggiatori.get(0);
            System.out.println("----------------------- " + viaggiatori.get(0).getLogin());
        }

        return viaggiatore;
    }

    
}
