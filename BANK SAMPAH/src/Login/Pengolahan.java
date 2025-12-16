/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "pengolahan")
@NamedQueries({
    @NamedQuery(name = "Pengolahan.findAll", query = "SELECT p FROM Pengolahan p"),
    @NamedQuery(name = "Pengolahan.findByIdPengolahan", query = "SELECT p FROM Pengolahan p WHERE p.idPengolahan = :idPengolahan"),
    @NamedQuery(name = "Pengolahan.findByHasilPengolahan", query = "SELECT p FROM Pengolahan p WHERE p.hasilPengolahan = :hasilPengolahan"),
    @NamedQuery(name = "Pengolahan.findByBeratHasilPengolahan", query = "SELECT p FROM Pengolahan p WHERE p.beratHasilPengolahan = :beratHasilPengolahan"),
    @NamedQuery(name = "Pengolahan.findByTanggalPengolahan", query = "SELECT p FROM Pengolahan p WHERE p.tanggalPengolahan = :tanggalPengolahan"),
    @NamedQuery(name = "Pengolahan.findByMetodePengolahan", query = "SELECT p FROM Pengolahan p WHERE p.metodePengolahan = :metodePengolahan")})
public class Pengolahan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_pengolahan")
    private Integer idPengolahan;
    @Column(name = "hasil_pengolahan")
    private String hasilPengolahan;
    @Column(name = "berat_hasil_pengolahan")
    private String beratHasilPengolahan;
    @Column(name = "tanggal_pengolahan")
    @Temporal(TemporalType.DATE)
    private Date tanggalPengolahan;
    @Column(name = "metode_pengolahan")
    private String metodePengolahan;
    @JoinColumn(name = "id_jenissampah", referencedColumnName = "id_jenissampah")
    @ManyToOne
    private JenisSampah idJenissampah;
    @JoinColumn(name = "id_petugas", referencedColumnName = "id_petugas")
    @ManyToOne
    private Petugas idPetugas;
    @OneToMany(mappedBy = "idPengolahan")
    private Collection<PenjualanMitra> penjualanMitraCollection;
    @OneToMany(mappedBy = "idPengolahan")
    private Collection<StokGudang> stokGudangCollection;

    public Pengolahan() {
    }

    public Pengolahan(Integer idPengolahan) {
        this.idPengolahan = idPengolahan;
    }

    public Integer getIdPengolahan() {
        return idPengolahan;
    }

    public void setIdPengolahan(Integer idPengolahan) {
        this.idPengolahan = idPengolahan;
    }

    public String getHasilPengolahan() {
        return hasilPengolahan;
    }

    public void setHasilPengolahan(String hasilPengolahan) {
        this.hasilPengolahan = hasilPengolahan;
    }

    public String getBeratHasilPengolahan() {
        return beratHasilPengolahan;
    }

    public void setBeratHasilPengolahan(String beratHasilPengolahan) {
        this.beratHasilPengolahan = beratHasilPengolahan;
    }

    public Date getTanggalPengolahan() {
        return tanggalPengolahan;
    }

    public void setTanggalPengolahan(Date tanggalPengolahan) {
        this.tanggalPengolahan = tanggalPengolahan;
    }

    public String getMetodePengolahan() {
        return metodePengolahan;
    }

    public void setMetodePengolahan(String metodePengolahan) {
        this.metodePengolahan = metodePengolahan;
    }

    public JenisSampah getIdJenissampah() {
        return idJenissampah;
    }

    public void setIdJenissampah(JenisSampah idJenissampah) {
        this.idJenissampah = idJenissampah;
    }

    public Petugas getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(Petugas idPetugas) {
        this.idPetugas = idPetugas;
    }
    public Collection<PenjualanMitra> getPenjualanMitraCollection() {
        return penjualanMitraCollection;
    }

    public void setPenjualanMitraCollection(Collection<PenjualanMitra> penjualanMitraCollection) {
        this.penjualanMitraCollection = penjualanMitraCollection;
    }
    public Collection<StokGudang> getStokGudangCollection() {
        return stokGudangCollection;
    }

    public void setStokGudangCollection(Collection<StokGudang> stokGudangCollection) {
        this.stokGudangCollection = stokGudangCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPengolahan != null ? idPengolahan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pengolahan)) {
            return false;
        }
        Pengolahan other = (Pengolahan) object;
        if ((this.idPengolahan == null && other.idPengolahan != null) || (this.idPengolahan != null && !this.idPengolahan.equals(other.idPengolahan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return hasilPengolahan;
    }
    
}
