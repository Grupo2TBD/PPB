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

import model.PermisoFotografia;

@Stateless
public class PermisoFotografiaEJBFacade extends AbstractFacade<PermisoFotografia>{

        @PersistenceContext(unitName = "ian")
	private EntityManager em;
    public PermisoFotografiaEJBFacade() {
        super(PermisoFotografia.class);
    }
    
    
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
