/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package war;

import viaggi.Percorso;
import ejb.RicercaWSRemote;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import viaggi.Tappa;

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
    public Percorso[] ricerca(@WebParam(name = "partenza")
    String partenza, @WebParam(name = "arrivo")
    String arrivo, @WebParam(name = "giorno")
    String giorno, @WebParam(name = "mese")
    String mese, @WebParam(name = "anno")
    String anno) {
        return ejbRef.ricerca(partenza, arrivo, giorno, mese, anno);
    }

    @WebMethod(operationName = "ricerca_1")
    @RequestWrapper(className = "ricerca_1")
    @ResponseWrapper(className = "ricerca_1Response")
    public Percorso[] ricerca(@WebParam(name = "partenza")
    String partenza, @WebParam(name = "arrivo")
    String arrivo, @WebParam(name = "giorno1")
    String giorno1, @WebParam(name = "mese1")
    String mese1, @WebParam(name = "anno1")
    String anno1, @WebParam(name = "giorno2")
    String giorno2, @WebParam(name = "mese2")
    String mese2, @WebParam(name = "anno2")
    String anno2) {
        return ejbRef.ricerca(partenza, arrivo, giorno1, mese1, anno1, giorno2, mese2, anno2);
    }

    @WebMethod(operationName = "ricerca_2")
    @RequestWrapper(className = "ricerca_2")
    @ResponseWrapper(className = "ricerca_2Response")
    public Percorso[] ricerca(@WebParam(name = "partenza")
    String partenza, @WebParam(name = "arrivo")
    String arrivo, @WebParam(name = "data")
    Calendar data) {
        return ejbRef.ricerca(partenza, arrivo, data);
    }

    @WebMethod(operationName = "ricerca_3")
    @RequestWrapper(className = "ricerca_3")
    @ResponseWrapper(className = "ricerca_3Response")
    public Percorso[] ricerca(@WebParam(name = "partenza")
    String partenza, @WebParam(name = "arrivo")
    String arrivo, @WebParam(name = "data1")
    Calendar data1, @WebParam(name = "data2")
    Calendar data2) {
        return ejbRef.ricerca(partenza, arrivo, data1, data2);
    }

    @WebMethod(operationName = "prova")
    public Percorso prova() {
        return ejbRef.prova();
    }

}
