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
public interface LoginEJBLocal {
    String Login(String mail, String pass);
}
