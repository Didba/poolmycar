/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

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

   
}
