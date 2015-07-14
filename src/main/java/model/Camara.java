/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "CAMARA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Camara.findAll", query = "SELECT c FROM Camara c"),
    @NamedQuery(name = "Camara.findByIdCamara", query = "SELECT c FROM Camara c WHERE c.idCamara = :idCamara"),
    @NamedQuery(name = "Camara.findByMegapixelesCamara", query = "SELECT c FROM Camara c WHERE c.megapixelesCamara = :megapixelesCamara"),
    @NamedQuery(name = "Camara.findByZoomCamara", query = "SELECT c FROM Camara c WHERE c.zoomCamara = :zoomCamara"),
    @NamedQuery(name = "Camara.findByTamanoPantallaCamara", query = "SELECT c FROM Camara c WHERE c.tamanoPantallaCamara = :tamanoPantallaCamara"),
    @NamedQuery(name = "Camara.findByTipoGuardadoCamara", query = "SELECT c FROM Camara c WHERE c.tipoGuardadoCamara = :tipoGuardadoCamara"),
    @NamedQuery(name = "Camara.findByDireccionImagenCamara", query = "SELECT c FROM Camara c WHERE c.direccionImagenCamara = :direccionImagenCamara"),
    @NamedQuery(name = "Camara.findByNombreCamara", query = "SELECT c FROM Camara c WHERE c.nombreCamara = :nombreCamara"),
    @NamedQuery(name = "Camara.findByMarcaCamara", query = "SELECT c FROM Camara c WHERE c.marcaCamara = :marcaCamara"),
    @NamedQuery(name = "Camara.findByPesoCamara", query = "SELECT c FROM Camara c WHERE c.pesoCamara = :pesoCamara"),
    @NamedQuery(name = "Camara.findByCantidadFotografiasCamara", query = "SELECT c FROM Camara c WHERE c.cantidadFotografiasCamara = :cantidadFotografiasCamara")})
public class Camara implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CAMARA")
    private Integer idCamara;
    @Column(name = "MEGAPIXELES_CAMARA")
    private Integer megapixelesCamara;
    @Column(name = "ZOOM_CAMARA")
    private Integer zoomCamara;
    @Column(name = "TAMANO_PANTALLA_CAMARA")
    private Integer tamanoPantallaCamara;
    @Size(max = 20)
    @Column(name = "TIPO_GUARDADO_CAMARA")
    private String tipoGuardadoCamara;
    @Size(max = 50)
    @Column(name = "DIRECCION_IMAGEN_CAMARA")
    private String direccionImagenCamara;
    @Size(max = 20)
    @Column(name = "NOMBRE_CAMARA")
    private String nombreCamara;
    @Size(max = 20)
    @Column(name = "MARCA_CAMARA")
    private String marcaCamara;
    @Column(name = "PESO_CAMARA")
    private Integer pesoCamara;
    @Column(name = "CANTIDAD_FOTOGRAFIAS_CAMARA")
    private Integer cantidadFotografiasCamara;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCamara")
    private Collection<Fotografia> fotografiaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "camara")
    private Collection<Exif> exifCollection;

    public Camara() {
    }

    public Camara(Integer idCamara) {
        this.idCamara = idCamara;
    }

    public Integer getIdCamara() {
        return idCamara;
    }

    public void setIdCamara(Integer idCamara) {
        this.idCamara = idCamara;
    }

    public Integer getMegapixelesCamara() {
        return megapixelesCamara;
    }

    public void setMegapixelesCamara(Integer megapixelesCamara) {
        this.megapixelesCamara = megapixelesCamara;
    }

    public Integer getZoomCamara() {
        return zoomCamara;
    }

    public void setZoomCamara(Integer zoomCamara) {
        this.zoomCamara = zoomCamara;
    }

    public Integer getTamanoPantallaCamara() {
        return tamanoPantallaCamara;
    }

    public void setTamanoPantallaCamara(Integer tamanoPantallaCamara) {
        this.tamanoPantallaCamara = tamanoPantallaCamara;
    }

    public String getTipoGuardadoCamara() {
        return tipoGuardadoCamara;
    }

    public void setTipoGuardadoCamara(String tipoGuardadoCamara) {
        this.tipoGuardadoCamara = tipoGuardadoCamara;
    }

    public String getDireccionImagenCamara() {
        return direccionImagenCamara;
    }

    public void setDireccionImagenCamara(String direccionImagenCamara) {
        this.direccionImagenCamara = direccionImagenCamara;
    }

    public String getNombreCamara() {
        return nombreCamara;
    }

    public void setNombreCamara(String nombreCamara) {
        this.nombreCamara = nombreCamara;
    }

    public String getMarcaCamara() {
        return marcaCamara;
    }

    public void setMarcaCamara(String marcaCamara) {
        this.marcaCamara = marcaCamara;
    }

    public Integer getPesoCamara() {
        return pesoCamara;
    }

    public void setPesoCamara(Integer pesoCamara) {
        this.pesoCamara = pesoCamara;
    }

    public Integer getCantidadFotografiasCamara() {
        return cantidadFotografiasCamara;
    }

    public void setCantidadFotografiasCamara(Integer cantidadFotografiasCamara) {
        this.cantidadFotografiasCamara = cantidadFotografiasCamara;
    }

    @XmlTransient
    public Collection<Fotografia> getFotografiaCollection() {
        return fotografiaCollection;
    }

    public void setFotografiaCollection(Collection<Fotografia> fotografiaCollection) {
        this.fotografiaCollection = fotografiaCollection;
    }

    @XmlTransient
    public Collection<Exif> getExifCollection() {
        return exifCollection;
    }

    public void setExifCollection(Collection<Exif> exifCollection) {
        this.exifCollection = exifCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCamara != null ? idCamara.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Camara)) {
            return false;
        }
        Camara other = (Camara) object;
        if ((this.idCamara == null && other.idCamara != null) || (this.idCamara != null && !this.idCamara.equals(other.idCamara))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Camara[ idCamara=" + idCamara + " ]";
    }
    
}
