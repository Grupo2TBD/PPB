/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import model.Usuario;

/**
 *
 * @author ian
 */
public interface UsuarioEJBLocal {
    void insertaUsuarioDefault(Usuario user, String mail, String name, String lastname, String pass, String date, String sex, String alias);
    String Login(String mail, String pass);
}
