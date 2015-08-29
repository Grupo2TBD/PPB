/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Local;
import model.Usuario;

/**
 *
 * @author ian
 */
@Local
public interface UsuarioEJBLocal {
    void insertUserData(Usuario user, String mail, String name, String lastname, String pass, String date, String sex, String alias);
    String Login(String mail, String pass);
    String editarPerfil(int idUser,String name, String lastname, String pass, String sex, String alias);
    String Registro(String mail, String name, String lastname, String pass, String date, String sex, String alias);
}
