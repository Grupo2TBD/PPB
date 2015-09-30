/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import facade.FotografiasEJBFacade;
import facade.MapaEJBFacade;
import facade.SeguirUsuarioEJBFacade;
import facade.UsuarioEJBFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.Fotografia;
import model.Seguidor;
import model.Usuario;

/**
 *
 * @author sebastian
 */
@Stateless
public class MapaEJB implements MapaEJBLocal{
    
    @EJB
    FotografiasEJBFacade fotoFacade;
    
    @EJB
    MapaEJBFacade mapFacade;
    
    @EJB
    UsuarioEJBFacade usuarioFacade;
    
    @EJB
    SeguirUsuarioEJBFacade seguirFacade;

    @Override
    public List<Fotografia> buscaPorPais(String nombre) {
        return mapFacade.buscaPorPais(nombre);
    }
    
    @Override
    public List<Fotografia> todasGeo(){
        List<Fotografia> todas = this.fotoFacade.findAll();
        List<Fotografia> todasGeo = new ArrayList();
        
        int total = todas.size();
        int contador = 0;
        
        while(total != 0){
            if(todas.get(contador).getPunto() != null){
                todasGeo.add(todas.get(contador));
            }
            total--;
            contador++;
        }
        
        return todasGeo;
    }
    
    @Override
    public String geolocalizarFoto(Fotografia foto, String punto){
        
        Fotografia fotox = this.fotoFacade.find(foto.getIdPhoto());
        fotox.setPunto(punto);
        this.fotoFacade.edit(fotox);
        return "Geolocalizada en: "+fotox.getPunto();
    }
    
    @Override
    public List<Fotografia> todasGeoUsuario(Usuario user){

        List<Fotografia> todas = this.fotoFacade.buscarFotografiasUsuario(user);
        List<Fotografia> todasGeo = new ArrayList();
        
        int total = todas.size();
        int contador = 0;
        
        while(total != 0){
            if(todas.get(contador).getPunto() != null){
                todasGeo.add(todas.get(contador));
            }
            total--;
            contador++;
        }
        
        return todasGeo;
    }
    
    @Override
    public List<Fotografia> buscaPaisUsuario(Usuario user, String pais){
    
        Usuario userx = this.usuarioFacade.find(user.getIdUser());
        
        return this.mapFacade.buscarPorUsuarioPais(userx.getIdUser(), pais);
    }
    
    //Fotos por continente
    @Override
    public List<Fotografia> buscarContinente(String continente){
    
        return this.mapFacade.buscaPorContinente(continente);
    
    }
    
    //Fotos por continente y usuario
    @Override
    public List<Fotografia> buscaContinenteUsuario(Usuario user, String continente){
    
        Usuario userx = this.usuarioFacade.find(user.getIdUser());
        
        return this.mapFacade.buscarPorUsuarioPais(userx.getIdUser(), continente);
    }
    
    //Entrega fotografias geolocalizadas de las personas a las que el usuario sigue
    @Override
    public List<Fotografia> buscaFotosGeoSeguidos(Usuario user){
        Usuario usuario = this.usuarioFacade.find(user.getIdUser());
        
       List<Seguidor> seguidos = this.seguirFacade.buscarSeguidosUsuario(usuario.getIdUser());
       List<Fotografia> fotosSeguidos = new ArrayList();
       List<Fotografia> fotoUsuario = null;
       int total = seguidos.size();
       int contador = 0;
       while(total != 0){
           //Obtengo las fotos de la persona que sigo
           fotoUsuario = this.fotoFacade.buscarFotografiasUsuario(seguidos.get(contador).getUsuario1());
           int total1 = fotoUsuario.size();
           int contador1 = 0;
           while(total1 != 0){
               if(fotoUsuario.get(contador1).getPunto() != null){
                   fotosSeguidos.add(fotoUsuario.get(contador1));
               }
               total1--;
               contador1++;
           }
           total--;
           contador++;
       }
       return fotosSeguidos;
       
    }
    
    @Override
    public List<Fotografia> buscaFotosGeoSeguidosPais(Usuario user, String pais){
       Usuario usuario = this.usuarioFacade.find(user.getIdUser());
        
       //Obtengo los usuarios que sigo
       List<Seguidor> seguidos = this.seguirFacade.buscarSeguidosUsuario(usuario.getIdUser());
       //Guardo las fotos de las personas que sigo
       List<Fotografia> fotosSeguidos = new ArrayList();
       //Aux
       List<Fotografia> fotoUsuario = null;
       int total = seguidos.size();
       int contador = 0;
       while(total != 0){
           //Obtengo las fotos de la persona que sigo
           fotoUsuario = this.mapFacade.buscarPorUsuarioPais(seguidos.get(contador).getUsuario1().getIdUser(), pais);
           int total1 = fotoUsuario.size();
           int contador1 = 0;
           while(total1 != 0){
               fotosSeguidos.add(fotoUsuario.get(contador1));
               total1--;
               contador1++;
           }
           total--;
           contador++;
       }
       return fotosSeguidos;
    }
    
    @Override
    public List<Fotografia> buscaFotosGeoSeguidosContinente(Usuario user, String continente){
       Usuario usuario = this.usuarioFacade.find(user.getIdUser());
        
       //Obtengo los usuarios que sigo
       List<Seguidor> seguidos = this.seguirFacade.buscarSeguidosUsuario(usuario.getIdUser());
       //Guardo las fotos de las personas que sigo
       List<Fotografia> fotosSeguidos = new ArrayList();
       //Aux
       List<Fotografia> fotoUsuario = null;
       int total = seguidos.size();
       int contador = 0;
       while(total != 0){
           //Obtengo las fotos de la persona que sigo
           fotoUsuario = this.mapFacade.buscarPorUsuarioContinente(seguidos.get(contador).getUsuario1().getIdUser(), continente);
           int total1 = fotoUsuario.size();
           int contador1 = 0;
           while(total1 != 0){
               fotosSeguidos.add(fotoUsuario.get(contador1));
               total1--;
               contador1++;
           }
           total--;
           contador++;
       }
       return fotosSeguidos;
    }
    
    @Override
    public List<Fotografia> buscarFotosSeguidosEtiqTercerosPais(Usuario user, String pais){
    
        return this.mapFacade.buscarFotosSeguidosEtiqTercerosPais(user, pais);
    }
    
}