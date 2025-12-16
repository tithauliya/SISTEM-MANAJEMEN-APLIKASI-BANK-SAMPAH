/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "jenis_sampah")
@NamedQueries({
    @NamedQuery(name = "JenisSampah.findAll", query = "SELECT j FROM JenisSampah j"),
    @NamedQuery(name = "JenisSampah.findByIdJenissampah", query = "SELECT j FROM JenisSampah j WHERE j.idJenissampah = :idJenissampah"),
    @NamedQuery(name = "JenisSampah.findByNamaSampah", query = "SELECT j FROM JenisSampah j WHERE j.namaSampah = :namaSampah"),
    @NamedQuery(name = "JenisSampah.findByKategori", query = "SELECT j FROM JenisSampah j WHERE j.kategori = :kategori"),
    @NamedQuery(name = "JenisSampah.findByHargaPerKg", query = "SELECT j FROM JenisSampah j WHERE j.hargaPerKg = :hargaPerKg")})
public class JenisSampah implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_jenissampah")
    private Integer idJenissampah;
    @Column(name = "nama_sampah")
    private String namaSampah;
    @Column(name = "kategori")
    private String kategori;
    @Column(name = "harga_per_kg")
    private BigInteger hargaPerKg;
    @OneToMany(mappedBy = "idJenissampah")
    private Collection<Pengolahan> pengolahanCollection;
    @OneToMany(mappedBy = "idJenissampah")
    private Collection<StokGudang> stokGudangCollection;
    @OneToMany(mappedBy = "idJenissampah")
    private Collection<Setoran> setoranCollection;

    public JenisSampah() {
    }

    public JenisSampah(Integer idJenissampah) {
        this.idJenissampah = idJenissampah;
    }

    public Integer getIdJenissampah() {
        return idJenissampah;
    }

    public void setIdJenissampah(Integer idJenissampah) {
        this.idJenissampah = idJenissampah;
    }

    public String getNamaSampah() {
        return namaSampah;
    }

    public void setNamaSampah(String namaSampah) {
        this.namaSampah = namaSampah;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public BigInteger getHargaPerKg() {
        return hargaPerKg;
    }

    public void setHargaPerKg(BigInteger hargaPerKg) {
        this.hargaPerKg = hargaPerKg;
    }
    public Collection<Pengolahan> getPengolahanCollection() {
        return pengolahanCollection;
    }

    public void setPengolahanCollection(Collection<Pengolahan> pengolahanCollection) {
        this.pengolahanCollection = pengolahanCollection;
    }
    public Collection<StokGudang> getStokGudangCollection() {
        return stokGudangCollection;
    }

    public void setStokGudangCollection(Collection<StokGudang> stokGudangCollection) {
        this.stokGudangCollection = stokGudangCollection;
    }
    public Collection<Setoran> getSetoranCollection() {
        return setoranCollection;
    }

    public void setSetoranCollection(Collection<Setoran> setoranCollection) {
        this.setoranCollection = setoranCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJenissampah != null ? idJenissampah.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JenisSampah)) {
            return false;
        }
        JenisSampah other = (JenisSampah) object;
        if ((this.idJenissampah == null && other.idJenissampah != null) || (this.idJenissampah != null && !this.idJenissampah.equals(other.idJenissampah))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return namaSampah;
    }
    
}
