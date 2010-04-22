/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utenti;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import viaggi.Pacchetto;

/**
 *
 * @author Erica
 */
@Entity
public class Autista extends Viaggiatore{
    private static final long serialVersionUID = 1L;
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String numeroPatente;
    @OneToMany(cascade=CascadeType.ALL)
    private Set<TipoMezzo> tipoMezzo;
    @OneToOne
    private FeedbackAutista feedback;
    @OneToMany(mappedBy = "autista")
    private List<Pacchetto> pacchetti;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<Pacchetto> getPacchetti() {
        return pacchetti;
    }

    public void setPacchetti(List<Pacchetto> pacchetti) {
        this.pacchetti = pacchetti;
    }



    /**
     * Get the value of feedback
     *
     * @return the value of feedback
     */
    public FeedbackAutista getFeedback() {
        return feedback;
    }

    /**
     * Set the value of feedback
     *
     * @param feedback new value of feedback
     */
    public void setFeedback(FeedbackAutista feedback) {
        this.feedback = feedback;
    }


    public String getNumeroPatente() {
        return numeroPatente;
    }

    public void setNumeroPatente(String numeroPatente) {
        this.numeroPatente = numeroPatente;
    }

    public Set<TipoMezzo> getTipoMezzo() {
        return tipoMezzo;
    }

    public void setTipoMezzo(Set<TipoMezzo> tipoMezzo) {
        this.tipoMezzo = tipoMezzo;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
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
        if (!(object instanceof Autista)) {
            return false;
        }
        Autista other = (Autista) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "utenti.Autista[id=" + id + "]";
    }

}
