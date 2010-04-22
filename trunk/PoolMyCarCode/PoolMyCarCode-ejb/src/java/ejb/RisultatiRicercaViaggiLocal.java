/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.List;
import javax.ejb.Local;
import viaggi.Pacchetto;

/**
 *
 * @author Erica
 */
@Local
public interface RisultatiRicercaViaggiLocal {

    public void setPacchetti(java.util.List<viaggi.Pacchetto> pacchetti);

    public java.util.List<viaggi.Pacchetto> getPacchetti();

    public List<Pacchetto> getNextPacchetti();

    public void setPacchettiVisti(int pacchettiVisti);

    public int getPacchettiVisti();

    public java.util.List<viaggi.Pacchetto> getPredPacchetti();

    public boolean avanti();

    public boolean indietro();
    
    
}
