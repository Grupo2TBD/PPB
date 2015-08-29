/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ian
 */
@Entity
@Table(name = "PERMISO_FOTOGRAFIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PermisoFotografia.findAll", query = "SELECT p FROM PermisoFotografia p"),
    @NamedQuery(name = "PermisoFotografia.findByIdPermisoFotografia", query = "SELECT p FROM PermisoFotografia p WHERE p.idPermisoFotografia = :idPermisoFotografia"),
    @NamedQuery(name = "PermisoFotografia.findByCansharephoto", query = "SELECT p FROM PermisoFotografia p WHERE p.cansharephoto = :cansharephoto"),
    @NamedQuery(name = "PermisoFotografia.findByCancommentphoto", query = "SELECT p FROM PermisoFotografia p WHERE p.cancommentphoto = :cancommentphoto"),
    @NamedQuery(name = "PermisoFotografia.findByCandownloadphoto", query = "SELECT p FROM PermisoFotografia p WHERE p.candownloadphoto = :candownloadphoto")})
public class PermisoFotografia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERMISO_FOTOGRAFIA")
    private Integer idPermisoFotografia;
    @Column(name = "CANSHAREPHOTO")
    private Boolean cansharephoto;
    @Column(name = "CANCOMMENTPHOTO")
    private Boolean cancommentphoto;
    @Column(name = "CANDOWNLOADPHOTO")
    private Boolean candownloadphoto;
    @OneToMany(mappedBy = "idPermisoFotografia")
    private Collection<Fotografia> fotografiaCollection;

    public PermisoFotografia() {
    }

    public PermisoFotografia(Integer idPermisoFotografia) {
        this.idPermisoFotografia = idPermisoFotografia;
    }

    public Integer getIdPermisoFotografia() {
        return idPermisoFotografia;
    }

    public void setIdPermisoFotografia(Integer idPermisoFotografia) {
        this.idPermisoFotografia = idPermisoFotografia;
    }

    public Boolean getCansharephoto() {
        return cansharephoto;
    }

    public void setCansharephoto(Boolean cansharephoto) {
        this.cansharephoto = cansharephoto;
    }

    public Boolean getCancommentphoto() {
        return cancommentphoto;
    }

    public void setCancommentphoto(Boolean cancommentphoto) {
        this.cancommentphoto = cancommentphoto;
    }

    public Boolean getCandownloadphoto() {
        return candownloadphoto;
    }

    public void setCandownloadphoto(Boolean candownloadphoto) {
        this.candownloadphoto = candownloadphoto;
    }

    @XmlTransient
    public Collection<Fotografia> getFotografiaCollection() {
        return fotografiaCollection;
    }

    public void setFotografiaCollection(Collection<Fotografia> fotografiaCollection) {
        this.fotografiaCollection = fotografiaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermisoFotografia != null ? idPermisoFotografia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermisoFotografia)) {
            return false;
        }
        PermisoFotografia other = (PermisoFotografia) object;
        if ((this.idPermisoFotografia == null && other.idPermisoFotografia != null) || (this.idPermisoFotografia != null && !this.idPermisoFotografia.equals(other.idPermisoFotografia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.PermisoFotografia[ idPermisoFotografia=" + idPermisoFotografia + " ]";
    }
    
}
