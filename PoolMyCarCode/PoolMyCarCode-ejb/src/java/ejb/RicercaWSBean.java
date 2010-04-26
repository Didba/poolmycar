/*
Per questa classe si ha bisogno del package jdom che si può scaricare da internet (jdom.jar). Ovviamente la ricerca è da ottimizzare, io per adesso ho usato la findAll(). Bella albi! Guarda se funge. Crea un nuovo bean stateless Ricerca WS e copia il codice
*/

package ejb;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import viaggi.Pacchetto;
import viaggi.Viaggio;

/**
*
* @author Francesco
*/
@Stateless
public class RicercaWSBean implements RicercaWSLocal {
    @EJB
    private GestoreViaggiBeanLocal gestoreViaggiBean;


    public List<Percorso> ricerca(String partenza, String arrivo, String giorno, String mese, String anno) {

        Calendar data=new GregorianCalendar(new Integer(anno),getMese(mese),new Integer(giorno), 0 , 0);
        RisultatiRicercaViaggi risult= gestoreViaggiBean.ricercaViaggi(partenza, arrivo, false, null, null, data);
        List<Percorso> l=new LinkedList<Percorso>();

        for(Pacchetto p: risult.getPacchetti()){
            for(Viaggio v: p.getViaggi()){
                Percorso per=new Percorso();
                per.setAll(v.getId(),v.getPartenza(),v.getArrivo(),v.getTappeIntermedie(),v.getDataPartenza(),v.getLunghezzaPercorso(),0);
                l.add(per);
            }
        }

        return l;
    }

    public List<Percorso> ricerca(String partenza, String arrivo, String giorno1, String mese1, String anno1, String giorno2, String mese2, String anno2) {

        Calendar data1=new GregorianCalendar(new Integer(anno1),getMese(mese1),new Integer(giorno1), 0, 0);
        Calendar data2=new GregorianCalendar(new Integer(anno2),getMese(mese2),new Integer(giorno2), 0, 0);
        RisultatiRicercaViaggi risult= gestoreViaggiBean.ricercaViaggi(partenza, arrivo, true, data1, data2, null);

        List<Percorso> l=new LinkedList<Percorso>();

        for(Pacchetto p: risult.getPacchetti()){
            for(Viaggio v: p.getViaggi()){
                Percorso per=new Percorso();
                per.setAll(v.getId(),v.getPartenza(),v.getArrivo(),v.getTappeIntermedie(),v.getDataPartenza(),v.getLunghezzaPercorso(),0);
                l.add(per);
            }
        }

        return l;
    }

    public List<Percorso> ricerca(String partenza, String arrivo, Calendar data) {

        RisultatiRicercaViaggi risult= gestoreViaggiBean.ricercaViaggi(partenza, arrivo, false, null, null, data);

        List<Percorso> l=new LinkedList<Percorso>();

        for(Pacchetto p: risult.getPacchetti()){
            for(Viaggio v: p.getViaggi()){
                Percorso per=new Percorso();
                per.setAll(v.getId(),v.getPartenza(),v.getArrivo(),v.getTappeIntermedie(),v.getDataPartenza(),v.getLunghezzaPercorso(),0);
                l.add(per);
            }
        }

        return l;
    }

    public List<Percorso> ricerca(String partenza, String arrivo, Calendar data1, Calendar data2) {

        RisultatiRicercaViaggi risult= gestoreViaggiBean.ricercaViaggi(partenza, arrivo, true, data1, data2, null);

        List<Percorso> l=new LinkedList<Percorso>();

        for(Pacchetto p: risult.getPacchetti()){
            for(Viaggio v: p.getViaggi()){
                Percorso per=new Percorso();
                per.setAll(v.getId(),v.getPartenza(),v.getArrivo(),v.getTappeIntermedie(),v.getDataPartenza(),v.getLunghezzaPercorso(),0);
                l.add(per);
            }
        }

        return l;
    }
    

    









