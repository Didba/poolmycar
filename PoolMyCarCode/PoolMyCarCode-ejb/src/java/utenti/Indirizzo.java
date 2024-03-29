/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utenti;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**Un indirizzo relativo ad un viaggiatore oppure ad una tappa
 * Un oggetto che rappresenta un indirizzo. Esso può essere relativo indistintamente al domicilio di un viaggiatore oppure ad una tappa facente parte del percorso di un viaggio
 * @author Erica
 */
@Entity
public class Indirizzo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String via;
    private String numerocivico;
    private String citta;
    private String cap;
    private String stato;
    private String provincia;

    /**
     * Get the value of provincia
     *
     * @return the value of provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Set the value of provincia
     *
     * @param provincia new value of provincia
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
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
        if (!(object instanceof Indirizzo)) {
            return false;
        }
        Indirizzo other = (Indirizzo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

  /*  @Override
    public String toString() {
        return "utenti.Indirizzo[id=" + id + "]";
    }*/

    /**
     * @return the via
     */
    public String getVia() {
        return via;
    }

    /**
     * @param via the via to set
     */
    public void setVia(String via) {
        this.via = via;
    }

    /**
     * @return the numerocivico
     */
    public String getNumerocivico() {
        return numerocivico;
    }

    /**
     * @param numerocivico the numerocivico to set
     */
    public void setNumerocivico(String numerocivico) {
        this.numerocivico = numerocivico;
    }

    /**
     * @return the citta
     */
    public String getCitta() {
        return citta;
    }

    /**
     * @param citta the citta to set
     */
    public void setCitta(String citta) {
        this.citta = citta;
    }

    /**
     * @return the cap
     */
    public String getCap() {
        return cap;
    }

    /**
     * @param cap the cap to set
     */
    public void setCap(String cap) {
        this.cap = cap;
    }

    /**
     * @return the stato
     */
    public String getStato() {
        return stato;
    }

    /**
     * @param stato the stato to set
     */
    public void setStato(String stato) {
        this.stato = stato;
    }

    @Override
    public String toString(){
        String s = "";
        if(via!=null && !via.equals(""))
            s +=(via+ " ");
        if(numerocivico!=null && !numerocivico.equals(""))
            s +=(numerocivico+ " ");
        if(cap!=null && !cap.equals("")){
            s +=(cap+ " ");
        }
        if(citta!=null && !citta.equals(""))
            s +=(citta+ " ");
        if(provincia!=null && !provincia.equals(""))
            s +=(provincia+ " ");
        //if(stato!=null && !stato.equals(""))
         //   s +=(" "+stato);


        return s;
    }

}
