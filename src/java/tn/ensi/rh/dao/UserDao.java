/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.ensi.rh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import tn.ensi.rh.entities.Competence;
import tn.ensi.rh.entities.Departement;
import tn.ensi.rh.entities.Employe;
import tn.ensi.rh.entities.Etat;
import tn.ensi.rh.entities.Evaluation;
import tn.ensi.rh.entities.EvaluationPK;
import tn.ensi.rh.entities.Formation;
import tn.ensi.rh.entities.Metier;

public class UserDao {

    static String nom;

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        UserDao.nom = nom;
    }

    public static boolean login(String user, String password) {

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "select user_id, motdepass, nom from employes where user_id= ? and motdepass= ? ");
            ps.setString(1, user);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) // found
            {
                nom = rs.getString("user_id");
                //System.err.println(nom);
                System.out.println(rs.getString("user_id"));
                // System.out.println(rs.getString("nom"));

                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
        }
    }

    public static Employe employe1(String userid) throws SQLException {
        Connection connection = null;
        Employe e = new Employe();
        try {
            connection = Database.getConnection();
            String ordre_sql = "SELECT * FROM employes";
            // Exécuter la requête
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(ordre_sql);

            // Exploiter le résultat
            String nom1;
            String prenom1;
            String mail1;
            String chef1;
            Date date1;
            Date date2;
            String id_drapartement1;
            String id_poste1;

            while (resultSet.next()) {
                String uname1 = resultSet.getString("user_id");
                String password1 = resultSet.getString("motdepass");
                nom1 = resultSet.getString("nom");
                prenom1 = resultSet.getString("prenom");
                mail1 = resultSet.getString("mail");
                chef1 = resultSet.getString("chef");
                date1 = resultSet.getDate("datedenaissance");
                date2 = resultSet.getDate("datedetravail");
                id_drapartement1 = resultSet.getString("id_departement");
                id_poste1 = resultSet.getString("identification_du_poste");
                if (uname1 == null ? userid == null : uname1.equals(userid)) {

                    e.setUserId(uname1);
                    e.setMotdepass(password1);
                    e.setNom(nom1);
                    e.setPrenom(prenom1);
                    e.setMail(mail1);
                    e.setChef(employe1(chef1));
                    e.setDatedenaissance(date1);
                    e.setDatedetravail(date2);
                    e.setIdDepartement(departement1(Integer.parseInt(id_drapartement1)));
                    e.setIdentificationDuPoste(metier1(Integer.parseInt(id_poste1)));
                }
            }

            // Fermer la connexion
            connection.close();

            return e;

        } catch (SQLException ex) {
            System.out.println("======>" + ex);
        }
        return e;
    }

    public static Departement departement1(int id) throws SQLException {
        Connection connection = null;
        // List list = new ArrayList();
        Departement d = new Departement();
        try {
            connection = Database.getConnection();
            String ordre_sql = "SELECT * FROM departements";
            // Exécuter la requête
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(ordre_sql);
            // Exploiter le résultat
            String id_drapartement1;
            String nom_drapartement1;
            String desc_drapartement1;

            while (resultSet.next()) {
                id_drapartement1 = resultSet.getString("id_departement");
                nom_drapartement1 = resultSet.getString("nom_departement");
                desc_drapartement1 = resultSet.getString("description_departement");

                if (Integer.parseInt(id_drapartement1) == id) {

                    d.setIdDepartement(Integer.parseInt(id_drapartement1));
                    d.setNomDepartement(nom_drapartement1);
                    d.setDescriptionDepartement(desc_drapartement1);
                }
            }
            // Fermer la connexion
            connection.close();
            return d;
        } catch (SQLException ex) {
            System.out.println("======>" + ex);
        }
        return d;
    }

    public static Metier metier1(int id) throws SQLException {
        Connection connection = null;
        Metier m = new Metier();
        try {
            connection = Database.getConnection();
            String ordre_sql = "SELECT * FROM metiers";
            // Exécuter la requête
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(ordre_sql);
            // Exploiter le résultat
            String id_metier1;
            String nom_duposte1;
            String mission_duposte1;
            String desc_poste1;

            while (resultSet.next()) {
                id_metier1 = resultSet.getString("identification_du_poste");
                nom_duposte1 = resultSet.getString("nom_du_poste");
                mission_duposte1 = resultSet.getString("mission_du_poste");
                desc_poste1 = resultSet.getString("description_poste");

                if (Integer.parseInt(id_metier1) == id) {

                    m.setIdentificationDuPoste(Integer.parseInt(id_metier1));
                    m.setNomDuPoste(nom_duposte1);
                    m.setMissionDuPoste(mission_duposte1);
                    m.setDescriptionPoste(desc_poste1);
                }
            }
            // Fermer la connexion
            connection.close();
            return m;
        } catch (SQLException ex) {
            System.out.println("======>" + ex);
        }
        return m;
    }

    public static Etat etat1(String etat1) throws SQLException {
        Connection connection = null;
        Etat etat = new Etat();
        try {
            connection = Database.getConnection();
            String ordre_sql = "SELECT * FROM etats";
            // Exécuter la requête
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(ordre_sql);
            // Exploiter le résultat
            String etat2;
            while (resultSet.next()) {
                etat2 = resultSet.getString("etat");
                if (etat1 == null ? etat2 == null : etat1.equals(etat2)) {
                    etat.setEtat(etat2);
                }
            }
            // Fermer la connexion
            connection.close();
            return etat;
        } catch (SQLException ex) {
            System.out.println("======>" + ex);
        }
        return etat;
    }

    

    public static Formation formation1(int id) throws SQLException {
        Connection connection = null;
        Formation f = new Formation();
        try {
            connection = Database.getConnection();
            String ordre_sql = "SELECT * FROM formations";
            // Exécuter la requête
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(ordre_sql);
            // Exploiter le résultat
            String idf;
            String libelle;
            String etat;
            String user;
            while (resultSet.next()) {
                idf = resultSet.getString("IdF");
                libelle = resultSet.getString("Libelle");
                etat = resultSet.getString("etat");
                user = resultSet.getString("user_id");
                
                if (Integer.parseInt(idf) == id) {
                    f.setEtat(etat1(etat));
                    f.setUserId(employe1(user));
                    f.setLibelle(libelle) ;
                    f.setIdF(Integer.parseInt(idf));
                }
            }
            // Fermer la connexion
            connection.close();
            return f;
        } catch (SQLException ex) {
            System.out.println("======>" + ex);
        }
        return f;
    }
    
    
    
    public static Competence competence1(int id) throws SQLException {
        Connection connection = null;
        Competence c = new Competence();
        try {
            connection = Database.getConnection();
            String ordre_sql = "SELECT * FROM competences";
            // Exécuter la requête
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(ordre_sql);
            // Exploiter le résultat
            String Id_C1;
            String description1;
            String UserId1;
            String Id_F1;

            while (resultSet.next()) {
                Id_C1 = resultSet.getString("IdC");
                Id_F1 = resultSet.getString("IdF");
                description1 = resultSet.getString("descriptions");
                UserId1 = resultSet.getString("user_id");

                if (Integer.parseInt(Id_C1) == id) {
                    c.setIdC(Integer.parseInt(Id_C1));
                    c.setIdF(formation1(Integer.parseInt(Id_F1)));
                    c.setDescriptions(description1);
                    c.setUserId(employe1(UserId1));
                }
            }
            // Fermer la connexion
            connection.close();
            return c;
        } catch (SQLException ex) {
            System.out.println("======>" + ex);
        }
        return c;
    }
    
    
        
     public static EvaluationPK evaluation2(int idf, int idc, String user) throws SQLException {
        Connection connection = null;
        EvaluationPK eval2 = new EvaluationPK();
        try {
            connection = Database.getConnection();
            String ordre_sql = "SELECT * FROM evaluations";
            // Exécuter la requête
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(ordre_sql);
            // Exploiter le résultat;
            String idf1 ;
            String idc1 ;
            String user1 ;
            while (resultSet.next()) {
                idf1 = resultSet.getString("IdF");
                idc1 = resultSet.getString("IdC");
                user1 = resultSet.getString("user_id");

                if ((Integer.parseInt(idf1) == idf) && (Integer.parseInt(idc1) == idc) && (user1 == null ? user == null : user1.equals(user))) {
                    eval2.setUserId(user1);
                    eval2.setIdF((Integer.parseInt(idf1)));
                    eval2.setIdC((Integer.parseInt(idc1))) ;
                    
                }
            }
            // Fermer la connexion
            connection.close();
            return eval2;
        } catch (SQLException ex) {
            System.out.println("======>" + ex);
        }
        return eval2;
    }

    public static Evaluation evaluation1(EvaluationPK PK) throws SQLException {
        Connection connection = null;
        Evaluation eval = new Evaluation();
        try {
            connection = Database.getConnection();
            String ordre_sql = "SELECT * FROM evaluations";
            // Exécuter la requête
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(ordre_sql);
            // Exploiter le résultat;
            String note;
            String idf1 ;
            String idc1 ;
            String user1 ;
            while (resultSet.next()) {
                idf1 = resultSet.getString("IdF");
                idc1 = resultSet.getString("IdC");
                user1 = resultSet.getString("user_id");
           
                note = resultSet.getString("note");
                if(PK != null) {
                    eval.setNote(Integer.parseInt(note));
                   eval.setEmploye(employe1(user1));
                   eval.setFormation(formation1(Integer.parseInt(idf1)));
                   eval.setCompetence(competence1((Integer.parseInt(idc1))));
                }
            }
            // Fermer la connexion
            connection.close();
            return eval;
        } catch (SQLException ex) {
            System.out.println("======>" + ex);
        }
        return eval;
    }
    
    
}
