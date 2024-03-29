/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Local;
import utenti.TipoMezzo;

/** Interfaccia di TipoMezzoFacade
 * Interfaccia ad uso locale dell'oggetto che rende persistente l'oggetto Tipomezzo
 * @author berto
 */
@Local
public interface TipoMezzoFacadeLocal {

    void create(TipoMezzo tipoMezzo);

    void edit(TipoMezzo tipoMezzo);

    void remove(TipoMezzo tipoMezzo);

    TipoMezzo find(Object id);

    public TipoMezzo find(long id);

    List<TipoMezzo> findAll();

}
