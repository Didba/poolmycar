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

/**Il feedback di un autista
 * Oggetto che rappresenta il feedback di un autista.
 * Esso è calcolato sulla base di tutti i commenti dati dai viaggiatori che hanno partecipato a viaggi indetti dall'autista
 * Contiene quindi un puntatore alla lista di commenti e quattro punteggi calcolati.
 * @author Erica
 */
@Entity
public class FeedbackAutista implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "feedbackAutista")
    private List<CommentoAutista> commenti;
    private int puntualita;
    private int flessibilita;
    private int cordialitaCorrettezza;
    private int comfortDiGuida;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public int getComfortDiGuida() {
        return comfortDiGuida;
    }

    public void setComfortDiGuida(int comfortDiGuida) {
        this.comfortDiGuida = comfortDiGuida;
    }

    public List<CommentoAutista> getCommenti() {
        return commenti;
    }

    public void setCommenti(List<CommentoAutista> commenti) {
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
        if (!(object instanceof FeedbackAutista)) {
            return false;
        }
        FeedbackAutista other = (FeedbackAutista) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "utenti.Feedback[id=" + id + "]";
    }

}
