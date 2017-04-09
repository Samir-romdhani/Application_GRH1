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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author user
 */
@Entity
@Table(name = "competences", catalog = "rh_test211db", schema = "")
@NamedQueries({
    @NamedQuery(name = "Competence.findAll", query = "SELECT c FROM Competence c")
    , @NamedQuery(name = "Competence.findByIdC", query = "SELECT c FROM Competence c WHERE c.idC = :idC")
    , @NamedQuery(name = "Competence.findByDescriptions", query = "SELECT c FROM Competence c WHERE c.descriptions = :descriptions")})
public class Competence implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdC")
    private Integer idC;
    @Size(max = 100)
    @Column(name = "descriptions")
    private String descriptions;
    @JoinColumn(name = "IdF", referencedColumnName = "IdF")
    @ManyToOne
    private Formation idF;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private Employe userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competence")
    private Collection<Evaluation> evaluationCollection;

    public Competence() {
    }

    public Competence(Integer idC) {
        this.idC = idC;
    }

    public Integer getIdC() {
        return idC;
    }

    public void setIdC(Integer idC) {
        this.idC = idC;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Formation getIdF() {
        return idF;
    }

    public void setIdF(Formation idF) {
        this.idF = idF;
    }

    public Employe getUserId() {
        return userId;
    }

    public void setUserId(Employe userId) {
        this.userId = userId;
    }

    public Collection<Evaluation> getEvaluationCollection() {
        return evaluationCollection;
    }

    public void setEvaluationCollection(Collection<Evaluation> evaluationCollection) {
        this.evaluationCollection = evaluationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idC != null ? idC.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competence)) {
            return false;
        }
        Competence other = (Competence) object;
        if ((this.idC == null && other.idC != null) || (this.idC != null && !this.idC.equals(other.idC))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.ensi.rh.entities.Competence[ idC=" + idC + " ]";
    }
    
}
