/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import facade.PermisoAlbumEJBFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.PermisoAlbum;

/**
 *
 * @author sebastian
 */
@Stateless  
public class PermisoAlbumEJB implements PermisoAlbumEJBLocal{
    
    @EJB
    PermisoAlbumEJBFacade permisoAlbumFacade;
    
    public PermisoAlbumEJB(){
    
    }
    @Override
    public PermisoAlbum obtenerPemiso(int idPermiso)
    {
        Object id = idPermiso;
        return permisoAlbumFacade.find(id);
    }

   
}
