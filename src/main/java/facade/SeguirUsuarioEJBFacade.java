/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Fotografia;
import model.Seguidor;
import model.SeguidorPK;
import model.Usuario;

/**
 *
 * @author sebastian
 */
@Stateless
public class SeguirUsuarioEJBFacade extends AbstractFacade<Seguidor> {
    
    @PersistenceContext(unitName = "ian")
	private EntityManager em;
    public SeguirUsuarioEJBFacade() {
        super(Seguidor.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
/*Parámetros: id usuario, id usuario al que se sigue
    Función encarga de buscar al seguidor en la base de datos
    Retorno: Seguidor  
    */
    public Seguidor buscarSeguidor(int idUsuario, int idUsuarioSigue){
 
        return (Seguidor) em.createQuery(
            "SELECT s FROM Seguidor s WHERE s.seguidorPK.usuarioiduser = :idUser AND s.seguidorPK.usuarioiduser1 = :usuIdUser")
            .setParameter("idUser", idUsuario)
            .setParameter("usuIdUser", idUsuarioSigue)
            .getSingleResult();
    }
    
    /*Parámetros: id usuario
    Función encarga de buscar todos los seguidores del usuario en la base de datos
    Retorno: Lista de seguidores.  
    */
    public List<Seguidor> buscarSeguidores(int idUsuario){
 
        return em.createQuery(
            "SELECT s FROM Seguidor s WHERE s.seguidorPK.usuarioiduser1 = :idUsuario")
            .setParameter("idUsuario", idUsuario)
            .getResultList();

    }
    
    /*Parámetros: id usuario, fecha
    Función encarga de buscar todos los seguidores del usuario en la base de datos desde la fecha especificada 
    Retorno: Lista de seguidores.
    */
    public List<Seguidor> buscarSeguidoresFecha(int idUsuario, Date fecha)
    {
        
        return em.createQuery(
            "SELECT s FROM Seguidor s WHERE s.seguidorPK.usuarioiduser1 = :idUsuario AND s.fechaFollow > :fecha")
            .setParameter("idUsuario", idUsuario)
            .setParameter("fecha", fecha)
            .getResultList();
        
    }
    
    
    public List<Seguidor> buscarSeguidosUsuario(int idUsuario)
    {
        
        return em.createQuery(
            "SELECT s FROM Seguidor s WHERE s.seguidorPK.usuarioiduser = :idUsuario")
            .setParameter("idUsuario", idUsuario)
            .getResultList();
        
    }

}
