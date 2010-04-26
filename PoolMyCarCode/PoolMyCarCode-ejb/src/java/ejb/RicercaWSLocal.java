/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;
import viaggi.Pacchetto;

/**
 *
 * @author berto
 */
@Local
public interface RicercaWSLocal {

    public List<Pacchetto> ricerca(java.lang.String partenza, java.lang.String arrivo, java.lang.String giorno, java.lang.String mese, java.lang.String anno);

    public List<Pacchetto> ricerca(String partenza, String arrivo, String giorno1, String mese1, String anno1, String giorno2, String mese2, String anno2);

    public List<Pacchetto> ricerca(String partenza, String arrivo, Calendar data);

    public List<Pacchetto> ricerca(String partenza, String arrivo, Calendar data1, Calendar data2);

    
}
