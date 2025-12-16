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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "nasabah")
@NamedQueries({
    @NamedQuery(name = "Nasabah.findAll", query = "SELECT n FROM Nasabah n"),
    @NamedQuery(name = "Nasabah.findByIdNasabah", query = "SELECT n FROM Nasabah n WHERE n.idNasabah = :idNasabah"),
    @NamedQuery(name = "Nasabah.findByNamaNasabah", query = "SELECT n FROM Nasabah n WHERE n.namaNasabah = :namaNasabah"),
    @NamedQuery(name = "Nasabah.findByAlamatNasabah", query = "SELECT n FROM Nasabah n WHERE n.alamatNasabah = :alamatNasabah"),
    @NamedQuery(name = "Nasabah.findByTelepon", query = "SELECT n FROM Nasabah n WHERE n.telepon = :telepon"),
    @NamedQuery(name = "Nasabah.findByUsernameNasabah", query = "SELECT n FROM Nasabah n WHERE n.usernameNasabah = :usernameNasabah"),
    @NamedQuery(name = "Nasabah.findByPasswordNasabah", query = "SELECT n FROM Nasabah n WHERE n.passwordNasabah = :passwordNasabah"),
    @NamedQuery(name = "Nasabah.findByNamaIbu", query = "SELECT n FROM Nasabah n WHERE n.namaIbu = :namaIbu")})
public class Nasabah implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_nasabah")
    private Integer idNasabah;
    @Column(name = "nama_nasabah")
    private String namaNasabah;
    @Column(name = "alamat_nasabah")
    private String alamatNasabah;
    @Column(name = "telepon")
    private String telepon;
    @Column(name = "username_nasabah")
    private String usernameNasabah;
    @Column(name = "password_nasabah")
    private String passwordNasabah;
    @Column(name = "nama_ibu")
    private String namaIbu;
    @OneToMany(mappedBy = "idNasabah")
    private Collection<Penarikan> penarikanCollection;
    @OneToOne(mappedBy = "idNasabah")
    private Saldo saldo;
    @OneToMany(mappedBy = "idNasabah")
    private Collection<Setoran> setoranCollection;

    public Nasabah() {
    }

    public Nasabah(Integer idNasabah) {
        this.idNasabah = idNasabah;
    }

    public Integer getIdNasabah() {
        return idNasabah;
    }

    public void setIdNasabah(Integer idNasabah) {
        this.idNasabah = idNasabah;
    }

    public String getNamaNasabah() {
        return namaNasabah;
    }

    public void setNamaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
    }

    public String getAlamatNasabah() {
        return alamatNasabah;
    }

    public void setAlamatNasabah(String alamatNasabah) {
        this.alamatNasabah = alamatNasabah;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getUsernameNasabah() {
        return usernameNasabah;
    }

    public void setUsernameNasabah(String usernameNasabah) {
        this.usernameNasabah = usernameNasabah;
    }

    public String getPasswordNasabah() {
        return passwordNasabah;
    }

    public void setPasswordNasabah(String passwordNasabah) {
        this.passwordNasabah = passwordNasabah;
    }

    public String getNamaIbu() {
        return namaIbu;
    }

    public void setNamaIbu(String namaIbu) {
        this.namaIbu = namaIbu;
    }
    public Collection<Penarikan> getPenarikanCollection() {
        return penarikanCollection;
    }

    public void setPenarikanCollection(Collection<Penarikan> penarikanCollection) {
        this.penarikanCollection = penarikanCollection;
    }

    public Saldo getSaldo() {
        return saldo;
    }

    public void setSaldo(Saldo saldo) {
        this.saldo = saldo;
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
        hash += (idNasabah != null ? idNasabah.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nasabah)) {
            return false;
        }
        Nasabah other = (Nasabah) object;
        if ((this.idNasabah == null && other.idNasabah != null) || (this.idNasabah != null && !this.idNasabah.equals(other.idNasabah))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return namaNasabah;
    }
    
}
