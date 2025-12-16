/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "saldo")
@NamedQueries({
    @NamedQuery(name = "Saldo.findAll", query = "SELECT s FROM Saldo s"),
    @NamedQuery(name = "Saldo.findByIdSaldo", query = "SELECT s FROM Saldo s WHERE s.idSaldo = :idSaldo"),
    @NamedQuery(name = "Saldo.findByTotalSaldo", query = "SELECT s FROM Saldo s WHERE s.totalSaldo = :totalSaldo")})
public class Saldo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_saldo")
    private Integer idSaldo;
    @Column(name = "total_saldo")
    private BigInteger totalSaldo;
    @JoinColumn(name = "id_nasabah", referencedColumnName = "id_nasabah")
    @OneToOne
    private Nasabah idNasabah;

    public Saldo() {
    }

    public Saldo(Integer idSaldo) {
        this.idSaldo = idSaldo;
    }

    public Integer getIdSaldo() {
        return idSaldo;
    }

    public void setIdSaldo(Integer idSaldo) {
        this.idSaldo = idSaldo;
    }

    public BigInteger getTotalSaldo() {
        return totalSaldo;
    }

    public void setTotalSaldo(BigInteger totalSaldo) {
        this.totalSaldo = totalSaldo;
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
        hash += (idSaldo != null ? idSaldo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Saldo)) {
            return false;
        }
        Saldo other = (Saldo) object;
        if ((this.idSaldo == null && other.idSaldo != null) || (this.idSaldo != null && !this.idSaldo.equals(other.idSaldo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Login.Saldo[ idSaldo=" + idSaldo + " ]";
    }
    
}
