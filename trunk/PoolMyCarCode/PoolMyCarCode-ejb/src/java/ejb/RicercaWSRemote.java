/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import viaggi.Percorso;
import java.util.Calendar;
import javax.ejb.Remote;
import viaggi.Tappa;

/**Definisce le funzionalità di ricerca utilizzabili da applicazioni terze
 *
 * @author berto
 */
@Remote
public interface RicercaWSRemote {

    /**Utilizza la logica di business interna per ricercare un viaggio
     * 
     * @param partenza la stringa che rappresenta un indirizzo di partenza, può essere nullo
     * @param arrivo la stringa che rappresenta un indirizzo di arrivo, può essere nullo
     * @param giorno una stringa che indica il giorno di partenza. E' necessario specificarlo sottoforma di numero del mese (a doppia cifra)
     * @param mese una stringa che indica il mese di partenza. E' necessario specificarlo sottoforma di numero (a doppia cifra) oppure nome
     * completo in inglese o italiano
     * @param anno l'anno di partenza del viaggio
     * @return una lista di oggetti Percorso che incapsulano le informazioni essenziali riguardo ai viaggi trovati corrispondenti alla ricerca
     */
    public Percorso[] ricerca(java.lang.String partenza, java.lang.String arrivo, java.lang.String giorno, java.lang.String mese, java.lang.String anno);
    /**Utilizza la logica di business interna per ricercare un viaggio
     * 
     * @param partenza la stringa che rappresenta un indirizzo di partenza, può essere nullo
     * @param arrivo la stringa che rappresenta un indirizzo di arrivo, può essere nullo
     * @param giorno1 una stringa che indica l'estremo inferiore del giorno di partenza. E' necessario specificarlo sottoforma di numero del mese (a doppia cifra)
     * @param mese1 una stringa che indica l'estremo inferiore del mese di partenza. E' necessario specificarlo sottoforma di numero (a doppia cifra) oppure nome
     * completo in inglese o italiano
     * @param anno1 l'anno estremo inferiore di partenza del viaggio
     * @param giorno2 una stringa che indica l'estremo superiore del giorno di partenza. E' necessario specificarlo sottoforma di numero del mese (a doppia cifra)
     * @param mese1 una stringa che indica l'estremo superiore del mese di partenza. E' necessario specificarlo sottoforma di numero (a doppia cifra) oppure nome
     * completo in inglese o italiano
     * @param anno1 l'anno estremo superiore di partenza del viaggio
     * @return una lista di oggetti Percorso che incapsulano le informazioni essenziali riguardo ai viaggi trovati corrispondenti alla ricerca
     */
    public Percorso[] ricerca(java.lang.String partenza, java.lang.String arrivo, java.lang.String giorno1, java.lang.String mese1, java.lang.String anno1, java.lang.String giorno2, java.lang.String mese2, java.lang.String anno2);
    /**Utilizza la logica di business interna per ricercare un viaggio
     *
     * @param partenza la stringa che rappresenta un indirizzo di partenza, può essere nullo
     * @param arrivo la stringa che rappresenta un indirizzo di arrivo, può essere nullo
     * @param data un oggetto che indica il giorno di partenza
     * @return una lista di oggetti Percorso che incapsulano le informazioni essenziali riguardo ai viaggi trovati corrispondenti alla ricerca
     */
    public Percorso[] ricerca(String partenza, String arrivo, Calendar data);
    /**Utilizza la logica di business interna per ricercare un viaggio
     * 
     * @param partenza la stringa che rappresenta un indirizzo di partenza, può essere nullo
     * @param arrivo la stringa che rappresenta un indirizzo di arrivo, può essere nullo
     * @param data1 rappresenta l'estremo inferiore dell'intervallo di date
     * @param data1 rappresenta l'estremo superiore dell'intervallo di date
     * @return una lista di oggetti Percorso che incapsulano le informazioni essenziali riguardo ai viaggi trovati corrispondenti alla ricerca
     */
    public Percorso[] ricerca(String partenza, String arrivo, Calendar data1, Calendar data2);

    
}
