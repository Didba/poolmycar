/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import facades.PacchettoFacadeLocal;
import facades.TappaFacadeLocal;
import facades.ViaggioFacadeLocal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import utenti.Autista;
import viaggi.Bacheca;
import viaggi.Pacchetto;
import viaggi.Tappa;
import viaggi.Viaggio;

/**
 *
 * @author berto
 */
@Stateless
public class GestoreViaggiBeanBean implements GestoreViaggiBeanLocal {
    @EJB
    private TappaFacadeLocal tappaFacade;
    @EJB
    private ViaggioFacadeLocal viaggioFacade;
    @EJB
    private PacchettoFacadeLocal pacchettoFacade;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
    public List<Tappa> geocoding(List<String> indirizzi){
        List<Tappa> tappe=new LinkedList<Tappa>();
        for(String s: indirizzi){
            s.replace(' ', '+');
            URL url=null;
            try {
                url = new URL("http://maps.google.com/maps/geo?q=" + s + "&output=csv&sensor=false&key=ABQIAAAAuAzM4aqr6vo3bsSj_YOfIBRi_j0U6kJrkFvY4-OX2XYmEAa76BRFIJ78nqu_sSWAWUJTZFaxBpaeTA");
                
            } catch (MalformedURLException ex) {
                Logger.getLogger(GestoreViaggiBeanBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            BufferedReader in;
            try {
                in = new BufferedReader(new InputStreamReader(url.openStream()));
                
                String inputLine;

                while ((inputLine = in.readLine()) != null){
                    String[] coordinate=inputLine.split(",");
                    if(coordinate.length==4){
                        Tappa t=new Tappa();
                        t.setLatitudine(new Double(coordinate[2]));
                        t.setLongitudine(new Double(coordinate[3]));
                        tappe.add(t);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(GestoreViaggiBeanBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return tappe;
        
    }
    
    
    public void inserisciPacchetto(List<Tappa> tappe, List<Date> date, Autista autista, String nota, boolean richiestaContributi, Bacheca bacheca) throws IllegalStateException
    {

        //controllo dei parametri
        if(tappe.size()<2)
            throw new IllegalArgumentException("manca partenza o arrivo");
        if(date.size()<1)
            throw new IllegalArgumentException("inserisci almeno una data");
        if(autista==null)
            throw new IllegalArgumentException("manca autista");
        if(bacheca==null)
            throw new IllegalArgumentException("manca bacheca");

        Pacchetto pacchetto=new Pacchetto();
        pacchetto.setPartenza(tappe.get(0));
        pacchetto.setArrivo(tappe.get(tappe.size()-1)); //da ottimizzare
        pacchetto.setTappeIntermedie(tappe.subList(1, tappe.size()-1));
        pacchetto.setBacheca(bacheca);
        pacchetto.setInizio(date.get(0));
        pacchetto.setFine(date.get(date.size()-1));
        pacchetto.setAutista(autista);
        pacchetto.setNota(nota);
        pacchetto.setRichiestaContributi(richiestaContributi);
        pacchetto.creaViaggi(date);

        //a questo punto abbiamo il pacchetto bello e finito
        for(Tappa t: tappe)
            tappaFacade.create(t);  //controllare che lo faccia a dovere
        for(Viaggio v: pacchetto.getViaggi())
            viaggioFacade.create(v);   //MA METTE ANCHE LE TAPPE E QUINDI GLI INDIRIZZI???
                                       //PER ORA MANCA L'INDIRIZZO QUI
        pacchettoFacade.create(pacchetto);


    }
}
