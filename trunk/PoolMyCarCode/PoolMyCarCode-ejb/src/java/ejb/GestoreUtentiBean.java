/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;


import facades.ViaggiatoreFacadeLocal;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import utenti.TipoMezzo;
import utenti.Viaggiatore;

/**Bean stateless usato per incapsulare la logica di business relativa alla gestione utenti
 * La classe offre i metodi necessari all'applicazione per gestire il normale flusso di registrazione di utenti e
 * di passaggi da viaggiatori ad autista
 * @author berto
 */
@Stateless
public class GestoreUtentiBean implements GestoreUtentiLocal {
    @EJB
    private ViaggiatoreFacadeLocal viaggiatoreFacade;
    
    /**Esegue il login di un utente
     * Controlla nel DB la presenza del username e password inserite dall'utente.
     * @param user lo username dell'utente
     * @param pwd la password dell'utente
     * @return l'oggetto viaggiatore v con v.password e v.user uguali ai parametri inseiriti, se questo esiste; null altrimenti
     */
    public Viaggiatore doLogin(String user, String pwd){
        Viaggiatore v =viaggiatoreFacade.findLogin(user);
        if(v.getPassword().equals(pwd))
            return v;
        else
           return null;
    }
    /**Aggiunge un nuovo utente nel DB
     * L'utente fornisce una Stringa di login e una di password; viene invocata la facade dell'entità viaggiatore
     * per rendere persistente l'inserimento solo nel caso in cui la stringa login non sia stata già utilizzata da un altro utente
     * @param login la stringa che l'utente userà come login
     * @param password la stringa che l'utente userà come password
     * @return true se il parametro login non era già presente nel DB, false altrimenti
     */
    public boolean registraUtente(String login, String password) {
      
        Viaggiatore viaggiatore=new Viaggiatore();
        viaggiatore.setLogin(login);
        viaggiatore.setPassword(password);
        Viaggiatore v = viaggiatoreFacade.findLogin(login);
        if(v!=null)
            return false;
        else{
            viaggiatoreFacade.create(viaggiatore);
            return true;
        }
    }

    /**Aggiorna l'oggetto da memoria principale a DB
     * Aggiorna le modifiche effettuate sull'oggetto viaggiatore su DB
     * @param viaggiatore l'oggetto su cui apportare l'aggiornamento
     */
    public void aggiornaUtente(Viaggiatore viaggiatore) {
        viaggiatoreFacade.edit(viaggiatore);
    }

    /**Fa diventare Autista un Viaggiatore
     * Esegue una modifica sul DB per far passare l'utente dai privilegi di Viaggiatore a quelli di Autista.
     * Questo può solo avveire quando l'utente fornisce i suoi dati di patente ed un tipo di mezzo di trasporto da lui utilizzato
     * @param autista l'oggetto che rappresenta l'utente che intraprende il passaggio
     * @param patente una Stringa che rappresenta il numero di patente dell'utente
     * @param tipoMezzo una stringa che descrive un tipo di mezzo di trasporto usato dall'utente per dare passaggi.
     * @return false se l'utente era già un Autista; true altrimenti
     */
    public boolean diventaAutista(Viaggiatore autista, String patente, String tipoMezzo) {

        if(autista.isAutista())
            return false;
        autista.setAutista(true);
        TipoMezzo tp=new TipoMezzo();
        tp.setPosti(new Integer(tipoMezzo));
        tp.setNome("Mezzo a "+tipoMezzo+" posti");
        Set<TipoMezzo> tipiMezzo=new HashSet<TipoMezzo>();
        tipiMezzo.add(tp);
        autista.setMezzi(tipiMezzo);
        autista.setNumeroPatente(patente);
        viaggiatoreFacade.edit(autista);
        return true;
    }
}
