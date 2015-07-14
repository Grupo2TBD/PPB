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
public class ExifPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PHOTO")
    private int idPhoto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CAMARA")
    private int idCamara;

    public ExifPK() {
    }

    public ExifPK(int idPhoto, int idCamara) {
        this.idPhoto = idPhoto;
        this.idCamara = idCamara;
    }

    public int getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(int idPhoto) {
        this.idPhoto = idPhoto;
    }

    public int getIdCamara() {
        return idCamara;
    }

    public void setIdCamara(int idCamara) {
        this.idCamara = idCamara;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPhoto;
        hash += (int) idCamara;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExifPK)) {
            return false;
        }
        ExifPK other = (ExifPK) object;
        if (this.idPhoto != other.idPhoto) {
            return false;
        }
        if (this.idCamara != other.idCamara) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ExifPK[ idPhoto=" + idPhoto + ", idCamara=" + idCamara + " ]";
    }
    
}
