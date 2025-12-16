/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Login;

import java.util.List;
import javax.persistence.Column;
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
public class PENYIMPANANGUDANG extends javax.swing.JFrame {

    EntityManagerFactory emf;
    EntityManager em;

    // Tambahkan variabel global
    private StokGudang selectedStok = null;

    public PENYIMPANANGUDANG() {
        connect();
        initComponents();
        populateComboBox();
        showTable(null, null);

        setLocationRelativeTo(null);

        TableKELOLASAMPAH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = TableKELOLASAMPAH.getSelectedRow();
                if (row >= 0) {

                    Integer idStok = Integer.parseInt(TableKELOLASAMPAH.getValueAt(row, 0).toString());

                    selectedStok = em.find(StokGudang.class, idStok);

                    if (selectedStok != null) {
                        System.out.println("Data dipilih:");
                        System.out.println("ID: " + selectedStok.getIdStok());
                        System.out.println("Tanggal Masuk: " + selectedStok.getTanggalMasuk());
                        System.out.println("Berat Masuk: " + selectedStok.getBeratMasuk());
                        System.out.println("Berat Tersedia: " + selectedStok.getBeratTersedia());
                        System.out.println("Status: " + selectedStok.getStatusStok());
                        System.out.println("Jenis Sampah: " + (selectedStok.getIdJenissampah() != null ? selectedStok.getIdJenissampah().getNamaSampah() : ""));
                        System.out.println("Hasil Pengolahan: " + (selectedStok.getIdPengolahan() != null ? selectedStok.getIdPengolahan().getHasilPengolahan() : ""));
                        System.out.println("ID Setoran: " + (selectedStok.getIdSetoran() != null ? selectedStok.getIdSetoran().getIdSetoran() : ""));
                    }
                }
            }
        });
    }

    public void connect() {
        try {
            emf = Persistence.createEntityManagerFactory("BANK_SAMPAHPU");
            em = emf.createEntityManager();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Koneksi gagal: " + e.getMessage());
        }
    }
