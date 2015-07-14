/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.RegistroEJBLocal;
import java.io.File;
import java.util.List;
import model.Usuario;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 *
 * @author ian
 */
@Path("/reg")
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
    @Path("/istro")
    @Produces({"application/json"})
    public String stro(){
        
        return registroEJB.Registro("akjfosj@sacql.cl","name", "lastname", "pass","23/06/1994","f","yo");
    }
    
    @GET
    @Path("/get")
    @Produces("image/png")
    public Response getFile() {

            File file = new File("/home/ian/Escritorio/img/oli.png");

            ResponseBuilder response = Response.ok((Object) file);
            response.header("Content-Disposition",
                    "attachment; filename=image_from_server.png");
            return response.build();

    }
}
