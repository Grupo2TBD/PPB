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



/**
 *
 * @author ian
 */
@Stateless
public class AlbumEJB implements AlbumEJBLocal{
    
    @EJB
    AlbumEJBFacade albumFacade;
    
   @Override
   public void insertaAlbumDefault(){
       Album album=new Album();
       album.setIdAlbum(0);
       album.setNombreAlbum("Fotografias");
       this.albumFacade.create(album);
   }
    
    
}
