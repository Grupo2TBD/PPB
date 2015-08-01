/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Local;
import model.Fotografia;
import model.Usuario;

/**
 *
 * @author ian
 */
@Local
public interface AlbumEJBLocal {
    void insertaAlbumDefault(Usuario user);
    void buscaAlbum(Usuario user,Fotografia photo);
    String crearAlbum(int idUsuario,String nombre, String descripcion, int privacidad, int permisos, String fotografiaPortada);
}
