/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateful;
import viaggi.Pacchetto;

/**
 *
 * @author Erica
 */
@Stateful
public class RisultatiRicercaViaggi implements RisultatiRicercaViaggiLocal {
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private List<Pacchetto> pacchetti = null;
    protected int pacchettiVisti=0;

    /**
     * Get the value of pacchettiVisti
     *
     * @return the value of pacchettiVisti
     */
    public int getPacchettiVisti() {
        return pacchettiVisti;
    }

    /**
     * Set the value of pacchettiVisti
     *
     * @param pacchettiVisti new value of pacchettiVisti
     */
    public void setPacchettiVisti(int pacchettiVisti) {
        this.pacchettiVisti = pacchettiVisti;
    }


    /**
     * Get the value of pacchetti
     *
     * @return the value of pacchetti
     */
    public List<Pacchetto> getPacchetti() {
        return pacchetti;
    }

    /**
     * Set the value of pacchetti
     *
     * @param pacchetti new value of pacchetti
     */
    public void setPacchetti(List<Pacchetto> pacchetti) {
        this.pacchetti = pacchetti;
    }
//motodo per restituire i successivi n (al massimo n) pacchetti
    public List<Pacchetto> getNextPacchetti(int n){
        if((pacchetti == null)||(pacchetti.size()<=pacchettiVisti))
            return null;
        else{
            List<Pacchetto> l= new LinkedList<Pacchetto>();
            int i = pacchettiVisti;
            while(i<= pacchetti.size()  && (pacchettiVisti+n)> i){
                l.add(pacchetti.get(i));
                i++;
            }
            pacchettiVisti+=n;
            return l;
        }
    }

   
}
