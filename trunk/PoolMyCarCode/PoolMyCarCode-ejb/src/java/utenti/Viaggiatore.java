/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utenti;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import viaggi.Pacchetto;
import viaggi.Richiesta;
import viaggi.Viaggio;

/** Un viaggiatore
 * Questa classe definisce gli oggetti di tipo Viaggiatore. Esso può essere autista o meno in base al valore del campo isAutista
 * @author Erica
 */
@Entity
public class Viaggiatore implements Serializable {
    @ManyToMany(mappedBy = "viaggiatori")
    private List<Viaggio> viaggi;
    @OneToMany(mappedBy = "autore")
    private List<Richiesta> richieste;
    @OneToOne(cascade=CascadeType.ALL)
    private FeedbackViaggiatore feedbackViaggiatore;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    protected String cognome;
    protected String nome;
    protected String cf;
    @OneToOne(cascade=CascadeType.ALL)
    protected Indirizzo indirizzo;
    protected String telefono;
    protected String note;
    protected boolean fumatore;
    @OneToMany(mappedBy = "autore")
    private List<CommentoAutista> commentiRilasciati;
    @OneToMany(cascade=CascadeType.ALL)
    private Set<TipoMezzo> mezzi;
    private String login;
    private String numeroPatente;
    @OneToOne(cascade=CascadeType.ALL)
    private FeedbackAutista feedBackAutista;
    @OneToMany(mappedBy = "autista")
    private List<Pacchetto> pacchettiDaAutista;

    /**
     * Get the value of pacchettiDaAutista
     *
     * @return the value of pacchettiDaAutista
     */
    public List<Pacchetto> getPacchettiDaAutista() {
        return pacchettiDaAutista;
    }

    /**
     * Set the value of pacchettiDaAutista
     *
     * @param pacchettiDaAutista new value of pacchettiDaAutista
     */
    public void setPacchettiDaAutista(List<Pacchetto> pacchettiDaAutista) {
        this.pacchettiDaAutista = pacchettiDaAutista;
    }


    /**
     * Get the value of feedBackAutista
     *
     * @return the value of feedBackAutista
     */
    public FeedbackAutista getFeedBackAutista() {
        return feedBackAutista;
    }

    /**
     * Set the value of feedBackAutista
     *
     * @param feedBackAutista new value of feedBackAutista
     */
    public void setFeedbackAutista(FeedbackAutista feedBackAutista) {
        this.feedBackAutista = feedBackAutista;
    }

    /**
     * Get the value of mezzi
     *
     * @return the value of mezzi
     */
    public Set<TipoMezzo> getMezzi() {
        return mezzi;
    }

    /**
     * Set the value of mezzi
     *
     * @param mezzi new value of mezzi
     */
    public void setMezzi(Set<TipoMezzo> mezzi) {
        this.mezzi = mezzi;
    }

    /**
     * Get the value of numeroPatente
     *
     * @return the value of numeroPatente
     */
    public String getNumeroPatente() {
        return numeroPatente;
    }

    /**
     * Set the value of numeroPatente
     *
     * @param numeroPatente new value of numeroPatente
     */
    public void setNumeroPatente(String numeroPatente) {
        this.numeroPatente = numeroPatente;
    }

    private boolean isAutista = false;


    /**
     * Get the value of isAutista
     *
     * @return the value of isAutista
     */
    public boolean isAutista() {
        return isAutista;
    }

    /**
     * Set the value of isAutista
     *
     * @param isAutista new value of isAutista
     */
    public void setAutista(boolean isAutista) {
        this.isAutista = isAutista;
    }


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
     * Get the value of feedbackViaggiatore
     *
     * @return the value of feedbackViaggiatore
     */
    public FeedbackViaggiatore getFeedbackViaggiatore() {
        return feedbackViaggiatore;
    }

    /**
     * Set the value of feedbackViaggiatore
     *
     * @param feedbackViaggiatore new value of feedbackViaggiatore
     */
    public void setFeedbackViaggiatore(FeedbackViaggiatore feedbackViaggiatore) {
        this.feedbackViaggiatore = feedbackViaggiatore;
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
