/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.FotografiaEJBLocal;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import model.Fotografia;
import model.Usuario;

/**
 *
 * @author ian
 */
@Path("/upload")
public class UploadService {
    @EJB
    FotografiaEJBLocal fotografiaEJB;
    
    /////////////Pensar en formato 
    @POST
    @Consumes({"application/json"})
    public void uploadService(Usuario user, Fotografia photo) {
         
        this.fotografiaEJB.uploadPhoto(user, photo);
    }
    
}