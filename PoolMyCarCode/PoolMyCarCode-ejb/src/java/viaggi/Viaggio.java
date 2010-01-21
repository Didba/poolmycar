/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viaggi;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import utenti.Viaggiatore;

/**
 *
 * @author Erica
 */
@Entity
public class Viaggio implements Serializable {
    @ManyToOne
    private Pacchetto pacchetto;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataPartenza;
    private float lunghezzaPercorso;
    @OneToMany
    private List<Tappa> tappe;
    @ManyToMany
    private List<Viaggiatore> viaggiatori;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany
    private List<Richiesta> richieste;

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

    @Override
    public String toString() {
        return "viaggi.Viaggio[id=" + id + "]";
    }

}
