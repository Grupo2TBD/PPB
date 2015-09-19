/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import facade.AlbumEJBFacade;
import facade.AlbumFotografiaEJBFacade;
import facade.PermisoAlbumEJBFacade;
import facade.PrivacidadEJBFacade;
import facade.UsuarioEJBFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.Album;
import model.AlbumFotografia;
import model.AlbumFotografiaPK;
import model.Fotografia;
import model.PermisoAlbum;
import model.Privacidad;
import model.Usuario;



/**
 *
 * @author ian
 */
@Stateless
public class AlbumEJB implements AlbumEJBLocal{
    
    @EJB
    AlbumEJBFacade albumFacade;
    
    @EJB
    UsuarioEJBFacade userFacade;
    
    @EJB
    RecurrentesEJBLocal fecha;
    
    @EJB
    AlbumFotografiaEJBFacade albumFotografiaEJBFacade;
    
    @EJB
    PermisoAlbumEJBFacade permisoAlbumFacade;
    
    @EJB
    PrivacidadEJBFacade privacidadFacade;
    
   @Override
   public void insertaAlbumDefault(Usuario user){
       Album album=new Album();
       RecurrentesEJB fecha=new RecurrentesEJB();
       PermisoAlbum permisoAlbum = this.permisoAlbumFacade.find(1);
       Privacidad privacidad = this.privacidadFacade.find(0);
       album.setIdPermisoAlbum(permisoAlbum);
       album.setIdPrivacidad(privacidad);
       album.setIdUser(user);
       album.setNombreAlbum("Fotografías");
       album.setFechacreacionAlbum(fecha.fechaActual());
       album.setDescripcionAlbum("Fotografías de "+user.getNombreRealUser());
       album.setDireccionFotoPortadaAlbum(GlobalVariables.photoPath+GlobalVariables.defaultAlbumFrontPhoto);
       album.setCantidadFotografiasAlbum(0);
       album.setCantidadFavoritos(0);
       album.setCantidadComentarios(0);
       album.setUltimaActualizacionAlbum(fecha.fechaActual());
       this.albumFacade.create(album);
   }
    
    @Override
    public void buscaAlbum(Usuario user,Fotografia photo){
        
        AlbumFotografia AF = new AlbumFotografia();
        AlbumFotografiaPK AFK = new AlbumFotografiaPK();
        List<Album> list = this.albumFacade.findAll();
        int largo=list.size();
        int contador=0;
        while(largo!=0){
            if(user.getIdUser()==list.get(contador).getIdUser().getIdUser() && "Fotografías".equals(list.get(contador).getNombreAlbum())){
                AFK.setIdAlbum(list.get(contador).getIdAlbum());
                AFK.setIdPhoto(photo.getIdPhoto());
                AF.setFotografia(photo);
                AF.setAlbum(list.get(contador));
                AF.setAlbumFotografiaPK(AFK);
                AF.setFechaAgregadoAlbum(fecha.fechaActual());
                this.albumFotografiaEJBFacade.create(AF);
            }
            contador++;
            largo--;
        }
    }
   
   @Override
   public void crearAlbum(int idUsuario,String nombre, String descripcion, int privacidad, int permisos, String fotografiaPortada){
         
       Album album = new Album();
       //Asociando el album con el usuario creador
       Usuario user = this.userFacade.find(idUsuario);
       album.setIdUser(user);
       album.setNombreAlbum(nombre);
       album.setDescripcionAlbum(descripcion);
       //Asociar el album con su privacidad
       album.setIdPrivacidad(this.privacidadFacade.find(privacidad));
       //Asociar el album con sus permisos
       album.setIdPermisoAlbum(this.permisoAlbumFacade.find(permisos));
       album.setDireccionFotoPortadaAlbum(fotografiaPortada);
       //Informacion ingresada por nosotros
       album.setFechacreacionAlbum(fecha.fechaActual());
       album.setUltimaActualizacionAlbum(fecha.fechaActual());
       album.setCantidadFotografiasAlbum(0);
       album.setCantidadFavoritos(0);
       album.setCantidadComentarios(0);

       this.albumFacade.create(album);

              
   }
   
   @Override
    public void editarAlbum(int idAlbum, String nombre, String descripcion, int idPrivacidad, int idPermiso){
    
        //Buscando el álbum en el sistema
        Album album = this.albumFacade.find(idAlbum);
        
        //Editando información del álbum
        album.setNombreAlbum(nombre);
        album.setDescripcionAlbum(descripcion);
        
        //Asociar el album con su privacidad
        album.setIdPrivacidad(this.privacidadFacade.find(idPrivacidad));
       
        //Asociar el album con sus permisos
        album.setIdPermisoAlbum(this.permisoAlbumFacade.find(idPermiso));
        
        album.setUltimaActualizacionAlbum(fecha.fechaActual());
        
        this.albumFacade.edit(album);
 
    }
}
