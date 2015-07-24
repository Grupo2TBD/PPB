/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import facade.PermisoAlbumEJBFacade;
import facade.PermisoFotografiaEJBFacade;
import facade.PrivacidadEJBFacade;
import facade.TagEJBFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.Tag;




/**
 *
 * @author ian
 */
@Stateless
public class UploadEJB implements UploadEJBLocal{
    
    @EJB
    FotografiaEJBLocal foto;
    
    @EJB
    UsuarioEJBLocal user;
    
    @EJB
    TagEJBLocal tag;
    
    @EJB
    PrivacidadEJBFacade privacyFacade;
    
    @EJB
    AlbumEJBLocal album;
    
    @EJB
    PermisoFotografiaEJBFacade permisoFoto;
    
    @EJB
    PermisoAlbumEJBFacade permisoAlbum;
    
    @Override
    public void uploadPhoto(String dateFrom, String title, String description, String format){
    
    }
}

