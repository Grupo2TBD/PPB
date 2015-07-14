/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ian
 */
@Entity
@Table(name = "COMENTARIO_ALBUM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComentarioAlbum.findAll", query = "SELECT c FROM ComentarioAlbum c"),
    @NamedQuery(name = "ComentarioAlbum.findByIdComentarioAlbum", query = "SELECT c FROM ComentarioAlbum c WHERE c.idComentarioAlbum = :idComentarioAlbum"),
    @NamedQuery(name = "ComentarioAlbum.findByDescripcionComentarioAlbum", query = "SELECT c FROM ComentarioAlbum c WHERE c.descripcionComentarioAlbum = :descripcionComentarioAlbum"),
    @NamedQuery(name = "ComentarioAlbum.findByFechaPublicacionComentarioAlbum", query = "SELECT c FROM ComentarioAlbum c WHERE c.fechaPublicacionComentarioAlbum = :fechaPublicacionComentarioAlbum")})
public class ComentarioAlbum implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_COMENTARIO_ALBUM")
    private Integer idComentarioAlbum;
    @Size(max = 140)
    @Column(name = "DESCRIPCION_COMENTARIO_ALBUM")
    private String descripcionComentarioAlbum;
    @Column(name = "FECHA_PUBLICACION_COMENTARIO_ALBUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPublicacionComentarioAlbum;
    @JoinColumn(name = "ID_ALBUM", referencedColumnName = "ID_ALBUM")
    @ManyToOne(optional = false)
    private Album idAlbum;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    @ManyToOne(optional = false)
    private Usuario idUser;

    public ComentarioAlbum() {
    }

    public ComentarioAlbum(Integer idComentarioAlbum) {
        this.idComentarioAlbum = idComentarioAlbum;
    }

    public Integer getIdComentarioAlbum() {
        return idComentarioAlbum;
    }

    public void setIdComentarioAlbum(Integer idComentarioAlbum) {
        this.idComentarioAlbum = idComentarioAlbum;
    }

    public String getDescripcionComentarioAlbum() {
        return descripcionComentarioAlbum;
    }

    public void setDescripcionComentarioAlbum(String descripcionComentarioAlbum) {
        this.descripcionComentarioAlbum = descripcionComentarioAlbum;
    }

    public Date getFechaPublicacionComentarioAlbum() {
        return fechaPublicacionComentarioAlbum;
    }

    public void setFechaPublicacionComentarioAlbum(Date fechaPublicacionComentarioAlbum) {
        this.fechaPublicacionComentarioAlbum = fechaPublicacionComentarioAlbum;
    }

    public Album getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Album idAlbum) {
        this.idAlbum = idAlbum;
    }

    public Usuario getIdUser() {
        return idUser;
    }

    public void setIdUser(Usuario idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComentarioAlbum != null ? idComentarioAlbum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComentarioAlbum)) {
            return false;
        }
        ComentarioAlbum other = (ComentarioAlbum) object;
        if ((this.idComentarioAlbum == null && other.idComentarioAlbum != null) || (this.idComentarioAlbum != null && !this.idComentarioAlbum.equals(other.idComentarioAlbum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ComentarioAlbum[ idComentarioAlbum=" + idComentarioAlbum + " ]";
    }
    
}
