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

    /** Restituisce tutte le stringe "citta" che iniziano con il parametro
     * Esegue la query su DB nella tavola Indirizzo i dove i.citta inizia con subcitta. questo metodo viene usato per l'autocompletamento
     * @param subCitta la stringa da cercare
     * @return una lista di stringhe che rappresentano i nomi delle citta presenti su db
     */
    public List<String> getCitta(String subCitta) {
        
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

    /** carica tutti i nomi della città presenti nel database
     * Esegue la query su DB nella tavola Indirizzo e legge tutti i nomi del campo citta
     * @return la lista dei nomi delle città sotto forma di String
     */
    public List<String> getCitta() {

        List<String> citta=em.createQuery("select DISTINCT(o.citta) from Indirizzo as o").getResultList();
        
        return citta;

    }

}
