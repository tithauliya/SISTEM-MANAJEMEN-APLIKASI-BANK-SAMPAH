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
@Table(name = "penarikan")
@NamedQueries({
    @NamedQuery(name = "Penarikan.findAll", query = "SELECT p FROM Penarikan p"),
    @NamedQuery(name = "Penarikan.findByIdPenarikan", query = "SELECT p FROM Penarikan p WHERE p.idPenarikan = :idPenarikan"),
    @NamedQuery(name = "Penarikan.findByTanggal", query = "SELECT p FROM Penarikan p WHERE p.tanggal = :tanggal"),
    @NamedQuery(name = "Penarikan.findByJumlah", query = "SELECT p FROM Penarikan p WHERE p.jumlah = :jumlah"),
    @NamedQuery(name = "Penarikan.findByMetode", query = "SELECT p FROM Penarikan p WHERE p.metode = :metode")})
public class Penarikan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_penarikan")
    private Integer idPenarikan;
    @Column(name = "tanggal")
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    @Column(name = "jumlah")
    private Integer jumlah;
    @Column(name = "metode")
    private String metode;
    @JoinColumn(name = "id_nasabah", referencedColumnName = "id_nasabah")
    @ManyToOne
    private Nasabah idNasabah;

    public Penarikan() {
    }

    public Penarikan(Integer idPenarikan) {
        this.idPenarikan = idPenarikan;
    }

    public Integer getIdPenarikan() {
        return idPenarikan;
    }

    public void setIdPenarikan(Integer idPenarikan) {
        this.idPenarikan = idPenarikan;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public String getMetode() {
        return metode;
    }

    public void setMetode(String metode) {
        this.metode = metode;
    }

    public Nasabah getIdNasabah() {
        return idNasabah;
    }

    public void setIdNasabah(Nasabah idNasabah) {
        this.idNasabah = idNasabah;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPenarikan != null ? idPenarikan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Penarikan)) {
            return false;
        }
        Penarikan other = (Penarikan) object;
        if ((this.idPenarikan == null && other.idPenarikan != null) || (this.idPenarikan != null && !this.idPenarikan.equals(other.idPenarikan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Login.Penarikan[ idPenarikan=" + idPenarikan + " ]";
    }
    
}
