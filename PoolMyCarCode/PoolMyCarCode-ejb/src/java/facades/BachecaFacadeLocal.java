/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Local;
import viaggi.Bacheca;

/** Interfaccia di BachecaFacade
 * Interfaccia ad uso locale dell'oggetto che rende persistente l'oggetto Bacheca
 * @author berto
 */
@Local
public interface BachecaFacadeLocal {

    void create(Bacheca bacheca);

    void edit(Bacheca bacheca);

    void remove(Bacheca bacheca);

    Bacheca find(Object id);

    List<Bacheca> findAll();

}
