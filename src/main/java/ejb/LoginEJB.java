
package ejb;

/**
 *
 * @author ian
 */

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import facade.UsuarioEJBFacade;
import model.Usuario;

@Stateless  
public class LoginEJB implements LoginEJBLocal{

    @EJB
    UsuarioEJBFacade userFacade;
    
    
    @Override
    public String Login(String mail, String pass){
        List <Usuario> list = this.userFacade.findAll();
            int largo=list.size();
            int contador=0;
            while(largo!=0){
                if(mail.equals(list.get(contador).getEmailUser()) && pass.equals(list.get(contador).getPassUser())){
                   // return list.get(contador);
                    return "si";
                }
                contador++;
                largo--;
            }
            //Usuario  aux=new Usuario();
            //return aux;
            return "no";    
        
        
    }
    
  
    
            
}
