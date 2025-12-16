/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import java.io.Serializable;
import java.math.BigInteger;
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

/**
 *
 * @author HP
 */
@Entity
@Table(name = "penjualan_mitra")
@NamedQueries({
    @NamedQuery(name = "PenjualanMitra.findAll", query = "SELECT p FROM PenjualanMitra p"),
    @NamedQuery(name = "PenjualanMitra.findByIdPenjualanMitra", query = "SELECT p FROM PenjualanMitra p WHERE p.idPenjualanMitra = :idPenjualanMitra"),
    @NamedQuery(name = "PenjualanMitra.findByTanggalPenjualanMitra", query = "SELECT p FROM PenjualanMitra p WHERE p.tanggalPenjualanMitra = :tanggalPenjualanMitra"),
    @NamedQuery(name = "PenjualanMitra.findByBeratKgPenjualanMitra", query = "SELECT p FROM PenjualanMitra p WHERE p.beratKgPenjualanMitra = :beratKgPenjualanMitra"),
    @NamedQuery(name = "PenjualanMitra.findByTotalHargaPenjualanMitra", query = "SELECT p FROM PenjualanMitra p WHERE p.totalHargaPenjualanMitra = :totalHargaPenjualanMitra")})
public class PenjualanMitra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_penjualan_mitra")
    private Integer idPenjualanMitra;
    @Column(name = "tanggal_penjualan_mitra")
    @Temporal(TemporalType.DATE)
    private Date tanggalPenjualanMitra;
    @Column(name = "berat_kg_penjualan_mitra")
    private BigInteger beratKgPenjualanMitra;
    @Column(name = "total_harga_penjualan_mitra")
    private BigInteger totalHargaPenjualanMitra;
    @JoinColumn(name = "id_mitra", referencedColumnName = "id_mitra")
    @ManyToOne
    private MitraJual idMitra;
    @JoinColumn(name = "id_pengolahan", referencedColumnName = "id_pengolahan")
    @ManyToOne
    private Pengolahan idPengolahan;

    public PenjualanMitra() {
    }

    public PenjualanMitra(Integer idPenjualanMitra) {
        this.idPenjualanMitra = idPenjualanMitra;
    }

    public Integer getIdPenjualanMitra() {
        return idPenjualanMitra;
    }

    public void setIdPenjualanMitra(Integer idPenjualanMitra) {
        this.idPenjualanMitra = idPenjualanMitra;
    }

    public Date getTanggalPenjualanMitra() {
        return tanggalPenjualanMitra;
    }

    public void setTanggalPenjualanMitra(Date tanggalPenjualanMitra) {
        this.tanggalPenjualanMitra = tanggalPenjualanMitra;
    }

    public BigInteger getBeratKgPenjualanMitra() {
        return beratKgPenjualanMitra;
    }

    public void setBeratKgPenjualanMitra(BigInteger beratKgPenjualanMitra) {
        this.beratKgPenjualanMitra = beratKgPenjualanMitra;
    }

    public BigInteger getTotalHargaPenjualanMitra() {
        return totalHargaPenjualanMitra;
    }

    public void setTotalHargaPenjualanMitra(BigInteger totalHargaPenjualanMitra) {
        this.totalHargaPenjualanMitra = totalHargaPenjualanMitra;
    }

    public MitraJual getIdMitra() {
        return idMitra;
    }

    public void setIdMitra(MitraJual idMitra) {
        this.idMitra = idMitra;
    }

    public Pengolahan getIdPengolahan() {
        return idPengolahan;
    }

    public void setIdPengolahan(Pengolahan idPengolahan) {
        this.idPengolahan = idPengolahan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPenjualanMitra != null ? idPenjualanMitra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PenjualanMitra)) {
            return false;
        }
        PenjualanMitra other = (PenjualanMitra) object;
        if ((this.idPenjualanMitra == null && other.idPenjualanMitra != null) || (this.idPenjualanMitra != null && !this.idPenjualanMitra.equals(other.idPenjualanMitra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Login.PenjualanMitra[ idPenjualanMitra=" + idPenjualanMitra + " ]";
    }
    
}
