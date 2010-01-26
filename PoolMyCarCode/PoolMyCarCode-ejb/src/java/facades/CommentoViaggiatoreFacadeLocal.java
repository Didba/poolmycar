/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Local;
import utenti.CommentoViaggiatore;

/**
 *
 * @author berto
 */
@Local
public interface CommentoViaggiatoreFacadeLocal {

    void create(CommentoViaggiatore commentoViaggiatore);

    void edit(CommentoViaggiatore commentoViaggiatore);

    void remove(CommentoViaggiatore commentoViaggiatore);

    CommentoViaggiatore find(Object id);

    List<CommentoViaggiatore> findAll();

}
