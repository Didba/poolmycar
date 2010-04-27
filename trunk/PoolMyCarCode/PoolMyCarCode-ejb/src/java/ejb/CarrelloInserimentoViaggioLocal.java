/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;
import viaggi.Tappa;

/**Interfaccia per il mantenimento di dati di sessione durnte l'inserimento
 *
 * @author berto
 */
@Local
public interface CarrelloInserimentoViaggioLocal {
    public String getNota();

    void setNota(String nota);

    boolean getRichiestaContributi();

    void setRichiestaContributi(boolean richiestaContributi);

    List<Calendar> getDate();

    void setDate(List<Calendar> date);

    List<Tappa> getTappe();

    void setTappe(List<Tappa> tappe);

    int getLunghezzaPercorso();

    void setLunghezzaPercorso(int lunghezzaPercorso);

    public java.lang.String getPercorso();
}
