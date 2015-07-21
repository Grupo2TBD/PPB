/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.AlbumEJBLocal;
import ejb.UploadEJBLocal;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author ian
 */
@Path("/upload")
public class UploadService {
    @EJB
    UploadEJBLocal uploadEJB;
    
    @EJB
    AlbumEJBLocal albumEJB;
    
   @GET
    public void uploadService(){
        uploadEJB.insertPhotoInfo(1, 1, "Title", "Descripcion y weas locas :D","png");
    }
    
}