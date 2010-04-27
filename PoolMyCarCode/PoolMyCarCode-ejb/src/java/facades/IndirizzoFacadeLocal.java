/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Local;
import utenti.Indirizzo;

/** Interfaccia di IndirizzoFacade
 * Interfaccia ad uso locale dell'oggetto che rende persistente l'oggetto Indirizzo
 * @author berto
 */
@Local
public interface IndirizzoFacadeLocal {

    void create(Indirizzo indirizzo);

    void edit(Indirizzo indirizzo);

    void remove(Indirizzo indirizzo);

    Indirizzo find(Object id);

    List<Indirizzo> findAll();

    /** Restituisce tutte le stringe "citta" che iniziano con il parametro
     * @param subCitta la stringa da cercare
     * @return una lista di stringhe che rappresentano i nomi delle citta presenti su db
     */
    public List<String> getCitta(String subCitta);

    /** carica tutti i nomi della città presenti nel database
     * @return la lista dei nomi delle città sotto forma di String
     */
    public java.util.List<java.lang.String> getCitta();

}
