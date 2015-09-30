/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Local;
import javax.ws.rs.core.Response;
import model.Camara;
import model.Fotografia;
import model.PermisoFotografia;
import model.Privacidad;
import model.Tag;
import model.Usuario;

/**
 *
 * @author sebastian
 */
@Local
public interface FotografiaEJBLocal {
    Fotografia mostrarFotografiaSeleccionada(int idFotografia);
    List<Fotografia> mostrarFotografiasUsuario(int idUsuario);
    List<Fotografia> FotoUsuarioOrdenadasFS(int idUsuario);
    List<Fotografia> FotoUsuarioOrdenadasFT(int idUsuario);
    String realizarComentario(Fotografia fotografiaAComentar, Usuario usuarioRealizador, String descripcion);
    String eliminarComentario(int idComentario);
    String agregarFavorito(Fotografia fotografia, Usuario usuario);
    String eliminarFavorito(int idFotografia, int idUsuario);
    Response DownloadPhoto(String id);
    void editPhoto(int idPhoto, int idPrivacidad,int idPermiso, String titulo, String descripcion);
    List<Fotografia> findAll();
    String insertPhotoInfo(Usuario user, Camara camara, Privacidad privacidad, PermisoFotografia permisoFoto, List<Tag> tags, String titulo, String descripcion);
    void setRutaFoto(Fotografia foto);
    String eliminarFoto(Fotografia foto);
    List<Fotografia> buscaFotosSeguidos(Usuario user);
    List<Fotografia> fotosSeguidosEtiqTerceros(Usuario user);
}
