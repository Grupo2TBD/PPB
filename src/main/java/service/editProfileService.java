/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.UsuarioEJBLocal;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author ian
 */
@Path("/editarPerfil")
public class editProfileService {
    @EJB
    UsuarioEJBLocal userEJB;
    
    
    @GET
    public void editProfileData() {
        userEJB.editarPerfil(13,"ian", "lastname", "pass","m","gatoconbotas");
        
    }
    
}