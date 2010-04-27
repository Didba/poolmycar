/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Remote;

/**
 *
 * @author berto
 */
@Remote
public interface RicercaWSRemote {

    public java.util.List<ejb.Percorso> ricerca(java.lang.String partenza, java.lang.String arrivo, java.lang.String giorno, java.lang.String mese, java.lang.String anno);
    
}
