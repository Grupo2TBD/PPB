/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ian
 */
@Entity
@Table(name = "EXIF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exif.findAll", query = "SELECT e FROM Exif e"),
    @NamedQuery(name = "Exif.findByIdPhoto", query = "SELECT e FROM Exif e WHERE e.exifPK.idPhoto = :idPhoto"),
    @NamedQuery(name = "Exif.findByIdCamara", query = "SELECT e FROM Exif e WHERE e.exifPK.idCamara = :idCamara"),
    @NamedQuery(name = "Exif.findByAperturaExif", query = "SELECT e FROM Exif e WHERE e.aperturaExif = :aperturaExif"),
    @NamedQuery(name = "Exif.findByLargoFoco", query = "SELECT e FROM Exif e WHERE e.largoFoco = :largoFoco"),
    @NamedQuery(name = "Exif.findByFlashExif", query = "SELECT e FROM Exif e WHERE e.flashExif = :flashExif")})
public class Exif implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ExifPK exifPK;
    @Size(max = 20)
    @Column(name = "APERTURA_EXIF")
    private String aperturaExif;
    @Column(name = "LARGO_FOCO")
    private Integer largoFoco;
    @Column(name = "FLASH_EXIF")
    private Boolean flashExif;
    @JoinColumn(name = "ID_PHOTO", referencedColumnName = "ID_PHOTO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Fotografia fotografia;
    @JoinColumn(name = "ID_CAMARA", referencedColumnName = "ID_CAMARA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Camara camara;

    public Exif() {
    }

    public Exif(ExifPK exifPK) {
        this.exifPK = exifPK;
    }

    public Exif(int idPhoto, int idCamara) {
        this.exifPK = new ExifPK(idPhoto, idCamara);
    }

    public ExifPK getExifPK() {
        return exifPK;
    }

    public void setExifPK(ExifPK exifPK) {
        this.exifPK = exifPK;
    }

    public String getAperturaExif() {
        return aperturaExif;
    }

    public void setAperturaExif(String aperturaExif) {
        this.aperturaExif = aperturaExif;
    }

    public Integer getLargoFoco() {
        return largoFoco;
    }

    public void setLargoFoco(Integer largoFoco) {
        this.largoFoco = largoFoco;
    }

    public Boolean getFlashExif() {
        return flashExif;
    }

    public void setFlashExif(Boolean flashExif) {
        this.flashExif = flashExif;
    }

    public Fotografia getFotografia() {
        return fotografia;
    }

    public void setFotografia(Fotografia fotografia) {
        this.fotografia = fotografia;
    }

    public Camara getCamara() {
        return camara;
    }

    public void setCamara(Camara camara) {
        this.camara = camara;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (exifPK != null ? exifPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exif)) {
            return false;
        }
        Exif other = (Exif) object;
        if ((this.exifPK == null && other.exifPK != null) || (this.exifPK != null && !this.exifPK.equals(other.exifPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Exif[ exifPK=" + exifPK + " ]";
    }
    
}
