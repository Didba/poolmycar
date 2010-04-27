/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Local;
import utenti.FeedbackAutista;

/** Interfaccia di FeedbackAutistaFacade
 * Interfaccia ad uso locale dell'oggetto che rende persistente l'oggetto FeedbackAutista
 * @author berto
 */
@Local
public interface FeedbackAutistaFacadeLocal {

    void create(FeedbackAutista feedbackAutista);

    void edit(FeedbackAutista feedbackAutista);

    void remove(FeedbackAutista feedbackAutista);

    FeedbackAutista find(Object id);

    List<FeedbackAutista> findAll();

}
