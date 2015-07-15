/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import facade.FotografiaEJBFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.Fotografia;

/**
 *
 * @author ian
 */
@Stateless
public class UploadEJB implements UploadEJBLocal{
    @EJB
    FotografiaEJBFacade photoFacade;
    RecurrentesEJBLocal fechas;
    
    @Override
    public void insertPhotoInfo(int idUser, int idPrivacidad, String title, String description,String format){
        Fotografia foto=new Fotografia();
        foto.setTituloPhoto(title);
        foto.setDescripcionPhoto(description);
        foto.setFechaSubidaPhoto(fechas.fechaActual());
        foto.setFormatoPhoto(format);
    }
}
