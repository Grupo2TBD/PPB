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

import java.util.List;
import javax.ejb.Local;
import model.Usuario;

@Local
public interface RegistroEJBLocal {
    List <Usuario>get();
    Usuario test();
    String Registro(String mail, String name, String lastname, String pass, String date, String sex, String alias);
    void insertaUsuario(String mail, String name, String lastname, String pass, String date, String sex, String alias);
   
}

