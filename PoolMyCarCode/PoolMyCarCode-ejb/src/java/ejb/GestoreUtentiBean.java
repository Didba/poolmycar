/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import facades.ViaggiatoreFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import utenti.Autista;
import utenti.Viaggiatore;

/**
 *
 * @author berto
 */
@Stateless
public class GestoreUtentiBean implements GestoreUtentiLocal {
    @EJB
    private ViaggiatoreFacadeLocal viaggiatoreFacade;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
    public Viaggiatore doLogin(String user, String pwd){
        //return viaggiatoreFacade.findLogin(user, pwd);


        List<Viaggiatore> viaggiatori=null;

        viaggiatori=viaggiatoreFacade.findAll();

        for(Viaggiatore v: viaggiatori)
            if(v.getLogin().equals(user) && v.getPassword().equals(pwd))
                return v;


        return null;
    }

    public void registraUtente(String login, String password, boolean isAutista) {
        Viaggiatore viaggiatore=null;
        
        if(isAutista)
            viaggiatore=new Autista();
            
        else
            viaggiatore=new Viaggiatore();

        viaggiatore.setLogin(login);
        viaggiatore.setPassword(password);

        viaggiatoreFacade.create(viaggiatore);
    }
    
}
