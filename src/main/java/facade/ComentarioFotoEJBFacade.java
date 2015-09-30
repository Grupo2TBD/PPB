/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.ComentarioFotografia;
/**
 *
 * @author sebastian
 */
@Stateless
public class ComentarioFotoEJBFacade extends AbstractFacade<ComentarioFotografia>{

        @PersistenceContext(unitName = "ian")
	private EntityManager em;
    public ComentarioFotoEJBFacade() {
        super(ComentarioFotografia.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
