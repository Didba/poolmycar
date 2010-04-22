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
    protected int numGruppoPacchetti;
    private int numUltimoGruppo=0;

    /**
     * Get the value of numGruppoPacchetti
     *
     * @return the value of numGruppoPacchetti
     */
    public int getNumGruppoPacchetti() {
        return numGruppoPacchetti;
    }

    /**
     * Set the value of numGruppoPacchetti
     *
     * @param numGruppoPacchetti new value of numGruppoPacchetti
     */
    public void setNumGruppoPacchetti(int numGruppoPacchetti) {
        this.numGruppoPacchetti = numGruppoPacchetti;
    }

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
//motodo per restituire i successivi numGruppoPacchetti (al massimo numGruppoPacchetti) pacchetti
    public List<Pacchetto> getNextPacchetti(){

        if((pacchetti == null)||(pacchetti.size()<=pacchettiVisti))
            return null;
        else{
            List<Pacchetto> l= new LinkedList<Pacchetto>();
            int n = numGruppoPacchetti;
            if(pacchettiVisti+numGruppoPacchetti>= pacchetti.size())
                n = pacchetti.size()-pacchettiVisti-1;

            for(int i = 0 ; i<n;i++)
                l.add(pacchetti.get(pacchettiVisti+i));
            
            pacchettiVisti+=n;
            numUltimoGruppo=n;
            return l;
        }
    }
//motodo per restituire i precedenti numGruppoPacchetti  pacchetti
    public List<Pacchetto> getPredPacchetti(){
        
        if((pacchetti == null)||(pacchettiVisti-numGruppoPacchetti<=0))
            return null;
        else{
            List<Pacchetto> l= new LinkedList<Pacchetto>();
            int n = numGruppoPacchetti;
           for(int i = 0;i<n;i++){
                l.add(pacchetti.get(pacchettiVisti-numUltimoGruppo-1-i));
           }
            pacchettiVisti-=numUltimoGruppo;
            numUltimoGruppo=numGruppoPacchetti;
            return l;
        }
    }

    public boolean avanti(){
        return pacchettiVisti<pacchetti.size();
    }
    public boolean indietro(){
        return pacchettiVisti>numGruppoPacchetti;
    }

   
}
