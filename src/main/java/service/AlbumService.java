/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.AlbumEJBLocal;
import ejb.GlobalVariables;
import ejb.PermisoAlbumEJBLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import model.Album;
import model.Fotografia;
import model.PermisoAlbum;
import model.Privacidad;
import model.Usuario;

/**
 *
 * @author sebastian
 */
@Path("/album")
public class AlbumService {
    
    @EJB
    AlbumEJBLocal albumEJB;
    
    
    
    //WORKK
    @Path("/agregarFavorito")
    @GET
    @Produces({"application/json"})
    public String agregarFavorito(){
        
        Album album = new Album();
        Usuario user = new Usuario();
        album.setIdAlbum(3);
        user.setIdUser(1);
        return albumEJB.agregarFavorito(album, user);
    }
    
    //WORKK
    @Path("/eliminarFavorito")
    @GET
    @Produces({"application/json"})
    public String eliminarFavorito(){
        
        return albumEJB.eliminarFavorito(3, 1);
    }
    
    /*
    //Funcionando ! :)
    @Path("/realizarComentario")
    @GET
    @Produces({"application/json"})
    public String realizarComentario(){
        
        Album album = new Album();
        Usuario user = new Usuario();
        album.setIdAlbum(3);
        user.setIdUser(4);
        return albumEJB.comentarAlbum(album, user, "Testing comentario album");
    }
    
    //Funcionando ! :)
    @Path("/eliminarComentario")
    @GET
    @Produces({"application/json"})
    public String eliminarComentario(){
        
        return albumEJB.eliminarComentario(1);
    }
    */
    
    
    //WORKK
    @Path("/crear")
    @GET//@POST
    @Produces({"application/json"})
    public String crearAlbum(){
        int idUsuario=1;
        String nombre = "ZACK ALBUM 2 ";
        String descripcion = "The best album ever";
        int privacidad = 1;
        int permisos = 1;
        String fotografiaPortada = GlobalVariables.defaultAlbumFrontPhoto;
       
        return albumEJB.crearAlbum(idUsuario, nombre, descripcion, privacidad, permisos, fotografiaPortada);
        
    }
    
    //WORKK
    @GET
    @Path("/getAlbum/{id}")
    @Produces({"application/json"})
    public Album getAlbum(@PathParam("id")int idAlbum){
        
        return albumEJB.mostrarAlbum(idAlbum);
        
    }
    
    
    
    //WORKK
    @Path("/editarAlbum")
    @GET//@POST
    @Produces({"application/json"})
    public String editarAlbum(){
        int idAlbum=2;
        String nombre = "ALBUM ZACK EDITADO";
        String descripcion = "The worst album ever 2";
        int privacidad = 1;
        int permisos = 2;
       
        return albumEJB.editarAlbum(idAlbum, nombre, descripcion, privacidad, permisos);
        
    }
    
    //WORKK
    @Path("/editarAlbumFoto")
    @GET//@POST
    @Produces({"application/json"})
    public String editarFotoPortada(){
        int idAlbum = 1;
        String fotoPortada = "PORTADA EDITADA ZACK";
        return albumEJB.editarAlbumFotoPortada(idAlbum, fotoPortada);
        
    }
    
    //TRIGGER PARA ELIMINAR LAS FOTOS ASOCIADAS A ESTE EN LA TABLA ALBUM_FOTOGRAFIA
    //WORKK
    @Path("/eliminarAlbum")
    @GET//@POST
    @Produces({"application/json"})
    public String eliminarAlbum(){
        int idAlbum = 2;
       
        return albumEJB.eliminarAlbum(idAlbum);
        
    }
    
    //WORKK
    @GET
    @Path("/getById/{id}")
    @Produces({"application/json"})
    public List<Album> getByAlias(@PathParam("id")int idUsuario){
        return albumEJB.mostrarAlbumesUsuario(idUsuario);
        
    }
    
      //WORKK
    @GET
    @Path("/addFotoAlbum")
    @Produces({"application/json"})
    public String addFotoAlbum(){
        Fotografia foto = new Fotografia();
        Album album = new Album();
        foto.setIdPhoto(18);
        album.setIdAlbum(6);
        return albumEJB.addFotoAlbum(foto, album);
    }
   
    //WORKK
    @GET
    @Path("/deleteFotoAlbum")
    @Produces({"application/json"})
    public String deleteFotoAlbum(){
        Fotografia foto = new Fotografia();
        Album album = new Album();
        foto.setIdPhoto(3);
        album.setIdAlbum(3);
        return albumEJB.deleteFotoAlbum(foto, album);
    }
    
    @GET
    @Path("/getByIdAlbum/{id}")
    @Produces({"application/json"})
    public List<Fotografia> getFotoByAlbum(@PathParam("id")int idAlbum){
        
        return albumEJB.buscarFotoAlbum(idAlbum);
     
    }
}
