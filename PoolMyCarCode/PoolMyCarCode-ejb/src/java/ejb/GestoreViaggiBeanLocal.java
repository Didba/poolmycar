/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Local;

/**
 *
 * @author berto
 */
@Local
public interface GestoreViaggiBeanLocal {

    public java.util.List<viaggi.Tappa> geocoding(java.util.List<java.lang.String> indirizzi);

    public void inserisciPacchetto(java.util.List<viaggi.Tappa> tappe, java.util.List<java.util.Date> date, utenti.Autista autista, java.lang.String nota, boolean richiestaContributi, viaggi.Bacheca bacheca);
    
}
