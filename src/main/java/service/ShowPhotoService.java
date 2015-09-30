/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.FotografiaEJBLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import model.Camara;
import model.Fotografia;
import model.PermisoFotografia;
import model.Privacidad;
import model.Tag;
import model.Usuario;

/**
 *
 * @author ian
 */
@Path("/Photo")
public class ShowPhotoService {
    
    @EJB
    FotografiaEJBLocal fotografiaEJB;
    
    //WORKK
    @GET
    @Path("/subirFoto")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public String uploadService(){
        Usuario user = new Usuario();
        user.setIdUser(1);
        Privacidad privacidad = new Privacidad();
        privacidad.setIdPrivacidad(1);
        PermisoFotografia permisoFoto = new PermisoFotografia();
        permisoFoto.setIdPermisoFotografia(1);
        Camara camara = new Camara();
        camara.setIdCamara(1);
        String titulo = "ZACK PHOTO";
        String descripcion = "Test descripcion";
        List<Tag> tags = new ArrayList();
        Tag tag1 = new Tag();
        tag1.setNombreTag("Tag 234");
        Tag tag2 = new Tag();
        tag2.setNombreTag("Tag 7324");
        tags.add(tag1);
        tags.add(tag2);
        
        List<Usuario> etiqueta = new ArrayList();
        
        return fotografiaEJB.insertPhotoInfo(user, camara, privacidad, permisoFoto, tags, titulo, descripcion);
    }
    
    
    //WORKK
    //ian
    @GET
    @Path("/showAll")
    @Produces({"application/json"})
    public List<Fotografia> showAllPhotos(){
        return fotografiaEJB.findAll();
    }
    
    //WORKK
    @GET
    @Path("edit")
    @Produces({"application/json"})
    public String editarFoto() {
        fotografiaEJB.editPhoto(1, 1, 1, "NUEVO TITULO ZACK", "DESCRIPCION ZACK");
        return "FOTO EDITADA";
    }
    
    
    //WORKK
    @GET
    @Path("/eliminarFavorito")
    @Produces({"application/json"})
    public String eliminarFavorito(){
        int idFotografia =  1;
        
        int idUser = 1;
        
      
        return fotografiaEJB.eliminarFavorito(idFotografia, idUser);
    }
    
    //WORKK
    @GET
    @Path("/agregarFavorito")
    @Produces({"application/json"})
    public String agregarFavorito(){
        int idFotografia =  1;
        int idUser = 1;
        Fotografia foto = new Fotografia();
        foto.setIdPhoto(idFotografia);
        Usuario user = new Usuario();
        user.setIdUser(idUser);
        return fotografiaEJB.agregarFavorito(foto, user);
    }
    
    //WORKK
    @GET
    @Path("/realizarComentario")
    @Produces({"application/json"})
    public String realizarComentario(){
        int idFotografia =  4;
        int idUser = 1;
        Fotografia foto = new Fotografia();
        foto.setIdPhoto(idFotografia);
        Usuario user = new Usuario();
        user.setIdUser(idUser);
        String comentario = "Testing comment ZACK";
        /*Debes arreglar los parámetros de esta función solamente el resto de la función está bien*/ 
        return fotografiaEJB.realizarComentario(foto, user, comentario);
    }
    
    
    //WORKK
    @GET
    @Path("/eliminarComentario")
    @Produces({"application/json"})
    public String eliminarComentario(){
        int idComentario =  1;    
        return fotografiaEJB.eliminarComentario(idComentario);
    }
     
    
    //ian
    @GET
    @Path("/Download")
    @Produces("image/png")
    public Response download(){
        String id="oli.png";
        return fotografiaEJB.DownloadPhoto(id);
    }
    
    //WORKK
    @GET
    @Path("/mostrar")
    @Produces({"application/json"})
    public Fotografia mostrarFotografia(){
        int idFotografia = 17;
        return fotografiaEJB.mostrarFotografiaSeleccionada(idFotografia);
    }
    
    //WORKK
    @GET
    @Path("/mostrarTodas")
    @Produces({"application/json"})
    public List<Fotografia> mostrarFotografiasUsuario(){
        int idUser =  1;
        return fotografiaEJB.mostrarFotografiasUsuario(idUser);
    }
    
    //WORKK
    //Ordenada ascendentemente por fecha
    @GET
    @Path("/mostrarOrdenadasFS")
    @Produces({"application/json"})
    public List<Fotografia> fotoUsuarioOrdenadaFS(){
        int idUser =  1;
        return fotografiaEJB.FotoUsuarioOrdenadasFS(idUser);
    }
    
    //WORKK
    //Ordenada ascendentemente por fecha
    @GET
    @Path("/mostrarOrdenadasFT")
    @Produces({"application/json"})
    public List<Fotografia> fotoUsuarioOrdenadaFT(){
        int idUser =  1;
        return fotografiaEJB.FotoUsuarioOrdenadasFT(idUser);
    }
    
}
