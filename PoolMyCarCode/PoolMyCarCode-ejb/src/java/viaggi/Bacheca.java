/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viaggi;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**Classe che rappresenta una bacheca
 * Questa classe definisce i metodi e le variabili relative alla bacheca di un dato viaggio
 * @author Fra
 */
@Entity
public class Bacheca implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Pacchetto pacchetto;
    @OneToMany(cascade=CascadeType.ALL)
    private List<Post> messaggi;

    public List<Post> getMessaggi() {
        return messaggi;
    }

    public void setMessaggi(List<Post> messaggi) {
        this.messaggi = messaggi;
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
        if (!(object instanceof Bacheca)) {
            return false;
        }
        Bacheca other = (Bacheca) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "viaggi.Bacheca[id=" + id + "]";
    }

}
