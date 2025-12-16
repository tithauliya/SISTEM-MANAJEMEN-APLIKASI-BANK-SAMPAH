/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Login;

import Login.JenisSampah;
import Login.MENUPETUGAS;
import Login.Nasabah;
import Login.Petugas;
import Login.Setoran;
import Login.Saldo;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class PENGOLAHANSAMPAH extends javax.swing.JFrame {

    EntityManagerFactory emf;
    EntityManager em;

    public PENGOLAHANSAMPAH(java.awt.Frame parent, boolean modal) {
        initComponents();
        connect();
        loadComboMetode();
        loadComboJenisSampah();
        loadComboPetugas();
        tampilPengolahan();
        setTanggalHariIni();
        setLocationRelativeTo(null);
        jTextIDPengolahan.setText(String.valueOf(autoID()));

        TablePENGOLAHAN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = TablePENGOLAHAN.getSelectedRow();
                if (row >= 0) {
                    jTextIDPengolahan.setText(TablePENGOLAHAN.getValueAt(row, 0).toString());
                    jTextHasilPengolahan2.setText(TablePENGOLAHAN.getValueAt(row, 1).toString());
                    jTextHasilPengolahan1.setText(TablePENGOLAHAN.getValueAt(row, 2).toString());
                    jTextTANGGALPENGOLAHAN.setText(TablePENGOLAHAN.getValueAt(row, 3).toString());
                    jComboBoxMetodePengolahan.setSelectedItem(TablePENGOLAHAN.getValueAt(row, 4).toString());

                    String namaJenis = TablePENGOLAHAN.getValueAt(row, 5).toString();
                    for (int i = 0; i < jComboBoxIDJenisSampah1.getItemCount(); i++) {
                        JenisSampah js = (JenisSampah) jComboBoxIDJenisSampah1.getItemAt(i);
                        if (js != null && js.getNamaSampah().equals(namaJenis)) {
                            jComboBoxIDJenisSampah1.setSelectedIndex(i);
                            break;
                        }
                    }

                    String namaPetugas = TablePENGOLAHAN.getValueAt(row, 6).toString();
                    for (int i = 0; i < jComboBoxPETUGAS.getItemCount(); i++) {
                        Petugas pet = (Petugas) jComboBoxPETUGAS.getItemAt(i);
                        if (pet != null && pet.getNamaPetugas().equals(namaPetugas)) {
                            jComboBoxPETUGAS.setSelectedIndex(i);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void connect() {
        try {
            emf = Persistence.createEntityManagerFactory("BANK_SAMPAHPU");
            em = emf.createEntityManager();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Koneksi gagal: " + e.getMessage());
        }
    }

    private void tampilPengolahan() {
        try {
            String[] kolom = {"ID Pengolahan", "Hasil", "Berat (Kg)", "Tanggal", "Metode", "Jenis Sampah", "Petugas"};
            DefaultTableModel model = new DefaultTableModel(null, kolom);

            List<Pengolahan> list = em.createNamedQuery("Pengolahan.findAll", Pengolahan.class).getResultList();
            for (Pengolahan p : list) {
                String id = p.getIdPengolahan().toString();
                String hasil = p.getHasilPengolahan();
                String berat = p.getBeratHasilPengolahan();
                String tanggal = new SimpleDateFormat("yyyy-MM-dd").format(p.getTanggalPengolahan());
                String metode = p.getMetodePengolahan();
                String jenis = (p.getIdJenissampah() != null) ? p.getIdJenissampah().getNamaSampah() : "-";
                String petugas = (p.getIdPetugas() != null) ? p.getIdPetugas().getNamaPetugas() : "-";
                model.addRow(new Object[]{id, hasil, berat, tanggal, metode, jenis, petugas});
            }

            TablePENGOLAHAN.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menampilkan data pengolahan: " + e.getMessage());
        }
    }

    private void loadComboMetode() {
        jComboBoxMetodePengolahan.removeAllItems();
        jComboBoxMetodePengolahan.addItem("--Pilih Metode--");
        jComboBoxMetodePengolahan.addItem("Kompos");
        jComboBoxMetodePengolahan.addItem("Daur Ulang");
        jComboBoxMetodePengolahan.addItem("Pencacahan");
        jComboBoxMetodePengolahan.addItem("Pemilahan Lanjut");
    }

    private void loadComboJenisSampah() {
        try {
            List<JenisSampah> list = em.createNamedQuery("JenisSampah.findAll", JenisSampah.class).getResultList();
            jComboBoxIDJenisSampah1.removeAllItems();
            jComboBoxIDJenisSampah1.addItem(null);
            for (JenisSampah js : list) {
                jComboBoxIDJenisSampah1.addItem(js);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal load jenis sampah: " + e.getMessage());
        }
    }

    private void loadComboPetugas() {
        try {
            List<Petugas> list = em.createNamedQuery("Petugas.findAll", Petugas.class).getResultList();
            jComboBoxPETUGAS.removeAllItems();
            jComboBoxPETUGAS.addItem(null);
            for (Petugas p : list) {
                jComboBoxPETUGAS.addItem(p);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal load petugas: " + e.getMessage());
        }
    }

    private void setTanggalHariIni() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        jTextTANGGALPENGOLAHAN.setText(sdf.format(new Date()));
    }

    private void bersih() {
        jTextIDPengolahan.setText("");
        jTextHasilPengolahan1.setText("");
        jTextHasilPengolahan2.setText("");
        jComboBoxMetodePengolahan.setSelectedIndex(0);
        jComboBoxIDJenisSampah1.setSelectedIndex(0);
        jComboBoxPETUGAS.setSelectedIndex(0);
        setTanggalHariIni();
    }

    public int autoID() {
        int id = 1; // default ID awal

        try {
            // Ambil ID terakhir dari database
            String sql = "SELECT p.idPengolahan FROM Pengolahan p ORDER BY p.idPengolahan DESC";
            List<Integer> hasil = em.createQuery(sql, Integer.class)
                    .setMaxResults(1)
                    .getResultList();

            if (!hasil.isEmpty()) {
                id = hasil.get(0) + 1; // tambah 1 dari ID terakhir
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal generate ID: " + e.getMessage());
        }

        return id;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablePENGOLAHAN = new javax.swing.JTable();
        jToggleBATAL = new javax.swing.JToggleButton();
        jComboBoxPETUGAS = new javax.swing.JComboBox();
        jToggleSIMPAN = new javax.swing.JToggleButton();
        jTextIDPengolahan = new javax.swing.JTextField();
        jTextTANGGALPENGOLAHAN = new javax.swing.JTextField();
        jComboBoxMetodePengolahan = new javax.swing.JComboBox();
        jTextHasilPengolahan1 = new javax.swing.JTextField();
        jTextHasilPengolahan2 = new javax.swing.JTextField();
        jComboBoxIDJenisSampah1 = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jToggleCEKGUDANG = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nasabah");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setForeground(new java.awt.Color(0, 0, 0));

        TablePENGOLAHAN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(TablePENGOLAHAN);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, 550, 310));

        jToggleBATAL.setBackground(new java.awt.Color(255, 51, 51));
        jToggleBATAL.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jToggleBATAL.setForeground(new java.awt.Color(255, 255, 255));
        jToggleBATAL.setText("BATAL");
        jToggleBATAL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleBATALActionPerformed(evt);
            }
        });
        getContentPane().add(jToggleBATAL, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 400, 100, 30));

        jComboBoxPETUGAS.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxPETUGAS.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxPETUGAS.setForeground(new java.awt.Color(0, 0, 0));
        jComboBoxPETUGAS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        getContentPane().add(jComboBoxPETUGAS, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 330, 30));

        jToggleSIMPAN.setBackground(new java.awt.Color(0, 51, 0));
        jToggleSIMPAN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jToggleSIMPAN.setForeground(new java.awt.Color(255, 255, 255));
        jToggleSIMPAN.setText("SIMPAN");
        jToggleSIMPAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleSIMPANActionPerformed(evt);
            }
        });
        getContentPane().add(jToggleSIMPAN, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 400, 90, 30));

        jTextIDPengolahan.setBackground(new java.awt.Color(255, 255, 255));
        jTextIDPengolahan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextIDPengolahan.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jTextIDPengolahan, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 330, 30));

        jTextTANGGALPENGOLAHAN.setBackground(new java.awt.Color(255, 255, 255));
        jTextTANGGALPENGOLAHAN.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextTANGGALPENGOLAHAN.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jTextTANGGALPENGOLAHAN, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 330, 30));

        jComboBoxMetodePengolahan.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxMetodePengolahan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxMetodePengolahan.setForeground(new java.awt.Color(0, 0, 0));
        jComboBoxMetodePengolahan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        getContentPane().add(jComboBoxMetodePengolahan, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 330, 30));

        jTextHasilPengolahan1.setBackground(new java.awt.Color(255, 255, 255));
        jTextHasilPengolahan1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextHasilPengolahan1.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jTextHasilPengolahan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 330, 30));

        jTextHasilPengolahan2.setBackground(new java.awt.Color(255, 255, 255));
        jTextHasilPengolahan2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextHasilPengolahan2.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jTextHasilPengolahan2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 330, 30));

        jComboBoxIDJenisSampah1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxIDJenisSampah1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxIDJenisSampah1.setForeground(new java.awt.Color(0, 0, 0));
        jComboBoxIDJenisSampah1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        getContentPane().add(jComboBoxIDJenisSampah1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 330, 30));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Petugas");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, 20));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Metode Pengelohan");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, -1, 20));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Tanggal Pengolahan");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, 20));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Jenis Sampah");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, 20));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Hasil Pengolahan");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, 20));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Berat Hasil Pengolahan");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, 20));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("ID Pengolahan");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/foto/Group 150.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 50));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/foto/LOGO BANK SAMPAH.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 127, 83));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("INPUT SETORAN SAMPAH");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, -1, 30));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Gabriola", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("BANK SAMPAH - MENU PETUGAS");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 10, -1, 30));

        jToggleCEKGUDANG.setBackground(new java.awt.Color(255, 255, 255));
        jToggleCEKGUDANG.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jToggleCEKGUDANG.setForeground(new java.awt.Color(0, 0, 0));
        jToggleCEKGUDANG.setText("CEK GUDANG");
        jToggleCEKGUDANG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleCEKGUDANGActionPerformed(evt);
            }
        });
        getContentPane().add(jToggleCEKGUDANG, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 400, 140, 30));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/foto/Group 151 (1).png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, -1));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("ID Pengolahan");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, 20));

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Hasil Pengolahan");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        MENUPETUGAS z = new MENUPETUGAS();
        z.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jToggleBATALActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleBATALActionPerformed
        bersih();
    }//GEN-LAST:event_jToggleBATALActionPerformed

    private void jToggleSIMPANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleSIMPANActionPerformed

        try {
            // ====================================================================
            // 1. VALIDASI INPUT
            // ====================================================================
            if (jTextIDPengolahan.getText().trim().isEmpty()
                    || jTextHasilPengolahan1.getText().trim().isEmpty()
                    || jTextHasilPengolahan2.getText().trim().isEmpty()
                    || jComboBoxMetodePengolahan.getSelectedIndex() == 0
                    || jComboBoxIDJenisSampah1.getSelectedItem() == null
                    || jComboBoxPETUGAS.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Semua data harus diisi!");
                return;
            }

            int beratPengolahan = Integer.parseInt(jTextHasilPengolahan1.getText());
            String hasilPengolahanStr = jTextHasilPengolahan2.getText();
            JenisSampah jenis = (JenisSampah) jComboBoxIDJenisSampah1.getSelectedItem();
            Petugas petugas = (Petugas) jComboBoxPETUGAS.getSelectedItem();
            String metode = jComboBoxMetodePengolahan.getSelectedItem().toString();
            Date tanggal = new SimpleDateFormat("yyyy-MM-dd").parse(jTextTANGGALPENGOLAHAN.getText());

            // ====================================================================
            // 2. CEK STOK GUDANG (Status "Tersedia")
            // ====================================================================
            StokGudang stokBahan = null;

            // Gunakan getResultList (Daftar) agar tidak error jika ada banyak stok tersedia
            List<StokGudang> daftarStok = em.createQuery(
                    "SELECT s FROM StokGudang s WHERE s.idJenissampah.idJenissampah = :id AND s.statusStok = :status AND s.beratTersedia > 0",
                    StokGudang.class)
                    .setParameter("id", jenis.getIdJenissampah())
                    .setParameter("status", "Tersedia") // Pastikan tulisan di Database "Tersedia"
                    .getResultList();

            if (daftarStok.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Stok sampah jenis ini belum tersedia di gudang!");
                return;
            }

            // Cari stok yang beratnya mencukupi
            for (StokGudang s : daftarStok) {
                if (s.getBeratTersedia() >= beratPengolahan) {
                    stokBahan = s;
                    break;
                }
            }

            // Jika tidak ada stok yang cukup
            if (stokBahan == null) {
                int total = 0;
                for (StokGudang s : daftarStok) {
                    total += s.getBeratTersedia();
                }

                JOptionPane.showMessageDialog(this,
                        "Stok tidak mencukupi untuk pengolahan ini!\n"
                        + "Total Stok 'Tersedia': " + total + " Kg\n"
                        + "Dibutuhkan: " + beratPengolahan + " Kg");
                return;
            }

            // ====================================================================
            // 3. MULAI TRANSAKSI SIMPAN
            // ====================================================================
            em.getTransaction().begin();

            // A. Simpan Data ke Tabel Pengolahan
            Pengolahan p = new Pengolahan();
            p.setIdPengolahan(Integer.parseInt(jTextIDPengolahan.getText()));
            p.setBeratHasilPengolahan(String.valueOf(beratPengolahan));
            p.setHasilPengolahan(hasilPengolahanStr);
            p.setTanggalPengolahan(tanggal);
            p.setMetodePengolahan(metode);
            p.setIdJenissampah(jenis);
            p.setIdPetugas(petugas);
            em.persist(p);

            // B. Kurangi Stok Bahan Baku & UPDATE STATUS JIKA HABIS
            int sisaStok = stokBahan.getBeratTersedia() - beratPengolahan;

            stokBahan.setBeratTersedia(sisaStok); // Update berat baru

            // --- INI LOGIKA AGAR MENJADI "TIDAK TERSEDIA" / "HABIS" ---
            if (sisaStok <= 0) {
                stokBahan.setStatusStok("Habis");
                // Catatan: Kamu bisa ganti kata "Habis" jadi "Tidak Tersedia" sesuai selera
            }
            // -----------------------------------------------------------

            em.merge(stokBahan);

            // C. Tambah Stok Barang Jadi (Hasil Pengolahan)
            StokGudang stokHasil;
            try {
                stokHasil = em.createQuery(
                        "SELECT s FROM StokGudang s WHERE s.idJenissampah.idJenissampah = :id AND s.statusStok = :status",
                        StokGudang.class)
                        .setParameter("id", jenis.getIdJenissampah())
                        .setParameter("status", "Hasil Pengolahan")
                        .getSingleResult();

                // Jika ada, tambah stoknya
                stokHasil.setBeratMasuk(stokHasil.getBeratMasuk() + beratPengolahan);
                stokHasil.setBeratTersedia(stokHasil.getBeratTersedia() + beratPengolahan);
                em.merge(stokHasil);

            } catch (javax.persistence.NoResultException ex) {
                // Jika belum ada, buat baru
                stokHasil = new StokGudang();
                stokHasil.setIdJenissampah(jenis);
                stokHasil.setIdPengolahan(p);
                stokHasil.setTanggalMasuk(new Date());
                stokHasil.setBeratMasuk(beratPengolahan);
                stokHasil.setBeratTersedia(beratPengolahan);
                stokHasil.setStatusStok("Hasil Pengolahan");
                em.persist(stokHasil);
            }

            em.getTransaction().commit();

            JOptionPane.showMessageDialog(this, "Pengolahan berhasil disimpan!\nStok gudang telah diperbarui.");

            tampilPengolahan();
            bersih();
            jTextIDPengolahan.setText(String.valueOf(autoID()));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Kolom berat harus berisi angka!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal menyimpan: " + e.getMessage());
        }
    }//GEN-LAST:event_jToggleSIMPANActionPerformed

    private void jToggleCEKGUDANGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleCEKGUDANGActionPerformed
        PENYIMPANANGUDANG l = new PENYIMPANANGUDANG();
        l.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jToggleCEKGUDANGActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(PENGOLAHANSAMPAH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(PENGOLAHANSAMPAH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(PENGOLAHANSAMPAH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(PENGOLAHANSAMPAH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new PENGOLAHANSAMPAH().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablePENGOLAHAN;
    private javax.swing.JComboBox jComboBoxIDJenisSampah1;
    private javax.swing.JComboBox jComboBoxMetodePengolahan;
    private javax.swing.JComboBox jComboBoxPETUGAS;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextHasilPengolahan1;
    private javax.swing.JTextField jTextHasilPengolahan2;
    private javax.swing.JTextField jTextIDPengolahan;
    private javax.swing.JTextField jTextTANGGALPENGOLAHAN;
    private javax.swing.JToggleButton jToggleBATAL;
    private javax.swing.JToggleButton jToggleCEKGUDANG;
    private javax.swing.JToggleButton jToggleSIMPAN;
    // End of variables declaration//GEN-END:variables
}
