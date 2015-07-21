
package ejb;

/**
 *
 * @author ian
 */

import facade.AlbumEJBFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import facade.UsuarioEJBFacade;
import java.text.ParseException;
import model.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Album;
import model.PermisoAlbum;

@Stateless  
public class RegistroEJB implements RegistroEJBLocal{

    @EJB
    UsuarioEJBFacade userFacade;
    
    @EJB
    AlbumEJBLocal albumEJB;
    
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
            user.setDireccionFotoPerfilUser("perfil");
            user.setDireccionFotoPortadaUser("portada");
            this.userFacade.create(user);
            
            //Album album = new Album();
            /*PermisoAlbum pa=new PermisoAlbum(1);
            album.setIdUser(user);
            album.setNombreAlbum("Fotografías");
            album.setFechacreacionAlbum(fecha.fechaActual());
            album.setDescripcionAlbum("Fotografías de "+user.getNombreRealUser());
            album.setDireccionFotoPortadaAlbum(GlobalVariables.photoPath+GlobalVariables.defaultAlbumFrontPhoto);
            album.setCantidadFotografiasAlbum(0);
            album.setCantidadFavoritos(0);
            album.setCantidadComentarios(0);
            album.setUltimaActualizacionAlbum(fecha.fechaActual());
            album.setIdPermisoAlbum(pa);*/
            albumEJB.insertaAlbumDefault(user);
            //this.albumFacade.create(album);
            
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
