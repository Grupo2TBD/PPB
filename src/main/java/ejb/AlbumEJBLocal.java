/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Local;
import model.Album;
import model.Fotografia;
import model.Usuario;

/**
 *
 * @author sebastian
 */
@Local
public interface AlbumEJBLocal {
    void insertaAlbumDefault(Usuario user);
    String crearAlbum(int idUsuario,String nombre, String descripcion, int privacidad, int permisos, String fotografiaPortada);
    String editarAlbum(int idAlbum, String nombre, String descripcion,int idPrivacidad, int idPermisos);
    String editarAlbumFotoPortada(int idAlbum,String fotografiaPortada);
    String eliminarAlbum(int idAlbum);
    public Album mostrarAlbum(int idAlbum);
    List<Album> mostrarAlbumesUsuario(int idUsuario);
    String agregarFavorito(Album album, Usuario user);
    String eliminarFavorito(int idAlbum, int idUsuario);
    void buscaAlbum(Usuario user, Fotografia photo);
    String addFotoAlbum(Fotografia foto, Album album);
    public String deleteFotoAlbum(Fotografia foto, Album album);
    List<Fotografia> buscarFotoAlbum(int idAlbum);
}
