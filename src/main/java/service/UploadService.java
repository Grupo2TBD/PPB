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
    FotografiaEJBLocal upload;
    
    
    @GET
    public void uploadService() {
        upload.uploadPhoto("23/06/1994", "En la playa", "Descripciónde la foto", "png",0,18,16);
        
    }
    
}