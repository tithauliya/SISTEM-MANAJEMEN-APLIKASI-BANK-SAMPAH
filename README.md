# ♻️ Aplikasi Bank Sampah - Sistem Informasi Manajemen Sampah (UINSA)

![Status](https://img.shields.io/badge/Status-Completed-success?style=for-the-badge)
![Java](https://img.shields.io/badge/Java-Swing-orange?style=for-the-badge&logo=java)
![Database](https://img.shields.io/badge/PostgreSQL-Database-blue?style=for-the-badge&logo=postgresql)

> **Proyek Tugas Akhir Mata Kuliah Basis Data Lanjutan)**
> Program Studi Sistem Informasi, UIN Sunan Ampel Surabaya.

## 👥 Tim Pengembang
Aplikasi ini dikembangkan oleh:
1. **TITHA AULIYA KHOTIM** (09010624017) 
2. **ALVIRA DWI FEBRIANI** (09020624022)
3. **RIDHO DARMAWAN** (09020624061)

---

## 📚 BAGIAN 1: FITUR & ALUR KERJA APLIKASI

### A. Peran & Tanggung Jawab Petugas (Admin)
Petugas memiliki hak akses penuh untuk mengelola operasional Bank Sampah. Berdasarkan antarmuka aplikasi, tugas petugas meliputi:

1.  **Input Setoran Sampah:** Mencatat sampah yang dibawa nasabah, menghitung berat, dan mengkonversinya menjadi saldo tabungan nasabah.
2.  **Kelola Data Master:**
    * **Data Nasabah:** Registrasi dan pengelolaan akun warga.
    * **Data Mitra Jual:** Mengelola data pengepul besar (tempat Bank Sampah menjual kembali sampah).
    * **Jenis Sampah:** Mengatur harga beli sampah per kilogram (fluktuatif).
3.  **Manajemen Gudang (Stok):** Memantau total berat sampah yang terkumpul di gudang sebelum dijual.
4.  **Penjualan ke Mitra:** Menjual stok sampah yang ada di gudang kepada Mitra Jual untuk mendapatkan uang tunai (Kas Bank).

### B. Peran Nasabah (User)
Nasabah adalah warga penyetor sampah. Melalui aplikasi, nasabah dapat:
1.  **Cek Saldo:** Melihat akumulasi uang hasil setoran sampah.
2.  **Tarik Saldo:** Mencairkan tabungan mereka (Withdraw) dengan validasi PIN keamanan.
3.  **Riwayat Transaksi:** Memantau histori penarikan dana yang pernah dilakukan.

### C. Fitur Khusus: Dompet Bank Sampah (Central Treasury)
Aplikasi ini dilengkapi dengan fitur **"Dompet Bank Sampah"** atau Kas Pusat. Ini adalah rekening virtual milik sistem (bukan milik nasabah) yang berfungsi untuk:

* **Menampung Pendapatan:** Saat Petugas melakukan *Penjualan ke Mitra*, uang hasil penjualan masuk ke Dompet ini.
* **Sumber Dana Penarikan:** Saat Nasabah melakukan *Tarik Saldo*, uang diambil dari Dompet ini.
* **Monitoring Likuiditas:** Memastikan Bank Sampah memiliki uang tunai yang cukup untuk membayar nasabah.

> **Logika Kas:**
> * `Jual ke Mitra` ➝ Saldo Dompet Bertambah (+).
> * `Nasabah Tarik Saldo` ➝ Saldo Dompet Berkurang (-).

---

## 📸 DOKUMENTASI ANTARMUKA (SCREENSHOTS)

Berikut adalah tampilan antarmuka aplikasi yang telah dikembangkan:

### 1. Dashboard Utama
Menu navigasi yang intuitif untuk membedakan akses Petugas dan Nasabah.

**Menu Petugas (Admin)**  
<img width="1380" height="896" alt="Screenshot 2025-12-16 144937" src="https://github.com/user-attachments/assets/556aaecc-16f5-4365-ac01-fa6e62927c9a" />
 **Menu Nasabah (User)**
 <img width="1897" height="969" alt="Screenshot 2025-12-16 144849" src="https://github.com/user-attachments/assets/5467aa10-c31d-4291-9666-ed75429f0477" />

---

## 🛠️ BAGIAN 2: LANGKAH INSTALASI

### I. Persiapan (Prerequisites)
Pastikan komputer Anda sudah terinstall:
* **Apache NetBeans IDE** (v12+).
* **JDK 8** (Java Development Kit).
* **PostgreSQL Database**.
* **Jaspersoft Studio** (Opsional, untuk edit laporan).

### II. Konfigurasi Database
1.  Buat database di PostgreSQL dengan nama: `BANK_SAMPAH`.
2.  Sesuaikan file koneksi di `src/META-INF/persistence.xml`:
    * User: `postgres`
    * Password: `password` (Sesuaikan dengan PC Anda).

### III. Library Dependencies
Pastikan file `.jar` berikut ada di folder `Libraries` project NetBeans Anda:
* `postgresql-jdbc.jar`
* `eclipselink.jar` & `javax.persistence.jar`
* `jasperreports.jar` (beserta *commons* & *itext*).

---

## 📜 Lisensi
Hak Cipta © 2025 - Mahasiswa Sistem Informasi UIN Sunan Ampel Surabaya.
