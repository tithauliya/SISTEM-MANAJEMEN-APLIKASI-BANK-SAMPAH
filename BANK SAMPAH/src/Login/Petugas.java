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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "petugas")
@NamedQueries({
    @NamedQuery(name = "Petugas.findAll", query = "SELECT p FROM Petugas p"),
    @NamedQuery(name = "Petugas.findByIdPetugas", query = "SELECT p FROM Petugas p WHERE p.idPetugas = :idPetugas"),
    @NamedQuery(name = "Petugas.findByNamaPetugas", query = "SELECT p FROM Petugas p WHERE p.namaPetugas = :namaPetugas"),
    @NamedQuery(name = "Petugas.findByUsernamePetugas", query = "SELECT p FROM Petugas p WHERE p.usernamePetugas = :usernamePetugas"),
    @NamedQuery(name = "Petugas.findByPasswordPetugas", query = "SELECT p FROM Petugas p WHERE p.passwordPetugas = :passwordPetugas"),
    @NamedQuery(name = "Petugas.findByNamaIbu", query = "SELECT p FROM Petugas p WHERE p.namaIbu = :namaIbu")})
public class Petugas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_petugas")
    private Integer idPetugas;
    @Column(name = "nama_petugas")
    private String namaPetugas;
    @Column(name = "username_petugas")
    private String usernamePetugas;
    @Column(name = "password_petugas")
    private String passwordPetugas;
    @Column(name = "nama_ibu")
    private String namaIbu;
    @OneToMany(mappedBy = "idPetugas")
    private Collection<Pengolahan> pengolahanCollection;
    @OneToMany(mappedBy = "idPetugas")
    private Collection<Setoran> setoranCollection;

    public Petugas() {
    }

    public Petugas(Integer idPetugas) {
        this.idPetugas = idPetugas;
    }

    public Integer getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(Integer idPetugas) {
        this.idPetugas = idPetugas;
    }

    public String getNamaPetugas() {
        return namaPetugas;
    }

    public void setNamaPetugas(String namaPetugas) {
        this.namaPetugas = namaPetugas;
    }

    public String getUsernamePetugas() {
        return usernamePetugas;
    }

    public void setUsernamePetugas(String usernamePetugas) {
        this.usernamePetugas = usernamePetugas;
    }

    public String getPasswordPetugas() {
        return passwordPetugas;
    }

    public void setPasswordPetugas(String passwordPetugas) {
        this.passwordPetugas = passwordPetugas;
    }

    public String getNamaIbu() {
        return namaIbu;
    }

    public void setNamaIbu(String namaIbu) {
        this.namaIbu = namaIbu;
    }
    public Collection<Pengolahan> getPengolahanCollection() {
        return pengolahanCollection;
    }

    public void setPengolahanCollection(Collection<Pengolahan> pengolahanCollection) {
        this.pengolahanCollection = pengolahanCollection;
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
        hash += (idPetugas != null ? idPetugas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Petugas)) {
            return false;
        }
        Petugas other = (Petugas) object;
        if ((this.idPetugas == null && other.idPetugas != null) || (this.idPetugas != null && !this.idPetugas.equals(other.idPetugas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return namaPetugas;
    }
    
}
