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

    public java.util.List<viaggi.Pacchetto> findDate(java.util.Date data1, java.util.Date data2);

    public java.util.List<viaggi.Pacchetto> findDataSingola(java.util.Date dataOra);

}