// Isi ComboBox sesuai field StokGudang dan relasi

    private void populateComboBox() {
        jComboBox1.removeAllItems();
        jComboBox1.addItem("--Pilih Kolom--");
        jComboBox1.addItem("ID Stok");
        jComboBox1.addItem("Tanggal Masuk");
        jComboBox1.addItem("Berat Masuk");
        jComboBox1.addItem("Berat Tersedia");
        jComboBox1.addItem("Status Stok");
        jComboBox1.addItem("Nama Jenis Sampah");   // relasi StokGudang -> JenisSampah
        jComboBox1.addItem("Hasil Pengolahan");    // relasi StokGudang -> Pengolahan
        jComboBox1.addItem("ID Setoran");          // relasi StokGudang -> Setoran
    }

    private void showTable(String filterKolom, String keyword) {
        try {
            List<StokGudang> data = em.createNamedQuery("StokGudang.findAll", StokGudang.class)
                    .getResultList();

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{
                        "ID Stok", "Tanggal Masuk", "Berat Masuk", "Berat Tersedia",
                        "Jumlah", "Status Stok", "Nama Jenis Sampah", "Hasil Pengolahan", "ID Setoran"
                    }, 0
            );

            for (StokGudang s : data) {
                String namaJenis = s.getIdJenissampah() != null ? s.getIdJenissampah().getNamaSampah() : "";
                String hasilPengolahan = s.getIdPengolahan() != null ? s.getIdPengolahan().getHasilPengolahan() : "";
                String idSetoran = s.getIdSetoran() != null ? s.getIdSetoran().getIdSetoran().toString() : "";

                Object[] row = new Object[]{
                    s.getIdStok(),
                    s.getTanggalMasuk(),
                    s.getBeratMasuk(),
                    s.getBeratTersedia(),
                    s.getJumlah(),
                    s.getStatusStok(),
                    namaJenis,
                    hasilPengolahan,
                    idSetoran
                };

                // Filter
                if (keyword != null && !keyword.isEmpty()) {
                    boolean match = false;
                    String lowerKeyword = keyword.toLowerCase();

                    if (filterKolom == null || filterKolom.equals("--Pilih Kolom--")) {
                        for (Object obj : row) {
                            if (obj != null && obj.toString().toLowerCase().contains(lowerKeyword)) {
                                match = true;
                                break;
                            }
                        }
                    } else {
                        int idx = getColumnIndex(filterKolom);
                        if (idx != -1 && row[idx] != null && row[idx].toString().toLowerCase().contains(lowerKeyword)) {
                            match = true;
                        }
                    }

                    if (!match) {
                        continue;
                    }
                }

                model.addRow(row);
            }

            TableKELOLASAMPAH.setModel(model);

            // Center alignment
            DefaultTableCellRenderer center = new DefaultTableCellRenderer();
            center.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < TableKELOLASAMPAH.getColumnCount(); i++) {
                TableKELOLASAMPAH.getColumnModel().getColumn(i).setCellRenderer(center);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal tampil data: " + e.getMessage());
        }
    }

    private int getColumnIndex(String kolom) {
        switch (kolom) {
            case "ID Stok":
                return 0;
            case "Tanggal Masuk":
                return 1;
            case "Berat Masuk":
                return 2;
            case "Berat Tersedia":
                return 3;
            case "Jumlah":
                return 4;
            case "Status Stok":
                return 5;
            case "Nama Jenis Sampah":
                return 6;
            case "Hasil Pengolahan":
                return 7;
            case "ID Setoran":
                return 8;
            default:
                return -1;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TableKELOLASAMPAH = new javax.swing.JTable();
        jButtonPENGOLAHAN = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jToggleButtonPILIH = new javax.swing.JToggleButton();
        jComboBox1 = new javax.swing.JComboBox();
        jTextFieldTEXTUNTUKCARI = new javax.swing.JTextField();
        jToggleButtonCARI = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableKELOLASAMPAH.setBackground(new java.awt.Color(255, 255, 255));
        TableKELOLASAMPAH.setForeground(new java.awt.Color(0, 0, 0));
        TableKELOLASAMPAH.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TableKELOLASAMPAH);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 870, 300));

        jButtonPENGOLAHAN.setBackground(new java.awt.Color(51, 102, 0));
        jButtonPENGOLAHAN.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButtonPENGOLAHAN.setForeground(new java.awt.Color(255, 255, 255));
        jButtonPENGOLAHAN.setText("LAKUKAN PENGOLAHAN SAMPAH");
        jButtonPENGOLAHAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPENGOLAHANActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonPENGOLAHAN, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 90, 260, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/foto/Group 150.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, 50));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/foto/LOGO BANK SAMPAH.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 127, 83));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Gabriola", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("BANK SAMPAH - MENU PETUGAS");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, -1, 30));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("STOK SAMPAH DI GUDANG");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, -1, 40));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Gabriola", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, 80));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Pilih & cari berdasarkan :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        jToggleButtonPILIH.setBackground(new java.awt.Color(0, 51, 0));
        jToggleButtonPILIH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jToggleButtonPILIH.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButtonPILIH.setText("PILIH");
        jToggleButtonPILIH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonPILIHActionPerformed(evt);
            }
        });
        getContentPane().add(jToggleButtonPILIH, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, -1, -1));

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 140, -1));

        jTextFieldTEXTUNTUKCARI.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldTEXTUNTUKCARI.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jTextFieldTEXTUNTUKCARI, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 110, -1));

        jToggleButtonCARI.setBackground(new java.awt.Color(0, 51, 0));
        jToggleButtonCARI.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jToggleButtonCARI.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButtonCARI.setText("cari");
        jToggleButtonCARI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonCARIActionPerformed(evt);
            }
        });
        getContentPane().add(jToggleButtonCARI, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, 60, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/foto/Group 148.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPENGOLAHANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPENGOLAHANActionPerformed
        PENGOLAHANSAMPAH dialog = new PENGOLAHANSAMPAH(this, true);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

    }//GEN-LAST:event_jButtonPENGOLAHANActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        MENUPETUGAS z = new MENUPETUGAS();
        z.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jToggleButtonPILIHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonPILIHActionPerformed

        Object selectedObj = jComboBox1.getSelectedItem();
        if (selectedObj == null) {
            JOptionPane.showMessageDialog(this, "Silakan pilih kolom terlebih dahulu!");
            return;
        }

        String selectedKolom = selectedObj.toString();

        if (selectedKolom.equals("--Pilih Kolom--")) {
            JOptionPane.showMessageDialog(this, "Silakan pilih kolom terlebih dahulu!");
            return;
        }

        int colIndex = getColumnIndex(selectedKolom);
        if (colIndex != -1) {
            // Reset semua renderer ke default
            DefaultTableCellRenderer defaultRenderer = new DefaultTableCellRenderer();
            defaultRenderer.setHorizontalAlignment(JLabel.CENTER);

            for (int i = 0; i < TableKELOLASAMPAH.getColumnCount(); i++) {
                TableKELOLASAMPAH.getColumnModel().getColumn(i).setCellRenderer(defaultRenderer);
            }

            // Sorot kolom yang dipilih dengan warna kuning
            DefaultTableCellRenderer highlight = new DefaultTableCellRenderer();
            highlight.setBackground(java.awt.Color.YELLOW);
            highlight.setHorizontalAlignment(JLabel.CENTER);

            TableKELOLASAMPAH.getColumnModel().getColumn(colIndex).setCellRenderer(highlight);

            TableKELOLASAMPAH.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Kolom tidak dikenali!");
        }
    }//GEN-LAST:event_jToggleButtonPILIHActionPerformed

    private void jToggleButtonCARIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonCARIActionPerformed
        Object selectedObj = jComboBox1.getSelectedItem();
        String selectedKolom = (selectedObj != null && !selectedObj.toString().equals("--Pilih Kolom--"))
                ? selectedObj.toString()
                : null;

        String keyword = (jTextFieldTEXTUNTUKCARI.getText() != null)
                ? jTextFieldTEXTUNTUKCARI.getText().trim()
                : "";

        showTable(selectedKolom, keyword);
    }//GEN-LAST:event_jToggleButtonCARIActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PENYIMPANANGUDANG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PENYIMPANANGUDANG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PENYIMPANANGUDANG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PENYIMPANANGUDANG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PENYIMPANANGUDANG().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableKELOLASAMPAH;
    private javax.swing.JButton jButtonPENGOLAHAN;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldTEXTUNTUKCARI;
    private javax.swing.JToggleButton jToggleButtonCARI;
    private javax.swing.JToggleButton jToggleButtonPILIH;
    // End of variables declaration//GEN-END:variables
}
