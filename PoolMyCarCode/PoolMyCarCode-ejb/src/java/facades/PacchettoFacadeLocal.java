/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Local;
import viaggi.Pacchetto;

/**
 *
 * @author berto
 */
@Local
public interface PacchettoFacadeLocal {

    void create(Pacchetto pacchetto);

    void edit(Pacchetto pacchetto);

    void remove(Pacchetto pacchetto);

    Pacchetto find(Object id);

    List<Pacchetto> findAll();

}
