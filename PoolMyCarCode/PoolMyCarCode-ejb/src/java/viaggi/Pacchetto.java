/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viaggi;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;
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
import utenti.Viaggiatore;

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
    @OneToMany
    private List<Tappa> tappeIntermedie;
    @ManyToOne
    private Autista autista;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar fine;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar inizio;
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

    public Calendar getFine() {
        return fine;
    }

    public void setFine(Calendar fine) {
        this.fine = fine;
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

    public void creaViaggi(List<Calendar> date) throws IllegalStateException
    {
        if(partenza==null || arrivo==null)
            throw new IllegalStateException("Inserisci partenza e/o arrivo");

        this.viaggi= new LinkedList();
        for(Calendar d:date){
            Viaggio viaggio=new Viaggio();
            viaggio.setPartenza(partenza);
            viaggio.setArrivo(arrivo);
            viaggio.setPacchetto(this);
            viaggio.setDataPartenza(d);
            viaggio.setLunghezzaPercorso(-1); //da mettere a posto!
            viaggio.setViaggiatori(new LinkedList<Viaggiatore>());
            viaggio.setRichieste(new LinkedList<Richiesta>());
            viaggio.setTappeIntermedie(tappeIntermedie);
        }
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
}
