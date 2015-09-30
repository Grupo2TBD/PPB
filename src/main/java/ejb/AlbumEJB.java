/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import facade.AlbumEJBFacade;
import facade.AlbumFavoritoEJBFacade;
import facade.AlbumFotografiaEJBFacade;
import facade.FotografiasEJBFacade;
import facade.PermisoAlbumEJBFacade;
import facade.PrivacidadEJBFacade;
import facade.UsuarioEJBFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.Album;
import model.AlbumFotografia;
import model.AlbumFotografiaPK;
import model.FavoritoAlbum;
import model.PermisoAlbum;
import model.Privacidad;
import model.Usuario;
import model.FavoritoAlbumPK;
import model.Fotografia;
/**
 *
 * @author sebastian
 */
@Stateless
public class AlbumEJB implements AlbumEJBLocal {
    
    @EJB
    AlbumEJBFacade albumFacade;
        
    @EJB
    RecurrentesEJBLocal recurrentesEJB;
    
    @EJB
    UsuarioEJBFacade usuarioFacade;
    
    @EJB
    PermisoAlbumEJBFacade permisoAlbumFacade;
    
    @EJB
    PrivacidadEJBFacade privacidadFacade;
     
    @EJB
    AlbumFavoritoEJBFacade albumFavoritoFacade;
    
    @EJB
    AlbumFotografiaEJBFacade albumFotografiaFacade;
    
    @EJB
    FotografiasEJBFacade fotografiaFacade;
    
    //ian
    @Override
    public void buscaAlbum(Usuario user,Fotografia photo){
        
        AlbumFotografia AF = new AlbumFotografia();
        AlbumFotografiaPK AFK = new AlbumFotografiaPK();
        List<Album> list = this.albumFacade.findAll();
        int largo=list.size();
        int contador=0;
        while(largo!=0){
            if(user.getIdUser()==list.get(contador).getUsuarioiduser().getIdUser() && "Fotografías".equals(list.get(contador).getNombreAlbum())){
                AF.setFotografia(photo);
                AF.setAlbum(list.get(contador));
                AF.setAlbumFotografiaPK(AFK);
                AF.setFechaAgregadoAlbum(recurrentesEJB.fechaActual());
                this.albumFotografiaFacade.create(AF);
            }
            contador++;
            largo--;
        }
    }
    
    //ian
    @Override
    public void insertaAlbumDefault(Usuario user){
       Album album=new Album();
       RecurrentesEJB fecha=new RecurrentesEJB();
       PermisoAlbum permisoAlbum = this.permisoAlbumFacade.find(1);
       Privacidad privacidad = this.privacidadFacade.find(1);
       album.setUsuarioiduser(user);
       album.setNombreAlbum("Fotografias");
       album.setFechaCreacionAlbum(fecha.fechaActual());
       album.setDescripcionAlbum("Fotografias de "+user.getNombreRealUser());
       album.setDireccionFotoPortadaAlbum(GlobalVariables.photoPath+GlobalVariables.defaultAlbumFrontPhoto);
       album.setCantidadFotografiasAlbum(0);
       album.setCantidadFavoritos(0);
       album.setUltimaActualizacionAlbum(fecha.fechaActual());
       album.setPermisoAlbumidpermisoalbum(permisoAlbum);
       album.setPrivacidadidprivacidad(privacidad);
       this.albumFacade.create(album);
   }
    
    /*
    Parámetros: Album a agregar a favorito, y el usuario que lo agrega
    Función encargada de registrar los albumes agregados a favorito por el usuario. 
    Retorno: String indicando que la acción fue realizada. 
    */
    @Override
    public String agregarFavorito(Album album, Usuario user){
    
        FavoritoAlbumPK favoritoAlbum1 = new FavoritoAlbumPK();
        favoritoAlbum1.setAlbumidalbum(album.getIdAlbum());
        favoritoAlbum1.setUsuarioiduser(user.getIdUser());
        
        FavoritoAlbum favAlbum = new FavoritoAlbum();
        favAlbum.setFavoritoAlbumPK(favoritoAlbum1);
        favAlbum.setAlbum(album);
        favAlbum.setUsuario(user);
        favAlbum.setFechaFavoritoAlbum(recurrentesEJB.fechaActual());
        
        albumFavoritoFacade.create(favAlbum);
        
        return "Album Agregado a Favorito";
    }
    
