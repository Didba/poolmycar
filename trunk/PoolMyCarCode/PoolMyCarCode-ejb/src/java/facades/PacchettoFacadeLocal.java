/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;
import utenti.Viaggiatore;
import viaggi.Pacchetto;

/** Interfaccia di PacchettoFacade
 * Interfaccia ad uso locale dell'oggetto che rende persistente l'oggetto Pacchetto
 * @author berto
 */
@Local
public interface PacchettoFacadeLocal {

    /** scrive nel database un pacchetto
     * scrive nel database un'oggetto di tipo pacchetto e i suoi relativi viaggi
     * @param pacchetto l'oggetto da scrivere
     */
    void create(Pacchetto pacchetto);

    void edit(Pacchetto pacchetto);

    void remove(Pacchetto pacchetto);

    Pacchetto find(Object id);

    List<Pacchetto> findAll();

    /** Seleziona i pacchetti che intercorrono tra i due parametri
     * @param data1 la data da cui iniziare la ricerca
     * @param data2 la data in cui finire la ricerca
     * @return una lista di pacchetti contenti i viaggi corrispondenti alle features di ricerca
     */
    public java.util.List<viaggi.Pacchetto> findDate(Calendar data1,  Calendar data2);

    /** carica i pacchetti relativi alla data nel parametro
     * esegue una query per caricando tutti i pacchetti che intercorrono nella data compresa nel parametro a partire dall'ora indicata nell'oggetto calendar.
     * Questo metodo verrà usato per la ricerca dei viaggi
     * @param dataOra l'oggetto che comprende data e ora relativa ai viaggi da cercare
     * @return una lista di pacchetti contenti i viaggi corrispondenti alle features di ricerca
     */
    public java.util.List<viaggi.Pacchetto> findDataSingola(Calendar dataOra);

    public java.util.List<viaggi.Pacchetto> findDaAutista(Viaggiatore autista);

    public viaggi.Pacchetto findOne(java.lang.Long id);


}
