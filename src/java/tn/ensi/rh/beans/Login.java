/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.ensi.rh.beans;

import tn.ensi.rh.dao.UserDao;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import tn.ensi.rh.dao.Database;
import tn.ensi.rh.entities.Competence;
import tn.ensi.rh.entities.Departement;
import tn.ensi.rh.entities.Employe;
import tn.ensi.rh.entities.Evaluation;
import tn.ensi.rh.entities.EvaluationPK;
import tn.ensi.rh.entities.Formation;
import tn.ensi.rh.entities.Metier;

@ManagedBean(name = "login")
@SessionScoped

public  class Login implements Serializable {

    private static final long serialVersionUID = 1L;
    private String password;
    private String message, uname;

    private String nom;
    private String prenom;
    private String mail;

    /*
    private Employe employes = new Employe(uname, password) ;

    public Employe getEmployes() {
        return employes;
    }

    public void setEmployes(Employe employes) {
        this.employes = employes;
    }
     */
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
    public String loginProject() {
        boolean result = UserDao.login(uname, password);
        if (result) {
            // get Http Session and store username
            HttpSession session = Util.getSession();
            session.setAttribute("username", uname);

            return "template.xhtml";
        } else {

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Invalid Login!",
                            "Please Try Again!"));

            return "login.xhtml";
        }
    }

    public String logout() {
        HttpSession session = Util.getSession();
        session.invalidate();
        return "login.xhtml";
    }

    public Employe e1(String userid) throws SQLException {
        return UserDao.employe1(userid);
    }

    public Departement d1(int id) throws SQLException {
        return UserDao.departement1(id);
    }

    public Metier m1(int id) throws SQLException {
        return UserDao.metier1(id);
    }
    
     public Formation f1(int id) throws SQLException {
        return UserDao.formation1(id);
    }
     
      public Competence c1(int id) throws SQLException {
        return UserDao.competence1(id);
    }
      
      
      public EvaluationPK eval2(int idf, int idc, String userid) throws SQLException {
          return UserDao.evaluation2(idf, idc, userid); 
      }
      
       public Evaluation eval1(EvaluationPK pk) throws SQLException {
        return UserDao.evaluation1(pk);
    }
    
}
