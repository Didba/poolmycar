/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Local;
import utenti.Viaggiatore;

/**
 *
 * @author berto
 */
@Local
public interface ViaggiatoreFacadeLocal {

    void create(Viaggiatore viaggiatore);

    void edit(Viaggiatore viaggiatore);

    void remove(Viaggiatore viaggiatore);

    Viaggiatore find(Object id);

    List<Viaggiatore> findAll();

}
