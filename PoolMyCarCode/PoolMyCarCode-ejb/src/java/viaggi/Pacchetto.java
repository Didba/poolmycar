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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import utenti.Autista;
import utenti.CommentoAutista;
import utenti.CommentoViaggiatore;

/**
 *
 * @author Erica
 */
@Entity
public class Pacchetto implements Serializable {
    @OneToOne(mappedBy = "pacchetto")
    private Bacheca bacheca;
    @OneToMany(mappedBy = "pacchetto")
    private List<CommentoViaggiatore> commentiViaggiatori;
    @OneToMany(mappedBy = "pacchetto")
    private List<CommentoAutista> commentiAutista;
    @OneToMany(mappedBy = "pacchetto")
    private List<Viaggio> viaggi;
    @OneToOne
    private Tappa partenza;
    @OneToOne
    private Tappa arrivo;
    @ManyToOne
    private Autista autista;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fine;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date inizio;
    private String nota;
    private boolean richiestaContributi;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    public Tappa getArrivo() {
        return arrivo;
    }

    public void setArrivo(Tappa arrivo) {
        this.arrivo = arrivo;
    }

    public Autista getAutista() {
        return autista;
    }

    public void setAutista(Autista autista) {
        this.autista = autista;
    }

    public Bacheca getBacheca() {
        return bacheca;
    }

    public void setBacheca(Bacheca bacheca) {
        this.bacheca = bacheca;
    }

    public List<CommentoAutista> getCommentiAutista() {
        return commentiAutista;
    }

    public void setCommentiAutista(List<CommentoAutista> commentiAutista) {
        this.commentiAutista = commentiAutista;
    }

    public List<CommentoViaggiatore> getCommentiViaggiatori() {
        return commentiViaggiatori;
    }

    public void setCommentiViaggiatori(List<CommentoViaggiatore> commentiViaggiatori) {
        this.commentiViaggiatori = commentiViaggiatori;
    }

    public Date getFine() {
        return fine;
    }

    public void setFine(Date fine) {
        this.fine = fine;
    }

    public Date getInizio() {
        return inizio;
    }

    public void setInizio(Date inizio) {
        this.inizio = inizio;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Tappa getPartenza() {
        return partenza;
    }

    public void setPartenza(Tappa partenza) {
        this.partenza = partenza;
    }

    public boolean isRichiestaContributi() {
        return richiestaContributi;
    }

    public void setRichiestaContributi(boolean richiestaContributi) {
        this.richiestaContributi = richiestaContributi;
    }

    public List<Viaggio> getViaggi() {
        return viaggi;
    }

    public void setViaggi(List<Viaggio> viaggi) {
        this.viaggi = viaggi;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pacchetto)) {
            return false;
        }
        Pacchetto other = (Pacchetto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "viaggi.Pacchetto[id=" + id + "]";
    }
}
