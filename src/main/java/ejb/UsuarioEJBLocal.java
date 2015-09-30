/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import model.Fotografia;
import model.Seguidor;
import model.SeguidorPK;
import model.Usuario;

/**
 *
 * @author sebastian
 */
@Local
public interface UsuarioEJBLocal {
   String Login(Usuario user);
   Usuario findByAlias(String alias);
   List<Usuario> findAll();
   void insertData(Usuario user);
   boolean verifica(Usuario user);
   boolean editarPerfil(Usuario user);
   String seguir(int idUsuario, int idUsuarioSeguir);
   String dejarDeSeguir(int idUsuario, int idUsuarioDejarSeguir);
   List<Fotografia> mostrarFotografiaSeguidores(int idUsuario);
   List<Fotografia> mostrarFotografiaSeguidoresFecha(int idUsuario, String fecha) throws ParseException;
   List<Fotografia> mostrarFotografiaSeguidoresNumero(int idUsuario, int numero);
   List<Fotografia> mostrarFotografiaSeguidoresNumeroFecha(int idUsuario, int numero, String fecha) throws ParseException;
   List<Seguidor> mostrarSeguidoresFecha (int idUsuario, String fecha) throws ParseException;
   List<Seguidor> mostrarSeguidores (int idUsuario);
   Usuario find(int idUsuario);
}
