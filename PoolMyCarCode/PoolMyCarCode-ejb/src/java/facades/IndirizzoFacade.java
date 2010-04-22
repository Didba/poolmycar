/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.LinkedList;
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

    public List<String> getCitta(String subCitta) {
        /*
        return (List<String>) em.createQuery("select distinct citta from Indirizzo as i where upper(i.citta) LIKE upper('"+subCitta+"%')").getResultList();
         */
        List<Indirizzo> indirizzi=findAll();
        List<String> citta = new LinkedList<String>();
        for(Indirizzo i: indirizzi){
            if(i.getCitta().toUpperCase().startsWith(subCitta.toUpperCase()))
                citta.add(i.getCitta());
        }
        return citta;
    }

}
