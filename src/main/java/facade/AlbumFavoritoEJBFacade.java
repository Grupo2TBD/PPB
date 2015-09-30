/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.FavoritoAlbum;
import model.FavoritoAlbumPK;

/**
 *
 * @author sebastian
 */
@Stateless
public class AlbumFavoritoEJBFacade extends AbstractFacade<FavoritoAlbum>{

        @PersistenceContext(unitName = "ian")
	private EntityManager em;
    public AlbumFavoritoEJBFacade() {
        super(FavoritoAlbum.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public FavoritoAlbum buscarFavorito(int idAlbum, int idUser){
    
        return (FavoritoAlbum) em.createQuery(
                "SELECT f FROM FavoritoAlbum f WHERE f.favoritoAlbumPK.albumidalbum = :idAlbum AND f.favoritoAlbumPK.usuarioiduser = :idUser")
            .setParameter("idAlbum", idAlbum)
            .setParameter("idUser", idUser)
            .getSingleResult();
    }
}
