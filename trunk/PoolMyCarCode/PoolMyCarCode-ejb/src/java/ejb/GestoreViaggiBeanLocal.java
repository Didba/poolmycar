/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.Date;
import java.util.List;
import utenti.Autista;
import viaggi.Bacheca;
import viaggi.Tappa;

/**
 *
 * @author berto
 */
interface GestoreViaggiBeanLocal {
    public Tappa geocoding(String indirizzo);
    public void inserisciPacchetto(List<Tappa> tappe, List<Date> date, Autista autista, String nota, boolean richiestaContributi, Bacheca bacheca) throws IllegalStateException;


}
