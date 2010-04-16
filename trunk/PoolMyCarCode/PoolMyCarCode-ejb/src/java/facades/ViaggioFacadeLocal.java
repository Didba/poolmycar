/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Local;
import viaggi.Viaggio;

/**
 *
 * @author berto
 */
@Local
public interface ViaggioFacadeLocal {

    void create(Viaggio viaggio);

    void edit(Viaggio viaggio);

    void remove(Viaggio viaggio);

    Viaggio find(Object id);

    List<Viaggio> findAll();

    
}