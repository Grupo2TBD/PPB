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
@Table(name = "FAVORITO_FOTOGRAFIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FavoritoFotografia.findAll", query = "SELECT f FROM FavoritoFotografia f"),
    @NamedQuery(name = "FavoritoFotografia.findByIdUser", query = "SELECT f FROM FavoritoFotografia f WHERE f.favoritoFotografiaPK.idUser = :idUser"),
    @NamedQuery(name = "FavoritoFotografia.findByIdPhoto", query = "SELECT f FROM FavoritoFotografia f WHERE f.favoritoFotografiaPK.idPhoto = :idPhoto"),
    @NamedQuery(name = "FavoritoFotografia.findByFechaFavorito", query = "SELECT f FROM FavoritoFotografia f WHERE f.fechaFavorito = :fechaFavorito")})
public class FavoritoFotografia implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FavoritoFotografiaPK favoritoFotografiaPK;
    @Column(name = "FECHA_FAVORITO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFavorito;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "ID_PHOTO", referencedColumnName = "ID_PHOTO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Fotografia fotografia;

    public FavoritoFotografia() {
    }

    public FavoritoFotografia(FavoritoFotografiaPK favoritoFotografiaPK) {
        this.favoritoFotografiaPK = favoritoFotografiaPK;
    }

    public FavoritoFotografia(int idUser, int idPhoto) {
        this.favoritoFotografiaPK = new FavoritoFotografiaPK(idUser, idPhoto);
    }

    public FavoritoFotografiaPK getFavoritoFotografiaPK() {
        return favoritoFotografiaPK;
    }

    public void setFavoritoFotografiaPK(FavoritoFotografiaPK favoritoFotografiaPK) {
        this.favoritoFotografiaPK = favoritoFotografiaPK;
    }

    public Date getFechaFavorito() {
        return fechaFavorito;
    }

    public void setFechaFavorito(Date fechaFavorito) {
        this.fechaFavorito = fechaFavorito;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        hash += (favoritoFotografiaPK != null ? favoritoFotografiaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FavoritoFotografia)) {
            return false;
        }
        FavoritoFotografia other = (FavoritoFotografia) object;
        if ((this.favoritoFotografiaPK == null && other.favoritoFotografiaPK != null) || (this.favoritoFotografiaPK != null && !this.favoritoFotografiaPK.equals(other.favoritoFotografiaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.FavoritoFotografia[ favoritoFotografiaPK=" + favoritoFotografiaPK + " ]";
    }
    
}
