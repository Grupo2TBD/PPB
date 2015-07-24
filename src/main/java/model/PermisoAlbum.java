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
@Table(name = "PERMISO_ALBUM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PermisoAlbum.findAll", query = "SELECT p FROM PermisoAlbum p"),
    @NamedQuery(name = "PermisoAlbum.findByIdPermisoAlbum", query = "SELECT p FROM PermisoAlbum p WHERE p.idPermisoAlbum = :idPermisoAlbum"),
    @NamedQuery(name = "PermisoAlbum.findByCansharealbum", query = "SELECT p FROM PermisoAlbum p WHERE p.cansharealbum = :cansharealbum"),
    @NamedQuery(name = "PermisoAlbum.findByCanfavoritealbum", query = "SELECT p FROM PermisoAlbum p WHERE p.canfavoritealbum = :canfavoritealbum"),
    @NamedQuery(name = "PermisoAlbum.findByCancommentalbum", query = "SELECT p FROM PermisoAlbum p WHERE p.cancommentalbum = :cancommentalbum"),
    @NamedQuery(name = "PermisoAlbum.findByCandeletealbum", query = "SELECT p FROM PermisoAlbum p WHERE p.candeletealbum = :candeletealbum")})
public class PermisoAlbum implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERMISO_ALBUM")
    private Integer idPermisoAlbum;
    @Column(name = "CANSHAREALBUM")
    private Boolean cansharealbum;
    @Column(name = "CANFAVORITEALBUM")
    private Boolean canfavoritealbum;
    @Column(name = "CANCOMMENTALBUM")
    private Boolean cancommentalbum;
    @Column(name = "CANDELETEALBUM")
    private Boolean candeletealbum;
    @OneToMany(mappedBy = "idPermisoAlbum")
    private Collection<Album> albumCollection;

    public PermisoAlbum() {
    }

    public PermisoAlbum(Integer idPermisoAlbum) {
        this.idPermisoAlbum = idPermisoAlbum;
    }

    public Integer getIdPermisoAlbum() {
        return idPermisoAlbum;
    }

    public void setIdPermisoAlbum(Integer idPermisoAlbum) {
        this.idPermisoAlbum = idPermisoAlbum;
    }

    public Boolean getCansharealbum() {
        return cansharealbum;
    }

    public void setCansharealbum(Boolean cansharealbum) {
        this.cansharealbum = cansharealbum;
    }

    public Boolean getCanfavoritealbum() {
        return canfavoritealbum;
    }

    public void setCanfavoritealbum(Boolean canfavoritealbum) {
        this.canfavoritealbum = canfavoritealbum;
    }

    public Boolean getCancommentalbum() {
        return cancommentalbum;
    }

    public void setCancommentalbum(Boolean cancommentalbum) {
        this.cancommentalbum = cancommentalbum;
    }

    public Boolean getCandeletealbum() {
        return candeletealbum;
    }

    public void setCandeletealbum(Boolean candeletealbum) {
        this.candeletealbum = candeletealbum;
    }

    @XmlTransient
    public Collection<Album> getAlbumCollection() {
        return albumCollection;
    }

    public void setAlbumCollection(Collection<Album> albumCollection) {
        this.albumCollection = albumCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermisoAlbum != null ? idPermisoAlbum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermisoAlbum)) {
            return false;
        }
        PermisoAlbum other = (PermisoAlbum) object;
        if ((this.idPermisoAlbum == null && other.idPermisoAlbum != null) || (this.idPermisoAlbum != null && !this.idPermisoAlbum.equals(other.idPermisoAlbum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.PermisoAlbum[ idPermisoAlbum=" + idPermisoAlbum + " ]";
    }
    
}
