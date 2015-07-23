/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import facade.UsuarioEJBFacade;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.Usuario;



/**
 *
 * @author ian
 */
@Stateless
public class UsuarioEJB implements UsuarioEJBLocal{
    
    @EJB
    UsuarioEJBFacade userFacade;
  
    @Override
    public void insertaUsuarioDefault(Usuario user, String mail, String name, String lastname, String pass, String date, String sex, String alias){
        RecurrentesEJB fecha=new RecurrentesEJB();
            user.setEmailUser(mail);
            user.setNombreRealUser(name);
            user.setApellidoUser(lastname);
            user.setPassUser(pass);
            user.setAliasUser(alias);
            try {
                user.setFechaCumpleanosUser(fecha.FechaAngularToJava(date));
            } catch (ParseException ex) {
                Logger.getLogger(RegistroEJB.class.getName()).log(Level.SEVERE, null, ex);
            }
            user.setFechaCreacionCuenta(fecha.fechaActual());
            user.setFechaUltimaActualizacion(fecha.fechaActual());
            user.setSexoUser(sex);
            user.setCantidadAlbumesCreados(0);
            user.setCantidadFotografiasSubidas(0);
            user.setCantidadSeguidores(0);
            user.setCantidadSeguidos(0);
            user.setDireccionFotoPerfilUser(GlobalVariables.photoPath+GlobalVariables.defaultProfilePhoto);
            user.setDireccionFotoPortadaUser(GlobalVariables.photoPath+GlobalVariables.defaultFrontPhoto);
            this.userFacade.create(user);
    }
    
    @Override
    public String Login(String mail, String pass){
        List <Usuario> list = this.userFacade.findAll();
            int largo=list.size();
            int contador=0;
            while(largo!=0){
                if(mail.equals(list.get(contador).getEmailUser()) && pass.equals(list.get(contador).getPassUser())){
                   
                    return "si";
                }
                contador++;
                largo--;
            }
            return "no";    
        
        
    }
    
    @Override
    public void editarPerfil(int idUser, String name, String lastname, String pass, String sex, String alias){
        Object id=idUser;
        Usuario user=this.userFacade.find(id);
        user.setNombreRealUser(name);
        user.setApellidoUser(lastname);
        user.setPassUser(pass);
        user.setSexoUser(sex);
        user.setAliasUser(alias);
        this.userFacade.edit(user);
    
    }
}
