/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import model.Album;
import model.Usuario;

/**
 *
 * @author ian
 */
public interface AlbumEJBLocal {
    void insertaAlbumDefault(Usuario user);
    
}
