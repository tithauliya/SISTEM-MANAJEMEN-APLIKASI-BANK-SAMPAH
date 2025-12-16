/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "stok_gudang")
@NamedQueries({
    @NamedQuery(name = "StokGudang.findAll", query = "SELECT s FROM StokGudang s"),
    @NamedQuery(name = "StokGudang.findByIdStok", query = "SELECT s FROM StokGudang s WHERE s.idStok = :idStok"),
    @NamedQuery(name = "StokGudang.findByJumlah", query = "SELECT s FROM StokGudang s WHERE s.jumlah = :jumlah"),
    @NamedQuery(name = "StokGudang.findByTanggalMasuk", query = "SELECT s FROM StokGudang s WHERE s.tanggalMasuk = :tanggalMasuk"),
    @NamedQuery(name = "StokGudang.findByBeratMasuk", query = "SELECT s FROM StokGudang s WHERE s.beratMasuk = :beratMasuk"),
    @NamedQuery(name = "StokGudang.findByBeratTersedia", query = "SELECT s FROM StokGudang s WHERE s.beratTersedia = :beratTersedia"),
    @NamedQuery(name = "StokGudang.findByStatusStok", query = "SELECT s FROM StokGudang s WHERE s.statusStok = :statusStok")})
public class StokGudang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_stok")
    private Integer idStok;
    @Column(name = "jumlah")
    private Integer jumlah;
    @Column(name = "tanggal_masuk")
    @Temporal(TemporalType.DATE)
    private Date tanggalMasuk;
    @Column(name = "berat_masuk")
    private Integer beratMasuk;
    @Column(name = "berat_tersedia")
    private Integer beratTersedia;
    @Column(name = "status_stok")
    private String statusStok;
    @JoinColumn(name = "id_jenissampah", referencedColumnName = "id_jenissampah")
    @ManyToOne
    private JenisSampah idJenissampah;
    @JoinColumn(name = "id_pengolahan", referencedColumnName = "id_pengolahan")
    @ManyToOne
    private Pengolahan idPengolahan;
    @JoinColumn(name = "id_setoran", referencedColumnName = "id_setoran")
    @ManyToOne
    private Setoran idSetoran;

    public StokGudang() {
    }

    public StokGudang(Integer idStok) {
        this.idStok = idStok;
    }

    public Integer getIdStok() {
        return idStok;
    }

    public void setIdStok(Integer idStok) {
        this.idStok = idStok;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Date getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(Date tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public Integer getBeratMasuk() {
        return beratMasuk;
    }

    public void setBeratMasuk(Integer beratMasuk) {
        this.beratMasuk = beratMasuk;
    }

    public Integer getBeratTersedia() {
        return beratTersedia;
    }

    public void setBeratTersedia(Integer beratTersedia) {
        this.beratTersedia = beratTersedia;
    }

    public String getStatusStok() {
        return statusStok;
    }

    public void setStatusStok(String statusStok) {
        this.statusStok = statusStok;
    }

    public JenisSampah getIdJenissampah() {
        return idJenissampah;
    }

    public void setIdJenissampah(JenisSampah idJenissampah) {
        this.idJenissampah = idJenissampah;
    }

    public Pengolahan getIdPengolahan() {
        return idPengolahan;
    }

    public void setIdPengolahan(Pengolahan idPengolahan) {
        this.idPengolahan = idPengolahan;
    }

    public Setoran getIdSetoran() {
        return idSetoran;
    }

    public void setIdSetoran(Setoran idSetoran) {
        this.idSetoran = idSetoran;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStok != null ? idStok.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StokGudang)) {
            return false;
        }
        StokGudang other = (StokGudang) object;
        if ((this.idStok == null && other.idStok != null) || (this.idStok != null && !this.idStok.equals(other.idStok))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Login.StokGudang[ idStok=" + idStok + " ]";
    }
    
}
