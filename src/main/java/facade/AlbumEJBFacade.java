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
import model.Album;
import model.Usuario;

/**
 *
 * @author sebastian
 */
@Stateless
public class AlbumEJBFacade extends AbstractFacade<Album>{
    
    @PersistenceContext(unitName = "ian")
	private EntityManager em;
    public AlbumEJBFacade() {
        super(Album.class);
    }
    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /*Parámetros: Usuario
    Función encargada de extraer de la base de datos todos los álbumes de un usuario
    Retorno: Lista de álbumes del usuario especificado.
    */
    public List<Album> buscarAlbumesUsuario(Usuario user){
        return em.createQuery(
            "SELECT a FROM Album a WHERE a.usuarioiduser =:idUsuario")
            .setParameter("idUsuario", user)
            .getResultList();
    }
    
}
