/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viaggi;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**Classe che mima le funzionalità dell'entità Viaggio
 * Viene utilizzata come parametro del WEB Service per evitare le implicazioni derivate dalla costruzione di template XML
 * con riferimenti incrociati
 * @author berto
 */
public class Percorso implements Serializable {


    private long id;
    private Tappa partenza;
    private Tappa arrivo;
    private Tappa[] tappeIntermedie;
    private Calendar dataPartenza;
    private float lunghezzaPercorso;
    private int postiLiberi;

    public Tappa[] getTappeIntermedie() {
        return tappeIntermedie;
    }

    public void setTappeIntermedie(Tappa[] tappeIntermedie) {
        this.tappeIntermedie = tappeIntermedie;
    }

    public Tappa getArrivo() {
        return arrivo;
    }

    public void setArrivo(Tappa arrivo) {
        this.arrivo = arrivo;
    }

    public Calendar getDataPartenza() {
        return dataPartenza;
    }

    public void setDataPartenza(Calendar dataPartenza) {
        this.dataPartenza = dataPartenza;
    }

    public float getLunghezzaPercorso() {
        return lunghezzaPercorso;
    }

    public void setLunghezzaPercorso(float lunghezzaPercorso) {
        this.lunghezzaPercorso = lunghezzaPercorso;
    }

    public Tappa getPartenza() {
        return partenza;
    }

    public void setPartenza(Tappa partenza) {
        this.partenza = partenza;
    }

    public int getPostiLiberi() {
        return postiLiberi;
    }

    public void setPostiLiberi(int postiLiberi) {
        this.postiLiberi = postiLiberi;
    }

    public Percorso(){}

    /** Setta in una sola volta tutti i parametri della classe Percorso
     *
     * @param i
     * @param p
     * @param a
     * @param t
     * @param d
     * @param l
     * @param pl
     */
    public void setAll(long i,Tappa p,Tappa a,List<Tappa> t,Calendar d,float l,int pl){
        id=i;
        partenza=p;
        arrivo=a;
        tappeIntermedie=creaArray(t);
        dataPartenza=d;
        lunghezzaPercorso=l;
        postiLiberi=pl;
    }
    public long getId() {
        return id;
    }

    public void setId(long id){
        this.id=id;
    }

    

    @Override
    public String toString(){
        String s="";

        s=s+"partenza: "+partenza;
        int i=1;
        for(Tappa t: tappeIntermedie){
            s=s+"\ttappa "+(i++)+": "+t;
        }
        s=s+"\tarrivo: "+arrivo;
        s=s+"\ndata: "+dataPartenza;
        s=s+"\tlunghezza percorso : "+lunghezzaPercorso;
        s=s+"\tposti liberi: "+postiLiberi+"\n";

        return s;
    }
    private Tappa[] creaArray(List<Tappa> l) {
        Tappa[] ris=new Tappa[l.size()];
        int i=0;
        for(Tappa p:l){
            ris[i]=p;
            i++;
        }
        return ris;
    }

}
