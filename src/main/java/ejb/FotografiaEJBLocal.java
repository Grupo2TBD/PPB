/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;


/**
 *
 * @author ian
 */
public interface FotografiaEJBLocal {
    void insertPhotoInfo (String dateFrom, String title, String description, String format, int idPrivacidad);
}
