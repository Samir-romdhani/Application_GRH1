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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author user
 */
@Entity
@Table(name = "employes", catalog = "rh_test211db", schema = "")
@NamedQueries({
    @NamedQuery(name = "Employe.findAll", query = "SELECT e FROM Employe e")
    , @NamedQuery(name = "Employe.findByUserId", query = "SELECT e FROM Employe e WHERE e.userId = :userId")
    , @NamedQuery(name = "Employe.findByNom", query = "SELECT e FROM Employe e WHERE e.nom = :nom")
    , @NamedQuery(name = "Employe.findByPrenom", query = "SELECT e FROM Employe e WHERE e.prenom = :prenom")
    , @NamedQuery(name = "Employe.findByMail", query = "SELECT e FROM Employe e WHERE e.mail = :mail")
    , @NamedQuery(name = "Employe.findByDatedenaissance", query = "SELECT e FROM Employe e WHERE e.datedenaissance = :datedenaissance")
    , @NamedQuery(name = "Employe.findByDatedetravail", query = "SELECT e FROM Employe e WHERE e.datedetravail = :datedetravail")
    , @NamedQuery(name = "Employe.findByMotdepass", query = "SELECT e FROM Employe e WHERE e.motdepass = :motdepass")})
public class Employe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "user_id")
    private String userId;
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
    @Column(name = "datedetravail")
    @Temporal(TemporalType.DATE)
    private Date datedetravail;
    @Size(max = 50)
    @Column(name = "motdepass")
    private String motdepass;
    @OneToMany(mappedBy = "userId")
    private Collection<Competence> competenceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employe")
    private Collection<Evaluation> evaluationCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employe")
    private Compte compte;
    @OneToMany(mappedBy = "userId")
    private Collection<Formation> formationCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<Demandeabsence> demandeabsenceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chef")
    private Collection<Employe> employeCollection;
    @JoinColumn(name = "chef", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Employe chef;
    @JoinColumn(name = "id_departement", referencedColumnName = "id_departement")
    @ManyToOne
    private Departement idDepartement;
    @JoinColumn(name = "identification_du_poste", referencedColumnName = "identification_du_poste")
    @ManyToOne(optional = false)
    private Metier identificationDuPoste;

    public Employe() {
    }

    public Employe(String userId) {
        this.userId = userId;
    }
    

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Date getDatedetravail() {
        return datedetravail;
    }

    public void setDatedetravail(Date datedetravail) {
        this.datedetravail = datedetravail;
    }

    public String getMotdepass() {
        return motdepass;
    }

    public void setMotdepass(String motdepass) {
        this.motdepass = motdepass;
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

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
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

    public Collection<Employe> getEmployeCollection() {
        return employeCollection;
    }

    public void setEmployeCollection(Collection<Employe> employeCollection) {
        this.employeCollection = employeCollection;
    }

    public Employe getChef() {
        return chef;
    }

    public void setChef(Employe chef) {
        this.chef = chef;
    }

    public Departement getIdDepartement() {
        return idDepartement;
    }

    public void setIdDepartement(Departement idDepartement) {
        this.idDepartement = idDepartement;
    }

    public Metier getIdentificationDuPoste() {
        return identificationDuPoste;
    }

    public void setIdentificationDuPoste(Metier identificationDuPoste) {
        this.identificationDuPoste = identificationDuPoste;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employe)) {
            return false;
        }
        Employe other = (Employe) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.ensi.rh.entities.Employe[ userId=" + userId + " ]";
    }
    
}
