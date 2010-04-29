/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viaggi;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import utenti.Viaggiatore;

/**Classe che rappresenta un viaggio
 * Questa classe definisce i metodi e le variabili relative ad un Viaggio
 * @author Fra
 */
@Entity
public class Viaggio implements Serializable {
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar dataPartenza;
    private float lunghezzaPercorso;
    @OneToMany(cascade=CascadeType.ALL)
    private List<Tappa> tappeIntermedie;
    @ManyToMany(cascade={CascadeType.MERGE,CascadeType.REFRESH})
    private List<Viaggiatore> viaggiatori;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany(cascade=CascadeType.ALL)
    private List<Richiesta> richieste;
    @OneToOne
    private Tappa partenza;
    @OneToOne
    private Tappa arrivo;
    boolean modificato;  //modificato rispetto al pacchetto: true nel momento in cui si aggiungono tappe a questo viaggio

    public boolean isModificato() {
        return modificato;
    }

    public void setModificato(boolean modificato) {
        this.modificato = modificato;
    }
    /**
     * Get the value of partenza
     *
     * @return the value of partenza
     */
    public Tappa getPartenza() {
        return partenza;
    }

    /**
     * Set the value of partenza
     *
     * @param partenza new value of partenza
     */
    public void setPartenza(Tappa partenza) {
        this.partenza = partenza;
    }


    /**
     * Get the value of arrivo
     *
     * @return the value of arrivo
     */
    public Tappa getArrivo() {
        return arrivo;
    }

    /**
     * Set the value of arrivo
     *
     * @param arrivo new value of arrivo
     */
    public void setArrivo(Tappa arrivo) {
        this.arrivo = arrivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Viaggio)) {
            return false;
        }
        Viaggio other = (Viaggio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
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

    public List<Richiesta> getRichieste() {
        return richieste;
    }

    public void setRichieste(List<Richiesta> richieste) {
        this.richieste = richieste;
    }

    public List<Tappa> getTappeIntermedie() {
        return tappeIntermedie;
    }

    public void setTappeIntermedie(List<Tappa> tappeIntermedie) {
        this.tappeIntermedie = tappeIntermedie;
    }

    public List<Viaggiatore> getViaggiatori() {
        return viaggiatori;
    }

    public void setViaggiatori(List<Viaggiatore> viaggiatori) {
        this.viaggiatori = viaggiatori;
    }

    @Override
    public String toString() {
        return "viaggi.Viaggio[id=" + id + "]";
    }

}
