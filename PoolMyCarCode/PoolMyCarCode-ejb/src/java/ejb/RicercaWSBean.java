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

/**Definisce le funzionalità di ricerca utilizzabili da applicazioni terze
 * Rende disponibile la ricerca di viaggi come logica di business utilizzabile tramite invocazioni al nostro WEB Service
 * @author berto
 */
@Stateless
public class RicercaWSBean implements RicercaWSRemote {
    @EJB
    private GestoreViaggiBeanLocal gestoreViaggiBean;
    
    /**Utilizza la logica di business interna per ricercare un viaggio
     * Resituisce la lista di viaggi che partono il giorno selezionato. E' possibile restringere ulteriormente il campo di ricerca
     * specificando ulteriormente la partenza e l'arrivo
     * @param partenza la stringa che rappresenta un indirizzo di partenza, può essere nullo
     * @param arrivo la stringa che rappresenta un indirizzo di arrivo, può essere nullo
     * @param giorno una stringa che indica il giorno di partenza. E' necessario specificarlo sottoforma di numero del mese (a doppia cifra)
     * @param mese una stringa che indica il mese di partenza. E' necessario specificarlo sottoforma di numero (a doppia cifra) oppure nome
     * completo in inglese o italiano
     * @param anno l'anno di partenza del viaggio
     * @return una lista di oggetti Percorso che incapsulano le informazioni essenziali riguardo ai viaggi trovati corrispondenti alla ricerca
     */
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
    /**Utilizza la logica di business interna per ricercare un viaggio
     * Resituisce la lista di viaggi che partono il giorno compreso tra i due intervalli forniti. E' possibile restringere ulteriormente il campo di ricerca
     * specificando ulteriormente la partenza e l'arrivo
     * @param partenza la stringa che rappresenta un indirizzo di partenza, può essere nullo
     * @param arrivo la stringa che rappresenta un indirizzo di arrivo, può essere nullo
     * @param giorno1 una stringa che indica l'estremo inferiore del giorno di partenza. E' necessario specificarlo sottoforma di numero del mese (a doppia cifra)
     * @param mese1 una stringa che indica l'estremo inferiore del mese di partenza. E' necessario specificarlo sottoforma di numero (a doppia cifra) oppure nome
     * completo in inglese o italiano
     * @param anno1 l'anno estremo inferiore di partenza del viaggio
     * @param giorno2 una stringa che indica l'estremo superiore del giorno di partenza. E' necessario specificarlo sottoforma di numero del mese (a doppia cifra)
     * @param mese1 una stringa che indica l'estremo superiore del mese di partenza. E' necessario specificarlo sottoforma di numero (a doppia cifra) oppure nome
     * completo in inglese o italiano
     * @param anno1 l'anno estremo superiore di partenza del viaggio
     * @return una lista di oggetti Percorso che incapsulano le informazioni essenziali riguardo ai viaggi trovati corrispondenti alla ricerca
     */
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
    /**Utilizza la logica di business interna per ricercare un viaggio
     * Resituisce la lista di viaggi che partono il giorno selezionato. E' possibile restringere ulteriormente il campo di ricerca
     * specificando ulteriormente la partenza e l'arrivo
     * @param partenza la stringa che rappresenta un indirizzo di partenza, può essere nullo
     * @param arrivo la stringa che rappresenta un indirizzo di arrivo, può essere nullo
     * @param data un oggetto che indica il giorno di partenza
     * @return una lista di oggetti Percorso che incapsulano le informazioni essenziali riguardo ai viaggi trovati corrispondenti alla ricerca
     */
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

    /**Utilizza la logica di business interna per ricercare un viaggio
     * Resituisce la lista di viaggi che partono il giorno compreso tra i due intervalli forniti. E' possibile restringere ulteriormente il campo di ricerca
     * specificando ulteriormente la partenza e l'arrivo
     * @param partenza la stringa che rappresenta un indirizzo di partenza, può essere nullo
     * @param arrivo la stringa che rappresenta un indirizzo di arrivo, può essere nullo
     * @param data1 rappresenta l'estremo inferiore dell'intervallo di date
     * @param data1 rappresenta l'estremo superiore dell'intervallo di date
     * @return una lista di oggetti Percorso che incapsulano le informazioni essenziali riguardo ai viaggi trovati corrispondenti alla ricerca
     */
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
