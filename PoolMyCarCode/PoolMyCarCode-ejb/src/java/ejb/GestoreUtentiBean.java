/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;


import facades.ViaggiatoreFacadeLocal;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import utenti.TipoMezzo;
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
        Viaggiatore v =viaggiatoreFacade.findLogin(user);
        if(v.getPassword().equals(pwd))
            return v;
        else
           return null;
    }

    public boolean registraUtente(String login, String password) {
      
        Viaggiatore viaggiatore=new Viaggiatore();
        viaggiatore.setLogin(login);
        viaggiatore.setPassword(password);
        Viaggiatore v = viaggiatoreFacade.findLogin(login);
        if(v!=null)
            return false;
        else{
            viaggiatoreFacade.create(viaggiatore);
            return true;
        }
    }

    public boolean diventaAutista(Viaggiatore autista, String patente, String tipoMezzo) {

        autista.setAutista(true);
        TipoMezzo tp=new TipoMezzo();
        tp.setPosti(new Integer(tipoMezzo));
        tp.setNome("Mezzo a "+tipoMezzo+" posti");
        Set<TipoMezzo> tipiMezzo=new HashSet<TipoMezzo>();
        tipiMezzo.add(tp);
        autista.setMezzi(tipiMezzo);
        autista.setNumeroPatente(patente);
        viaggiatoreFacade.edit(autista);

        return true;
    }
}