    /*
    Parámetros: id del album y usuario 
    Función encargada de eliminar el album agregado a favoritos por un usuario
    Retorno: String indicando que la acción fue realizada. 
    */
    
    @Override
    public String eliminarFavorito(int idAlbum, int idUsuario){
            
        FavoritoAlbum favDelete = albumFavoritoFacade.buscarFavorito(idAlbum, idUsuario);
        albumFavoritoFacade.remove(favDelete);
        
        return "Album Eliminado de favorito";
    }
    
    
    
    /*
    Parámetros: Album a comentar, usuario realizador, y el comentario
    Función encargada almacenar el comentario realizado por un usuario en el album respectivo
    Retorno: String indicando que la acción fue realizada. 
    */
    /*
    @Override
    public String comentarAlbum(Album albumAComentar, Usuario usuarioRealizador, String descripcion){
    
         ComentarioAlbum comentario = new ComentarioAlbum();
         comentario.setIdComentarioAlbum(1);
         comentario.setIdUser(usuarioRealizador);
         comentario.setIdAlbum(albumAComentar);
         comentario.setDescripcionComentarioAlbum(descripcion);
         comentario.setFechaPublicacionComentarioAlbum(recurrentesEJB.fechaActual());
         comentarioAlbumFacade.create(comentario);
         return "Comentario Realizado Album";
    }
    */
    
    /*
    Parámetros: id comentario a eliminar
    Función encargada de eliminar un comentario realizado a un álbum
    Retorno: String indicando que la acción fue realizada. 
    */
    /*
    @Override
    public String eliminarComentario(int idComentarioAlbum){
        Object idComentario = idComentarioAlbum;
        ComentarioAlbum comentario = comentarioAlbumFacade.find(idComentario);
        comentarioAlbumFacade.remove(comentario);
        
        return "Comentario Eliminado";
    }
    */
    
    
    /*
    Parámetros: usuario creador del álbum, nombre del álbum, descripción álbum, id privacidad álbum, id permisos álbum, dirección fotografía portada
    Función encargada de crear un álbum dentro del sistema, y asociarla al usuario creador.
    Retorno: Cadena de caracteres indicando que la acción fue realizada con éxito. 
    */
    @Override
    public String crearAlbum(int idUsuario,String nombre, String descripcion, int privacidad, int permisos, String fotografiaPortada){
         
       Album album = new Album();
  
       //Asociando el album con el usuario creador
       Object idusuario =idUsuario;
       Usuario user = this.usuarioFacade.find(idusuario);
       album.setUsuarioiduser(user);
       
       album.setNombreAlbum(nombre);
       album.setDescripcionAlbum(descripcion);
 
      //Asociar el album con su privacidad
       Object idprivacidad = privacidad;
       Privacidad priv = this.privacidadFacade.find(idprivacidad);
       album.setPrivacidadidprivacidad(priv);
       
       //Asociar el album con sus permisos
       Object idpermiso = permisos;
       PermisoAlbum permisoAlbum = this.permisoAlbumFacade.find(idpermiso);
       album.setPermisoAlbumidpermisoalbum(permisoAlbum);
       
       album.setDireccionFotoPortadaAlbum(fotografiaPortada);
       
       //Informacion ingresada por nosotros
       album.setFechaCreacionAlbum(recurrentesEJB.fechaActual());
       album.setUltimaActualizacionAlbum(recurrentesEJB.fechaActual());
       
       album.setCantidadFotografiasAlbum(0);
       album.setCantidadFavoritos(0);
       album.setAlbumFotografiaCollection(null);
       album.setFavoritoAlbumCollection(null);

       this.albumFacade.create(album);

       return "Album creado";
              
   }
    /*
    Parámetros: id del álbum a editar, nuevo nombre, nueva descripción, nueva privacidad, nuevos permisos.
    Función encargada de editar la información de un álbum.
    Retorno: Cadena de caracteres que indica que la acción fue realizada con éxito.
    */
    //Si hay un parámetro que el usuario no haya cambiado, angular debe devolver el que estaba.
    @Override
    public String editarAlbum(int idAlbum, String nombre, String descripcion, int idPrivacidad, int idPermisos){
    
        //Buscando el álbum en el sistema
        Object id = idAlbum;
        Album album = this.albumFacade.find(id);
        
        //Editando información del álbum
        album.setNombreAlbum(nombre);
        album.setDescripcionAlbum(descripcion);
        
        //Asociar el album con su privacidad
        Object idprivacidad = idPrivacidad;
        Privacidad priv = this.privacidadFacade.find(idprivacidad);
        album.setPrivacidadidprivacidad(priv);
       
        //Asociar el album con sus permisos
        Object idpermiso = idPermisos;
        PermisoAlbum permisoAlbum = this.permisoAlbumFacade.find(idpermiso);
        album.setPermisoAlbumidpermisoalbum(permisoAlbum);
        
        album.setUltimaActualizacionAlbum(this.recurrentesEJB.fechaActual());
        
        this.albumFacade.edit(album);
 
        return "Album Editado";
    }
    /*Parámetros: id del álbum, y la dirección donde se guardará la fotografía de portada.
    Función encargada de editar la fotografía de portada del álbum
    Retorno: Cadena de caracteres indicando que la acción se realizó con éxito.
    */
    @Override
    public String editarAlbumFotoPortada(int idAlbum,String fotografiaPortada){
        
        //Buscando el álbum en el sistema
        Object id = idAlbum;
        Album album = this.albumFacade.find(id);
        
        album.setDireccionFotoPortadaAlbum(fotografiaPortada);
        album.setUltimaActualizacionAlbum(this.recurrentesEJB.fechaActual());
        
        this.albumFacade.edit(album);
    
        return "Fotografia de portada editada";
    }
    
