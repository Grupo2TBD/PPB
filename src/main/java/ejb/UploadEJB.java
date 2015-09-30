/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import facade.FotografiasEJBFacade;
import facade.TagEJBFacade;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
@Stateless
public class UploadEJB implements UploadEJBLocal{
    @EJB
    FotografiasEJBFacade photoFacade;
    
    @EJB
    RecurrentesEJBLocal fechas;
    
    @EJB
    TagEJBFacade tagFacade;
    
    /*Parámetros
    idFoto - (idUser) - idCamara* - idLocalizacion* - (idPrivacidad) - idTipoClasificacion* - (idPermisoFotografia) - (fecha-subida) - (titulo)
    - fechatomada* - (cantidadVisitas) - (Descripcion) - (CantidadFavoritosFoto) - formato foto - direccionFisicaFoto - (cantidadcompartido) 
    - (CantDescargas) - (CantidadComentariosPositivos) - (Cantcomentariosnegativos) - (CantcomentarioNuetros)
    
    Etiqueta
    */
    
    //HAY QUE PONER EL ID AUTOINCREMENTAL DE FOTOGRAFIA
    @Override
    public String insertPhotoInfo(Usuario user, Camara camara, Privacidad privacidad, PermisoFotografia permisoFoto, List<Tag> tags, List<Usuario> etiqueta, String titulo, String descripcion){
        Fotografia foto = new Fotografia();
        foto.setUsuarioiduser(user);
        foto.setPrivacidadidprivacidad(privacidad);
        foto.setPermisoFotografiaidpermisofotografia(permisoFoto);
        foto.setCamaraidcamara(camara);
        foto.setTituloPhoto(titulo);
        foto.setDescripcionPhoto(descripcion);
        foto.setFechaSubidaPhoto(fechas.fechaActual());
        foto.setCantidadVisitasPhoto(0);
        foto.setCantidadFavoritosPhoto(0);
        foto.setCantidadDescargas(0);
        foto.setCantidadComentarios(0);
        foto.setCantidadComentariosPositivos(0);
        foto.setCantidadComentariosNegativos(0);
        foto.setCantidadComentariosNeutros(0);
        foto.setUltimaActualizacionPhoto(fechas.fechaActual());
        foto.setTagCollection(verificarTag(tags));
        foto.setUsuarioCollection(etiqueta);
        photoFacade.create(foto);
        insertRuta(foto);
        return "Foto insertada";
    }
    
    @Override
    public void insertRuta(Fotografia foto){
        List<Fotografia> list = this.photoFacade.findAll();
            int largo=list.size();
            int contador=0;
            while(largo!=0){
                
                contador++;
                largo--;
                
            }  
        list.get(contador-1).setDireccionFisicaPhoto(GlobalVariables.photoPath+Integer.toString(list.get(contador-1).getIdPhoto())+".png");
    }
    
    //Función para verificar si el tag existe en la base de datos o no 
    public List <Tag> verificarTag(List<Tag> tags){
        List<Tag> tag = new ArrayList();
        for (int i = 0; i < tags.size(); i++) {

            if(tagFacade.buscarNombre(tags.get(i).getNombreTag()) == null){
                tagFacade.create(tags.get(i));
                
                tag.add(tagFacade.buscarNombre(tags.get(i).getNombreTag()));
            }else{
                tag.add(tags.get(i));
            }
            
        }
        return tag;
    }
    

}


