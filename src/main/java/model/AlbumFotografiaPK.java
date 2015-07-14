/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ian
 */
@Embeddable
public class AlbumFotografiaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ALBUM")
    private int idAlbum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PHOTO")
    private int idPhoto;

    public AlbumFotografiaPK() {
    }

    public AlbumFotografiaPK(int idAlbum, int idPhoto) {
        this.idAlbum = idAlbum;
        this.idPhoto = idPhoto;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public int getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(int idPhoto) {
        this.idPhoto = idPhoto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idAlbum;
        hash += (int) idPhoto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlbumFotografiaPK)) {
            return false;
        }
        AlbumFotografiaPK other = (AlbumFotografiaPK) object;
        if (this.idAlbum != other.idAlbum) {
            return false;
        }
        if (this.idPhoto != other.idPhoto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.AlbumFotografiaPK[ idAlbum=" + idAlbum + ", idPhoto=" + idPhoto + " ]";
    }
    
}
