/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Local;
import utenti.Viaggiatore;

/** Interfaccia di ViaggiatoreFacade
 * Interfaccia ad uso locale dell'oggetto che rende persistente l'oggetto Viaggiatore
 * @author berto
 */
@Local
public interface ViaggiatoreFacadeLocal {

    void create(Viaggiatore viaggiatore);

    void edit(Viaggiatore viaggiatore);

    void remove(Viaggiatore viaggiatore);

    Viaggiatore find(Object id);

    List<Viaggiatore> findAll();
    
    /**Esegue la login
     * Esegue la query sul database e carica l'oggetto o di tipo Viaggiatore tale che o.login=usr
     * @param usr login dell'utente
     * @return puntatore all'oggetto Viaggiatore relativo all'input
     */
    public Viaggiatore findLogin(String user);

}
