/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.ensi.rh.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.ensi.rh.entities.Demandeemploi;

/**
 *
 * @author user
 */
@Stateless
public class DemandeemploiFacade extends AbstractFacade<Demandeemploi> {

    @PersistenceContext(unitName = "Application_GRH1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeemploiFacade() {
        super(Demandeemploi.class);
    }
    
}
