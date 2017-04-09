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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import static tn.ensi.rh.dao.UserDao.login;

/**
 *
 * @author user
 */
@Entity
@Table(name = "formations", catalog = "rh_test211db", schema = "")
@NamedQueries({
    @NamedQuery(name = "Formation.findAll", query = "SELECT f FROM Formation f")
    , @NamedQuery(name = "Formation.findByIdF", query = "SELECT f FROM Formation f WHERE f.idF = :idF")
    , @NamedQuery(name = "Formation.findByLibelle", query = "SELECT f FROM Formation f WHERE f.libelle = :libelle")
    , @NamedQuery(name = "Formation.findByDateCreation", query = "SELECT f FROM Formation f WHERE f.dateCreation = :dateCreation")
    , @NamedQuery(name = "Formation.findByDateFormation", query = "SELECT f FROM Formation f WHERE f.dateFormation = :dateFormation")})
public class Formation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdF")
    private Integer idF;
    @Size(max = 50)
    @Column(name = "Libelle")
    private String libelle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_creation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_formation")
    @Temporal(TemporalType.DATE)
    private Date dateFormation;
    @OneToMany(mappedBy = "idF")
    private Collection<Competence> competenceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formation")
    private Collection<Evaluation> evaluationCollection;
    @JoinColumn(name = "etat", referencedColumnName = "etat")
    @ManyToOne
    private Etat etat;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private Employe userId;

    public Formation() {
    }

    public Formation(Integer idF) {
        this.idF = idF;
    }

    public Formation(Integer idF, Date dateCreation, Date dateFormation) {
        this.idF = idF;
        this.dateCreation = dateCreation;
        this.dateFormation = dateFormation;
    }

    public Integer getIdF() {
        return idF;
    }

    public void setIdF(Integer idF) {
        this.idF = idF;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateFormation() {
        return dateFormation;
    }

    public void setDateFormation(Date dateFormation) {
        this.dateFormation = dateFormation;
    }

    public Collection<Competence> getCompetenceCollection() {
        return competenceCollection;
    }

    public void setCompetenceCollection(Collection<Competence> competenceCollection) {
        this.competenceCollection = competenceCollection;
    }

    public Collection<Evaluation> getEvaluationCollection() {
        return evaluationCollection;
    }

    public void setEvaluationCollection(Collection<Evaluation> evaluationCollection) {
        this.evaluationCollection = evaluationCollection;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Employe getUserId() {
        return userId;
    }

    public void setUserId(Employe userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idF != null ? idF.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formation)) {
            return false;
        }
        Formation other = (Formation) object;
        if ((this.idF == null && other.idF != null) || (this.idF != null && !this.idF.equals(other.idF))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.ensi.rh.entities.Formation[ idF=" + idF + " ]";
    }
    
}
