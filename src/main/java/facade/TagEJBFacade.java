/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Tag;

/**
 *
 * @author sebastian
 */
@Stateless
public class TagEJBFacade extends AbstractFacade<Tag>{
    
    @PersistenceContext(unitName = "ian")
    private EntityManager em;
    public TagEJBFacade() {
        super(Tag.class);
    }
    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Tag buscarNombre(String nombre){
        
        List<Tag> tag = em.createNamedQuery("Tag.findByNombreTag")
                .setParameter("nombreTag", nombre)
                .getResultList();
        if(tag.isEmpty()){
            return null;
        }
        else{
            return tag.get(0);
        }
       
                
    }
    
}
