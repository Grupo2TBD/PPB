/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import ejb.AlbumEJBLocal;
import ejb.CamaraEJBLocal;
import ejb.ExifEJBLocal;
import ejb.FotografiaEJBLocal;
import ejb.TagEJBLocal;
import ejb.UsuarioEJBLocal;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import model.Camara;
/**
 *
 * @author ian
 */
@Path("/prueba")
//@ApplicationPath("/")
public class prueba {
    @EJB
    AlbumEJBLocal albumEJB;
           
    @EJB
    FotografiaEJBLocal fotografiaEJB;
    
    @EJB
    ExifEJBLocal exifEJB;
    
    @EJB
    TagEJBLocal tagEJB;
    
    @EJB
    UsuarioEJBLocal usuarioEJB;
    
    @EJB
    CamaraEJBLocal camaraEJB;
    
    
    //CU1
    @GET
    @Path("/registro")
    @Produces({"application/json"})
    public String creaUsuario(){
        String mail="ian@tbd.cl";
        String name="Ian";
        String lastname="Orellana";
        String pass="pass";
        String date="23/06/1994";
        String sex="m";
        String alias="gatoconbotas";
        return usuarioEJB.Registro(mail,name,lastname,pass,date,sex,alias);
    
    }
    
    //CU2
    @GET
    @Path("/editarPerfil")
    @Produces({"application/json"})
    public String editProfileData() {
        int idUsuario=4;
        String name="ian";
        String lastname="Orellana";
        String pass="contraseña";
        String sex="m";
        String alias="rojo";
        return usuarioEJB.editarPerfil(idUsuario,name,lastname,pass,sex,alias);
        
    }
    
    //CU4--------->Arreglar ruta
    @GET
    @Path("/insertaAlbum")
    public void crearAlbum(){
        int idUsuario=3;
        String nombre="Vacaciones de Sac";
        String descripcion="Y asi termine en el hospital";
        int privacidad=0;
        int permisos=1;
        String fotoPortada="direccion";
        albumEJB.crearAlbum(idUsuario,nombre,descripcion,privacidad,permisos,fotoPortada);
    }
    
    //CU5
    @GET
    @Path("/editarAlbum")
    public void editarAlbum(){
        
        int idAlbum=1;
        String nombre="album editado";
        String descripcion="descripcion";
        int idPrivacidad=1;
        int idPermiso=2;
        albumEJB.editarAlbum(idAlbum,nombre,descripcion,idPrivacidad,idPermiso);
    }
    
    
    //CU17---------------------->Pensar lo del formato----Añadir detalles
    @GET
    @Path("/subirFoto")
    public void uploadService() {
       String dateFrom="23/06/1994";
       String title="titulo";
       String descripcion="playa";
       String format="png";
       int idPrivacidad=0;
       int idUsuario=4;
       int idPermiso=0;
       
       Camara camara=new Camara();
       String nombreCamara="XT1000";
       int MgPx=10;
       int zoom=19;
       int pantalla=12;
       String tipoGuardado="tipo";////----->WTF?
       String marca="Samsung";
       int peso=12;
       camara=camaraEJB.insertCamera(nombreCamara, MgPx, zoom, pantalla,tipoGuardado, marca, peso);
       fotografiaEJB.uploadPhoto(dateFrom,title,descripcion,format,idPrivacidad,idUsuario,idPermiso);
        
    }
    
    
    //CU18
    @GET
    @Path("/editarFoto")
    public void editPhotoService() {
        int idPhoto=1;
        int idPrivacidad=1;
        int idPermiso=2;
        String titulo="Titulo editado";
        String descripcion="Descripcion editada";
        fotografiaEJB.editPhoto(idPhoto, idPrivacidad, idPermiso, titulo, descripcion);
        
    }
    
    
    //CU19
    @GET
    @Path("/login")
    @Produces({"application/json"})
    public String login(){
        String mail="ian@tbd.cl";
        String pass="pass";
        return usuarioEJB.Login(mail, pass);
    
    }
    
}