    /*Parámetros: id del álbum
    Función encargada de eliminar un álbum dentro del sistema
    Retorno: Cadena de caracteres indicando que el álbum fue eliminado con éxito. 
    */
    @Override
    public String eliminarAlbum(int idAlbum){
        
        Object id = idAlbum;
        Album album = this.albumFacade.find(id);
        
        this.albumFacade.remove(album);
        return "Album eliminado";
    }
    
    /*Parámetros: id álbum
    Función encargada de mostrar un álbum
    Retorno: El álbum que se desea mostrar
    */
    @Override
    public Album mostrarAlbum(int idAlbum){
        
        Object idalbum = idAlbum;
        return  this.albumFacade.find(idalbum); 
    }
    
    /*Parámetros: id del usuario.
    Función encarga de mostrar todos los álbumes de un usuario.
    Retorno: Los álbumes del usuario especificado. 
    */
    @Override
    public List<Album> mostrarAlbumesUsuario(int idUsuario){
        Object idUser = idUsuario;
        Usuario user = this.usuarioFacade.find(idUser);
        return this.albumFacade.buscarAlbumesUsuario(user);
        
    }
    
    
     @Override
    public String addFotoAlbum(Fotografia foto, Album album){
   
        AlbumFotografiaPK albumFoto = new AlbumFotografiaPK();
        AlbumFotografia fotoadd = new AlbumFotografia();
        albumFoto.setAlbumidalbum(album.getIdAlbum());
        albumFoto.setFotografiaidphoto(foto.getIdPhoto());
       
       
        Fotografia fotox = this.fotografiaFacade.find(foto.getIdPhoto());
        Album albumx = this.albumFacade.find(album.getIdAlbum());
       
        fotoadd.setAlbumFotografiaPK(albumFoto);
        fotoadd.setAlbum(albumx);
        fotoadd.setFotografia(fotox);
        fotoadd.setFechaAgregadoAlbum(recurrentesEJB.fechaActual());
       
        this.albumFotografiaFacade.create(fotoadd);
       
        return "Foto agregada a album";
    }
   
    @Override
    public String deleteFotoAlbum(Fotografia foto, Album album){
   
        AlbumFotografia af = this.albumFotografiaFacade.buscarFotoAlbum(foto.getIdPhoto(), album.getIdAlbum());
        this.albumFotografiaFacade.remove(af);
       
       
        return "Foto eliminada del album";
    }
    
    @Override
    public List<Fotografia> buscarFotoAlbum(int idAlbum){
        Album album = this.albumFacade.find(idAlbum);
        List<AlbumFotografia> albumFoto = this.albumFotografiaFacade.buscarFotosAlbum(album.getIdAlbum());
        List<Fotografia> fotosAlbum = new ArrayList();
        int total = albumFoto.size();
        int contador = 0;
        while( total!= 0 ){      
            fotosAlbum.add(this.fotografiaFacade.find(albumFoto.get(contador).getAlbumFotografiaPK().getFotografiaidphoto()));
            contador ++;
            total--;
        }
        return fotosAlbum;
    }
}
