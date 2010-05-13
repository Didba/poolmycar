/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Local;
import viaggi.Pacchetto;

/** Bean statefull che contiene la lista di pacchetti trovati a seguito di una ricerca.
 *  Settando la variabile numGruppoPacchetti si possono utilizzare i metodi getNextPacchetti e getPredPacchetti per scorrere la lista in gruppi
 * di numGruppoPacchetti pacchetti.
 *
 * @author Erica
 */
@Local
public interface RisultatiRicercaViaggiLocal {

    /**
     * Get the value of pacchetti
     *
     * @return the value of pacchetti
     */
    public java.util.List<viaggi.Pacchetto> getPacchetti();

    /**
     * Set the value of pacchetti
     *
     * @param pacchetti new value of pacchetti
     */
    public void setPacchetti(List<Pacchetto> pacchetti);

    /** Restituisce una lista di pacchetti di dimensione numGruppoPacchetti con i pacchetti non ancora restituiti.
     * Esempio, se numGruppoPacchetti = 3 alla prima chiamata il metodo restituirà i pacchetti dal primo al terzo, alla seconda chiamata
     * restituirà i pacchetti dal quarto al sesto e così via. Se i pacchetti ancora da visualizzare sono meno di quelli da restituire allora restituirà
     * solo gli ultimi.
     *
     * @return gruppo di pacchetti
     */
    public List<Pacchetto> getNextPacchetti();

    /** Restituisce una lista di pacchetti di dimensione numGruppoPacchetti con i pacchetti già restituiti con getNextPacchetti.
     * Esempio, se numGruppoPacchetti = 3 e il metodo getNextPacchetti ha già restituito i pacchetti fino al sesto allora la prima chiamata di questo metodo
     * restituirà i pacchetti dal primo al terzo. Se non ci sono pacchetti precedenti a quelli restituiti l'ultima volta il metodo ritornerà null.
     * @return gruppo di pacchetti
     */
    public java.util.List<viaggi.Pacchetto> getPredPacchetti();

    /**
     * Metodo che restituisce true se ci sono ancora pacchetti da restituire successivi agli ultimi restituiti
     * @return true se ci sono ancora pacchetti, false altrimenti
     */
    public boolean avanti();

    /**Metodo che restituisce true se ci sono ancora pacchetti da restituire precedenti agli ultimi restituiti
     *
     * @return true se ci sono ancora pacchetti, false altrimenti
     */
    public boolean indietro();

    /**
     * Get the value of numGruppoPacchetti
     *
     * @return the value of numGruppoPacchetti
     */
    public int getNumGruppoPacchetti();

    /**
     * Set the value of numGruppoPacchetti. Il numero dei pacchetti rappresenta il numero di pacchetti restituiti di volta in volta per la visualizzazione
     *
     * @param numGruppoPacchetti new value of numGruppoPacchetti
     */
    public void setNumGruppoPacchetti(int numGruppoPacchetti);
}
