/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

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

/**
 *
 * @author HP
 */
@Entity
@Table(name = "mitra_jual")
@NamedQueries({
    @NamedQuery(name = "MitraJual.findAll", query = "SELECT m FROM MitraJual m"),
    @NamedQuery(name = "MitraJual.findByIdMitra", query = "SELECT m FROM MitraJual m WHERE m.idMitra = :idMitra"),
    @NamedQuery(name = "MitraJual.findByNamaMitra", query = "SELECT m FROM MitraJual m WHERE m.namaMitra = :namaMitra"),
    @NamedQuery(name = "MitraJual.findByAlamatMitra", query = "SELECT m FROM MitraJual m WHERE m.alamatMitra = :alamatMitra"),
    @NamedQuery(name = "MitraJual.findByTelponMitra", query = "SELECT m FROM MitraJual m WHERE m.telponMitra = :telponMitra"),
    @NamedQuery(name = "MitraJual.findByJenisKerjaSama", query = "SELECT m FROM MitraJual m WHERE m.jenisKerjaSama = :jenisKerjaSama")})
public class MitraJual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_mitra")
    private Integer idMitra;
    @Column(name = "nama_mitra")
    private String namaMitra;
    @Column(name = "alamat_mitra")
    private String alamatMitra;
    @Column(name = "telpon_mitra")
    private String telponMitra;
    @Column(name = "jenis_kerja_sama")
    private String jenisKerjaSama;
    @OneToMany(mappedBy = "idMitra")
    private Collection<PenjualanMitra> penjualanMitraCollection;

    public MitraJual() {
    }

    public MitraJual(Integer idMitra) {
        this.idMitra = idMitra;
    }

    public Integer getIdMitra() {
        return idMitra;
    }

    public void setIdMitra(Integer idMitra) {
        this.idMitra = idMitra;
    }

    public String getNamaMitra() {
        return namaMitra;
    }

    public void setNamaMitra(String namaMitra) {
        this.namaMitra = namaMitra;
    }

    public String getAlamatMitra() {
        return alamatMitra;
    }

    public void setAlamatMitra(String alamatMitra) {
        this.alamatMitra = alamatMitra;
    }

    public String getTelponMitra() {
        return telponMitra;
    }

    public void setTelponMitra(String telponMitra) {
        this.telponMitra = telponMitra;
    }

    public String getJenisKerjaSama() {
        return jenisKerjaSama;
    }

    public void setJenisKerjaSama(String jenisKerjaSama) {
        this.jenisKerjaSama = jenisKerjaSama;
    }
    public Collection<PenjualanMitra> getPenjualanMitraCollection() {
        return penjualanMitraCollection;
    }

    public void setPenjualanMitraCollection(Collection<PenjualanMitra> penjualanMitraCollection) {
        this.penjualanMitraCollection = penjualanMitraCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMitra != null ? idMitra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MitraJual)) {
            return false;
        }
        MitraJual other = (MitraJual) object;
        if ((this.idMitra == null && other.idMitra != null) || (this.idMitra != null && !this.idMitra.equals(other.idMitra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return namaMitra;
    }
    
}
