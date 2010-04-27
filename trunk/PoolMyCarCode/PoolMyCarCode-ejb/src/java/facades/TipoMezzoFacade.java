/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utenti.TipoMezzo;

/**Oggetto che viene usato per scrivere un tipo di mezzo nel database
 * Questo oggetto contiene i metodi utili a scrivere un oggetto nel database. Viene usato per la persistenza di tale oggetto
 * @author berto
 */
@Stateless
public class TipoMezzoFacade implements TipoMezzoFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(TipoMezzo tipoMezzo) {
        em.persist(tipoMezzo);
    }

    public void edit(TipoMezzo tipoMezzo) {
        em.merge(tipoMezzo);
    }

    public void remove(TipoMezzo tipoMezzo) {
        em.remove(em.merge(tipoMezzo));
    }

    public TipoMezzo find(Object id) {
        return em.find(TipoMezzo.class, id);
    }

    public TipoMezzo find(long id) {
        return em.find(TipoMezzo.class, id);
    }

    public List<TipoMezzo> findAll() {
        return em.createQuery("select object(o) from TipoMezzo as o").getResultList();
    }

}
