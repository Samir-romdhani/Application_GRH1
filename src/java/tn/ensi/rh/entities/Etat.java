/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.ensi.rh.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author user
 */
@Entity
@Table(name = "etats", catalog = "rh_test211db", schema = "")
@NamedQueries({
    @NamedQuery(name = "Etat.findAll", query = "SELECT e FROM Etat e")
    , @NamedQuery(name = "Etat.findByEtat", query = "SELECT e FROM Etat e WHERE e.etat = :etat")})
public class Etat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "etat")
    private String etat;
    @OneToMany(mappedBy = "etat")
    private Collection<Formation> formationCollection;
    @OneToMany(mappedBy = "etat")
    private Collection<Demandeabsence> demandeabsenceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "etat")
    private Collection<Demandeemploi> demandeemploiCollection;

    public Etat() {
    }

    public Etat(String etat) {
        this.etat = etat;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Collection<Formation> getFormationCollection() {
        return formationCollection;
    }

    public void setFormationCollection(Collection<Formation> formationCollection) {
        this.formationCollection = formationCollection;
    }

    public Collection<Demandeabsence> getDemandeabsenceCollection() {
        return demandeabsenceCollection;
    }

    public void setDemandeabsenceCollection(Collection<Demandeabsence> demandeabsenceCollection) {
        this.demandeabsenceCollection = demandeabsenceCollection;
    }

    public Collection<Demandeemploi> getDemandeemploiCollection() {
        return demandeemploiCollection;
    }

    public void setDemandeemploiCollection(Collection<Demandeemploi> demandeemploiCollection) {
        this.demandeemploiCollection = demandeemploiCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (etat != null ? etat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etat)) {
            return false;
        }
        Etat other = (Etat) object;
        if ((this.etat == null && other.etat != null) || (this.etat != null && !this.etat.equals(other.etat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.ensi.rh.entities.Etat[ etat=" + etat + " ]";
    }
    
}
