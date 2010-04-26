/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Local;
import utenti.Indirizzo;

/**
 *
 * @author berto
 */
@Local
public interface IndirizzoFacadeLocal {

    void create(Indirizzo indirizzo);

    void edit(Indirizzo indirizzo);

    void remove(Indirizzo indirizzo);

    Indirizzo find(Object id);

    List<Indirizzo> findAll();

    public List<String> getCitta(String subCitta);

    public java.util.List<java.lang.String> getCitta();

}
