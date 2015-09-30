/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import facade.FotografiasEJBFacade;
import facade.SeguirUsuarioEJBFacade;
import facade.UsuarioEJBFacade;
import java.text.ParseException;
import java.util.Date; 
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.Fotografia;
import model.Seguidor;
import model.SeguidorPK;
import model.Usuario;

/**
 *
 * @author sebastian
 */
@Stateless
public class UsuarioEJB implements UsuarioEJBLocal {
    
    @EJB
    SeguirUsuarioEJBFacade seguirUsuarioFacade;
    
    @EJB
    UsuarioEJBFacade usuarioFacade;
    
    @EJB
    RecurrentesEJBLocal recurrentesLocal;
    
    @EJB
    FotografiasEJBFacade fotografiasFacade;
    
    @EJB
    AlbumEJBLocal albumEJB;
    
    //ian
    @Override
    public String Login(Usuario user){
        List <Usuario> list = this.usuarioFacade.findAll();
            int largo=list.size();
            int contador=0;
            while(largo!=0){
                if(user.getAliasUser().equals(list.get(contador).getAliasUser()) && user.getPassUser().equals(list.get(contador).getPassUser())){
                   
                    return "true";
                }
                contador++;
                largo--;
            }
            return "Usuario o contraseña incorrectos";    
     
    }
    
    //ian
    @Override
    public Usuario findByAlias(String alias){
        List<Usuario> list= this.usuarioFacade.findAll();
        int largo = list.size();
            int contador = 0;
            while(largo!=0){
                if(list.get(contador).getAliasUser().equals(alias)){
                   
                    return list.get(contador);
                }
                contador++;
                largo--;
            }
            Usuario def = new Usuario();
       return def;  
    }
    
    //ian
    @Override
    public List<Usuario> findAll(){
     
        return this.usuarioFacade.findAll();
    }
    
    //ian
    @Override
    public void insertData(Usuario user){
            user.setFechaCreacionCuenta(recurrentesLocal.fechaActual());
            user.setFechaUltimaActualizacion(recurrentesLocal.fechaActual());
            user.setCantidadAlbumesCreados(0);
            user.setCantidadFotografiasSubidas(0);
            user.setCantidadSeguidores(0);
            user.setCantidadSeguidos(0);
            user.setDireccionFotoPerfilUser(GlobalVariables.photoPath+GlobalVariables.defaultProfilePhoto);
            user.setDireccionFotoPortadaUser(GlobalVariables.photoPath+GlobalVariables.defaultFrontPhoto);
            this.usuarioFacade.create(user);
            this.albumEJB.insertaAlbumDefault(user);
    }
    
