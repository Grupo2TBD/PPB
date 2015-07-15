/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

/**
 *
 * @author ian
 */


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Usuario;

@Stateless
public class FotografiaEJBFacade extends AbstractFacade<Usuario>{

        @PersistenceContext(unitName = "ian")
	private EntityManager em;
    public FotografiaEJBFacade() {
        super(Usuario.class);
    }
    
    
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
