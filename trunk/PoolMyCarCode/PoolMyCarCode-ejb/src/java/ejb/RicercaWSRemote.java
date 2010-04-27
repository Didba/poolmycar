/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.Calendar;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author berto
 */
@Remote
public interface RicercaWSRemote {

    public List<ejb.Percorso> ricerca(java.lang.String partenza, java.lang.String arrivo, java.lang.String giorno, java.lang.String mese, java.lang.String anno);

    public List<ejb.Percorso> ricerca(java.lang.String partenza, java.lang.String arrivo, java.lang.String giorno1, java.lang.String mese1, java.lang.String anno1, java.lang.String giorno2, java.lang.String mese2, java.lang.String anno2);

    public List<Percorso> ricerca(String partenza, String arrivo, Calendar data);

    public List<Percorso> ricerca(String partenza, String arrivo, Calendar data1, Calendar data2);
    
}
