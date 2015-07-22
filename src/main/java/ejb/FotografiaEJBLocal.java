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
    void insertPhotoInfo(int idUser, int idPrivacidad, String title, String description,String format);
}
