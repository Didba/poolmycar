/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateful;
import viaggi.Tappa;

/**Bean Stateful usato per mantenere i dati di sessione durante l'inserimento
 * La classe mantiene al suo interno le informazioni reltive alle tappe selezionate, alle date,
 * e altri parametri del viaggio da inserire
 * @author berto
 */
@Stateful
public class CarrelloInserimentoViaggioBean implements CarrelloInserimentoViaggioLocal {
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
    private List<Tappa> tappe;
    private List<Calendar> date;
    private boolean richiestaContributi;
    private String nota;
    private int lunghezzaPercorso;

    public int getLunghezzaPercorso() {
        return lunghezzaPercorso;
    }

    public void setLunghezzaPercorso(int lunghezzaPercorso) {
        this.lunghezzaPercorso = lunghezzaPercorso;
    }

    /**
     * Get the value of nota
     *
     * @return the value of nota
     */
    public String getNota() {
        return nota;
    }

    /**
     * Set the value of nota
     *
     * @param nota new value of nota
     */
    public void setNota(String nota) {
        this.nota = nota;
    }


    /**
     * Get the value of richiestaContributi
     *
     * @return the value of richiestaContributi
     */
    public boolean getRichiestaContributi() {
        return richiestaContributi;
    }

    /**
     * Set the value of richiestaContributi
     *
     * @param richiestaContributi new value of richiestaContributi
     */
    public void setRichiestaContributi(boolean richiestaContributi) {
        this.richiestaContributi = richiestaContributi;
    }


    /**
     * Get the value of date
     *
     * @return the value of date
     */
    public List<Calendar> getDate() {
        return date;
    }

    /**
     * Set the value of date
     *
     * @param date new value of date
     */
    public void setDate(List<Calendar> date) {
        this.date = date;
    }


    /**
     * Get the value of tappe
     *
     * @return the value of tappe
     */
    public List<Tappa> getTappe() {
        return tappe;
    }

    /**
     * Set the value of tappe
     *
     * @param tappe new value of tappe
     */
    public void setTappe(List<Tappa> tappe) {
        this.tappe = tappe;
    }

    public String getPercorso(){
        String percorso = "";
        if(tappe.size()>0){
        percorso += ("from: " + tappe.get(0).getIndirizzo().toString());

            for (Tappa t : tappe.subList(1, tappe.size())) {
                percorso += (" to: " + t.getIndirizzo().toString());
            }



        }
        return percorso;
    }

}
