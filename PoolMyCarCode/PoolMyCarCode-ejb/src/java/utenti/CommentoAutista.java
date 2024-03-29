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

/**Oggetto che rappresenta di un autista
 *Oggetto che rappresenta un commento dato da un autista ad un viaggiatore a fronte di un viaggio
 * @author Erica
 */
@Entity
public class CommentoAutista implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nota;
    private int puntualita;
    private int flessibilita;
    private int cordialitaCorrettezza;
    private int comfortDiGuida;
    @ManyToOne
    private Viaggiatore autore;
    @ManyToOne
    private FeedbackAutista feedbackAutista;
    @ManyToOne
    private Pacchetto pacchetto;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataCreazione;

    public Viaggiatore getAutore() {
        return autore;
    }

    public void setAutore(Viaggiatore autore) {
        this.autore = autore;
    }

    public Calendar getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(Calendar dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public FeedbackAutista getFeedbackAutista() {
        return feedbackAutista;
    }

    public void setFeedbackAutista(FeedbackAutista feedbackAutista) {
        this.feedbackAutista = feedbackAutista;
    }

    public Pacchetto getPacchetto() {
        return pacchetto;
    }

    public void setPacchetto(Pacchetto pacchetto) {
        this.pacchetto = pacchetto;
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

    public int getComfortDiGuida() {
        return comfortDiGuida;
    }

    public void setComfortDiGuida(int comfortDiGuida) {
        this.comfortDiGuida = comfortDiGuida;
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
        if (!(object instanceof CommentoAutista)) {
            return false;
        }
        CommentoAutista other = (CommentoAutista) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "utenti.Commento[id=" + id + "]";
    }

}
