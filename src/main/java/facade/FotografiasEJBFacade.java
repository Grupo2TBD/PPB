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
import javax.persistence.Query;
import model.Fotografia;
import model.Usuario;

/**
 *
 * @author sebastian
 */
@Stateless
public class FotografiasEJBFacade extends AbstractFacade<Fotografia>{
    
    @PersistenceContext(unitName = "ian")
    private EntityManager em;
    public FotografiasEJBFacade() {
        super(Fotografia.class);
    }
    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /*Parámetros: Usuario
    Función encargada de extraer de la base de datos todas las fotografías del usuario.
    Retorno: Lista con todas las fotografías del usuario. 
    */
    public List<Fotografia> buscarFotografiasUsuario(Usuario user)
    {
        return em.createQuery(
            "SELECT f FROM Fotografia f WHERE f.usuarioiduser = :idUsuario")
            .setParameter("idUsuario", user)
            .getResultList();
    }
    /*Parámetros: Usuario
    Función encargada de extraer de la base de datos todas las fotografías del usuario ordenadas por fecha de subida.
    Retorno: Lista con todas las fotografías del usuario ordenadas por fecha de subida
    */
    public List<Fotografia> FotoUsuarioOrdenadaFS(Usuario user)
    {
        return em.createQuery(
            "SELECT f FROM Fotografia f WHERE f.usuarioiduser =:idUsuario ORDER BY f.fechaSubidaPhoto")
            .setParameter("idUsuario", user)
            .getResultList();
        
    }
    
    /*Parámetros: Usuario
    Función encargada de extraer de la base de datos todas las fotografías del usuario ordenadas por fecha de tomada
    Retorno: Lista con todas las fotografías del usuario ordenadas por fecha de tomada 
    */
    public List<Fotografia> FotoUsuarioOrdenadaFT(Usuario user)
    {
        return em.createQuery(
            "SELECT f FROM Fotografia f WHERE f.usuarioiduser =:idUsuario ORDER BY f.fechaTomadaPhoto")
            .setParameter("idUsuario", user)
            .getResultList();
        
    }
    
    /*Parámetros: Usuario
    Función encargada de extraer de la base de datos todas las fotografías del usuario desde la fecha especificada.
    Retorno: Lista con todas las fotografías del usuario desde la fecha especificada
    */
    public List<Fotografia> FotoSeguidoresPorFecha(Usuario user, Date fecha)
    {
        return em.createQuery(
            "SELECT f FROM Fotografia f WHERE f.usuarioiduser =:idUsuario AND f.fechaSubidaPhoto > :fecha ORDER BY f.cantidadVisitasPhoto desc")
            .setParameter("idUsuario", user)
            .setParameter("fecha", fecha)
            .getResultList();
        
    }
    
    /*Parámetros: Usuario
    Función encargada de extraer de la base de datos todas las fotografías del usuario ordenadas por la cantidad de visitas
    Retorno: Lista con todas las fotografías del usuario ordenadas por cantidad de visitas 
    */
    public List<Fotografia> FotoSeguidoresPorNumero(Usuario user)
    {
        return em.createQuery(
            "SELECT f FROM Fotografia f WHERE f.usuarioiduser =:idUsuario ORDER BY f.cantidadVisitasPhoto desc")
            .setParameter("idUsuario", user)
            .getResultList();
        
    }
    
     
    public List<Fotografia> fotosSeguidosEtiqTerceros(Usuario user){
    
        Query query = em.createNativeQuery("{call usuariosSeguidosEtiqPorTerceros(?)}",
                     Fotografia.class).setParameter(1, user.getIdUser());  
        
       List<Fotografia> foto = query.getResultList();
        
        return foto;
    }
   
    
    
}
