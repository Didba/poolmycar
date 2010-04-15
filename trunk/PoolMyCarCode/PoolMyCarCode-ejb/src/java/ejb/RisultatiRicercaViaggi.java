/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.List;
import javax.ejb.Stateful;
import viaggi.Viaggio;

/**
 *
 * @author Erica
 */
@Stateful
public class RisultatiRicercaViaggi implements RisultatiRicercaViaggiLocal {
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    private List<Viaggio> viaggi = null;

    /**
     * Get the value of viaggi
     *
     * @return the value of viaggi
     */
    public List<Viaggio> getViaggi() {
        return viaggi;
    }

    /**
     * Set the value of viaggi
     *
     * @param viaggi new value of viaggi
     */
    public void setViaggi(List<Viaggio> viaggi) {
        this.viaggi = viaggi;
    }

}
