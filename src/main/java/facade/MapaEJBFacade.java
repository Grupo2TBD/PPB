/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Fotografia;
import model.Mapa;
import model.Usuario;

/**
 *
 * @author sebastian
 */
@Stateless
public class MapaEJBFacade extends AbstractFacade<Mapa>{
    
    @PersistenceContext(unitName = "ian")
	private EntityManager em;
    public MapaEJBFacade() {
        super(Mapa.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    public List<Fotografia> buscaPorPais(String pais){
        
       Query query = em.createNativeQuery("{call buscaPorPais(?)}",
                     Fotografia.class).setParameter(1, pais);  
        
       List<Fotografia> foto = query.getResultList();
        
        return foto;
                                   
    }
    
    public List<Fotografia> buscaPorContinente(String continente){
        
       Query query = em.createNativeQuery("{call buscarPorContinente(?)}",
                     Fotografia.class).setParameter(1, continente);  
        
       List<Fotografia> foto = query.getResultList();
        
        return foto;
                                   
    }
    
    public List<Fotografia> buscarPorUsuarioPais(int idUsuario, String pais){
        
       Query query = em.createNativeQuery("{call buscaPorUsuarioPais(?, ?)}",
                     Fotografia.class).setParameter(1, idUsuario).setParameter(2, pais);  
        
       List<Fotografia> foto = query.getResultList();
        
        return foto;
                                   
    }
    
     public List<Fotografia> buscarPorUsuarioContinente(int idUsuario, String continente){
        
       Query query = em.createNativeQuery("{call buscarPorUsuarioContinente(?, ?)}",
                     Fotografia.class).setParameter(1, idUsuario).setParameter(2, continente);  
        
       List<Fotografia> foto = query.getResultList();
        
        return foto;
                                   
    }
     
     
      public List<Fotografia> buscarFotosSeguidosEtiqTercerosPais(Usuario user, String pais){
    
        Query query = em.createNativeQuery("{call usuariosSeguidosEtiqPorTercerosPais(?,?)}",
                     Fotografia.class).setParameter(1, user.getIdUser()).setParameter(2, pais);  
        
       List<Fotografia> foto = query.getResultList();
        
        return foto;
    }
   
    
 
    
    
}
