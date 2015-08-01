/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Local;
import model.Fotografia;


/**
 *
 * @author ian
 */
@Local
public interface FotografiaEJBLocal {
    void insertPhotoInfo (String dateFrom, String title, String description, String format,Fotografia foto, int idUser);
    void uploadPhoto(String dateFrom, String title, String description, String format,int idPrivacy,int idUsuario,int idPermiso);
    void editPhoto(int idPhoto, int idPrivacidad,int idPermiso, String titulo, String descripcion);
}
