/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Local;
import model.PermisoAlbum;

/**
 *
 * @author sebastian
 */
@Local
public interface PermisoAlbumEJBLocal {
    
    PermisoAlbum obtenerPemiso(int idPermiso);
}
