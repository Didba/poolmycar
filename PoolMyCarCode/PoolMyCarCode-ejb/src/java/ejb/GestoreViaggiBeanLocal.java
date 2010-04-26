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

/**
 *
 * @author berto
 */
public interface GestoreViaggiBeanLocal {
    public Tappa geocoding(String indirizzo);

    public RisultatiRicercaViaggi ricercaViaggi(String partenza, String arrivo, boolean intervallo, Calendar data1, Calendar data2, Calendar dataOraPartenza);

    public Pacchetto inserisciPacchetto(List<Tappa> tappe, List<Calendar> date, Viaggiatore autista, String nota, boolean richiestaContributi,String distanza) throws IllegalStateException;

    public Pacchetto aggiornaPacchetto(Pacchetto pacchetto);

    public List<String> getCitta(String subCitta);

    public java.util.List<java.lang.String> getCitta();

}
