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
public class FavoritoFotografiaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USER")
    private int idUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PHOTO")
    private int idPhoto;

    public FavoritoFotografiaPK() {
    }

    public FavoritoFotografiaPK(int idUser, int idPhoto) {
        this.idUser = idUser;
        this.idPhoto = idPhoto;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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
        hash += (int) idUser;
        hash += (int) idPhoto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FavoritoFotografiaPK)) {
            return false;
        }
        FavoritoFotografiaPK other = (FavoritoFotografiaPK) object;
        if (this.idUser != other.idUser) {
            return false;
        }
        if (this.idPhoto != other.idPhoto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.FavoritoFotografiaPK[ idUser=" + idUser + ", idPhoto=" + idPhoto + " ]";
    }
    
}
