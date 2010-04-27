/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Local;

/**Interfaccia per il bean RiempiDB
 * Interfaccia per l'oggetto assegnato a riempire il DB di prova nel momento di inizializzazione
 * @author Erica
 */
@Local
public interface RiempiDBLocal {

    public void riempi() throws java.text.ParseException;
    
}
