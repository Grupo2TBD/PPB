/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.UsuarioEJBLocal;
import facade.UsuarioEJBFacade;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import model.Fotografia;
import model.Seguidor;
import model.SeguidorPK;
import model.Usuario;

/**
 *
 * @author sebastian
 */
@Path("/usuario")
public class UsuarioService {
    @EJB
    UsuarioEJBLocal usuarioLocal;
    
    @EJB
    UsuarioEJBFacade usuarioFacade;
    
    //WORKK
    @GET
    @Path("/actualInfo")
    @Produces({"application/json"})
    public Usuario showActualInfo(Usuario user) {
        Usuario user1 = new Usuario();
        user1.setIdUser(1);
        return this.usuarioLocal.find(user1.getIdUser());
    }
    
    //WORK
    //@PUT
    @GET
    @Path("/newInfo")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public String insertNewInfo(Usuario user){
        JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
        //jsonObjBuilder.add( "message", usuarioLocal.editarPerfil(user) );
 
        JsonObject jsonObj = jsonObjBuilder.build();
        Usuario user1 = new Usuario();
        user1.setIdUser(1);
        user1.setNombreRealUser("Francisco");
        user1.setApellidoUser("Lira");
        user1.setPassUser("alfrepass");
        user1.setSexoUser("m");
        user1.setAliasUser("nemesisxD");
        if(usuarioLocal.editarPerfil(user1) == true){
            return "EDITADO";
        }
        else{
            return "logi";
        }
        //return Response.status( Response.Status.ACCEPTED).entity(jsonObj).build();
    }
    
 
    
    //WORKK
    //ian
    @GET
    @Path("/getByAlias/{alias}")
    @Produces({"application/json"})
    public Usuario getByAlias(@PathParam("alias")String nombre){
        
        return usuarioLocal.findByAlias(nombre);
    }
    
    //WORKK
    //ian
    @GET
    @Path("/getAll")
    @Produces({"application/json"})
    public List<Usuario> getAll(){
        return this.usuarioLocal.findAll();
    }
    
    //WORK
    //ian
    //@POST
    @GET
    @Path("/create")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response exist(Usuario user){
        
        JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
        jsonObjBuilder.add( "message", usuarioLocal.verifica(user) );
 
        JsonObject jsonObj = jsonObjBuilder.build();
        /*Usuario user1 = new Usuario();
        user1.setAliasUser("Romeo");
        user1.setEmailUser("acevedoc.sebastian@gmail.com");*/
        if(usuarioLocal.verifica(user)){
            usuarioLocal.insertData(user);
        }

        return Response.status( Response.Status.OK ).entity( jsonObj ).build();
    }
    
    
    //WORKK
    @Path("/seguir")
    @GET//@POST
    @Produces({"application/json"})
    public String seguir(){
        int idUsuario = 1;
        int idUsuarioSeguir = 3;
        return usuarioLocal.seguir(idUsuario, idUsuarioSeguir);   
    }
    
    //WORKK
    @Path("/dejarSeguir")
    @GET//@POST
    @Produces({"application/json"})
    public String dejarDeSeguir(){
        int idUsuario = 1;
        int idUsuarioSeguir = 2;
        return usuarioLocal.dejarDeSeguir(idUsuario, idUsuarioSeguir);
    }
    
    //WORKK
    @Path("/mostrarFotografiasSeguidores")
    @GET//@POST
    @Produces({"application/json"})
    public List<Fotografia> mostrarFotografiaSeguidores(){
        
        int idUsuario = 3;
        return usuarioLocal.mostrarFotografiaSeguidores(idUsuario);
    }
    
    //WORKK
    @Path("/mostrarFotografiasSeguidoresFecha")
    @GET//@POST
    @Produces({"application/json"})
    public List<Fotografia> mostrarFotografiaSeguidoresFecha() throws ParseException{
        
        int idUsuario = 3;
        String fecha = "2015-06-07";   
        return usuarioLocal.mostrarFotografiaSeguidoresFecha(idUsuario, fecha);
    }
    
    //WORKK
    @Path("/mostrarFotografiasSeguidoresNumero")
    @GET//@POST
    @Produces({"application/json"})
    public List<Fotografia> mostrarFotografiaSeguidoresNumero(){
        
        int idUsuario = 3;
        int numero = 1;   
        return usuarioLocal.mostrarFotografiaSeguidoresNumero(idUsuario, numero);
    }
    
    //WORKK
    @Path("/mostrarFotografiasSeguidoresNumeroFecha")
    @GET//@POST
    @Produces({"application/json"})
    public List<Fotografia> mostrarFotografiaSeguidoresNumeroFecha() throws ParseException{
        
        int idUsuario = 3;
        int numero = 3;   
        String fecha = "2016-07-22";   
        return usuarioLocal.mostrarFotografiaSeguidoresNumeroFecha(idUsuario, numero, fecha);
    }
    
    //WORKK
    @Path("/mostrarSeguidoresFecha")
    @GET//@POST
    @Produces({"application/json"})
    public List<Seguidor> mostrarSeguidoresFecha() throws ParseException{
        
        int idUsuario = 3;  
        String fecha = "2000-07-12";   
        return usuarioLocal.mostrarSeguidoresFecha(idUsuario, fecha);
    }
    
    //WORKK
    @Path("/mostrarSeguidores")
    @GET//@POST
    @Produces({"application/json"})
    public List<Seguidor> mostrarSeguidores(){
        
        int idUsuario = 3;     
        return usuarioLocal.mostrarSeguidores(idUsuario);
    }
    
    @Path("/allUsers")
    @GET
    @Produces({"application/json"})
    public List<Usuario> mostrarUsuarios(){
        return usuarioFacade.findAll();
    }
}
