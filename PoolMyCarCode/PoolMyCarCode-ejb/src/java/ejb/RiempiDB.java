/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import facades.PacchettoFacadeLocal;
import facades.ViaggiatoreFacadeLocal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import utenti.Indirizzo;
import utenti.TipoMezzo;
import utenti.Viaggiatore;
import viaggi.Bacheca;
import viaggi.Pacchetto;
import viaggi.Tappa;

/**
 *
 * @author Erica
 */
@Stateless
public class RiempiDB implements RiempiDBLocal {
    @EJB
    private ViaggiatoreFacadeLocal viaggiatoreFacade;
    @EJB
    private GestoreViaggiBeanLocal gestoreViaggiBean;
    @EJB
    private PacchettoFacadeLocal pacchettoFacade;


    public void riempi() throws ParseException {
        Viaggiatore autista = new Viaggiatore();
        TipoMezzo tp = new TipoMezzo();
        tp.setNome("auto");
        Set<TipoMezzo> tipiMezzo = new HashSet<TipoMezzo>();
        tipiMezzo.add(tp);
        autista.setMezzi(tipiMezzo);
        autista.setAutista(true);
        autista.setNumeroPatente("isukhflsh");
        autista.setNome("Ciccio");
        autista.setCognome("Pinco");
        autista.setCf("hcdisuahf");
        autista.setAutista(true);
        Indirizzo ind = new Indirizzo();
        ind.setCap("109");
        ind.setCitta("collegno");
        autista.setIndirizzo(ind);
        autista.setTelefono("1234345");
        autista.setNote("nota");
        autista.setFumatore(false);
        autista.setLogin("eri");
        autista.setPassword("eri");

        viaggiatoreFacade.create(autista);

        Pacchetto p;
        List<String> d;

        d = new LinkedList<String>();
        d.add("12/04/2010 15:00");
        d.add("14/04/2010 15:00");
        d.add("16/04/2010 15:00");
        d.add("19/04/2010 15:00");
        p = creap("Torino", "Milano", autista, d);
        pacchettoFacade.create(p);

        d = new LinkedList<String>();
        d.add("12/04/2010 15:00");
        d.add("14/04/2010 15:00");
        d.add("16/04/2010 15:00");
        d.add("19/04/2010 15:00");
        p = creap("Torino", "Bari", autista, d);
        pacchettoFacade.create(p);

        d = new LinkedList<String>();
        d.add("12/04/2010 15:00");
        d.add("14/04/2010 15:00");
        d.add("16/04/2010 15:00");
        d.add("19/04/2010 15:00");
        p = creap("Torino", "Manduria", autista, d);
        pacchettoFacade.create(p);

        d = new LinkedList<String>();
        d.add("12/04/2010 15:00");
        d.add("14/04/2010 15:00");
        d.add("16/04/2010 15:00");
        d.add("19/04/2010 15:00");
        p = creap("Torino", "Ancona", autista, d);
        pacchettoFacade.create(p);

        d = new LinkedList<String>();
        d.add("12/04/2010 15:00");
        d.add("14/04/2010 15:00");
        d.add("16/04/2010 15:00");
        d.add("19/04/2010 15:00");
        p = creap("Torino", "Lecce", autista, d);
        pacchettoFacade.create(p);

        d = new LinkedList<String>();
        d.add("12/04/2010 15:00");
        d.add("14/04/2010 15:00");
        d.add("16/04/2010 15:00");
        d.add("19/04/2010 15:00");
        p = creap("Torino", "Cuneo", autista, d);
        pacchettoFacade.create(p);







    }

    private Pacchetto creap(String p, String a, Viaggiatore aut, List<String> d) throws ParseException {
        Pacchetto pacchetto = new Pacchetto();
        Tappa part = gestoreViaggiBean.geocoding(p);
        Tappa arr = gestoreViaggiBean.geocoding(a);
        pacchetto.setPartenza(part);
        pacchetto.setArrivo(arr); //da ottimizzare
        List<Calendar> date = new LinkedList<Calendar>();
        for (String s : d) {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date data1 = df.parse(s.trim());
            Calendar c = new GregorianCalendar();
            c.setTime(data1);
            date.add(c);
        }

        pacchetto.setInizio(date.get(0));
        pacchetto.setFine(date.get(date.size() - 1));
        pacchetto.setAutista(aut);
        pacchetto.setBacheca(new Bacheca());
        pacchetto.setTappeIntermedie(new LinkedList<Tappa>());
        pacchetto.setNota("nota");
        pacchetto.setRichiestaContributi(true);
        pacchetto.setBacheca(new Bacheca());
        pacchetto.setLunghezzaPercorso(100);//TO-DO
        //va fatta per ultima
        pacchetto.creaViaggi(date);
        return pacchetto;
    }
}
