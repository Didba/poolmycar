/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Local;
import utenti.Viaggiatore;

/**Interfaccia per la logica di business relativa alla gestione utenti
 *
 * @author berto
 */
@Local
public interface GestoreUtentiLocal {

    /**Esegue il login di un utente
     *
     * @param user lo username dell'utente
     * @param pwd la password dell'utente
     * @return l'oggetto viaggiatore v con v.password e v.user uguali ai parametri inseiriti, se questo esiste; null altrimenti
     */
    public Viaggiatore doLogin(String login, String password);

    /**Aggiunge un nuovo utente nel DB
     *
     * @param login la stringa che l'utente userà come login
     * @param password la stringa che l'utente userà come password
     * @return true se il parametro login non era già presente nel DB, false altrimenti
     */
    public boolean registraUtente(String login, String password);

    /**Aggiorna l'oggetto da memoria principale a DB
     *
     * @param viaggiatore l'oggetto su cui apportare l'aggiornamento
     */
    public void aggiornaUtente(Viaggiatore viaggiatore);

   /**Fa diventare Autista un Viaggiatore
     * 
     * @param autista l'oggetto che rappresenta l'utente che intraprende il passaggio
     * @param patente una Stringa che rappresenta il numero di patente dell'utente
     * @param tipoMezzo una stringa che descrive un tipo di mezzo di trasporto usato dall'utente per dare passaggi.
     * @return false se l'utente era già un Autista; true altrimenti
     */
    public boolean diventaAutista(Viaggiatore viaggiatore, String patente, String tipoMezzo);
}
