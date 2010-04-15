/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.List;
import javax.ejb.Local;
import viaggi.Viaggio;

/**
 *
 * @author Erica
 */
@Local
public interface RisultatiRicercaViaggiLocal {
     public List<Viaggio> getViaggi() ;
     public void setViaggi(List<Viaggio> viaggi);
    
}
