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
import javax.persistence.Id;
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
@Table(name = "dompet_bank_sampah")
@NamedQueries({
    @NamedQuery(name = "DompetBankSampah_1.findAll", query = "SELECT d FROM DompetBankSampah_1 d"),
    @NamedQuery(name = "DompetBankSampah_1.findByIdDompet", query = "SELECT d FROM DompetBankSampah_1 d WHERE d.idDompet = :idDompet"),
    @NamedQuery(name = "DompetBankSampah_1.findByTotalKasBank", query = "SELECT d FROM DompetBankSampah_1 d WHERE d.totalKasBank = :totalKasBank"),
    @NamedQuery(name = "DompetBankSampah_1.findByTerakhirDiupdate", query = "SELECT d FROM DompetBankSampah_1 d WHERE d.terakhirDiupdate = :terakhirDiupdate")})
public class DompetBankSampah_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_dompet")
    private Integer idDompet;
    @Basic(optional = false)
    @Column(name = "total_kas_bank")
    private long totalKasBank;
    @Column(name = "terakhir_diupdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date terakhirDiupdate;

    public DompetBankSampah_1() {
    }

    public DompetBankSampah_1(Integer idDompet) {
        this.idDompet = idDompet;
    }

    public DompetBankSampah_1(Integer idDompet, long totalKasBank) {
        this.idDompet = idDompet;
        this.totalKasBank = totalKasBank;
    }

    public Integer getIdDompet() {
        return idDompet;
    }

    public void setIdDompet(Integer idDompet) {
        this.idDompet = idDompet;
    }

    public long getTotalKasBank() {
        return totalKasBank;
    }

    public void setTotalKasBank(long totalKasBank) {
        this.totalKasBank = totalKasBank;
    }

    public Date getTerakhirDiupdate() {
        return terakhirDiupdate;
    }

    public void setTerakhirDiupdate(Date terakhirDiupdate) {
        this.terakhirDiupdate = terakhirDiupdate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDompet != null ? idDompet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DompetBankSampah_1)) {
            return false;
        }
        DompetBankSampah_1 other = (DompetBankSampah_1) object;
        if ((this.idDompet == null && other.idDompet != null) || (this.idDompet != null && !this.idDompet.equals(other.idDompet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Login.DompetBankSampah_1[ idDompet=" + idDompet + " ]";
    }

}
