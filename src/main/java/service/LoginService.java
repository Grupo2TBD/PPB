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
import javax.ws.rs.Produces;

/**
 *
 * @author ian
 */
@Path("/login")
//@ApplicationPath("/")
public class LoginService {
    @EJB
    UsuarioEJBLocal userEJB;
    
    
    @GET//@POST
    @Produces({"application/json"})
    public String registro(){
        return userEJB.Login("ian@tbd.cl","pass");
    }
    
    
    
}
