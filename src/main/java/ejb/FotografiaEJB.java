/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import facade.ComentarioFotoEJBFacade;
import facade.ExifEJBFacade;
import facade.FotografiaFavoritoEJBFacade;
import facade.FotografiasEJBFacade;
import facade.SeguirUsuarioEJBFacade;
import facade.TagEJBFacade;
import facade.TipoClasificacionEJBFacade;
import facade.UsuarioEJBFacade;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import model.Camara;
import model.Fotografia;
import model.Usuario;
import model.ComentarioFotografia;
import model.Exif;
import model.FavoritoFotografia;
import model.FavoritoFotografiaPK;
import model.PermisoFotografia;
import model.Privacidad;
import model.Seguidor;
import model.Tag;
import model.TipoClasificacion;
import weka.ClassifyComment;

/**
 *
 * @author sebastian
 */
@Stateless
public class FotografiaEJB implements FotografiaEJBLocal {
    
    @EJB
    FotografiasEJBFacade fotografiaFacade;
    
    @EJB
    UsuarioEJBFacade usuarioFacade;
    
    @EJB
    ComentarioFotoEJBFacade comentarioFotografiaFacade;
    
    @EJB
    RecurrentesEJBLocal recurrentesLocal;
    
    @EJB
    FotografiaFavoritoEJBFacade fotografiaFavoritoFacade;
    
    @EJB
    AlbumEJBLocal albumEJB;
    
    @EJB
    TagEJBFacade tagFacade;
    
    @EJB
    ExifEJBFacade exifFacade;
    
    @EJB
    TipoClasificacionEJBFacade tcFacade;
    
    @EJB
    SeguirUsuarioEJBFacade seguirFacade;
    
    /*Parámetros
    idFoto - (idUser) - idCamara* - idLocalizacion* - (idPrivacidad) - idTipoClasificacion* - (idPermisoFotografia) - (fecha-subida) - (titulo)
    - fechatomada* - (cantidadVisitas) - (Descripcion) - (CantidadFavoritosFoto) - formato foto - direccionFisicaFoto - (cantidadcompartido) 
    - (CantDescargas) - (CantidadComentariosPositivos) - (Cantcomentariosnegativos) - (CantcomentarioNuetros)
    
    Etiqueta
    */
   
    @Override
    public String insertPhotoInfo(Usuario user, Camara camara, Privacidad privacidad, PermisoFotografia permisoFoto, List<Tag> tags, String titulo, String descripcion){
        System.out.println("entra");
        Fotografia foto = new Fotografia();
        foto.setUsuarioiduser(user);
        foto.setPrivacidadidprivacidad(privacidad);
        foto.setPermisoFotografiaidpermisofotografia(permisoFoto);
        foto.setCamaraidcamara(camara);
        foto.setTituloPhoto(titulo);
        foto.setDescripcionPhoto(descripcion);
        foto.setFechaSubidaPhoto(recurrentesLocal.fechaActual());
        foto.setCantidadVisitasPhoto(0);
        foto.setCantidadFavoritosPhoto(0);
        foto.setCantidadDescargas(0);
        foto.setCantidadComentarios(0);
        foto.setCantidadComentariosPositivos(0);
        foto.setCantidadComentariosNegativos(0);
        foto.setCantidadComentariosNeutros(0);
        foto.setUltimaActualizacionPhoto(recurrentesLocal.fechaActual());
        foto.setTagCollection(verificarTag(tags));
        Exif exif = this.exifFacade.find(1);
        foto.setExifidexif(exif);
        TipoClasificacion tipoC = this.tcFacade.find(1);
        foto.setTipoClasificacionidtipoclasificacion(tipoC);
        //foto.setUsuarioCollection(etiqueta);
        this.fotografiaFacade.create(foto);
        setRutaFoto(foto);        
        return "Foto insertada";
    }
    
