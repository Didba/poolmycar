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
import javax.persistence.Query;
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
        
        //Query q = em.createQuery("select object(o) from Indirizzo as o where UPPER(o.citta) LIKE UPPER('t%') GROUP BY o.citta ORDER BY COUNT(o.citta) DESC");
        /*Query q = em.createQuery("select object(o) from Indirizzo as o where UPPER(o.citta) LIKE UPPER('t%')");

        List<Indirizzo> ris = q.getResultList();
        

        List<String> citta = new LinkedList<String>();

        int i=0;
        for(Indirizzo ind: ris)
            if(i==3)
                break;
            else{
                citta.add(ind.getCitta());
                i++;
            }
        */

        List<Indirizzo> indirizzi=em.createQuery("select object(o) from Indirizzo as o").getResultList();
        List<String> citta = new LinkedList<String>();
        int j=0;
        // è o(2n) perchè è quadratica, ma può contenere solo 3 elementi
        for(Indirizzo i: indirizzi){
            if(i.getCitta().toUpperCase().startsWith(subCitta.toUpperCase()))
                if(!contiene(citta,i.getCitta())){
                    citta.add(i.getCitta());
                    j++;
                }
            if(j==3)
                break;
        }
        return citta;
    }

    private boolean contiene(List<String> citta, String c) {
        for(String s: citta)
            if(s.equals(c))
                return true;
        return false;
    }

    public List<String> getCitta() {

        List<Indirizzo> indirizzi=em.createQuery("select DISTINCT (o.citta) from Indirizzo as o").getResultList();
        List<String> citta = new LinkedList<String>();
        for(Indirizzo i: indirizzi)
        
                    citta.add(i.getCitta());

        System.out.println("------------- le citta" + citta);
        return citta;

    }

}
