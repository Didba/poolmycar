/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Local;

/**
 *
 * @author berto
 */
@Local
public interface GestoreViaggiBeanLocal {

    public void geocoding(java.util.List<java.lang.String> indirizzi);
    
}
