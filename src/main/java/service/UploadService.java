/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.FotografiaEJBLocal;
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
    FotografiaEJBLocal fotografiaEJB;
    
    
    @GET
    public void uploadService() {
        fotografiaEJB.insertPhotoInfo("23/06/1994", "Título foto", "Descripciónde la foto", "png");
        
    }
    
}