/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ian
 */
@Entity
@Table(name = "ALBUM_FOTOGRAFIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AlbumFotografia.findAll", query = "SELECT a FROM AlbumFotografia a"),
    @NamedQuery(name = "AlbumFotografia.findByIdAlbum", query = "SELECT a FROM AlbumFotografia a WHERE a.albumFotografiaPK.idAlbum = :idAlbum"),
    @NamedQuery(name = "AlbumFotografia.findByIdPhoto", query = "SELECT a FROM AlbumFotografia a WHERE a.albumFotografiaPK.idPhoto = :idPhoto"),
    @NamedQuery(name = "AlbumFotografia.findByFechaAgregadoAlbum", query = "SELECT a FROM AlbumFotografia a WHERE a.fechaAgregadoAlbum = :fechaAgregadoAlbum")})
public class AlbumFotografia implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AlbumFotografiaPK albumFotografiaPK;
    @Column(name = "FECHA_AGREGADO_ALBUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAgregadoAlbum;
    @JoinColumn(name = "ID_ALBUM", referencedColumnName = "ID_ALBUM", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Album album;
    @JoinColumn(name = "ID_PHOTO", referencedColumnName = "ID_PHOTO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Fotografia fotografia;

    public AlbumFotografia() {
    }

    public AlbumFotografia(AlbumFotografiaPK albumFotografiaPK) {
        this.albumFotografiaPK = albumFotografiaPK;
    }

    public AlbumFotografia(int idAlbum, int idPhoto) {
        this.albumFotografiaPK = new AlbumFotografiaPK(idAlbum, idPhoto);
    }

    public AlbumFotografiaPK getAlbumFotografiaPK() {
        return albumFotografiaPK;
    }

    public void setAlbumFotografiaPK(AlbumFotografiaPK albumFotografiaPK) {
        this.albumFotografiaPK = albumFotografiaPK;
    }

    public Date getFechaAgregadoAlbum() {
        return fechaAgregadoAlbum;
    }

    public void setFechaAgregadoAlbum(Date fechaAgregadoAlbum) {
        this.fechaAgregadoAlbum = fechaAgregadoAlbum;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Fotografia getFotografia() {
        return fotografia;
    }

    public void setFotografia(Fotografia fotografia) {
        this.fotografia = fotografia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (albumFotografiaPK != null ? albumFotografiaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlbumFotografia)) {
            return false;
        }
        AlbumFotografia other = (AlbumFotografia) object;
        if ((this.albumFotografiaPK == null && other.albumFotografiaPK != null) || (this.albumFotografiaPK != null && !this.albumFotografiaPK.equals(other.albumFotografiaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.AlbumFotografia[ albumFotografiaPK=" + albumFotografiaPK + " ]";
    }
    
}
