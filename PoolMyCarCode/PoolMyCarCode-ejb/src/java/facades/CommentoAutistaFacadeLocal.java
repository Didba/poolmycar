/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Local;
import utenti.CommentoAutista;

/** Interfaccia di CommentoAutistaFacade
 * Interfaccia ad uso locale dell'oggetto che rende persistente l'oggetto CommentoAutista
 * @author berto
 */
@Local
public interface CommentoAutistaFacadeLocal {

    void create(CommentoAutista commentoAutista);

    void edit(CommentoAutista commentoAutista);

    void remove(CommentoAutista commentoAutista);

    CommentoAutista find(Object id);

    List<CommentoAutista> findAll();

}
