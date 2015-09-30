/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.FotografiaEJBLocal;
import facade.FotografiasEJBFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import model.Fotografia;
import model.Usuario;

/**
 *
 * @author ian
 */
@Path("/fotoService")
public class FotoService {
    @EJB
    FotografiaEJBLocal foto;
    
    @EJB
    FotografiasEJBFacade fotoFacade;
    
    @GET
    @Path("/getAll")
    @Produces({"application/json"})
    public List<Fotografia> getAll(){
        return this.fotoFacade.findAll();
    }
    
    @GET
    @Path("/getById/{Id}")
    @Produces({"application/json"})
    public List<Fotografia> getByAlias(@PathParam("Id")int idUsuario){
        
        return foto.mostrarFotografiasUsuario(idUsuario);
    }
}
