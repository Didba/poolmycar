/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package war;

import ejb.Percorso;
import ejb.RicercaWSRemote;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author berto
 */
@WebService()
public class RicercaWS {
    @EJB
    private RicercaWSRemote ejbRef;
    // Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "ricerca")
    public List<Percorso> ricerca(@WebParam(name = "partenza")
    String partenza, @WebParam(name = "arrivo")
    String arrivo, @WebParam(name = "giorno")
    String giorno, @WebParam(name = "mese")
    String mese, @WebParam(name = "anno")
    String anno) {
        return ejbRef.ricerca(partenza, arrivo, giorno, mese, anno);
    }

}
