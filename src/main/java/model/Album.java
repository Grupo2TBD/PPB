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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ian
 */
@Entity
@Table(name = "ALBUM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Album.findAll", query = "SELECT a FROM Album a"),
    @NamedQuery(name = "Album.findByIdAlbum", query = "SELECT a FROM Album a WHERE a.idAlbum = :idAlbum"),
    @NamedQuery(name = "Album.findByNombreAlbum", query = "SELECT a FROM Album a WHERE a.nombreAlbum = :nombreAlbum"),
    @NamedQuery(name = "Album.findByFechacreacionAlbum", query = "SELECT a FROM Album a WHERE a.fechacreacionAlbum = :fechacreacionAlbum"),
    @NamedQuery(name = "Album.findByDescripcionAlbum", query = "SELECT a FROM Album a WHERE a.descripcionAlbum = :descripcionAlbum"),
    @NamedQuery(name = "Album.findByDireccionFotoPortadaAlbum", query = "SELECT a FROM Album a WHERE a.direccionFotoPortadaAlbum = :direccionFotoPortadaAlbum"),
    @NamedQuery(name = "Album.findByCantidadFotografiasAlbum", query = "SELECT a FROM Album a WHERE a.cantidadFotografiasAlbum = :cantidadFotografiasAlbum"),
    @NamedQuery(name = "Album.findByCantidadFavoritos", query = "SELECT a FROM Album a WHERE a.cantidadFavoritos = :cantidadFavoritos"),
    @NamedQuery(name = "Album.findByCantidadComentarios", query = "SELECT a FROM Album a WHERE a.cantidadComentarios = :cantidadComentarios"),
    @NamedQuery(name = "Album.findByUltimaActualizacionAlbum", query = "SELECT a FROM Album a WHERE a.ultimaActualizacionAlbum = :ultimaActualizacionAlbum")})
public class Album implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ALBUM")
    private Integer idAlbum;
    @Size(max = 20)
    @Column(name = "NOMBRE_ALBUM")
    private String nombreAlbum;
    @Column(name = "FECHACREACION_ALBUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacionAlbum;
    @Size(max = 30)
    @Column(name = "DESCRIPCION_ALBUM")
    private String descripcionAlbum;
    @Size(max = 50)
    @Column(name = "DIRECCION_FOTO_PORTADA_ALBUM")
    private String direccionFotoPortadaAlbum;
    @Column(name = "CANTIDAD_FOTOGRAFIAS_ALBUM")
    private Integer cantidadFotografiasAlbum;
    @Column(name = "CANTIDAD_FAVORITOS")
    private Integer cantidadFavoritos;
    @Column(name = "CANTIDAD_COMENTARIOS")
    private Integer cantidadComentarios;
    @Column(name = "ULTIMA_ACTUALIZACION_ALBUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaActualizacionAlbum;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "album")
    private Collection<FavoritoAlbum> favoritoAlbumCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "album")
    private Collection<AlbumFotografia> albumFotografiaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAlbum")
    private Collection<ComentarioAlbum> comentarioAlbumCollection;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    @ManyToOne
    private Usuario idUser;
    @JoinColumn(name = "ID_PERMISO_ALBUM", referencedColumnName = "ID_PERMISO_ALBUM")
    @ManyToOne
    private PermisoAlbum idPermisoAlbum;
    @JoinColumn(name = "ID_PRIVACIDAD", referencedColumnName = "ID_PRIVACIDAD")
    @ManyToOne
    private Privacidad idPrivacidad;

    public Album() {
    }

    public Album(Integer idAlbum) {
        this.idAlbum = idAlbum;
    }

    public Integer getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Integer idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }

    public Date getFechacreacionAlbum() {
        return fechacreacionAlbum;
    }

    public void setFechacreacionAlbum(Date fechacreacionAlbum) {
        this.fechacreacionAlbum = fechacreacionAlbum;
    }

    public String getDescripcionAlbum() {
        return descripcionAlbum;
    }

    public void setDescripcionAlbum(String descripcionAlbum) {
        this.descripcionAlbum = descripcionAlbum;
    }

    public String getDireccionFotoPortadaAlbum() {
        return direccionFotoPortadaAlbum;
    }

    public void setDireccionFotoPortadaAlbum(String direccionFotoPortadaAlbum) {
        this.direccionFotoPortadaAlbum = direccionFotoPortadaAlbum;
    }

    public Integer getCantidadFotografiasAlbum() {
        return cantidadFotografiasAlbum;
    }

    public void setCantidadFotografiasAlbum(Integer cantidadFotografiasAlbum) {
        this.cantidadFotografiasAlbum = cantidadFotografiasAlbum;
    }

    public Integer getCantidadFavoritos() {
        return cantidadFavoritos;
    }

    public void setCantidadFavoritos(Integer cantidadFavoritos) {
        this.cantidadFavoritos = cantidadFavoritos;
    }

    public Integer getCantidadComentarios() {
        return cantidadComentarios;
    }

    public void setCantidadComentarios(Integer cantidadComentarios) {
        this.cantidadComentarios = cantidadComentarios;
    }

    public Date getUltimaActualizacionAlbum() {
        return ultimaActualizacionAlbum;
    }

    public void setUltimaActualizacionAlbum(Date ultimaActualizacionAlbum) {
        this.ultimaActualizacionAlbum = ultimaActualizacionAlbum;
    }

    @XmlTransient
    public Collection<FavoritoAlbum> getFavoritoAlbumCollection() {
        return favoritoAlbumCollection;
    }

    public void setFavoritoAlbumCollection(Collection<FavoritoAlbum> favoritoAlbumCollection) {
        this.favoritoAlbumCollection = favoritoAlbumCollection;
    }

    @XmlTransient
    public Collection<AlbumFotografia> getAlbumFotografiaCollection() {
        return albumFotografiaCollection;
    }

    public void setAlbumFotografiaCollection(Collection<AlbumFotografia> albumFotografiaCollection) {
        this.albumFotografiaCollection = albumFotografiaCollection;
    }

    @XmlTransient
    public Collection<ComentarioAlbum> getComentarioAlbumCollection() {
        return comentarioAlbumCollection;
    }

    public void setComentarioAlbumCollection(Collection<ComentarioAlbum> comentarioAlbumCollection) {
        this.comentarioAlbumCollection = comentarioAlbumCollection;
    }

    public Usuario getIdUser() {
        return idUser;
    }

    public void setIdUser(Usuario idUser) {
        this.idUser = idUser;
    }

    public PermisoAlbum getIdPermisoAlbum() {
        return idPermisoAlbum;
    }

    public void setIdPermisoAlbum(PermisoAlbum idPermisoAlbum) {
        this.idPermisoAlbum = idPermisoAlbum;
    }

    public Privacidad getIdPrivacidad() {
        return idPrivacidad;
    }

    public void setIdPrivacidad(Privacidad idPrivacidad) {
        this.idPrivacidad = idPrivacidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlbum != null ? idAlbum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Album)) {
            return false;
        }
        Album other = (Album) object;
        if ((this.idAlbum == null && other.idAlbum != null) || (this.idAlbum != null && !this.idAlbum.equals(other.idAlbum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Album[ idAlbum=" + idAlbum + " ]";
    }
    
}
