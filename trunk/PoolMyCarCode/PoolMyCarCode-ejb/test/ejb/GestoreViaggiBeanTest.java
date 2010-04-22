/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utenti.CommentoAutista;
import utenti.CommentoViaggiatore;
import utenti.Indirizzo;
import utenti.Viaggiatore;
import viaggi.Bacheca;
import viaggi.Pacchetto;
import viaggi.Richiesta;
import viaggi.Tappa;
import viaggi.Viaggio;

/**
 *
 * @author berto
 */
public class GestoreViaggiBeanTest {

    public GestoreViaggiBeanTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of geocoding method, of class GestoreViaggiBean.
     */
    @Test
    public void testGeocoding() {
        System.out.println("geocoding");
        String indirizzo = "Torino, Corso Svizzera 185, Italy";
        GestoreViaggiBean instance = new GestoreViaggiBean();
        
        Tappa expResult = new Tappa();
        expResult.setLatitudine(45.0904249);
        expResult.setLongitudine(7.6607690);
        
        Tappa result = instance.geocoding(indirizzo);
        result.setIndirizzo(null);
        assertEquals(result, expResult);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inserisciPacchetto method, of class GestoreViaggiBean.
     */
    @Test
    public void testInserisciPacchetto() {
        System.out.println("inserisciPacchetto");
        Tappa t1=new Tappa();
        Indirizzo i1=new Indirizzo();
        i1.setCitta("Torino");
        t1.setIndirizzo(i1);
        Tappa t2=new Tappa();
        Indirizzo i2=new Indirizzo();
        i2.setCitta("Milano");
        t2.setIndirizzo(i1);
        List<Tappa> tappe = new LinkedList<Tappa>();
        tappe.add(t1);
        tappe.add(t2);
        List<Calendar> date = new LinkedList<Calendar>();
        Calendar c = new GregorianCalendar(2010, 04, 10);
        date.add(c);
        Viaggiatore autista = new Viaggiatore();
        autista.setLogin("autista");
        autista.setPassword("password");
        autista.setAutista(true);
        String nota = "Nota";
        boolean richiestaContributi = false;
        String distanza = "10000";
        GestoreViaggiBean instance = new GestoreViaggiBean();
        Pacchetto expResult = new Pacchetto();
        expResult.setPartenza(t1);
        expResult.setArrivo(t2);
        expResult.setTappeIntermedie(new LinkedList<Tappa>());
        expResult.setBacheca(new Bacheca());
        expResult.setAutista(autista);
        expResult.setCommentiAutista(new LinkedList<CommentoAutista>());
        expResult.setCommentiViaggiatori(new LinkedList<CommentoViaggiatore>());
        expResult.setFine(c);
        expResult.setInizio(c);
        expResult.setLunghezzaPercorso(10000);
        expResult.setNota(nota);
        expResult.setRichiestaContributi(false);
        expResult.setTappeIntermedie(new LinkedList<Tappa>());
        Viaggio v=new Viaggio();
        v.setPartenza(t1);
        v.setArrivo(t2);
        v.setPacchetto(expResult);
        v.setDataPartenza(c);
        v.setLunghezzaPercorso(10000);
        v.setViaggiatori(new LinkedList<Viaggiatore>());
        v.setRichieste(new LinkedList<Richiesta>());
        v.setTappeIntermedie(new LinkedList<Tappa>());
        List<Viaggio> viaggi= new LinkedList<Viaggio>();
        expResult.setViaggi(viaggi);
        Pacchetto result = instance.inserisciPacchetto(tappe, date, autista, nota, richiestaContributi, distanza);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ricercaViaggi method, of class GestoreViaggiBean.
     */
    @Test
    public void testRicercaViaggi() {
        System.out.println("ricercaViaggi");
        String partenza = "";
        String arrivo = "";
        boolean intervallo = false;
        Date data1 = null;
        Date data2 = null;
        Date dataOraPartenza = null;
        GestoreViaggiBean instance = new GestoreViaggiBean();
        RisultatiRicercaViaggi expResult = null;
        RisultatiRicercaViaggi result = instance.ricercaViaggi(partenza, arrivo, intervallo, data1, data2, dataOraPartenza);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of aggiornaPacchetto method, of class GestoreViaggiBean.
     */
    @Test
    public void testAggiornaPacchetto() {
        System.out.println("aggiornaPacchetto");
        Pacchetto pacchetto = null;
        GestoreViaggiBean instance = new GestoreViaggiBean();
        Pacchetto expResult = null;
        Pacchetto result = instance.aggiornaPacchetto(pacchetto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}