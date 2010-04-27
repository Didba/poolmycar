/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Local;
import viaggi.Viaggio;

/** Interfaccia di ViaggioFacade
 * Interfaccia ad uso locale dell'oggetto che rende persistente l'oggetto Viaggio
 * @author berto
 */
@Local
public interface ViaggioFacadeLocal {

    /** scrive nel database un viaggio
     * @param viaggio l'oggetto da scrivere
     */
    void create(Viaggio viaggio);

    void edit(Viaggio viaggio);

    void remove(Viaggio viaggio);

    Viaggio find(Object id);

    List<Viaggio> findAll();

    
}
