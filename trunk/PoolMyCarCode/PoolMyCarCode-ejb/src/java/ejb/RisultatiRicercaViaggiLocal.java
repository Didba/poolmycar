/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Local;

/**
 *
 * @author Erica
 */
@Local
public interface RisultatiRicercaViaggiLocal {

    public void setPacchetti(java.util.List<viaggi.Pacchetto> pacchetti);

    public java.util.List<viaggi.Pacchetto> getPacchetti();
    
    
}
