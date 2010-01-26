/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Local;
import utenti.FeedbackViaggiatore;

/**
 *
 * @author berto
 */
@Local
public interface FeedbackViaggiatoreFacadeLocal {

    void create(FeedbackViaggiatore feedbackViaggiatore);

    void edit(FeedbackViaggiatore feedbackViaggiatore);

    void remove(FeedbackViaggiatore feedbackViaggiatore);

    FeedbackViaggiatore find(Object id);

    List<FeedbackViaggiatore> findAll();

}
