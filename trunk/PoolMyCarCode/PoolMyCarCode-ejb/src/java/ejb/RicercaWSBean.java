/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
 * @author berto
 */
@Stateless
public class RicercaWSBean implements RicercaWSRemote {
    @EJB
    private GestoreViaggiBeanLocal gestoreViaggiBean;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
    public List<Percorso> ricerca(String partenza, String arrivo, String giorno, String mese, String anno) {

        Calendar data=new GregorianCalendar(new Integer(anno),getMese(mese),new Integer(giorno), 0 , 0);
        RisultatiRicercaViaggi risult= gestoreViaggiBean.ricercaViaggi(partenza, arrivo, false, null, null, data);
        List<Percorso> l=new LinkedList<Percorso>();

        for(Pacchetto p: risult.getPacchetti()){
            for(Viaggio v: p.getViaggi()){
                Percorso per=new Percorso();
                per.setAll(v.getId(),v.getPartenza(),v.getArrivo(),v.getTappeIntermedie(),v.getDataPartenza(),v.getLunghezzaPercorso(),(p.getTipoMezzo().getPosti()-v.getViaggiatori().size()));
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
                per.setAll(v.getId(),v.getPartenza(),v.getArrivo(),v.getTappeIntermedie(),v.getDataPartenza(),v.getLunghezzaPercorso(),(p.getTipoMezzo().getPosti()-v.getViaggiatori().size()));
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
                per.setAll(v.getId(),v.getPartenza(),v.getArrivo(),v.getTappeIntermedie(),v.getDataPartenza(),v.getLunghezzaPercorso(),(p.getTipoMezzo().getPosti()-v.getViaggiatori().size()));
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
                per.setAll(v.getId(),v.getPartenza(),v.getArrivo(),v.getTappeIntermedie(),v.getDataPartenza(),v.getLunghezzaPercorso(),(p.getTipoMezzo().getPosti()-v.getViaggiatori().size()));
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
 
}
