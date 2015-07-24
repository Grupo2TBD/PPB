/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import facade.AlbumEJBFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.Album;
import model.PermisoAlbum;
import model.Privacidad;
import model.Usuario;



/**
 *
 * @author ian
 */
@Stateless
public class AlbumEJB implements AlbumEJBLocal{
    
    @EJB
    AlbumEJBFacade albumFacade;
    
   @Override
   public void insertaAlbumDefault(Usuario user){
       Album album=new Album();
       RecurrentesEJB fecha=new RecurrentesEJB();
       PermisoAlbum pa=new PermisoAlbum(1);
       Privacidad privacy=new Privacidad(0);
       album.setIdUser(user);
       album.setNombreAlbum("Fotografías");
       album.setFechacreacionAlbum(fecha.fechaActual());
       album.setDescripcionAlbum("Fotografías de "+user.getNombreRealUser());
       album.setDireccionFotoPortadaAlbum(GlobalVariables.photoPath+GlobalVariables.defaultAlbumFrontPhoto);
       album.setCantidadFotografiasAlbum(0);
       album.setCantidadFavoritos(0);
       album.setCantidadComentarios(0);
       album.setUltimaActualizacionAlbum(fecha.fechaActual());
       album.setIdPermisoAlbum(pa);
       album.setIdPrivacidad(privacy);
       this.albumFacade.create(album);
   }
    
    
   
   
}
