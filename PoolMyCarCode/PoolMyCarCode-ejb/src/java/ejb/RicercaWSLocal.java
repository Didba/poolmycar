/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import ejb.Percorso;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;


/**
 *
 * @author berto
 */
@Local
public interface RicercaWSLocal {

    public List<Percorso> ricerca(java.lang.String partenza, java.lang.String arrivo, java.lang.String giorno, java.lang.String mese, java.lang.String anno);

    public List<Percorso> ricerca(String partenza, String arrivo, String giorno1, String mese1, String anno1, String giorno2, String mese2, String anno2);

    public List<Percorso> ricerca(String partenza, String arrivo, Calendar data);

    public List<Percorso> ricerca(String partenza, String arrivo, Calendar data1, Calendar data2);

    
}
