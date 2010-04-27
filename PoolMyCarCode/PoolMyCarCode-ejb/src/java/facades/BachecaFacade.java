/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import viaggi.Bacheca;

/**Oggetto che viene usato per scrivere la bacheca nel database
 * Questo oggetto contiene i metodi utili a scrivere un oggetto nel database. Viene usato per la persistenza di tale oggetto
 * @author berto
 */
@Stateless
public class BachecaFacade implements BachecaFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(Bacheca bacheca) {
        em.persist(bacheca);
    }

    public void edit(Bacheca bacheca) {
        em.merge(bacheca);
    }

    public void remove(Bacheca bacheca) {
        em.remove(em.merge(bacheca));
    }

    public Bacheca find(Object id) {
        return em.find(Bacheca.class, id);
    }

    public List<Bacheca> findAll() {
        return em.createQuery("select object(o) from Bacheca as o").getResultList();
    }

}
