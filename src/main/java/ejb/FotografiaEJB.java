/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import facade.FotografiaEJBFacade;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.Fotografia;

/**
 *
 * @author ian
 */
@Stateless
public class FotografiaEJB implements FotografiaEJBLocal{
    @EJB
    FotografiaEJBFacade photoFacade;
    
    @EJB
    RecurrentesEJBLocal fecha;
    
    @Override
    public void insertPhotoInfo(String dateFrom, String title, String description, String format, int idPrivacidad) {
        Fotografia foto=new Fotografia();
        Object id=idPrivacidad;
        foto.setFechaSubidaPhoto(fecha.fechaActual());
        try {
            foto.setFechaTomadaPhoto(fecha.FechaAngularToJava(dateFrom));
        } catch (ParseException ex) {
            Logger.getLogger(FotografiaEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
        foto.setCantidadVisitasPhoto(0);
        foto.setTituloPhoto(title);
        foto.setDescripcionPhoto(description);
        foto.setCantidadFavoritosPhoto(0);
        foto.setFormatoPhoto(format);
        foto.setUltimaActualizacionPhoto(fecha.fechaActual());
        foto.setCantidadCompartidos(0);
        foto.setCantidadDescargadas(0);
        foto.setCantidadComentariosPositivos(0);
        foto.setCantidadComentariosNegativos(0);
        foto.setCantidadComentariosNeutros(0);
        this.photoFacade.create(foto);
    }
}