    @Override
    public void setRutaFoto(Fotografia foto){
        foto.setDireccionFisicaPhoto(GlobalVariables.photoPath+Integer.toString(foto.getIdPhoto())+"png");
        this.fotografiaFacade.edit(foto);
    }
    

    
    //Función para verificar si el tag existe en la base de datos o no 
 public List <Tag> verificarTag(List<Tag> tags){
        List<Tag> tag = new ArrayList();
        for (int i = 0; i < tags.size(); i++) {

            if(tagFacade.buscarNombre(tags.get(i).getNombreTag()) == null){
                tagFacade.create(tags.get(i));
                
                tag.add(tagFacade.buscarNombre(tags.get(i).getNombreTag()));
            }else{
                
                tag.add(this.tagFacade.buscarNombre(tags.get(i).getNombreTag()));
            }
            
        }
        return tag;
    }
    

    
    /*Parámetros: Fotografía a agregar a favorito y el usuario el cual lo agrega
    Función encargada de registrar las fotografias que un usuario a agregado a favorito
    Retorno: String indicando que la acción fue realizada
    */
    @Override
    public String agregarFavorito(Fotografia fotografia, Usuario usuario){
        
        FavoritoFotografiaPK favoritoPK1 = new FavoritoFotografiaPK();
        favoritoPK1.setFotografiaidphoto(fotografia.getIdPhoto());
        favoritoPK1.setUsuarioiduser(usuario.getIdUser());
        
        FavoritoFotografia favFotografia = new FavoritoFotografia();
        favFotografia.setFavoritoFotografiaPK(favoritoPK1);
        favFotografia.setFotografia(fotografia);
        favFotografia.setUsuario(usuario);
        favFotografia.setFechaFavorito(recurrentesLocal.fechaActual());
        
        fotografiaFavoritoFacade.create(favFotografia);
        return "Agregado a Favorito";
    }
    
    /*Parámetros: id de la fotografia a quitar de favoritos y el usuario el cual la desea eliminar
    Función encargada de quitar de favoritos una fotografia del usuario
    Retorno: String indicando que la acción fue realizada
    */
    @Override
    public String eliminarFavorito(int idFotografia, int idUsuario){
        
        FavoritoFotografia favDelte = fotografiaFavoritoFacade.buscarFavorito(idFotografia, idUsuario);
        fotografiaFavoritoFacade.remove(favDelte);
        return "Eliminado de favorito";
    }
    
