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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "FOTOGRAFIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fotografia.findAll", query = "SELECT f FROM Fotografia f"),
    @NamedQuery(name = "Fotografia.findByIdPhoto", query = "SELECT f FROM Fotografia f WHERE f.idPhoto = :idPhoto"),
    @NamedQuery(name = "Fotografia.findByFechaSubidaPhoto", query = "SELECT f FROM Fotografia f WHERE f.fechaSubidaPhoto = :fechaSubidaPhoto"),
    @NamedQuery(name = "Fotografia.findByFechaTomadaPhoto", query = "SELECT f FROM Fotografia f WHERE f.fechaTomadaPhoto = :fechaTomadaPhoto"),
    @NamedQuery(name = "Fotografia.findByCantidadVisitasPhoto", query = "SELECT f FROM Fotografia f WHERE f.cantidadVisitasPhoto = :cantidadVisitasPhoto"),
    @NamedQuery(name = "Fotografia.findByTituloPhoto", query = "SELECT f FROM Fotografia f WHERE f.tituloPhoto = :tituloPhoto"),
    @NamedQuery(name = "Fotografia.findByDescripcionPhoto", query = "SELECT f FROM Fotografia f WHERE f.descripcionPhoto = :descripcionPhoto"),
    @NamedQuery(name = "Fotografia.findByCantidadFavoritosPhoto", query = "SELECT f FROM Fotografia f WHERE f.cantidadFavoritosPhoto = :cantidadFavoritosPhoto"),
    @NamedQuery(name = "Fotografia.findByFormatoPhoto", query = "SELECT f FROM Fotografia f WHERE f.formatoPhoto = :formatoPhoto"),
    @NamedQuery(name = "Fotografia.findByDireccionFisicaPhoto", query = "SELECT f FROM Fotografia f WHERE f.direccionFisicaPhoto = :direccionFisicaPhoto"),
    @NamedQuery(name = "Fotografia.findByUltimaActualizacionPhoto", query = "SELECT f FROM Fotografia f WHERE f.ultimaActualizacionPhoto = :ultimaActualizacionPhoto"),
    @NamedQuery(name = "Fotografia.findByCantidadCompartidos", query = "SELECT f FROM Fotografia f WHERE f.cantidadCompartidos = :cantidadCompartidos"),
    @NamedQuery(name = "Fotografia.findByCantidadDescargadas", query = "SELECT f FROM Fotografia f WHERE f.cantidadDescargadas = :cantidadDescargadas"),
    @NamedQuery(name = "Fotografia.findByCantidadComentariosPositivos", query = "SELECT f FROM Fotografia f WHERE f.cantidadComentariosPositivos = :cantidadComentariosPositivos"),
    @NamedQuery(name = "Fotografia.findByCantidadComentariosNegativos", query = "SELECT f FROM Fotografia f WHERE f.cantidadComentariosNegativos = :cantidadComentariosNegativos"),
    @NamedQuery(name = "Fotografia.findByCantidadComentariosNeutros", query = "SELECT f FROM Fotografia f WHERE f.cantidadComentariosNeutros = :cantidadComentariosNeutros")})
