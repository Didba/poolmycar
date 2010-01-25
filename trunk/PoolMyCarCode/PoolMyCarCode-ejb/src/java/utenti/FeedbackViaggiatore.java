/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utenti;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Erica
 */
@Entity
public class FeedbackViaggiatore implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "feedbackViaggiatore")
    private List<CommentoViaggiatore> commenti;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<CommentoViaggiatore> getCommenti() {
        return commenti;
    }

    public void setCommenti(List<CommentoViaggiatore> commenti) {
        this.commenti = commenti;
    }

    public int getCordialitaCorrettezza() {
        return cordialitaCorrettezza;
    }

    public void setCordialitaCorrettezza(int cordialitaCorrettezza) {
        this.cordialitaCorrettezza = cordialitaCorrettezza;
    }

    public int getFlessibilita() {
        return flessibilita;
    }

    public void setFlessibilita(int flessibilita) {
        this.flessibilita = flessibilita;
    }

    public int getPuntualita() {
        return puntualita;
    }

    public void setPuntualita(int puntualita) {
        this.puntualita = puntualita;
    }
    private int puntualita;
    private int flessibilita;
    private int cordialitaCorrettezza;


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
        if (!(object instanceof FeedbackViaggiatore)) {
            return false;
        }
        FeedbackViaggiatore other = (FeedbackViaggiatore) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "utenti.FeedbackViaggiatore[id=" + id + "]";
    }

}
