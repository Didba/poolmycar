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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import viaggi.Richiesta;
import viaggi.Viaggio;

/**
 *
 * @author Erica
 */
@Entity
public class Viaggiatore implements Serializable {
    @ManyToMany(mappedBy = "viaggiatori")
    private List<Viaggio> viaggi;
    @OneToMany(mappedBy = "autore")
    private List<Richiesta> richieste;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    protected String cognome;
    protected String nome;
    protected String cf;
    @OneToOne
    protected Indirizzo indirizzo;
    protected String telefono;
    protected String note;
    protected boolean fumatore;
    @OneToMany(mappedBy = "autore")
    private List<CommentoAutista> commentiRilasciati;
    protected String login;

    /**
     * Get the value of login
     *
     * @return the value of login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set the value of login
     *
     * @param login new value of login
     */
    public void setLogin(String login) {
        this.login = login;
    }
    protected String password;

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }


    public List<CommentoAutista> getCommentiRilasciati() {
        return commentiRilasciati;
    }

    public void setCommentiRilasciati(List<CommentoAutista> commentiRilasciati) {
        this.commentiRilasciati = commentiRilasciati;
    }

    public List<Richiesta> getRichieste() {
        return richieste;
    }

    public void setRichieste(List<Richiesta> richieste) {
        this.richieste = richieste;
    }

    public List<Viaggio> getViaggi() {
        return viaggi;
    }

    public void setViaggi(List<Viaggio> viaggi) {
        this.viaggi = viaggi;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public boolean isFumatore() {
        return fumatore;
    }

    public void setFumatore(boolean fumatore) {
        this.fumatore = fumatore;
    }

    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }



    /**
     * Get the value of cognome
     *
     * @return the value of cognome
     */
    public String getCognome() {
        return cognome;
    }
    

    /**
     * Get the value of nome
     *
     * @return the value of nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Set the value of nome
     *
     * @param nome new value of nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Set the value of cognome
     *
     * @param cognome new value of cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
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
        if (!(object instanceof Viaggiatore)) {
            return false;
        }
        Viaggiatore other = (Viaggiatore) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "utenti.Viaggiatore[id=" + id + "]";
    }

}
