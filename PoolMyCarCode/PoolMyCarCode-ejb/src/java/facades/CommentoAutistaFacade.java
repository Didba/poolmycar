/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utenti.CommentoAutista;

/**Oggetto che viene usato per scrivere i commenti dell'autista nel database
 * Questo oggetto contiene i metodi utili a scrivere un oggetto nel database. Viene usato per la persistenza di tale oggetto
 * @author berto
 */
@Stateless
public class CommentoAutistaFacade implements CommentoAutistaFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(CommentoAutista commentoAutista) {
        em.persist(commentoAutista);
    }

    public void edit(CommentoAutista commentoAutista) {
        em.merge(commentoAutista);
    }

    public void remove(CommentoAutista commentoAutista) {
        em.remove(em.merge(commentoAutista));
    }

    public CommentoAutista find(Object id) {
        return em.find(CommentoAutista.class, id);
    }

    public List<CommentoAutista> findAll() {
        return em.createQuery("select object(o) from CommentoAutista as o").getResultList();
    }

}
