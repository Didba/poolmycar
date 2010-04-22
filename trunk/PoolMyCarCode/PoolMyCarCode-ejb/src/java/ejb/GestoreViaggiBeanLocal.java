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

   RisultatiRicercaViaggi ricercaViaggi(String partenza, String arrivo, boolean intervallo, Date data1, Date data2, Date dataOraPartenza);

    public Pacchetto inserisciPacchetto(List<Tappa> tappe, List<Calendar> date, Viaggiatore autista, String nota, boolean richiestaContributi,String distanza) throws IllegalStateException;

    public Pacchetto aggiornaPacchetto(Pacchetto pacchetto);


}
