/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.ensi.rh.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author user
 */
@Entity
@Table(name = "condidats", catalog = "rh_test211db", schema = "")
@NamedQueries({
    @NamedQuery(name = "Condidat.findAll", query = "SELECT c FROM Condidat c")
    , @NamedQuery(name = "Condidat.findById", query = "SELECT c FROM Condidat c WHERE c.id = :id")
    , @NamedQuery(name = "Condidat.findByNom", query = "SELECT c FROM Condidat c WHERE c.nom = :nom")
    , @NamedQuery(name = "Condidat.findByPrenom", query = "SELECT c FROM Condidat c WHERE c.prenom = :prenom")
    , @NamedQuery(name = "Condidat.findByMail", query = "SELECT c FROM Condidat c WHERE c.mail = :mail")
    , @NamedQuery(name = "Condidat.findByDatedenaissance", query = "SELECT c FROM Condidat c WHERE c.datedenaissance = :datedenaissance")
    , @NamedQuery(name = "Condidat.findByScolarit\u00e9", query = "SELECT c FROM Condidat c WHERE c.scolarit\u00e9 = :scolarit\u00e9")
    , @NamedQuery(name = "Condidat.findByCompetences", query = "SELECT c FROM Condidat c WHERE c.competences = :competences")
    , @NamedQuery(name = "Condidat.findByCertifications", query = "SELECT c FROM Condidat c WHERE c.certifications = :certifications")})
public class Condidat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "nom")
    private String nom;
    @Size(max = 50)
    @Column(name = "prenom")
    private String prenom;
    @Size(max = 50)
    @Column(name = "mail")
    private String mail;
    @Column(name = "datedenaissance")
    @Temporal(TemporalType.DATE)
    private Date datedenaissance;
    @Size(max = 100)
    @Column(name = "scolarit\u00e9")
    private String scolarité;
    @Size(max = 500)
    @Column(name = "competences")
    private String competences;
    @Size(max = 500)
    @Column(name = "certifications")
    private String certifications;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private Collection<Demandeemploi> demandeemploiCollection;

    public Condidat() {
    }

    public Condidat(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getDatedenaissance() {
        return datedenaissance;
    }

    public void setDatedenaissance(Date datedenaissance) {
        this.datedenaissance = datedenaissance;
    }

    public String getScolarité() {
        return scolarité;
    }

    public void setScolarité(String scolarité) {
        this.scolarité = scolarité;
    }

    public String getCompetences() {
        return competences;
    }

    public void setCompetences(String competences) {
        this.competences = competences;
    }

    public String getCertifications() {
        return certifications;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Condidat)) {
            return false;
        }
        Condidat other = (Condidat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.ensi.rh.entities.Condidat[ id=" + id + " ]";
    }
    
}
