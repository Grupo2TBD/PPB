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
public class SeguidorPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USER")
    private int idUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USU_ID_USER")
    private int usuIdUser;

    public SeguidorPK() {
    }

    public SeguidorPK(int idUser, int usuIdUser) {
        this.idUser = idUser;
        this.usuIdUser = usuIdUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getUsuIdUser() {
        return usuIdUser;
    }

    public void setUsuIdUser(int usuIdUser) {
        this.usuIdUser = usuIdUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUser;
        hash += (int) usuIdUser;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeguidorPK)) {
            return false;
        }
        SeguidorPK other = (SeguidorPK) object;
        if (this.idUser != other.idUser) {
            return false;
        }
        if (this.usuIdUser != other.usuIdUser) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.SeguidorPK[ idUser=" + idUser + ", usuIdUser=" + usuIdUser + " ]";
    }
    
}
