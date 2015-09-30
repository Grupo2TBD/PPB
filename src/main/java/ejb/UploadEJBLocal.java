/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
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
public interface UploadEJBLocal {
   
    String insertPhotoInfo(Usuario user, Camara camara, Privacidad privacidad, PermisoFotografia permisoFoto, List<Tag> tags, List<Usuario> etiqueta, String titulo, String descripcion);
    void insertRuta(Fotografia foto);
}
