/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateful;
import viaggi.Pacchetto;

/**
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
    protected int numGruppoPacchetti;

    /**
     * Get the value of numGruppoPacchetti
     *
     * @return the value of numGruppoPacchetti
     */
    public int getNumGruppoPacchetti() {
        return numGruppoPacchetti;
    }

    /**
     * Set the value of numGruppoPacchetti
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

    public boolean avanti() {
        return fineFinestra < pacchetti.size();
    }

    public boolean indietro() {
        return inizioFinestra >= numGruppoPacchetti;
    }
}
