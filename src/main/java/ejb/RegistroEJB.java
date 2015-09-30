
package ejb;

/**
 *
 * @author ian
 */

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import facade.UsuarioEJBFacade;
import java.text.ParseException;
import model.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless  
public class RegistroEJB implements RegistroEJBLocal{

    @EJB
    UsuarioEJBFacade userFacade;
    
    public RegistroEJB(){
    
    }
    
    @Override
    public List<Usuario> get() {
		
	return this.userFacade.findAll();
    }
        
    @Override 
    public Usuario test(){
        return this.userFacade.find(0);
    }
       
    
    @Override
        public void insertaUsuario(String mail, String name, String lastname, String pass, String date, String sex, String alias){
           Usuario user = new Usuario();
           RecurrentesEJB f=new RecurrentesEJB();
            user.setEmailUser(mail);
            user.setNombreRealUser(name);
            user.setApellidoUser(lastname);
            user.setPassUser(pass);
            user.setAliasUser(alias);
        try {
            user.setFechaCumpleañosUser(f.FechaAngularToJava(date));
        } catch (ParseException ex) {
            Logger.getLogger(RegistroEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
            user.setFechaCreacionCuenta(f.fechaActual());
            user.setFechaUltimaActualizacion(f.fechaActual());
            user.setSexoUser(sex);
            user.setCantidadAlbumesCreados(0);
            user.setCantidadFotografiasSubidas(0);
            user.setCantidadSeguidores(0);
            user.setCantidadSeguidos(0);
           this.userFacade.create(user);
            
        }    
    
    @Override
    public String Registro(String mail, String name, String lastname, String pass, String date, String sex, String alias){
        List <Usuario> list =this.userFacade.findAll();
            int largo=list.size();
            int contador=0;
            while(largo!=0){
                if(mail.equals(list.get(contador).getEmailUser()) || alias.equals(list.get(contador).getAliasUser())){
                    return "nup";
                }
                largo--;
                contador++;
            }
            insertaUsuario(mail,name,lastname,pass,date,sex,alias);
            return "ship";
      }
             
}
