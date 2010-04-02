/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Stateless;

/**
 *
 * @author berto
 */
@Stateless
public class GestoreUtentiBean implements GestoreUtentiLocal {
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
    //TO-DO:
    public boolean doLogin(String user, String pwd){
        return true;
    }

    //TO-DO: tira su oggetto Autista
    
}
