/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viaggi;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import utenti.CommentoAutista;
import utenti.CommentoViaggiatore;
import utenti.TipoMezzo;
import utenti.Viaggiatore;

/**Classe che rappresenta un pacchetto
 * Questa classe definisce i metodi e le variabili relative ad un pacchetto di viaggi
 * @author Fra
 */
@Entity
public class Pacchetto implements Serializable {

    @ManyToOne
    private TipoMezzo tipoMezzo;
    @ManyToOne
    private Viaggiatore autista;
    @OneToOne(mappedBy = "pacchetto")
    private Bacheca bacheca;
    @OneToMany(mappedBy = "pacchetto")
    private List<CommentoViaggiatore> commentiViaggiatori;
    @OneToMany(mappedBy = "pacchetto")
    private List<CommentoAutista> commentiAutista;
    @OneToMany
    private List<Viaggio> viaggi;
    @OneToOne
    private Tappa partenza;
    @OneToOne
    private Tappa arrivo;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Tappa> tappeIntermedie;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar fine;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar inizio;
    private String nota;
    private boolean richiestaContributi;
    protected float lunghezzaPercorso;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Get the value of lunghezzaPercorso
     *
     * @return the value of lunghezzaPercorso
     */
    public float getLunghezzaPercorso() {
        return lunghezzaPercorso;
    }

    /**
     * Set the value of lunghezzaPercorso
     *
     * @param lunghezzaPercorso new value of lunghezzaPercorso
     */
    public void setLunghezzaPercorso(float lunghezzaPercorso) {
        this.lunghezzaPercorso = lunghezzaPercorso;
    }

    /**
     * Get the value of tipoMezzo
     *
     * @return the value of tipoMezzo
     */
    public TipoMezzo getTipoMezzo() {
        return tipoMezzo;
    }

    /**
     * Set the value of tipoMezzo
     *
     * @param tipoMezzo new value of tipoMezzo
     */
    public void setTipoMezzo(TipoMezzo tipoMezzo) {
        this.tipoMezzo = tipoMezzo;
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

    public Tappa getArrivo() {
        return arrivo;
    }

    public void setArrivo(Tappa arrivo) {
        this.arrivo = arrivo;
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

    public Calendar getFine() {
        return fine;
    }

    public void setFine(Calendar fine) {
        this.fine = fine;
        this.fine.set(Calendar.HOUR_OF_DAY, 23);
        this.fine.set(Calendar.MINUTE, 59);
    }

    public Calendar getInizio() {
        return inizio;
    }

    public void setInizio(Calendar inizio) {
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

    public void creaViaggi(List<Calendar> date, int nPosti) throws IllegalStateException {
        if (partenza == null || arrivo == null) {
            throw new IllegalStateException("Inserisci partenza e/o arrivo");
        }

        this.viaggi = new LinkedList<Viaggio>();
        for (Calendar d : date) {
            d.set(Calendar.MINUTE, this.inizio.get(Calendar.MINUTE));
            d.set(Calendar.HOUR_OF_DAY, this.inizio.get(Calendar.HOUR_OF_DAY));
            System.out.println("inserisco viaggio del " + d);
            Viaggio viaggio = new Viaggio();
            viaggio.setPartenza(partenza);
            viaggio.setArrivo(arrivo);
            viaggio.setDataPartenza(d);
            viaggio.setLunghezzaPercorso(lunghezzaPercorso);
            viaggio.setViaggiatori(new LinkedList<Viaggiatore>());
            viaggio.setRichieste(new LinkedList<Richiesta>());
            viaggio.setTappeIntermedie(tappeIntermedie);
            viaggio.setModificato(false);
            viaggio.setPostiMax(nPosti);
            viaggi.add(viaggio);
        }
        System.out.println("viaggi.size()=" + viaggi.size());
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

    /**
     * @return the tappeIntermedie
     */
    public List<Tappa> getTappeIntermedie() {
        return tappeIntermedie;
    }

    /**
     * @param tappeIntermedie the tappeIntermedie to set
     */
    public void setTappeIntermedie(List<Tappa> tappeIntermedie) {
        this.tappeIntermedie = tappeIntermedie;
    }

    /**
     * @return the autista
     */
    public Viaggiatore getAutista() {
        return autista;
    }

    /**
     * @param autista the autista to set
     */
    public void setAutista(Viaggiatore autista) {
        this.autista = autista;
    }

    public String getPercorso() {
        String percorso = "from: " + partenza.getIndirizzo().toString();

        for (Tappa t : tappeIntermedie) {
            percorso += (" to: " + t.getIndirizzo().toString());
        }
        percorso += " to: " + arrivo.getIndirizzo().toString();

        return percorso;
    }
}
