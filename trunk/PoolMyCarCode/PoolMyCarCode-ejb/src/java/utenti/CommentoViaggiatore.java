/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utenti;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import viaggi.Pacchetto;

/**
 *
 * @author Erica
 */
@Entity
public class CommentoViaggiatore implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nota;
    private int puntualita;
    private int flessibilita;
    private int cordialitaCorrettezza;
    @ManyToOne
    private Pacchetto pacchetto;
    @ManyToOne
    private FeedbackViaggiatore feedbackViaggiatore;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataCreazione;



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

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public int getPuntualita() {
        return puntualita;
    }

    public void setPuntualita(int puntualita) {
        this.puntualita = puntualita;
    }

    public Calendar getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(Calendar dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public FeedbackViaggiatore getFeedbackViaggiatore() {
        return feedbackViaggiatore;
    }

    public void setFeedbackViaggiatore(FeedbackViaggiatore feedbackViaggiatore) {
        this.feedbackViaggiatore = feedbackViaggiatore;
    }

    public Pacchetto getPacchetto() {
        return pacchetto;
    }

    public void setPacchetto(Pacchetto pacchetto) {
        this.pacchetto = pacchetto;
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
        if (!(object instanceof CommentoViaggiatore)) {
            return false;
        }
        CommentoViaggiatore other = (CommentoViaggiatore) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "utenti.CommentoViaggiatore[id=" + id + "]";
    }

}
