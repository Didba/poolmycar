/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import utenti.Viaggiatore;
import viaggi.Pacchetto;
import viaggi.Tappa;

/**Interfaccia per la logica di business relativa alla gestione viaggi
 *
 * @author berto
 */
public interface GestoreViaggiBeanLocal {

     /**Esegue il geocoding dell'indirizzo specificato
     *
     * @param indirizzo una stringa rappresentante un indirizzo sulla Terra.
     * @return un oggetto tappa costruito sulla base dell'indirizzo fornito come parametro e delle coordinate ottenute
     * tramite il risultato del geocoding. L'oggetto Indirizzo contenuto in Tappa è frutto di una invocazione di
     * reverse geocoding per parsificare una stringa nei campi separati di via, città, stato etc...
     */
    public Tappa geocoding(String indirizzo);

    /**Ricerca i viaggi presenti su DB, sia per intervallo di date che per data singola.
     *
     * @param partenza stringa che rappresenta un indirizzo di partenza. Può anche essere vuota
     * @param arrivo stringa che rappresenta un indirizzo di arrivo. Può anche essere vuota
     * @param intervallo boolean per discriminare se la ricerca va effettuata con intervallo di date (true) o no (false)
     * @param data1 estremo inferiore dell'intervallo di date
     * @param data2 estremo superiore dell'intervallo di date
     * @param dataOraPartenza data per la ricerca senza intervallo
     * @return un bean stateful che contiene al suo interno i risultati della ricerca
     */
    public RisultatiRicercaViaggi ricercaViaggi(String partenza, String arrivo, boolean intervallo, Calendar data1, Calendar data2, Calendar dataOraPartenza);

    /**Crea un nuovo pacchetto su DB
     *
     * @param tappe la lista di tappe (comprese partenza ed arrivo) per cui passa il viaggio descritto dal pacchetto
     * @param date la lista di date per cui è valido il viaggio del pacchetto
     * @param autista il conducente che propone il viaggio
     * @param idMezzo riferimento al mezzo indicato per effettuare il viaggio
     * @param nota informazioni addizionali aggiunte dall'autista
     * @param richiestaContributi booleano per stabilire se il conducente richiede contributi finanziari a chi parteciperà al viaggio
     * @param distanza lunghezza totale calcolata in metri del tragitto attraverso tutte le tappe
     * @return una nuova istanza di oggetto Pacchetto, già resa persistente su DB
     * @throws IllegalStateException se il numero di tappe è minore di 2 (mancano partenza e arrivo) o se nessuna data è stata selezionata
     * o se manca l'autista associato al pacchetto
     */
    public Pacchetto inserisciPacchetto(List<Tappa> tappe, List<Calendar> date, Viaggiatore autista, long idMezzo,String nota, boolean richiestaContributi,String distanza) throws IllegalStateException;

    /**Aggiorna su DB le modifiche effettuate al pacchetto
     *
     * @param pacchetto
     * @return
     */
    public void aggiornaPacchetto(Pacchetto pacchetto);

    /**Restituisce le citta presenti nel DB che iniziano con la stringa subCitta
     *
     * @param subCitta
     * @return
     */
    public List<String> getCitta(String subCitta);

    /**Restituisce tutte le citta presenti nel DB
     *
     * @return
     */
    public java.util.List<java.lang.String> getCitta();

    public void caricaViaggi(utenti.Viaggiatore autista);

}
