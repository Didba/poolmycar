/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viaggi;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import utenti.Viaggiatore;

/**
 *
 * @author Erica
 */
@Entity
public class Richiesta implements Serializable {
    @ManyToMany(mappedBy = "richieste")
    private List<Viaggio> viaggi;//devono  appartenere tutti allo stesso pacchetto
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Viaggiatore autore;
    private boolean accettata;
    @OneToMany
    private List<Post> messaggi;
    @OneToOne
    private Tappa incontro;
    @OneToOne
    private Tappa destinazione;



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
        if (!(object instanceof Richiesta)) {
            return false;
        }
        Richiesta other = (Richiesta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public boolean isAccettata() {
        return accettata;
    }

    public void setAccettata(boolean accettata) {
        this.accettata = accettata;
    }

    public Viaggiatore getAutore() {
        return autore;
    }

    public void setAutore(Viaggiatore autore) {
        this.autore = autore;
    }

    public Tappa getDestinazione() {
        return destinazione;
    }

    public void setDestinazione(Tappa destinazione) {
        this.destinazione = destinazione;
    }

    public Tappa getIncontro() {
        return incontro;
    }

    public void setIncontro(Tappa incontro) {
        this.incontro = incontro;
    }

    public List<Post> getMessaggi() {
        return messaggi;
    }

    public void setMessaggi(List<Post> messaggi) {
        this.messaggi = messaggi;
    }

    public List<Viaggio> getViaggi() {
        return viaggi;
    }

    public void setViaggi(List<Viaggio> viaggi) {
        this.viaggi = viaggi;
    }

    @Override
    public String toString() {
        return "viaggi.Richiesta[id=" + id + "]";
    }

}