    /*Parámetros: Fotografía a comentar, Usuario realizador del comentario, y el comentario
    Función encargada de almacenar el comentario realizado por un usuario en la fotografía
    Retorno: String indicando que la acción fue realizada
    */
    //EN LA BASE DE DATOS HAY QUE PONER AUTO INCREMENTAL EL ID DE LA TABLA COMENTARIOFOTOGRAFIA Y VER LO DE WEKA PARA LA CLASIFICACION
    @Override
    public String realizarComentario(Fotografia fotografiaAComentar, Usuario usuarioRealizador, String descripcion){
        
        try {
            /*Object idFoto = fotografiaAComentar;
            Object idUser = usuarioRealizador;
            Fotografia foto = fotografiaFacade.find(idFoto);
            Usuario user = usuarioFacade.find(idUser);*/
            int num;
            Fotografia foto = this.fotografiaFacade.find(fotografiaAComentar.getIdPhoto());
            ComentarioFotografia comentario = new ComentarioFotografia();
            
            comentario.setFotografiaidphoto(fotografiaAComentar);
            comentario.setUsuarioiduser(usuarioRealizador);
            comentario.setDescripcionComentario(descripcion);
            
            TipoClasificacion tc = new TipoClasificacion();
            
            
            ClassifyComment cc = new ClassifyComment();
            String clasif = cc.classify(descripcion);
            if(clasif.equals("Positivo")){
                num=1;
                fotografiaAComentar.setCantidadComentariosPositivos(foto.getCantidadComentariosPositivos()+1);
            }else if(clasif.equals("Negativo")){
                num=2;
                fotografiaAComentar.setCantidadComentariosNegativos(foto.getCantidadComentariosNegativos()+1);
            }else{
                num=3;
                fotografiaAComentar.setCantidadComentariosNeutros(foto.getCantidadComentariosNeutros()+1);
            
            }
            fotografiaAComentar.setCantidadComentarios(foto.getCantidadComentarios()+1);
            comentario.setFechaPublicacionComentario(recurrentesLocal.fechaActual());
            tc.setIdTipoClasificacion(num);
            comentario.setTipoClasificacionidtipoclasificacion(tc);
            comentarioFotografiaFacade.create(comentario);
            fotografiaFacade.edit(fotografiaAComentar);
            
            return "Comentario Realizado";
        } catch (Exception ex) {
            Logger.getLogger(FotografiaEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "nop";
    }
    
    /*Parámetros: id del comentario a eliminar
    Función encargada de eliminar un comentario efectuado en una fotografía
    Retorno: String indicando que la acción fue realizada
    */
    @Override
    public String eliminarComentario(int idComentario){
        Object idComment = idComentario;
        ComentarioFotografia comentario = comentarioFotografiaFacade.find(idComment);
        
        comentarioFotografiaFacade.remove(comentario);
    
        return "Comentario Eliminado";
    }
    
    
    /*Parámetros: id de la fotografía
    Función encargada de mostrar una fotografía seleccionada por el usuario
    Retorno: Fotografía seleccionada por el usuario
    */

    @Override
    public Fotografia mostrarFotografiaSeleccionada(int idFotografia){
        
        Object id = idFotografia;
        Fotografia foto = fotografiaFacade.find(id);
        
        //Actualizando la cantidad de visitas de la fotografía a mostrar
        foto.setCantidadVisitasPhoto(foto.getCantidadVisitasPhoto() + 1);
        fotografiaFacade.edit(foto);
       
        return foto;
       
    }
    
    /*Parámetros: id del usuario
    Función encargada de mostar todas las fotografías del usuario 
    Retorno: Todas las fotografías del usuario
    */
    @Override
    public List<Fotografia> mostrarFotografiasUsuario(int idUsuario){
        
        Object idUser = idUsuario;
        Usuario user = this.usuarioFacade.find(idUser);
        return fotografiaFacade.buscarFotografiasUsuario(user);
    
    }
    
    /*Parámetros: id del usuario
    Función encargada de mostar todas las fotografías del usuario ordenadas por fecha de subida
    Retorno: Todas las fotografías del usuario ordenadas por fecha de subida
    */
    //Fotografias ordenadas por fecha de subida
    @Override
    public List<Fotografia> FotoUsuarioOrdenadasFS(int idUsuario){
    
        Object idUser = idUsuario;
        Usuario user = this.usuarioFacade.find(idUser);
        return fotografiaFacade.FotoUsuarioOrdenadaFS(user);
    }
    
    /*Parámetros: id del usuario
    Función encargada de mostar todas las fotografías del usuario ordenadas por fecha de tomada
    Retorno: Todas las fotografías del usuario ordenadas por fecha de tomadas
    */
    //Fotografias ordenadas por la fecha en la cual fue tomada
    @Override
    public List<Fotografia> FotoUsuarioOrdenadasFT(int idUsuario){
        Object idUser = idUsuario;
        Usuario user = this.usuarioFacade.find(idUser);
        return fotografiaFacade.FotoUsuarioOrdenadaFT(user);
    }
    
    //ian
    @Override
    public Response DownloadPhoto(String id){
        File file = new File(GlobalVariables.photoPath+id);

        Response.ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition",
                "attachment; filename=image_from_server.png");
        return response.build();
    }
    
    //ian
    @Override
    public void editPhoto(int idPhoto, int idPrivacidad,int idPermiso, String titulo, String descripcion){
        Object id=idPhoto;
        Fotografia photo = this.fotografiaFacade.find(idPhoto);
        photo.setTituloPhoto(titulo);
        photo.setDescripcionPhoto(descripcion);
        photo.setUltimaActualizacionPhoto(recurrentesLocal.fechaActual());
        this.fotografiaFacade.edit(photo);
    }
    
    //ian
    @Override
    public List<Fotografia> findAll(){
        return this.fotografiaFacade.findAll();
    }
    
       @Override
    //Entrega fotografias de las personas a las que el usuario sigue
    public List<Fotografia> buscaFotosSeguidos(Usuario user){
       Usuario usuario = this.usuarioFacade.find(user.getIdUser());
        
       List<Seguidor> seguidos = this.seguirFacade.buscarSeguidosUsuario(usuario.getIdUser());
       List<Fotografia> fotosSeguidos = new ArrayList();
       List<Fotografia> fotoUsuario = null;
       int total = seguidos.size();
       int contador = 0;
       while(total != 0){
           //Obtengo las fotos de la persona que sigo
           fotoUsuario = this.fotografiaFacade.buscarFotografiasUsuario(seguidos.get(contador).getUsuario1());
           int total1 = fotoUsuario.size();
           int contador1 = 0;
           while(total1 != 0){     
               fotosSeguidos.add(fotoUsuario.get(contador1));  
               total1--;
               contador1++;
           }
           total--;
           contador++;
       }
       return fotosSeguidos;
       
    }
    
    public List<Fotografia> fotosSeguidosEtiqTerceros(Usuario user){
    
        return this.fotografiaFacade.fotosSeguidosEtiqTerceros(user);
    }
    
        @Override
    public String eliminarFoto(Fotografia foto){
        this.fotografiaFacade.remove(this.fotografiaFacade.find(foto.getIdPhoto()));
        return "Foto eliminada";
    }
        
}
