/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Local;
import utenti.Autista;

/**
 *
 * @author berto
 */
@Local
public interface AutistaFacadeLocal {

    void create(Autista autista);

    void edit(Autista autista);

    void remove(Autista autista);

    Autista find(Object id);

    List<Autista> findAll();

}
