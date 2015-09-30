/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import model.Fotografia;
import model.Usuario;

/**
 *
 * @author sebastian
 */
public interface MapaEJBLocal {
    List<Fotografia> buscaPorPais(String nombre);
    List<Fotografia> todasGeo();
    String geolocalizarFoto(Fotografia foto, String punto);
    List<Fotografia> todasGeoUsuario(Usuario user);
    List<Fotografia> buscaPaisUsuario(Usuario user, String pais);
    List<Fotografia> buscarContinente(String continente);
    List<Fotografia> buscaContinenteUsuario(Usuario user, String continente);
    List<Fotografia> buscaFotosGeoSeguidos(Usuario user);
    List<Fotografia> buscaFotosGeoSeguidosPais(Usuario user, String pais);
    List<Fotografia> buscaFotosGeoSeguidosContinente(Usuario user, String continente);
    List<Fotografia> buscarFotosSeguidosEtiqTercerosPais(Usuario user, String pais);
}
