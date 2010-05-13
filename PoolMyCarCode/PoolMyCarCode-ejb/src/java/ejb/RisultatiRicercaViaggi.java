/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateful;
import viaggi.Pacchetto;

/** Bean statefull che contiene la lista di pacchetti trovati a seguito di una ricerca.
 *  Settando la variabile numGruppoPacchetti si possono utilizzare i metodi getNextPacchetti e getPredPacchetti per scorrere la lista in gruppi
 * di numGruppoPacchetti pacchetti.
 *
 * @author Erica
 */
@Stateful
public class RisultatiRicercaViaggi implements RisultatiRicercaViaggiLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private List<Pacchetto> pacchetti = null;
    private int inizioFinestra = 0;// indice del primo pacchetto della finestra di visualizzazione corrente
    private int fineFinestra = 0; // indice del pacchetto successivo alla finestra di visualizzazione corrente
    private int numGruppoPacchetti;

    /**
     * Get the value of numGruppoPacchetti
     *
     * @return the value of numGruppoPacchetti
     */
    public int getNumGruppoPacchetti() {
        return numGruppoPacchetti;
    }

    /**
     * Set the value of numGruppoPacchetti. Il numero dei pacchetti rappresenta il numero di pacchetti restituiti di volta in volta per la visualizzazione
     *
     * @param numGruppoPacchetti new value of numGruppoPacchetti
     */
    public void setNumGruppoPacchetti(int numGruppoPacchetti) {
        this.numGruppoPacchetti = numGruppoPacchetti;
    }

    /**
     * Get the value of pacchetti
     *
     * @return the value of pacchetti
     */
    public List<Pacchetto> getPacchetti() {
        return pacchetti;
    }

    /**
     * Set the value of pacchetti
     *
     * @param pacchetti new value of pacchetti
     */
    public void setPacchetti(List<Pacchetto> pacchetti) {
        this.pacchetti = pacchetti;
    }
//motodo per restituire i successivi numGruppoPacchetti (al massimo numGruppoPacchetti) pacchetti

    /** Restituisce una lista di pacchetti di dimensione numGruppoPacchetti con i pacchetti non ancora restituiti.
     * Esempio, se numGruppoPacchetti = 3 alla prima chiamata il metodo restituirà i pacchetti dal primo al terzo, alla seconda chiamata
     * restituirà i pacchetti dal quarto al sesto e così via. Se i pacchetti ancora da visualizzare sono meno di quelli da restituire allora restituirà
     * solo gli ultimi.
     *
     * @return gruppo di pacchetti
     */
    public List<Pacchetto> getNextPacchetti() {

        if ((pacchetti == null) || (pacchetti.size() <= fineFinestra)) {
            return null;
        } else {
            List<Pacchetto> l = new LinkedList<Pacchetto>();
            int n = numGruppoPacchetti;

            if (fineFinestra + numGruppoPacchetti > pacchetti.size()) {
                n = pacchetti.size() - fineFinestra ;
            }
            inizioFinestra=fineFinestra;
            fineFinestra+=n;

            for (int i = inizioFinestra; i < fineFinestra; i++) {
                l.add(pacchetti.get(i));
            }

            return l;
        }
    }
//motodo per restituire i precedenti numGruppoPacchetti  pacchetti

    /** Restituisce una lista di pacchetti di dimensione numGruppoPacchetti con i pacchetti già restituiti con getNextPacchetti.
     * Esempio, se numGruppoPacchetti = 3 e il metodo getNextPacchetti ha già restituito i pacchetti fino al sesto allora la prima chiamata di questo metodo
     * restituirà i pacchetti dal primo al terzo. Se non ci sono pacchetti precedenti a quelli restituiti l'ultima volta il metodo ritornerà null.
     * @return gruppo di pacchetti
     */
    public List<Pacchetto> getPredPacchetti() {

        if ((pacchetti == null) || (inizioFinestra - numGruppoPacchetti < 0)) {
            return null;
        } else {
            List<Pacchetto> l = new LinkedList<Pacchetto>();
            int n = numGruppoPacchetti;
            fineFinestra=inizioFinestra;
            inizioFinestra-=n;

           for (int i = inizioFinestra; i < fineFinestra; i++) {
                l.add(pacchetti.get(i));
            }

            return l;
        }
    }

    /**
     * Metodo che restituisce true se ci sono ancora pacchetti da restituire successivi agli ultimi restituiti
     * @return true se ci sono ancora pacchetti, false altrimenti
     */
    public boolean avanti() {
        return fineFinestra < pacchetti.size();
    }

    /**Metodo che restituisce true se ci sono ancora pacchetti da restituire precedenti agli ultimi restituiti
     *
     * @return true se ci sono ancora pacchetti, false altrimenti
     */
    public boolean indietro() {
        return inizioFinestra >= numGruppoPacchetti;
    }
}
