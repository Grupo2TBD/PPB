/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Local;
import model.Camara;

/**
 *
 * @author ian
 */

@Local
public interface CamaraEJBLocal {
    boolean findCamera(String nombre);
    Camara insertCamera(String nombre,int megaPx,int zoom, int pantalla, String tipo, String marca, int peso);
}
