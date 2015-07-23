
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
public class RegistroEJB implements RegistroEJBLocal{

    @EJB
    UsuarioEJBLocal userEJB;
    
    @EJB
    AlbumEJBLocal albumEJB;
    
    @EJB
    UsuarioEJBFacade userFacade;
    
    public RegistroEJB(){
    
    }
    
       
    @Override
    public void insertaUsuario(String mail, String name, String lastname, String pass, String date, String sex, String alias){
       Usuario user = new Usuario();
       userEJB.insertaUsuarioDefault(user, mail, name, lastname, pass, date, sex, alias);
       albumEJB.insertaAlbumDefault(user);
    }    
    
    
       
    @Override
    public String Registro(String mail, String name, String lastname, String pass, String date, String sex, String alias){
        List <Usuario> list =this.userFacade.findAll();
        
            int largo=list.size();
            int contador=0;
            while(largo!=0){
                if(mail.equals(list.get(contador).getEmailUser()) || alias.equals(list.get(contador).getAliasUser())){
                    return "no";
                }
                largo--;
                contador++;
            }
        insertaUsuario(mail,name,lastname,pass,date,sex,alias);
        
        return "si";
    }
             
}
