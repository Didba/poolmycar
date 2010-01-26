/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Local;
import viaggi.Richiesta;

/**
 *
 * @author berto
 */
@Local
public interface RichiestaFacadeLocal {

    void create(Richiesta richiesta);

    void edit(Richiesta richiesta);

    void remove(Richiesta richiesta);

    Richiesta find(Object id);

    List<Richiesta> findAll();

}
