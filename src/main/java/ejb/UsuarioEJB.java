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
    
    @EJB
    RecurrentesEJBLocal fecha;
    
    @EJB
    AlbumEJBLocal albumEJB;
  
    @Override
    public List<Usuario> usuarios(){
        return userFacade.findAll();
    }
    
  
    @Override
    public void insertData(Usuario user){
            
            user.setFechaCreacionCuenta(fecha.fechaActual());
            user.setFechaUltimaActualizacion(fecha.fechaActual());
            user.setCantidadAlbumesCreados(0);
            user.setCantidadFotografiasSubidas(0);
            user.setCantidadSeguidores(0);
            user.setCantidadSeguidos(0);
            user.setDireccionFotoPerfilUser(GlobalVariables.photoPath+GlobalVariables.defaultProfilePhoto);
            user.setDireccionFotoPortadaUser(GlobalVariables.photoPath+GlobalVariables.defaultFrontPhoto);
            this.userFacade.create(user);
            this.albumEJB.insertaAlbumDefault(user);
    }
    
    @Override
    public boolean verify(Usuario user){
        List <Usuario> list =this.userFacade.findAll();
        
            int largo=list.size();
            int contador=0;
            while(largo!=0){
                if(user.getEmailUser().equals(list.get(contador).getEmailUser()) || user.getAliasUser().equals(list.get(contador).getAliasUser())){
                    return false;
                }
                largo--;
                contador++;
            }
            
        return true;
    }
    
    @Override
    public boolean Login(Usuario user){
        List <Usuario> list = this.userFacade.findAll();
            int largo=list.size();
            int contador=0;
            while(largo!=0){
                if(user.getAliasUser().equals(list.get(contador).getAliasUser()) && user.getPassUser().equals(list.get(contador).getPassUser())){
                   
                    return true;
                }
                contador++;
                largo--;
            }
            return false;    
        
        
    }
    
    @Override
    public Usuario sendInfo(Usuario user){
        return userFacade.find(user.getIdUser());
    }
    
    @Override
    public boolean editarPerfil(Usuario user){
        List <Usuario> list =this.userFacade.findAll();
        int largo=list.size();
        int contador=0;
        while(largo!=0){
            if(user.getAliasUser().equals(list.get(contador).getAliasUser())){
                return false;
            }
            largo--;
        contador++;
        }
        
        Usuario usuario=this.userFacade.find(user.getIdUser());
        usuario.setNombreRealUser(user.getNombreRealUser());
        usuario.setApellidoUser(user.getApellidoUser());
        usuario.setPassUser(user.getPassUser());
        usuario.setSexoUser(user.getSexoUser());
        usuario.setAliasUser(user.getAliasUser());
        usuario.setFechaUltimaActualizacion(fecha.fechaActual());
        this.userFacade.edit(usuario);
        return true;
    }
    
}
