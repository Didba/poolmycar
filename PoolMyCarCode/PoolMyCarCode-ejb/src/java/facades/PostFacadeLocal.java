/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Local;
import viaggi.Post;

/**
 *
 * @author berto
 */
@Local
public interface PostFacadeLocal {

    void create(Post post);

    void edit(Post post);

    void remove(Post post);

    Post find(Object id);

    List<Post> findAll();

}
