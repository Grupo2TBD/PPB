/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.LoginEJBLocal;
import ejb.RegistroEJBLocal;
import java.util.List;
import model.Usuario;
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
    LoginEJBLocal loginEJB;
    
    
    @GET//@POST
    @Produces({"application/json"})
    public String registro(){
        return loginEJB.Login("akjfosj@sacql.cl","pass");
    }
    
    
    
}
