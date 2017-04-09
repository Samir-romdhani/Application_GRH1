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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "metiers", catalog = "rh_test211db", schema = "")
@NamedQueries({
    @NamedQuery(name = "Metier.findAll", query = "SELECT m FROM Metier m")
    , @NamedQuery(name = "Metier.findByIdentificationDuPoste", query = "SELECT m FROM Metier m WHERE m.identificationDuPoste = :identificationDuPoste")
    , @NamedQuery(name = "Metier.findByMissionDuPoste", query = "SELECT m FROM Metier m WHERE m.missionDuPoste = :missionDuPoste")
    , @NamedQuery(name = "Metier.findByNomDuPoste", query = "SELECT m FROM Metier m WHERE m.nomDuPoste = :nomDuPoste")
    , @NamedQuery(name = "Metier.findByDescriptionPoste", query = "SELECT m FROM Metier m WHERE m.descriptionPoste = :descriptionPoste")})
public class Metier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "identification_du_poste")
    private Integer identificationDuPoste;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "mission_du_poste")
    private String missionDuPoste;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nom_du_poste")
    private String nomDuPoste;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "description_poste")
    private String descriptionPoste;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "identificationDuPoste")
    private Collection<Employe> employeCollection;

    public Metier() {
    }

    public Metier(Integer identificationDuPoste) {
        this.identificationDuPoste = identificationDuPoste;
    }

    public Metier(Integer identificationDuPoste, String missionDuPoste, String nomDuPoste, String descriptionPoste) {
        this.identificationDuPoste = identificationDuPoste;
        this.missionDuPoste = missionDuPoste;
        this.nomDuPoste = nomDuPoste;
        this.descriptionPoste = descriptionPoste;
    }

    public Integer getIdentificationDuPoste() {
        return identificationDuPoste;
    }

    public void setIdentificationDuPoste(Integer identificationDuPoste) {
        this.identificationDuPoste = identificationDuPoste;
    }

    public String getMissionDuPoste() {
        return missionDuPoste;
    }

    public void setMissionDuPoste(String missionDuPoste) {
        this.missionDuPoste = missionDuPoste;
    }

    public String getNomDuPoste() {
        return nomDuPoste;
    }

    public void setNomDuPoste(String nomDuPoste) {
        this.nomDuPoste = nomDuPoste;
    }

    public String getDescriptionPoste() {
        return descriptionPoste;
    }

    public void setDescriptionPoste(String descriptionPoste) {
        this.descriptionPoste = descriptionPoste;
    }

    public Collection<Employe> getEmployeCollection() {
        return employeCollection;
    }

    public void setEmployeCollection(Collection<Employe> employeCollection) {
        this.employeCollection = employeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identificationDuPoste != null ? identificationDuPoste.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Metier)) {
            return false;
        }
        Metier other = (Metier) object;
        if ((this.identificationDuPoste == null && other.identificationDuPoste != null) || (this.identificationDuPoste != null && !this.identificationDuPoste.equals(other.identificationDuPoste))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.ensi.rh.entities.Metier[ identificationDuPoste=" + identificationDuPoste + " ]";
    }
    
}