    private int getMese(String s) {
    if (s.toUpperCase().equals("JANUARY") || s.toUpperCase().equals("GENNAIO") || s.equals("01")) {
    return Calendar.JANUARY;
    }
    if (s.toUpperCase().equals("FEBRUARY") || s.toUpperCase().equals("FEBBRAIO") || s.equals("02")) {
    return Calendar.FEBRUARY;
    }
    if (s.toUpperCase().equals("MARCH") || s.toUpperCase().equals("MARZO") || s.equals("03")) {
    return Calendar.MARCH;
    }
    if (s.toUpperCase().equals("APRIL") || s.toUpperCase().equals("APRILE") || s.equals("04")) {
    return Calendar.APRIL;
    }
    if (s.toUpperCase().equals("MAY") || s.toUpperCase().equals("MAGGIO") || s.equals("05")) {
    return Calendar.MAY;
    }
    if (s.toUpperCase().equals("JUNE") || s.toUpperCase().equals("GIUGNO") || s.equals("06")) {
    return Calendar.JUNE;
    }
    if (s.toUpperCase().equals("JULY") || s.toUpperCase().equals("LUGLIO") || s.equals("07")) {
    return Calendar.JULY;
    }
    if (s.toUpperCase().equals("AUGUST") || s.toUpperCase().equals("AGOSTO") || s.equals("08")) {
    return Calendar.AUGUST;
    }
    if (s.toUpperCase().equals("SEPTEMBER") || s.toUpperCase().equals("SETTEMBRE") || s.equals("09")) {
    return Calendar.SEPTEMBER;
    }
    if (s.toUpperCase().equals("OCTOBER") || s.toUpperCase().equals("OTTOBRE") || s.equals("10")) {
    return Calendar.OCTOBER;
    }
    if (s.toUpperCase().equals("NOVEMBER") || s.toUpperCase().equals("NOVEMBRE") || s.equals("11")) {
    return Calendar.NOVEMBER;
    }
    if (s.toUpperCase().equals("DECEMBER") || s.toUpperCase().equals("DICEMBRE") || s.equals("12")) {
    return Calendar.DECEMBER;
    }
    return -1;
    }

            /*
        List<Viaggio> tuttiViaggi=viaggioFacade.findAll();
        List<Viaggio> mieiViaggi=new LinkedList<Viaggio>();
        for(Viaggio v: tuttiViaggi){
            int day=v.getDataPartenza().get(Calendar.DAY_OF_MONTH);
            int month=v.getDataPartenza().get(Calendar.MONTH);
            int year=v.getDataPartenza().get(Calendar.YEAR);
            if(year==new Integer(anno) && month==getMese(mese) && day==new Integer(giorno))
            mieiViaggi.add(v);
        }
        //richiama ricerca
        Element root = new Element("Lista Viaggi");
        root.setAttribute("Giorno",giorno+" "+mese+" "+anno);
        Document doc = new Document(root);
        for(Viaggio v: mieiViaggi){
        Element viaggio = new Element("Viaggio");
        viaggio.setAttribute("ID",v.getId().toString());
        root.addContent(viaggio);

        Element p = new Element("Partenza");

        Element via = new Element("Via");
        Element numeroCivico = new Element("Numero Civico");
        Element citta = new Element("Città");
        Element provincia = new Element("Provincia");
        Element cap = new Element("CAP");
        Element stato = new Element("Stato");

        via.setText(v.getPartenza().getIndirizzo().getVia());
        numeroCivico.setText(v.getPartenza().getIndirizzo().getNumerocivico());
        citta.setText(v.getPartenza().getIndirizzo().getCitta());
        provincia.setText(v.getPartenza().getIndirizzo().getProvincia());
        cap.setText(v.getPartenza().getIndirizzo().getCap());
        stato.setText(v.getPartenza().getIndirizzo().getStato());

        p.addContent(via);p.addContent(numeroCivico);p.addContent(citta);
        p.addContent(provincia);p.addContent(cap);p.addContent(stato);
        viaggio.addContent(p);

        for(Tappa t:v.getTappeIntermedie()){
            Element e = new Element("Tappa Intermedia");

            via = new Element("Via");
            numeroCivico = new Element("Numero Civico");
            citta = new Element("Città");
            provincia = new Element("Provincia");
            cap = new Element("CAP");
            stato = new Element("Stato");

            via.setText(t.getIndirizzo().getVia());
            numeroCivico.setText(t.getIndirizzo().getNumerocivico());
            citta.setText(t.getIndirizzo().getCitta());
            provincia.setText(t.getIndirizzo().getProvincia());
            cap.setText(t.getIndirizzo().getCap());
            stato.setText(t.getIndirizzo().getStato());

            e.addContent(via);e.addContent(numeroCivico);e.addContent(citta);
            e.addContent(provincia);e.addContent(cap);e.addContent(stato);
            viaggio.addContent(e);
        }

        Element a = new Element("Arrivo");

        via = new Element("Via");
        numeroCivico = new Element("Numero Civico");
        citta = new Element("Città");
        provincia = new Element("Provincia");
        cap = new Element("CAP");
        stato = new Element("Stato");

        via.setText(v.getArrivo().getIndirizzo().getVia());
        numeroCivico.setText(v.getArrivo().getIndirizzo().getNumerocivico());
        citta.setText(v.getArrivo().getIndirizzo().getCitta());
        provincia.setText(v.getArrivo().getIndirizzo().getProvincia());
        cap.setText(v.getArrivo().getIndirizzo().getCap());
        stato.setText(v.getArrivo().getIndirizzo().getStato());

        a.addContent(via);a.addContent(numeroCivico);a.addContent(citta);
        a.addContent(provincia);a.addContent(cap);a.addContent(stato);
        viaggio.addContent(a);
        }

        return doc;*/
}