/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import facades.IndirizzoFacadeLocal;
import facades.PacchettoFacadeLocal;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import utenti.Indirizzo;
import viaggi.Bacheca;
import viaggi.Pacchetto;
import viaggi.Tappa;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utenti.Viaggiatore;
import viaggi.Viaggio;

/**
 *
 * @author berto
 */
@Stateless
public class GestoreViaggiBean implements GestoreViaggiBeanLocal {
    @EJB
    private IndirizzoFacadeLocal indirizzoFacade;
    @EJB
    private PacchettoFacadeLocal pacchettoFacade;
    private float percentuale = 0.5f;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
    public Tappa geocoding(String indirizzo) {
        Tappa tappa = new Tappa();
        double[] latlon = new double[2];
        indirizzo = indirizzo.replace(' ', '+');
        URL url = null;
        try {
            url = new URL("http://maps.google.com/maps/geo?q=" + indirizzo + "&output=csv&sensor=false&key=ABQIAAAAuAzM4aqr6vo3bsSj_YOfIBRi_j0U6kJrkFvY4-OX2XYmEAa76BRFIJ78nqu_sSWAWUJTZFaxBpaeTA&language=it");
        } catch (MalformedURLException ex) {
            Logger.getLogger(GestoreViaggiBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                String[] coordinate = inputLine.split(",");
                if (coordinate.length == 4) {
                    double result = new Double(coordinate[0]);
                    int res = (int) result;
                    if (res == 200) {
                        latlon[0] = new Double(coordinate[2]);
                        latlon[1] = new Double(coordinate[3]);
                    } else {
                        latlon = null;
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GestoreViaggiBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (latlon == null) {
            return null;
        }

        tappa.setLatitudine(latlon[0]);
        tappa.setLongitudine(latlon[1]);
        Indirizzo indi = reverseGeocoding(latlon[0], latlon[1]);
        tappa.setIndirizzo(indi);
        return tappa;

    }

    public Pacchetto inserisciPacchetto(List<Tappa> tappe, List<Calendar> date, Viaggiatore autista, String nota, boolean richiestaContributi, String distanza) throws IllegalStateException {

        //controllo dei parametri
        if (tappe.size() < 2) {
            throw new IllegalArgumentException("manca partenza o arrivo");
        }
        if (date.size() < 1) {
            throw new IllegalArgumentException("inserisci almeno una data");
        }
        if (autista == null) {
            throw new IllegalArgumentException("manca autista");
        }


        Pacchetto pacchetto = new Pacchetto();
        pacchetto.setPartenza(tappe.get(0));
        pacchetto.setArrivo(tappe.get(tappe.size() - 1)); //da ottimizzare
        pacchetto.setTappeIntermedie(tappe.subList(1, tappe.size() - 1));
        pacchetto.setInizio(date.get(0));
        pacchetto.setFine(date.get(date.size() - 1));
        pacchetto.setAutista(autista);
        pacchetto.setNota(nota);
        pacchetto.setRichiestaContributi(richiestaContributi);
        pacchetto.setBacheca(new Bacheca());
        pacchetto.setLunghezzaPercorso(Float.parseFloat(distanza));//TO-DO
        //va fatta per ultima
        pacchetto.creaViaggi(date);
        
        pacchettoFacade.create(pacchetto);

        return pacchettoFacade.findAll().get(0);


    }

    private Indirizzo reverseGeocoding(double lat, double lon) {
        //http://www.java-tips.org/java-se-tips/javax.xml.parsers/how-to-read-xml-file-in-java.html
        //http://code.google.com/intl/it-IT/apis/maps/documentation/geocoding/index.html#XML
        Indirizzo ris = new Indirizzo();
        URL url = null;
        try {
            url = new URL("http://maps.google.com/maps/api/geocode/xml?latlng=" + lat + "," + lon + "&sensor=false&language=it");

        } catch (MalformedURLException ex) {
            Logger.getLogger(GestoreViaggiBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            String buffer = "";
            while ((inputLine = in.readLine()) != null) {
                buffer = buffer + inputLine;
            }

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new ByteArrayInputStream(buffer.getBytes()));
            String value = "";
            String type = "";

            doc.getDocumentElement().normalize();

            NodeList nodeLst = doc.getElementsByTagName("address_component");


            for (int s = 0; s < nodeLst.getLength(); s++) {

                Node fstNode = nodeLst.item(s);

                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {


                    Element fstElmnt = (Element) fstNode;
                    NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("long_name");
                    Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
                    NodeList fstNm = fstNmElmnt.getChildNodes();
                    value = ((Node) fstNm.item(0)).getNodeValue();

                    fstElmnt = (Element) fstNode;
                    fstNmElmntLst = fstElmnt.getElementsByTagName("type");
                    fstNmElmnt = (Element) fstNmElmntLst.item(0);
                    fstNm = fstNmElmnt.getChildNodes();
                    type = ((Node) fstNm.item(0)).getNodeValue();

                    if (type.equals("street_number")) {
                        if (value!=null && !value.contains("-")) {
                            ris.setNumerocivico(value);
                        }
                    } else if (type.equals("route")) {
                        ris.setVia(value);
                    } else if (type.equals("locality")) {
                        ris.setCitta(value);
                    } else if (type.equals("country")) {
                        ris.setStato(value);
                    } else if (type.equals("postal_code")) {
                        ris.setCap(value);
                    }
                }

            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GestoreViaggiBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(GestoreViaggiBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestoreViaggiBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ris;
    }

    public RisultatiRicercaViaggi ricercaViaggi(String partenza, String arrivo, boolean intervallo, Date data1, Date data2, Date dataOraPartenza) {
        Calendar cdata1= new GregorianCalendar();
        Calendar cdata2= new GregorianCalendar();
        Calendar cdataOraPartenza= new GregorianCalendar();
        List<Pacchetto> pacchetti = null;
        if (intervallo) {
            cdata1.setTime(data1);
            cdata2.setTime(data2);
            pacchetti = pacchettoFacade.findDate(cdata1, cdata2);

        } else {
            cdataOraPartenza.setTime(dataOraPartenza);
            pacchetti = pacchettoFacade.findDataSingola(cdataOraPartenza);
        }



        //filtro la lista di pacchetti e tengo solo quelli per cui la partenza e l'arrivo sono in un intorno
        // se indicata controllo partenza
        if (partenza != null && !partenza.equals("")) {
            Tappa start = geocoding(partenza);
            Iterator it = pacchetti.iterator();
            while (it.hasNext()) {
                Pacchetto p = (Pacchetto) it.next();
                float lPercorso = p.getLunghezzaPercorso();
                double distMax = ((lPercorso * percentuale) / 100) / 1000;
                if (calcolaDistanze(start, p.getPartenza()) > distMax) {
                    it.remove();
                }
            }
        }
        // se indicato controllo l'arrivo
        if (arrivo!=null && !arrivo.equals("")) {
            Tappa start = geocoding(arrivo);
            Iterator it = pacchetti.iterator();
            while (it.hasNext()) {
                Pacchetto p = (Pacchetto) it.next();
                float lPercorso = p.getLunghezzaPercorso();
                double distMax = ((lPercorso * percentuale) / 100) / 1000;
                if (calcolaDistanze(start, p.getArrivo()) > distMax) {
                    it.remove();
                }
            }
        }
        // individuati i pacchetti per ognuno di essi lascio solo i viaggi di interesse
        for(Pacchetto p : pacchetti){
            Iterator it = p.getViaggi().iterator();
            while(it.hasNext()){
                Viaggio v = (Viaggio) it.next();
                if(intervallo){
                    if((v.getDataPartenza().compareTo(cdata1)<0) || (v.getDataPartenza().compareTo(cdata2)>0))
                        it.remove();
                }
                else{
                    Calendar c2 = (Calendar)cdataOraPartenza.clone();
                    c2.set(Calendar.HOUR_OF_DAY, 23);
                    c2.set(Calendar.MINUTE, 59);
                    if((v.getDataPartenza().compareTo(cdataOraPartenza)<0) || (v.getDataPartenza().compareTo(c2)>0))
                        it.remove();
                }
            }
        }

        //elimino i pacchetti senza viaggi
        Iterator it = pacchetti.iterator();
            while(it.hasNext()){
                Pacchetto v = (Pacchetto) it.next();
                if(v.getViaggi().size()==0)
                    it.remove();
            }

        System.out.println("-------- pacchetti trovati " + pacchetti);
        RisultatiRicercaViaggi bean = new RisultatiRicercaViaggi();
        bean.setPacchetti(pacchetti);
        return bean;

    }

    private double calcolaDistanze(Tappa t1, Tappa t2) {
        double R = 6371;
        double lat_alfa, lat_beta;
        double lon_alfa, lon_beta;
        double fi;
        double p, d;
        double latA = t1.getLatitudine();
        double latB = t2.getLatitudine();
        double lonA = t1.getLongitudine();
        double lonB = t2.getLongitudine();
        /* Converte i gradi in radianti */
        lat_alfa = Math.PI * latA / 180;
        lat_beta = Math.PI * latB / 180;
        lon_alfa = Math.PI * lonA / 180;
        lon_beta = Math.PI * lonB / 180;
        /* Calcola l'angolo compreso fi */
        fi = Math.abs(lon_alfa - lon_beta);
        /* Calcola il terzo lato del triangolo sferico */
        p = Math.acos(Math.sin(lat_beta) * Math.sin(lat_alfa) + Math.cos(lat_beta) * Math.cos(lat_alfa) * Math.cos(fi));
        /* Calcola la distanza sulla superficie
        terrestre R = ~6371 km */
        d = p * R;
        return (d);
    }

    public Pacchetto aggiornaPacchetto(Pacchetto pacchetto) {
        pacchettoFacade.edit(pacchetto);
        return pacchettoFacade.findAll().get(0);
    }

    public List<String> getCitta(String subCitta) {
        return indirizzoFacade.getCitta(subCitta);
    }
}
