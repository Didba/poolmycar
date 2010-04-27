/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utenti.Viaggiatore;

/**
 *
 * @author berto
 */
public class GestoreUtentiBeanTest {

    public GestoreUtentiBeanTest() {
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
     * Test of doLogin method, of class GestoreUtentiBean.
     */
    @Test
    public void testDoLogin() {
        System.out.println("doLogin");
        GestoreUtentiBean instance = new GestoreUtentiBean();
        instance.registraUtente("utenteDiProva", "");
        String user = "utenteDiProva";
        String pwd = "";
        Viaggiatore expResult = new Viaggiatore();
        expResult.setLogin("a");
        expResult.setPassword(pwd);
        Viaggiatore result = instance.doLogin(user, pwd);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registraUtente method, of class GestoreUtentiBean.
     */
    @Test
    public void testRegistraUtente() {
        System.out.println("registraUtente");
        String login = "";
        String password = "";
        GestoreUtentiBean instance = new GestoreUtentiBean();
        boolean expResult = false;
        boolean result = instance.registraUtente(login, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of diventaAutista method, of class GestoreUtentiBean.
     */
    @Test
    public void testDiventaAutista() {
        System.out.println("diventaAutista");
        Viaggiatore viaggiatore = null;
        String patente = "";
        String tipoMezzo = "";
        GestoreUtentiBean instance = new GestoreUtentiBean();
        boolean expResult = false;
        boolean result = instance.diventaAutista(viaggiatore, patente, tipoMezzo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}