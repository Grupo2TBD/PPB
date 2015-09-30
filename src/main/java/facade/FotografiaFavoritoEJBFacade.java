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
import model.FavoritoFotografia;

/**
 *
 * @author sebastian
 */
@Stateless
public class FotografiaFavoritoEJBFacade extends AbstractFacade<FavoritoFotografia>{

        @PersistenceContext(unitName = "ian")
	private EntityManager em;
    public FotografiaFavoritoEJBFacade() {
        super(FavoritoFotografia.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public FavoritoFotografia buscarFavorito(int idFotografia, int idUser){
    
        return (FavoritoFotografia) em.createQuery(
                "SELECT f FROM FavoritoFotografia f WHERE f.favoritoFotografiaPK.usuarioiduser = :idUser AND f.favoritoFotografiaPK.fotografiaidphoto = :idPhoto")
            .setParameter("idUser", idUser)
            .setParameter("idPhoto", idFotografia)
            .getSingleResult();
    }
}
