/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Local;
import utenti.Viaggiatore;

/**
 *
 * @author berto
 */
@Local
public interface GestoreUtentiLocal {

    public Viaggiatore doLogin(String login, String password);

    public void registraUtente(String login, String password, boolean autista);
}