public class Fotografia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PHOTO")
    private Integer idPhoto;
    @Column(name = "FECHA_SUBIDA_PHOTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSubidaPhoto;
    @Column(name = "FECHA_TOMADA_PHOTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTomadaPhoto;
    @Column(name = "CANTIDAD_VISITAS_PHOTO")
    private Integer cantidadVisitasPhoto;
    @Size(max = 20)
    @Column(name = "TITULO_PHOTO")
    private String tituloPhoto;
    @Size(max = 50)
    @Column(name = "DESCRIPCION_PHOTO")
    private String descripcionPhoto;
    @Column(name = "CANTIDAD_FAVORITOS_PHOTO")
    private Integer cantidadFavoritosPhoto;
    @Size(max = 20)
    @Column(name = "FORMATO_PHOTO")
    private String formatoPhoto;
    @Size(max = 50)
    @Column(name = "DIRECCION_FISICA_PHOTO")
    private String direccionFisicaPhoto;
    @Column(name = "ULTIMA_ACTUALIZACION_PHOTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaActualizacionPhoto;
    @Column(name = "CANTIDAD_COMPARTIDOS")
    private Integer cantidadCompartidos;
    @Column(name = "CANTIDAD_DESCARGADAS")
    private Integer cantidadDescargadas;
    @Column(name = "CANTIDAD_COMENTARIOS_POSITIVOS")
    private Integer cantidadComentariosPositivos;
    @Column(name = "CANTIDAD_COMENTARIOS_NEGATIVOS")
    private Integer cantidadComentariosNegativos;
    @Column(name = "CANTIDAD_COMENTARIOS_NEUTROS")
    private Integer cantidadComentariosNeutros;
    @JoinTable(name = "FOTOGRAFIA_TAG", joinColumns = {
        @JoinColumn(name = "ID_PHOTO", referencedColumnName = "ID_PHOTO")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_TAG", referencedColumnName = "ID_TAG")})
    @ManyToMany
    private Collection<Tag> tagCollection;
    @ManyToMany(mappedBy = "fotografiaCollection")
    private Collection<Usuario> usuarioCollection;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    @ManyToOne(optional = false)
    private Usuario idUser;
    @JoinColumn(name = "ID_CAMARA", referencedColumnName = "ID_CAMARA")
    @ManyToOne(optional = false)
    private Camara idCamara;
    @JoinColumn(name = "ID_LOCALIZACION", referencedColumnName = "ID_LOCALIZACION")
    @ManyToOne
    private Localizacion idLocalizacion;
    @JoinColumn(name = "ID_PRIVACIDAD", referencedColumnName = "ID_PRIVACIDAD")
    @ManyToOne(optional = false)
    private Privacidad idPrivacidad;
    @JoinColumn(name = "ID_PERMISO_FOTOGRAFIA", referencedColumnName = "ID_PERMISO_FOTOGRAFIA")
    @ManyToOne
    private PermisoFotografia idPermisoFotografia;
    @JoinColumn(name = "ID_TIPO_CLASIFICACION", referencedColumnName = "ID_TIPO_CLASIFICACION")
    @ManyToOne
    private TipoClasificacion idTipoClasificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fotografia")
    private Collection<AlbumFotografia> albumFotografiaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPhoto")
    private Collection<ComentarioFotografia> comentarioFotografiaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fotografia")
    private Collection<Exif> exifCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fotografia")
    private Collection<FavoritoFotografia> favoritoFotografiaCollection;

    public Fotografia() {
    }

    public Fotografia(Integer idPhoto) {
        this.idPhoto = idPhoto;
    }

    public Integer getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(Integer idPhoto) {
        this.idPhoto = idPhoto;
    }

    public Date getFechaSubidaPhoto() {
        return fechaSubidaPhoto;
    }

    public void setFechaSubidaPhoto(Date fechaSubidaPhoto) {
        this.fechaSubidaPhoto = fechaSubidaPhoto;
    }

    public Date getFechaTomadaPhoto() {
        return fechaTomadaPhoto;
    }

    public void setFechaTomadaPhoto(Date fechaTomadaPhoto) {
        this.fechaTomadaPhoto = fechaTomadaPhoto;
    }

    public Integer getCantidadVisitasPhoto() {
        return cantidadVisitasPhoto;
    }

    public void setCantidadVisitasPhoto(Integer cantidadVisitasPhoto) {
        this.cantidadVisitasPhoto = cantidadVisitasPhoto;
    }

    public String getTituloPhoto() {
        return tituloPhoto;
    }

    public void setTituloPhoto(String tituloPhoto) {
        this.tituloPhoto = tituloPhoto;
    }

    public String getDescripcionPhoto() {
        return descripcionPhoto;
    }

    public void setDescripcionPhoto(String descripcionPhoto) {
        this.descripcionPhoto = descripcionPhoto;
    }

    public Integer getCantidadFavoritosPhoto() {
        return cantidadFavoritosPhoto;
    }

    public void setCantidadFavoritosPhoto(Integer cantidadFavoritosPhoto) {
        this.cantidadFavoritosPhoto = cantidadFavoritosPhoto;
    }

    public String getFormatoPhoto() {
        return formatoPhoto;
    }

    public void setFormatoPhoto(String formatoPhoto) {
        this.formatoPhoto = formatoPhoto;
    }

    public String getDireccionFisicaPhoto() {
        return direccionFisicaPhoto;
    }

    public void setDireccionFisicaPhoto(String direccionFisicaPhoto) {
        this.direccionFisicaPhoto = direccionFisicaPhoto;
    }

    public Date getUltimaActualizacionPhoto() {
        return ultimaActualizacionPhoto;
    }

    public void setUltimaActualizacionPhoto(Date ultimaActualizacionPhoto) {
        this.ultimaActualizacionPhoto = ultimaActualizacionPhoto;
    }

    public Integer getCantidadCompartidos() {
        return cantidadCompartidos;
    }

    public void setCantidadCompartidos(Integer cantidadCompartidos) {
        this.cantidadCompartidos = cantidadCompartidos;
    }

    public Integer getCantidadDescargadas() {
        return cantidadDescargadas;
    }

    public void setCantidadDescargadas(Integer cantidadDescargadas) {
        this.cantidadDescargadas = cantidadDescargadas;
    }

    public Integer getCantidadComentariosPositivos() {
        return cantidadComentariosPositivos;
    }

    public void setCantidadComentariosPositivos(Integer cantidadComentariosPositivos) {
        this.cantidadComentariosPositivos = cantidadComentariosPositivos;
    }

    public Integer getCantidadComentariosNegativos() {
        return cantidadComentariosNegativos;
    }

    public void setCantidadComentariosNegativos(Integer cantidadComentariosNegativos) {
        this.cantidadComentariosNegativos = cantidadComentariosNegativos;
    }

    public Integer getCantidadComentariosNeutros() {
        return cantidadComentariosNeutros;
    }

    public void setCantidadComentariosNeutros(Integer cantidadComentariosNeutros) {
        this.cantidadComentariosNeutros = cantidadComentariosNeutros;
    }

    @XmlTransient
    public Collection<Tag> getTagCollection() {
        return tagCollection;
    }

    public void setTagCollection(Collection<Tag> tagCollection) {
        this.tagCollection = tagCollection;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    public Usuario getIdUser() {
        return idUser;
    }

    public void setIdUser(Usuario idUser) {
        this.idUser = idUser;
    }

    public Camara getIdCamara() {
        return idCamara;
    }

    public void setIdCamara(Camara idCamara) {
        this.idCamara = idCamara;
    }

    public Localizacion getIdLocalizacion() {
        return idLocalizacion;
    }

    public void setIdLocalizacion(Localizacion idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    public Privacidad getIdPrivacidad() {
        return idPrivacidad;
    }

    public void setIdPrivacidad(Privacidad idPrivacidad) {
        this.idPrivacidad = idPrivacidad;
    }

    public PermisoFotografia getIdPermisoFotografia() {
        return idPermisoFotografia;
    }

    public void setIdPermisoFotografia(PermisoFotografia idPermisoFotografia) {
        this.idPermisoFotografia = idPermisoFotografia;
    }

    public TipoClasificacion getIdTipoClasificacion() {
        return idTipoClasificacion;
    }

    public void setIdTipoClasificacion(TipoClasificacion idTipoClasificacion) {
        this.idTipoClasificacion = idTipoClasificacion;
    }

    @XmlTransient
    public Collection<AlbumFotografia> getAlbumFotografiaCollection() {
        return albumFotografiaCollection;
    }

    public void setAlbumFotografiaCollection(Collection<AlbumFotografia> albumFotografiaCollection) {
        this.albumFotografiaCollection = albumFotografiaCollection;
    }

    @XmlTransient
    public Collection<ComentarioFotografia> getComentarioFotografiaCollection() {
        return comentarioFotografiaCollection;
    }

    public void setComentarioFotografiaCollection(Collection<ComentarioFotografia> comentarioFotografiaCollection) {
        this.comentarioFotografiaCollection = comentarioFotografiaCollection;
    }

    @XmlTransient
    public Collection<Exif> getExifCollection() {
        return exifCollection;
    }

    public void setExifCollection(Collection<Exif> exifCollection) {
        this.exifCollection = exifCollection;
    }

    @XmlTransient
    public Collection<FavoritoFotografia> getFavoritoFotografiaCollection() {
        return favoritoFotografiaCollection;
    }

    public void setFavoritoFotografiaCollection(Collection<FavoritoFotografia> favoritoFotografiaCollection) {
        this.favoritoFotografiaCollection = favoritoFotografiaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPhoto != null ? idPhoto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fotografia)) {
            return false;
        }
        Fotografia other = (Fotografia) object;
        if ((this.idPhoto == null && other.idPhoto != null) || (this.idPhoto != null && !this.idPhoto.equals(other.idPhoto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Fotografia[ idPhoto=" + idPhoto + " ]";
    }
    
}
