/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "setoran")
@NamedQueries({
    @NamedQuery(name = "Setoran.findAll", query = "SELECT s FROM Setoran s"),
    @NamedQuery(name = "Setoran.findByIdSetoran", query = "SELECT s FROM Setoran s WHERE s.idSetoran = :idSetoran"),
    @NamedQuery(name = "Setoran.findByTanggalSetoran", query = "SELECT s FROM Setoran s WHERE s.tanggalSetoran = :tanggalSetoran"),
    @NamedQuery(name = "Setoran.findByBeratKgSetoran", query = "SELECT s FROM Setoran s WHERE s.beratKgSetoran = :beratKgSetoran"),
    @NamedQuery(name = "Setoran.findByTotalHargaSetoran", query = "SELECT s FROM Setoran s WHERE s.totalHargaSetoran = :totalHargaSetoran"),
    @NamedQuery(name = "Setoran.findByStatusSetoran", query = "SELECT s FROM Setoran s WHERE s.statusSetoran = :statusSetoran")})
public class Setoran implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_setoran")
    private Integer idSetoran;
    @Column(name = "tanggal_setoran")
    @Temporal(TemporalType.DATE)
    private Date tanggalSetoran;
    @Column(name = "berat_kg_setoran")
    private Integer beratKgSetoran;
    @Column(name = "total_harga_setoran")
    private BigInteger totalHargaSetoran;
    @Column(name = "status_setoran")
    private String statusSetoran;
    @OneToMany(mappedBy = "idSetoran")
    private Collection<StokGudang> stokGudangCollection;
    @JoinColumn(name = "id_jenissampah", referencedColumnName = "id_jenissampah")
    @ManyToOne
    private JenisSampah idJenissampah;
    @JoinColumn(name = "id_nasabah", referencedColumnName = "id_nasabah")
    @ManyToOne
    private Nasabah idNasabah;
    @JoinColumn(name = "id_petugas", referencedColumnName = "id_petugas")
    @ManyToOne
    private Petugas idPetugas;

    public Setoran() {
    }

    public Setoran(Integer idSetoran) {
        this.idSetoran = idSetoran;
    }

    public Integer getIdSetoran() {
        return idSetoran;
    }

    public void setIdSetoran(Integer idSetoran) {
        this.idSetoran = idSetoran;
    }

    public Date getTanggalSetoran() {
        return tanggalSetoran;
    }

    public void setTanggalSetoran(Date tanggalSetoran) {
        this.tanggalSetoran = tanggalSetoran;
    }

    public Integer getBeratKgSetoran() {
        return beratKgSetoran;
    }

    public void setBeratKgSetoran(Integer beratKgSetoran) {
        this.beratKgSetoran = beratKgSetoran;
    }

    public BigInteger getTotalHargaSetoran() {
        return totalHargaSetoran;
    }

    public void setTotalHargaSetoran(BigInteger totalHargaSetoran) {
        this.totalHargaSetoran = totalHargaSetoran;
    }

    public String getStatusSetoran() {
        return statusSetoran;
    }

    public void setStatusSetoran(String statusSetoran) {
        this.statusSetoran = statusSetoran;
    }
    public Collection<StokGudang> getStokGudangCollection() {
        return stokGudangCollection;
    }

    public void setStokGudangCollection(Collection<StokGudang> stokGudangCollection) {
        this.stokGudangCollection = stokGudangCollection;
    }

    public JenisSampah getIdJenissampah() {
        return idJenissampah;
    }

    public void setIdJenissampah(JenisSampah idJenissampah) {
        this.idJenissampah = idJenissampah;
    }

    public Nasabah getIdNasabah() {
        return idNasabah;
    }

    public void setIdNasabah(Nasabah idNasabah) {
        this.idNasabah = idNasabah;
    }

    public Petugas getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(Petugas idPetugas) {
        this.idPetugas = idPetugas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSetoran != null ? idSetoran.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Setoran)) {
            return false;
        }
        Setoran other = (Setoran) object;
        if ((this.idSetoran == null && other.idSetoran != null) || (this.idSetoran != null && !this.idSetoran.equals(other.idSetoran))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Login.Setoran[ idSetoran=" + idSetoran + " ]";
    }
    
}
