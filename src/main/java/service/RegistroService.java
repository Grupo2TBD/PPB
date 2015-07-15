/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

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
@Path("/registro")
//@ApplicationPath("/")
public class RegistroService {
    @EJB
    RegistroEJBLocal registroEJB;
    
    
    @GET
    @Produces({"application/json"})
    public List<Usuario> registro(){
        return registroEJB.get();
    }
    
    @GET
    @Path("/new")
    @Produces({"application/json"})
    public String stro(){
        
        return registroEJB.Registro("akjfosj@sacql.cl","name", "lastname", "pass","23/06/1994","f","yo");
    }
    
}