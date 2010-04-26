/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.Calendar;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utenti.Indirizzo;
import utenti.Viaggiatore;
import viaggi.Pacchetto;
import viaggi.Tappa;

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

    /**
     * Test of geocoding method, of class GestoreViaggiBean.
     */
    @Test
    public void testGeocoding() {
        System.out.println("geocoding");
        String indirizzo = "";
        GestoreViaggiBean instance = new GestoreViaggiBean();
        Tappa expResult = null;
        Tappa result = instance.geocoding(indirizzo);
        //assertEquals("", "");
        assertTrue(true);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inserisciPacchetto method, of class GestoreViaggiBean.
     */
    @Test
    public void testInserisciPacchetto() {
        System.out.println("inserisciPacchetto");
        List<Tappa> tappe = null;
        List<Calendar> date = null;
        Viaggiatore autista = null;
        String nota = "";
        boolean richiestaContributi = false;
        String distanza = "";
        GestoreViaggiBean instance = new GestoreViaggiBean();
        Pacchetto expResult = null;
        Pacchetto result = instance.inserisciPacchetto(tappe, date, autista, nota, richiestaContributi, distanza);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reverseGeocoding method, of class GestoreViaggiBean.
     */
    @Test
    public void testReverseGeocoding() {
        System.out.println("reverseGeocoding");
        double lat = 0.0;
        double lon = 0.0;
        GestoreViaggiBean instance = new GestoreViaggiBean();
        Indirizzo expResult = null;
        Indirizzo result = instance.reverseGeocoding(lat, lon);
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
        Calendar data1 = null;
        Calendar data2 = null;
        Calendar dataOraPartenza = null;
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

    /**
     * Test of getCitta method, of class GestoreViaggiBean.
     */
    @Test
    public void testGetCitta() {
        System.out.println("getCitta");
        String subCitta = "";
        GestoreViaggiBean instance = new GestoreViaggiBean();
        List expResult = null;
        List result = instance.getCitta(subCitta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}