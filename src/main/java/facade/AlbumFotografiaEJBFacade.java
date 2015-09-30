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
import model.AlbumFotografia;

/**
 *
 * @author sebastian
 */
@Stateless
public class AlbumFotografiaEJBFacade extends AbstractFacade<AlbumFotografia>{

        @PersistenceContext(unitName = "ian")
	private EntityManager em;
    public AlbumFotografiaEJBFacade() {
        super(AlbumFotografia.class);
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<AlbumFotografia> buscarFotosAlbum(int idAlbum){
   
        List<AlbumFotografia> albumFoto = em.createNamedQuery("AlbumFotografia.findByAlbumidalbum")
                                         .setParameter("albumidalbum", idAlbum)
                                         .getResultList();
       
        return albumFoto;
    }
     public AlbumFotografia buscarFotoAlbum(int idFoto, int idAlbum){
        AlbumFotografia albumFoto = (AlbumFotografia) em.createQuery("SELECT a FROM AlbumFotografia a WHERE a.albumFotografiaPK.albumidalbum = :albumidalbum AND a.albumFotografiaPK.fotografiaidphoto = :fotografiaidphoto")
                                         .setParameter("albumidalbum", idAlbum)
                                         .setParameter("fotografiaidphoto",idFoto)
                                         .getSingleResult();
        return albumFoto;
    }
}
