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
public interface RiempiDBLocal {

    public void riempi() throws java.text.ParseException;
    
}
