/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;
import viaggi.Tappa;

/**Interfaccia per il mantenimento di dati di sessione durante l'inserimento
 *
 * @author berto
 */
@Local
public interface CarrelloInserimentoViaggioLocal {
    /** Resituisce la nota settata
     *
     * @return nota
     */
    public String getNota();

    /** Setta una nota
     *
     * @param nota
     */
    void setNota(String nota);

    /** Restituisce il booleano che indica se l'autista richiede contributi o no
     *
     * @return true se l'autista richiede un contributo spese, false altrimenti
     */
    boolean getRichiestaContributi();

    /** Setta la richiesta dei contributi, true se l'autista li richiede e false altrimenti
     *
     * @param richiestaContributi
     */
    void setRichiestaContributi(boolean richiestaContributi);

    /** Restituisce la lista di date dei viaggi
     *
     * @return lista di date
     */
    List<Calendar> getDate();

    /** Setta la lista di date dei viaggi
     *
     * @param date
     */
    void setDate(List<Calendar> date);

    /** Restituisce le tappe impostate
     *
     * @return lista di tappe
     */
    List<Tappa> getTappe();

    /** Setta le tappe del pacchetto
     *
     * @param tappe
     */
    void setTappe(List<Tappa> tappe);

    /** Restituisce la lunghezza del percorso del pacchetto in metri
     *
     * @return lunghezza del percorso in metri
     */
    int getLunghezzaPercorso();

    /** Setta la lunghezza del percorso del pacchetto espressa in metri
     *
     * @param lunghezzaPercorso
     */
    void setLunghezzaPercorso(int lunghezzaPercorso);

    /** Restituisce una stringa che rappresenta il percorso del viaggio
     *
     * @return stringa formattata per la visualizzazione della mappa di google
     */
    public java.lang.String getPercorso();

    /** Restituisce il tipo mezzo settato
     *
     * @return tipo mezzo
     */
    public utenti.TipoMezzo getTipomezzo();

    /** Imposta il tipo mezzo del pacchetto
     *
     * @param tipomezzo
     */
    public void setTipomezzo(utenti.TipoMezzo tipomezzo);
}