    //ian
    @Override
    public boolean verifica(Usuario user){
        List <Usuario> list =this.usuarioFacade.findAll();
        
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
    
    
    //ian
    @Override
    public boolean editarPerfil(Usuario user){
        List <Usuario> list =this.usuarioFacade.findAll();
        int largo=list.size();
        int contador=0;
        while(largo!=0){
            if(user.getAliasUser().equals(list.get(contador).getAliasUser())){
                return false;
            }
            largo--;
        contador++;
        }
        
        Usuario usuario=this.usuarioFacade.find(user.getIdUser());
        usuario.setNombreRealUser(user.getNombreRealUser());
        usuario.setApellidoUser(user.getApellidoUser());
        usuario.setPassUser(user.getPassUser());
        usuario.setSexoUser(user.getSexoUser());
        usuario.setAliasUser(user.getAliasUser());
        usuario.setFechaUltimaActualizacion(recurrentesLocal.fechaActual());
        this.usuarioFacade.edit(usuario);
        return true;
    }
    
    /*Parámetros: id usuario que realizará la acción, id usuario al cual se seguirá
    Función encargada de registrar en la base de datos que un usuario comienza a seguir a otro.
    Retorno: Cadena de caracteres que indica que se esta siguiendo al otro usuario. 
    */
    @Override
    public String seguir(int idUsuario, int idUsuarioSeguir){
        
        //Creando objeto seguidor para agregarlo a la base de datos
        Seguidor seguidor = new Seguidor();
        SeguidorPK seguidor1 = new SeguidorPK();
        
        //Buscando el usuario que realiza la acción
        Object idusuario = idUsuario;
        Usuario usuario = usuarioFacade.find(idusuario);
        
        //Buscando el usuario al cual se va seguir
        Object idusuarioseguir = idUsuarioSeguir;
        Usuario usuarioSeguir = usuarioFacade.find(idusuarioseguir);
        
        //Actualizando cantidad seguidores del usuario que se va a seguir
        usuarioSeguir.setCantidadSeguidores(usuarioSeguir.getCantidadSeguidores() + 1);
        usuarioFacade.edit(usuarioSeguir);
        
        //Seteando los atributos del objeto seguidor 
        seguidor1.setUsuarioiduser(idUsuario);
        seguidor1.setUsuarioiduser1(idUsuarioSeguir);
        
        seguidor.setUsuario(usuario);
        seguidor.setUsuario1(usuarioSeguir);
        seguidor.setSeguidorPK(seguidor1);
        seguidor.setFechaFollow(recurrentesLocal.fechaActual());
        seguirUsuarioFacade.create(seguidor);
        return "Usuario "+seguidor.getUsuario().getIdUser()+" sigue a:"+seguidor.getUsuario1().getIdUser();
    }
    /*Parámetros: id usuario que realizará la acción, id usuario al cual se dejará de seguir
    Función encargada de registrar en la base de datos que un usuario deja de seguir a otro.
    Retorno: Cadena de caracteres que indica que se dejo de seguir al usuario. 
    */
    @Override
    public String dejarDeSeguir(int idUsuario, int idUsuarioDejarSeguir){
        
        Seguidor seguidor = seguirUsuarioFacade.buscarSeguidor(idUsuario, idUsuarioDejarSeguir);
        //Actualizando la cantidad de seguidores de la persona a la cual se dejará de seguir
        Usuario usuario = seguidor.getUsuario1();
        usuario.setCantidadSeguidores(usuario.getCantidadSeguidores() - 1);
        usuarioFacade.edit(usuario);
        
        seguirUsuarioFacade.remove(seguidor);
        return "Usuario "+seguidor.getUsuario().getIdUser()+" dejo de seguir a :"+seguidor.getUsuario1().getIdUser();
     
    }
    
    /*Parámetros: id usuario 
    Función encargada de mostrar todas las fotografias de los seguidores
    Retorno: Lista de fotografías de todos los seguidores. 
    */
    @Override
    public List<Fotografia> mostrarFotografiaSeguidores(int idUsuario){

        List<Seguidor> seguidores = seguirUsuarioFacade.buscarSeguidores(idUsuario);
        List<Fotografia> fotoSeguidores = null;
        int maxSeguidores = seguidores.size();
        int i = 0;
        int a = 0;
        
        while(maxSeguidores > 0)
        {
            if(i == 0 ){
                fotoSeguidores = fotografiasFacade.buscarFotografiasUsuario(seguidores.get(i).getUsuario());
            }
            else
            {   
                fotoSeguidores.addAll(a, fotografiasFacade.buscarFotografiasUsuario(seguidores.get(i).getUsuario()));
  
            }
            maxSeguidores--;
            i++;
            a = fotoSeguidores.size();
        }
            
    
        return fotoSeguidores;
    }
    
    /*Parámetros: id usuario, fecha 
    Función encargada de mostrar todas las fotografias de los seguidores desde la fecha especificada
    Retorno: Lista de fotografías de todos los seguidores desde la fecha especificada
    */
    @Override
    public List<Fotografia> mostrarFotografiaSeguidoresFecha(int idUsuario, String fecha) throws ParseException{
        
        List<Seguidor> seguidores = seguirUsuarioFacade.buscarSeguidores(idUsuario);
        List<Fotografia> fotoSeguidores = null;
        int maxSeguidores = seguidores.size();
        int i = 0;
        int a = 0;
        //Convirtiendo fecha
        Date fechat = recurrentesLocal.FechaAngularToJava(fecha);
        
        while(maxSeguidores > 0)
        {
            if(i == 0 ){
                fotoSeguidores = fotografiasFacade.FotoSeguidoresPorFecha(seguidores.get(i).getUsuario(), fechat);
            }
            else
            {   
                fotoSeguidores.addAll(a, fotografiasFacade.FotoSeguidoresPorFecha(seguidores.get(i).getUsuario(), fechat));
  
            }
            maxSeguidores--;
            i++;
            a = fotoSeguidores.size();
        }
            
    
        return fotoSeguidores;
    }
    
    /*Parámetros: id usuario, numero de fotografias por seguidor 
    Función encargada de mostrar la cantidad de fotografias por seguidor especificada 
    Retorno: Lista de fotografías de todos los seguidores 
    */
    @Override
    public List<Fotografia> mostrarFotografiaSeguidoresNumero(int idUsuario, int numero){
        List<Seguidor> seguidores = seguirUsuarioFacade.buscarSeguidores(idUsuario);
        List<Fotografia> fotoSeguidores = null;
        int maxSeguidores = seguidores.size();
        int i = 0;
        int a = 0;
        int aux = 0;
        int contador = 0;
               
            //Eliminar hasta que el numero de elementos del array sea igual a numero especificado size > numero pero si numero > size no hacer nada            
            while(maxSeguidores > 0)
            {
                if(i == 0 ){
                    fotoSeguidores = fotografiasFacade.FotoSeguidoresPorNumero(seguidores.get(i).getUsuario());
                    aux = fotoSeguidores.size();
                    while(aux > numero){
                        fotoSeguidores.remove(aux-1);
                        aux--;
                    }
                }
                else{   
                    fotoSeguidores.addAll(a, fotografiasFacade.FotoSeguidoresPorNumero(seguidores.get(i).getUsuario()));
                    aux = fotoSeguidores.size(); // nuevo total
                    contador = aux - a; // cantidad de elementos que se agregaron
                    while(contador > numero){
                        fotoSeguidores.remove(aux-1);
                        aux--;
                        contador--;
                    }
                }
                
                maxSeguidores--;
                i++;
                a = fotoSeguidores.size(); //antiguo total 
            }
            
    
        return fotoSeguidores;
    }
    
    /*Parámetros: id usuario, numero de fotos por seguidor, fecha 
    Función encargada de mostrar todas las fotografias de los seguidores desde la fecha especificada y la cantidad de fotografias especificada.
    Retorno: Lista de fotografías de todos los seguidores desde la fecha  y  cantidad de fotografias especificada
    */
    @Override
    public List<Fotografia> mostrarFotografiaSeguidoresNumeroFecha(int idUsuario, int numero, String fecha) throws ParseException{
        List<Seguidor> seguidores = seguirUsuarioFacade.buscarSeguidores(idUsuario);
        List<Fotografia> fotoSeguidores = null;
        int maxSeguidores = seguidores.size();
        int i = 0;
        int a = 0;
        int aux = 0;
        int contador = 0;
         //Convirtiendo fecha
        Date fechat = recurrentesLocal.FechaAngularToJava(fecha);
        
        //Eliminar hasta que el numero de elementos del array sea igual a numero especificado size > numero pero si numero > size no hacer nada            
        while(maxSeguidores > 0){
            if(i == 0 ){
                fotoSeguidores = fotografiasFacade.FotoSeguidoresPorFecha(seguidores.get(i).getUsuario(), fechat);                    
                aux = fotoSeguidores.size();
                    while(aux > numero){
                        fotoSeguidores.remove(aux-1);
                        aux--;
                    }
            }
            else{   
                fotoSeguidores.addAll(a, fotografiasFacade.FotoSeguidoresPorFecha(seguidores.get(i).getUsuario(), fechat));
                aux = fotoSeguidores.size(); // nuevo total
                contador = aux - a; // cantidad de elementos que se agregaron
                while(contador > numero){
                    fotoSeguidores.remove(aux-1);
                    aux--;
                    contador--;
                }
            }
                
            maxSeguidores--;
            i++;
            a = fotoSeguidores.size(); //antiguo total 
        }
            
        return fotoSeguidores;
    }
    
    /*Parámetros: id usuario, fecha
    Función encargada de mostrar los seguidores del usuario desde la fecha especificada 
    Retorno: Lista con los seguidores.
    */
    @Override
    public List<Seguidor> mostrarSeguidoresFecha (int idUsuario, String fecha) throws ParseException{
        
        Date fechat = recurrentesLocal.FechaAngularToJava(fecha);
        List<Seguidor> seguidores =  seguirUsuarioFacade.buscarSeguidoresFecha(idUsuario, fechat);
        return seguidores;
    }
    
    /*Parámetros: id usuario
    Función encargada de mostrar los seguidores del usuario
    Retorno: Lista con los seguidores.
    */
    @Override
    public List<Seguidor> mostrarSeguidores (int idUsuario){
        
        List<Seguidor> seguidores =  seguirUsuarioFacade.buscarSeguidores(idUsuario);
        return seguidores;
    }
  
    @Override
    public Usuario find(int idUsuario){
        Object id = idUsuario;
        return this.usuarioFacade.find(id);
    }
}
