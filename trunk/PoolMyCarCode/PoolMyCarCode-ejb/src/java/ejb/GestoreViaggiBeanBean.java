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
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import utenti.Autista;
import utenti.Indirizzo;
import viaggi.Bacheca;
import viaggi.Pacchetto;
import viaggi.Tappa;

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

    public Tappa geocoding(String indirizzo){
        Tappa tappa=new Tappa();
        double[] latlon=new double[2];
        indirizzo.replace(' ', '+');
        URL url=null;
        try {
            url = new URL("http://maps.google.com/maps/geo?q=" + indirizzo + "&output=csv&sensor=false&key=ABQIAAAAuAzM4aqr6vo3bsSj_YOfIBRi_j0U6kJrkFvY4-OX2XYmEAa76BRFIJ78nqu_sSWAWUJTZFaxBpaeTA");

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
                    double result = new Double(coordinate[0]);
                    int res=(int) result;
                    if(res==200){
                        latlon[0]=new Double(coordinate[2]);
                        latlon[1]=new Double(coordinate[3]);
                    }
                    else latlon=null;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GestoreViaggiBeanBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(latlon==null)
            return null;

        tappa.setLatitudine(latlon[0]);
        tappa.setLongitudine(latlon[1]);
        Indirizzo indi = new Indirizzo();
        indi.setVia(indirizzo);
        tappa.setIndirizzo(indi);
        //TO-DO:crea oggetto indirizzo parsificando la string e aggiungilo a tappa
        return tappa;

    }


    public void inserisciPacchetto(List<Tappa> tappe, List<Calendar> date, Autista autista, String nota, boolean richiestaContributi) throws IllegalStateException
    {

        //controllo dei parametri
        if(tappe.size()<2)
            throw new IllegalArgumentException("manca partenza o arrivo");
        if(date.size()<1)
            throw new IllegalArgumentException("inserisci almeno una data");
        if(autista==null)
            throw new IllegalArgumentException("manca autista");


        //TO-DO: facade della bacheca

        Pacchetto pacchetto=new Pacchetto();
        pacchetto.setPartenza(tappe.get(0));
        pacchetto.setArrivo(tappe.get(tappe.size()-1)); //da ottimizzare
        pacchetto.setTappeIntermedie(tappe.subList(1, tappe.size()-1));
        pacchetto.setInizio(date.get(0));
        pacchetto.setFine(date.get(date.size()-1));
        pacchetto.setAutista(autista);
        pacchetto.setNota(nota);
        pacchetto.setRichiestaContributi(richiestaContributi);
        pacchetto.setBacheca(new Bacheca());
        //va fatta per ultima
        pacchetto.creaViaggi(date);
        /*
        for(Tappa t: tappe)
            tappaFacade.create(t);  //TO-DO: controllare che lo faccia a dovere
        for(Viaggio v: pacchetto.getViaggi())
            viaggioFacade.create(v);
        */
        pacchettoFacade.create(pacchetto);


    }


}
