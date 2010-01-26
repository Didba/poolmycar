/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Local;
import viaggi.Tappa;

/**
 *
 * @author berto
 */
@Local
public interface TappaFacadeLocal {

    void create(Tappa tappa);

    void edit(Tappa tappa);

    void remove(Tappa tappa);

    Tappa find(Object id);

    List<Tappa> findAll();

}
