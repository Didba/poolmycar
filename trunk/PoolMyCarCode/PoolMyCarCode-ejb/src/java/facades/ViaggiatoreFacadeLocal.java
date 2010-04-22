/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import java.util.Set;
import javax.ejb.Local;
import utenti.TipoMezzo;
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

    public Viaggiatore findLogin(String user);

    void diventaAutista(Viaggiatore viaggiatore, String patente, Set<TipoMezzo> tipoMezzi);

}
