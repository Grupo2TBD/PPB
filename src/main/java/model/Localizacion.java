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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ian
 */
@Entity
@Table(name = "LOCALIZACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Localizacion.findAll", query = "SELECT l FROM Localizacion l"),
    @NamedQuery(name = "Localizacion.findByIdLocalizacion", query = "SELECT l FROM Localizacion l WHERE l.idLocalizacion = :idLocalizacion"),
    @NamedQuery(name = "Localizacion.findByPuntoLocalizacion", query = "SELECT l FROM Localizacion l WHERE l.puntoLocalizacion = :puntoLocalizacion")})
public class Localizacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_LOCALIZACION")
    private Integer idLocalizacion;
    @Size(max = 255)
    @Column(name = "PUNTO_LOCALIZACION")
    private String puntoLocalizacion;
    @OneToMany(mappedBy = "idLocalizacion")
    private Collection<Fotografia> fotografiaCollection;

    public Localizacion() {
    }

    public Localizacion(Integer idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    public Integer getIdLocalizacion() {
        return idLocalizacion;
    }

    public void setIdLocalizacion(Integer idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    public String getPuntoLocalizacion() {
        return puntoLocalizacion;
    }

    public void setPuntoLocalizacion(String puntoLocalizacion) {
        this.puntoLocalizacion = puntoLocalizacion;
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
        hash += (idLocalizacion != null ? idLocalizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Localizacion)) {
            return false;
        }
        Localizacion other = (Localizacion) object;
        if ((this.idLocalizacion == null && other.idLocalizacion != null) || (this.idLocalizacion != null && !this.idLocalizacion.equals(other.idLocalizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Localizacion[ idLocalizacion=" + idLocalizacion + " ]";
    }
    
}
