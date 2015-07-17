/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ian
 */
@Entity
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdUser", query = "SELECT u FROM Usuario u WHERE u.idUser = :idUser"),
    @NamedQuery(name = "Usuario.findByPassUser", query = "SELECT u FROM Usuario u WHERE u.passUser = :passUser"),
    @NamedQuery(name = "Usuario.findByFechaCreacionCuenta", query = "SELECT u FROM Usuario u WHERE u.fechaCreacionCuenta = :fechaCreacionCuenta"),
    @NamedQuery(name = "Usuario.findByAliasUser", query = "SELECT u FROM Usuario u WHERE u.aliasUser = :aliasUser"),
    @NamedQuery(name = "Usuario.findByNombreRealUser", query = "SELECT u FROM Usuario u WHERE u.nombreRealUser = :nombreRealUser"),
    @NamedQuery(name = "Usuario.findByDireccionFotoPerfilUser", query = "SELECT u FROM Usuario u WHERE u.direccionFotoPerfilUser = :direccionFotoPerfilUser"),
    @NamedQuery(name = "Usuario.findByApellidoUser", query = "SELECT u FROM Usuario u WHERE u.apellidoUser = :apellidoUser"),
    @NamedQuery(name = "Usuario.findBySexoUser", query = "SELECT u FROM Usuario u WHERE u.sexoUser = :sexoUser"),
    @NamedQuery(name = "Usuario.findByDireccionFotoPortadaUser", query = "SELECT u FROM Usuario u WHERE u.direccionFotoPortadaUser = :direccionFotoPortadaUser"),
    @NamedQuery(name = "Usuario.findByFechaCumpleanosUser", query = "SELECT u FROM Usuario u WHERE u.fechaCumpleanosUser = :fechaCumpleanosUser"),
    @NamedQuery(name = "Usuario.findByEmailUser", query = "SELECT u FROM Usuario u WHERE u.emailUser = :emailUser"),
    @NamedQuery(name = "Usuario.findByFechaUltimaActualizacion", query = "SELECT u FROM Usuario u WHERE u.fechaUltimaActualizacion = :fechaUltimaActualizacion"),
    @NamedQuery(name = "Usuario.findByCantidadFotografiasSubidas", query = "SELECT u FROM Usuario u WHERE u.cantidadFotografiasSubidas = :cantidadFotografiasSubidas"),
    @NamedQuery(name = "Usuario.findByCantidadAlbumesCreados", query = "SELECT u FROM Usuario u WHERE u.cantidadAlbumesCreados = :cantidadAlbumesCreados"),
    @NamedQuery(name = "Usuario.findByCantidadSeguidores", query = "SELECT u FROM Usuario u WHERE u.cantidadSeguidores = :cantidadSeguidores"),
    @NamedQuery(name = "Usuario.findByCantidadSeguidos", query = "SELECT u FROM Usuario u WHERE u.cantidadSeguidos = :cantidadSeguidos"),
    @NamedQuery(name = "Usuario.findByUbicacionUser", query = "SELECT u FROM Usuario u WHERE u.ubicacionUser = :ubicacionUser")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USER")
    private Integer idUser;
    @Size(max = 20)
    @Column(name = "PASS_USER")
    private String passUser;
    @Column(name = "FECHA_CREACION_CUENTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacionCuenta;
    @Size(max = 20)
    @Column(name = "ALIAS_USER")
    private String aliasUser;
    @Size(max = 20)
    @Column(name = "NOMBRE_REAL_USER")
    private String nombreRealUser;
    @Size(max = 50)
    @Column(name = "DIRECCION_FOTO_PERFIL_USER")
    private String direccionFotoPerfilUser;
    @Size(max = 20)
    @Column(name = "APELLIDO_USER")
    private String apellidoUser;
    @Size(max = 8)
    @Column(name = "SEXO_USER")
    private String sexoUser;
    @Size(max = 50)
    @Column(name = "DIRECCION_FOTO_PORTADA_USER")
    private String direccionFotoPortadaUser;
    @Column(name = "FECHA_CUMPLEANOS_USER")
    @Temporal(TemporalType.DATE)
    private Date fechaCumpleanosUser;
    @Size(max = 30)
    @Column(name = "EMAIL_USER")
    private String emailUser;
    @Column(name = "FECHA_ULTIMA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimaActualizacion;
    @Column(name = "CANTIDAD_FOTOGRAFIAS_SUBIDAS")
    private Integer cantidadFotografiasSubidas;
    @Column(name = "CANTIDAD_ALBUMES_CREADOS")
    private Integer cantidadAlbumesCreados;
    @Column(name = "CANTIDAD_SEGUIDORES")
    private Integer cantidadSeguidores;
    @Column(name = "CANTIDAD_SEGUIDOS")
    private Integer cantidadSeguidos;
    @Size(max = 255)
    @Column(name = "UBICACION_USER")
    private String ubicacionUser;
    @JoinTable(name = "ETIQUETA_USUARIO", joinColumns = {
        @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_PHOTO", referencedColumnName = "ID_PHOTO")})
    @ManyToMany
    private Collection<Fotografia> fotografiaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private Collection<Fotografia> fotografiaCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<FavoritoAlbum> favoritoAlbumCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private Collection<ComentarioFotografia> comentarioFotografiaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private Collection<ComentarioAlbum> comentarioAlbumCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<FavoritoFotografia> favoritoFotografiaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private Collection<Album> albumCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<Seguidor> seguidorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario1")
    private Collection<Seguidor> seguidorCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private Collection<Tag> tagCollection;

    public Usuario() {
    }

    public Usuario(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getPassUser() {
        return passUser;
    }

    public void setPassUser(String passUser) {
        this.passUser = passUser;
    }

    public Date getFechaCreacionCuenta() {
        return fechaCreacionCuenta;
    }

    public void setFechaCreacionCuenta(Date fechaCreacionCuenta) {
        this.fechaCreacionCuenta = fechaCreacionCuenta;
    }

    public String getAliasUser() {
        return aliasUser;
    }

    public void setAliasUser(String aliasUser) {
        this.aliasUser = aliasUser;
    }

    public String getNombreRealUser() {
        return nombreRealUser;
    }

    public void setNombreRealUser(String nombreRealUser) {
        this.nombreRealUser = nombreRealUser;
    }

    public String getDireccionFotoPerfilUser() {
        return direccionFotoPerfilUser;
    }

    public void setDireccionFotoPerfilUser(String direccionFotoPerfilUser) {
        this.direccionFotoPerfilUser = direccionFotoPerfilUser;
    }

    public String getApellidoUser() {
        return apellidoUser;
    }

    public void setApellidoUser(String apellidoUser) {
        this.apellidoUser = apellidoUser;
    }

    public String getSexoUser() {
        return sexoUser;
    }

    public void setSexoUser(String sexoUser) {
        this.sexoUser = sexoUser;
    }

    public String getDireccionFotoPortadaUser() {
        return direccionFotoPortadaUser;
    }

    public void setDireccionFotoPortadaUser(String direccionFotoPortadaUser) {
        this.direccionFotoPortadaUser = direccionFotoPortadaUser;
    }

    public Date getFechaCumpleanosUser() {
        return fechaCumpleanosUser;
    }

    public void setFechaCumpleanosUser(Date fechaCumpleanosUser) {
        this.fechaCumpleanosUser = fechaCumpleanosUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public Date getFechaUltimaActualizacion() {
        return fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }

    public Integer getCantidadFotografiasSubidas() {
        return cantidadFotografiasSubidas;
    }

    public void setCantidadFotografiasSubidas(Integer cantidadFotografiasSubidas) {
        this.cantidadFotografiasSubidas = cantidadFotografiasSubidas;
    }

    public Integer getCantidadAlbumesCreados() {
        return cantidadAlbumesCreados;
    }

    public void setCantidadAlbumesCreados(Integer cantidadAlbumesCreados) {
        this.cantidadAlbumesCreados = cantidadAlbumesCreados;
    }

    public Integer getCantidadSeguidores() {
        return cantidadSeguidores;
    }

    public void setCantidadSeguidores(Integer cantidadSeguidores) {
        this.cantidadSeguidores = cantidadSeguidores;
    }

    public Integer getCantidadSeguidos() {
        return cantidadSeguidos;
    }

    public void setCantidadSeguidos(Integer cantidadSeguidos) {
        this.cantidadSeguidos = cantidadSeguidos;
    }

    public String getUbicacionUser() {
        return ubicacionUser;
    }

    public void setUbicacionUser(String ubicacionUser) {
        this.ubicacionUser = ubicacionUser;
    }

    @XmlTransient
    public Collection<Fotografia> getFotografiaCollection() {
        return fotografiaCollection;
    }

    public void setFotografiaCollection(Collection<Fotografia> fotografiaCollection) {
        this.fotografiaCollection = fotografiaCollection;
    }

    @XmlTransient
    public Collection<Fotografia> getFotografiaCollection1() {
        return fotografiaCollection1;
    }

    public void setFotografiaCollection1(Collection<Fotografia> fotografiaCollection1) {
        this.fotografiaCollection1 = fotografiaCollection1;
    }

    @XmlTransient
    public Collection<FavoritoAlbum> getFavoritoAlbumCollection() {
        return favoritoAlbumCollection;
    }

    public void setFavoritoAlbumCollection(Collection<FavoritoAlbum> favoritoAlbumCollection) {
        this.favoritoAlbumCollection = favoritoAlbumCollection;
    }

    @XmlTransient
    public Collection<ComentarioFotografia> getComentarioFotografiaCollection() {
        return comentarioFotografiaCollection;
    }

    public void setComentarioFotografiaCollection(Collection<ComentarioFotografia> comentarioFotografiaCollection) {
        this.comentarioFotografiaCollection = comentarioFotografiaCollection;
    }

    @XmlTransient
    public Collection<ComentarioAlbum> getComentarioAlbumCollection() {
        return comentarioAlbumCollection;
    }

    public void setComentarioAlbumCollection(Collection<ComentarioAlbum> comentarioAlbumCollection) {
        this.comentarioAlbumCollection = comentarioAlbumCollection;
    }

    @XmlTransient
    public Collection<FavoritoFotografia> getFavoritoFotografiaCollection() {
        return favoritoFotografiaCollection;
    }

    public void setFavoritoFotografiaCollection(Collection<FavoritoFotografia> favoritoFotografiaCollection) {
        this.favoritoFotografiaCollection = favoritoFotografiaCollection;
    }

    @XmlTransient
    public Collection<Album> getAlbumCollection() {
        return albumCollection;
    }

    public void setAlbumCollection(Collection<Album> albumCollection) {
        this.albumCollection = albumCollection;
    }

    @XmlTransient
    public Collection<Seguidor> getSeguidorCollection() {
        return seguidorCollection;
    }

    public void setSeguidorCollection(Collection<Seguidor> seguidorCollection) {
        this.seguidorCollection = seguidorCollection;
    }

    @XmlTransient
    public Collection<Seguidor> getSeguidorCollection1() {
        return seguidorCollection1;
    }

    public void setSeguidorCollection1(Collection<Seguidor> seguidorCollection1) {
        this.seguidorCollection1 = seguidorCollection1;
    }

    @XmlTransient
    public Collection<Tag> getTagCollection() {
        return tagCollection;
    }

    public void setTagCollection(Collection<Tag> tagCollection) {
        this.tagCollection = tagCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Usuario[ idUser=" + idUser + " ]";
    }
    
}
