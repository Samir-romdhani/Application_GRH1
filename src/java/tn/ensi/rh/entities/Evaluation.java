/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.ensi.rh.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "evaluations", catalog = "rh_test211db", schema = "")
@NamedQueries({
    @NamedQuery(name = "Evaluation.findAll", query = "SELECT e FROM Evaluation e")
    , @NamedQuery(name = "Evaluation.findByIdF", query = "SELECT e FROM Evaluation e WHERE e.evaluationPK.idF = :idF")
    , @NamedQuery(name = "Evaluation.findByIdC", query = "SELECT e FROM Evaluation e WHERE e.evaluationPK.idC = :idC")
    , @NamedQuery(name = "Evaluation.findByUserId", query = "SELECT e FROM Evaluation e WHERE e.evaluationPK.userId = :userId")
    , @NamedQuery(name = "Evaluation.findByNote", query = "SELECT e FROM Evaluation e WHERE e.note = :note")})
public class Evaluation implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EvaluationPK evaluationPK;
    @Column(name = "note")
    private Integer note;
    @JoinColumn(name = "IdF", referencedColumnName = "IdF", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Formation formation;
    @JoinColumn(name = "IdC", referencedColumnName = "IdC", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Competence competence;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Employe employe;

    public Evaluation() {
    }

    public Evaluation(EvaluationPK evaluationPK) {
        this.evaluationPK = evaluationPK;
    }

    public Evaluation(int idF, int idC, String userId) {
        this.evaluationPK = new EvaluationPK(idF, idC, userId);
    }

    public EvaluationPK getEvaluationPK() {
        return evaluationPK;
    }

    public void setEvaluationPK(EvaluationPK evaluationPK) {
        this.evaluationPK = evaluationPK;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evaluationPK != null ? evaluationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluation)) {
            return false;
        }
        Evaluation other = (Evaluation) object;
        if ((this.evaluationPK == null && other.evaluationPK != null) || (this.evaluationPK != null && !this.evaluationPK.equals(other.evaluationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.ensi.rh.entities.Evaluation[ evaluationPK=" + evaluationPK + " ]";
    }
    
}
