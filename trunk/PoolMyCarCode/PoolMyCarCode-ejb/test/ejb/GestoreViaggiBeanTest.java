/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utenti.Indirizzo;
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
        System.out.print("geocoding - ");
        String indirizzo = "Torino";
        GestoreViaggiBean instance = new GestoreViaggiBean();
        Tappa expResult = new Tappa();
        expResult.setLatitudine(45.0705621);
        expResult.setLongitudine(7.6866186);
        Tappa result = instance.geocoding(indirizzo);
        assertEquals(expResult.getLatitudine(),result.getLatitudine(),0.3);
        assertEquals(expResult.getLongitudine(),result.getLongitudine(),0.3);
        System.out.println("passato");
    }


    /**
     * Test of reverseGeocoding method, of class GestoreViaggiBean.
     */
    @Test
    public void testReverseGeocoding() {
        System.out.print("reverseGeocoding - ");
        double lat = 45.0705621;
        double lon = 7.6866186;
        GestoreViaggiBean instance = new GestoreViaggiBean();
        Indirizzo result = instance.reverseGeocoding(lat, lon);
        assertEquals(result.getCitta(), "Torino");
        System.out.print("passato");
    }

}