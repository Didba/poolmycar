/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import viaggi.Post;

/**Oggetto che viene usato per scrivere un post della bacheca nel database
 * Questo oggetto contiene i metodi utili a scrivere un oggetto nel database. Viene usato per la persistenza di tale oggetto
 * @author berto
 */
@Stateless
public class PostFacade implements PostFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(Post post) {
        em.persist(post);
    }

    public void edit(Post post) {
        em.merge(post);
    }

    public void remove(Post post) {
        em.remove(em.merge(post));
    }

    public Post find(Object id) {
        return em.find(Post.class, id);
    }

    public List<Post> findAll() {
        return em.createQuery("select object(o) from Post as o").getResultList();
    }

}
