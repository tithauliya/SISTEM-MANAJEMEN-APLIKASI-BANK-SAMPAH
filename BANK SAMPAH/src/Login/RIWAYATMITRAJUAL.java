/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Login;

import Login.MENUPETUGAS;
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
public class RIWAYATMITRAJUAL extends javax.swing.JFrame {

    private EntityManagerFactory emf;
    private EntityManager em;

    public RIWAYATMITRAJUAL() {
        initComponents();
        initComboBox();
        connect();
        showTable();
        setLocationRelativeTo(null);
    }

    private void connect() {
        try {
            emf = Persistence.createEntityManagerFactory("BANK_SAMPAHPU");
            em = emf.createEntityManager();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Koneksi gagal: " + e.getMessage());
        }
    }

    public void showTable() {
        showTable(null, null);
    }

    public void showTable(String kolom, String keyword) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            em.clear();

            List<PenjualanMitra> hasil = em.createNamedQuery("PenjualanMitra.findAll", PenjualanMitra.class)
                    .getResultList();

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"ID Penjualan", "Tanggal", "Mitra", "Pengolahan", "Berat (Kg)", "Total Harga"}, 0
            );

            for (PenjualanMitra p : hasil) {
                if (kolom != null && keyword != null && !keyword.isEmpty()) {
                    String value = "";
                    switch (kolom) {
                        case "ID Penjualan":
                            value = String.valueOf(p.getIdPenjualanMitra());
                            break;
                        case "Tanggal":
                            value = String.valueOf(p.getTanggalPenjualanMitra());
                            break;
                        case "Mitra":
                            value = (p.getIdMitra() != null ? p.getIdMitra().getNamaMitra() : "-");
                            break;
                        case "Pengolahan":
                            value = (p.getIdPengolahan() != null ? p.getIdPengolahan().getHasilPengolahan() : "-");
                            break;
                        case "Berat (Kg)":
                            value = String.valueOf(p.getBeratKgPenjualanMitra());
                            break;
                        case "Total Harga":
                            value = String.valueOf(p.getTotalHargaPenjualanMitra());
                            break;
                    }
                    if (!value.toLowerCase().contains(keyword.toLowerCase())) {
                        continue;
                    }
                }

                model.addRow(new Object[]{
                    p.getIdPenjualanMitra(),
                    p.getTanggalPenjualanMitra(),
                    (p.getIdMitra() != null ? p.getIdMitra().getNamaMitra() : "-"),
                    (p.getIdPengolahan() != null ? p.getIdPengolahan().getHasilPengolahan() : "-"),
                    p.getBeratKgPenjualanMitra(),
                    p.getTotalHargaPenjualanMitra()
                });
            }

            TableRIWAYAT.setModel(model);

            // Center table content
            DefaultTableCellRenderer center = new DefaultTableCellRenderer();
            center.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < TableRIWAYAT.getColumnCount(); i++) {
                TableRIWAYAT.getColumnModel().getColumn(i).setCellRenderer(center);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal tampil data: " + e.getMessage());
        }
    }

    private void initComboBox() {
        jComboBox1.addItem("--Pilih Kolom--");
        jComboBox1.addItem("ID Penjualan");
        jComboBox1.addItem("Tanggal");
        jComboBox1.addItem("Mitra");
        jComboBox1.addItem("Pengolahan");
        jComboBox1.addItem("Berat (Kg)");
        jComboBox1.addItem("Total Harga");
    }

    private int getColumnIndex(String kolom) {
        switch (kolom) {
            case "ID Penjualan":
                return 0;
            case "Tanggal":
                return 1;
            case "Mitra":
                return 2;
            case "Pengolahan":
                return 3;
            case "Berat (Kg)":
                return 4;
            case "Total Harga":
                return 5;
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
        TableRIWAYAT = new javax.swing.JTable();
        jButtonHAPUS = new javax.swing.JButton();
        jToggleButtonPILIH = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextFieldTEXTUNTUKCARI = new javax.swing.JTextField();
        jToggleButtonCARI = new javax.swing.JToggleButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableRIWAYAT.setBackground(new java.awt.Color(255, 255, 255));
        TableRIWAYAT.setForeground(new java.awt.Color(0, 0, 0));
        TableRIWAYAT.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TableRIWAYAT);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 870, 300));

        jButtonHAPUS.setBackground(new java.awt.Color(153, 0, 0));
        jButtonHAPUS.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jButtonHAPUS.setForeground(new java.awt.Color(255, 255, 255));
        jButtonHAPUS.setText("HAPUS DATA");
        jButtonHAPUS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHAPUSActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonHAPUS, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 90, 100, 30));

        jToggleButtonPILIH.setBackground(new java.awt.Color(0, 51, 0));
        jToggleButtonPILIH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jToggleButtonPILIH.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButtonPILIH.setText("PILIH");
        jToggleButtonPILIH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonPILIHActionPerformed(evt);
            }
        });
        getContentPane().add(jToggleButtonPILIH, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/foto/Group 150.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, 50));

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 180, 20));

        jTextFieldTEXTUNTUKCARI.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldTEXTUNTUKCARI.setForeground(new java.awt.Color(0, 0, 0));
        jTextFieldTEXTUNTUKCARI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTEXTUNTUKCARIActionPerformed(evt);
            }
        });
        getContentPane().add(jTextFieldTEXTUNTUKCARI, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 220, -1));

        jToggleButtonCARI.setBackground(new java.awt.Color(0, 51, 0));
        jToggleButtonCARI.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jToggleButtonCARI.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButtonCARI.setText("cari");
        jToggleButtonCARI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonCARIActionPerformed(evt);
            }
        });
        getContentPane().add(jToggleButtonCARI, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 90, 60, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/foto/LOGO BANK SAMPAH.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 127, 83));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Gabriola", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("BANK SAMPAH - MENU PETUGAS");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 10, -1, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Pilih & cari berdasarkan :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("RIWAYAT MITRA JUAL");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 20, -1, 40));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Gabriola", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, 80));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/foto/Group 148.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonHAPUSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHAPUSActionPerformed
        int[] rows = TableRIWAYAT.getSelectedRows(); // ambil semua baris yang dipilih
        if (rows.length == 0) {
            JOptionPane.showMessageDialog(this, "Pilih data penjualan mitra dulu di tabel!");
            return;
        }

        String[] idPenjualanLama = new String[rows.length];
        String[] namaMitraLama = new String[rows.length];
        String[] pengolahanLama = new String[rows.length];
        String[] beratLama = new String[rows.length];
        String[] totalHargaLama = new String[rows.length];

        for (int i = 0; i < rows.length; i++) {
            idPenjualanLama[i] = TableRIWAYAT.getValueAt(rows[i], 0).toString();
            namaMitraLama[i] = TableRIWAYAT.getValueAt(rows[i], 2).toString();
            pengolahanLama[i] = TableRIWAYAT.getValueAt(rows[i], 3).toString();
            beratLama[i] = TableRIWAYAT.getValueAt(rows[i], 4).toString();
            totalHargaLama[i] = TableRIWAYAT.getValueAt(rows[i], 5).toString();
        }

        hapuspenjualanmitra dialog = new hapuspenjualanmitra(this, true,
                idPenjualanLama, namaMitraLama,
                pengolahanLama, beratLama, totalHargaLama
        );
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        showTable();
    }//GEN-LAST:event_jButtonHAPUSActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        PENJUALANKEMITRA z = new PENJUALANKEMITRA();
        z.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jToggleButtonPILIHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonPILIHActionPerformed

        String selectedKolom = jComboBox1.getSelectedItem().toString();

        if (selectedKolom.equals("--Pilih Kolom--")) {
            JOptionPane.showMessageDialog(this, "Silakan pilih kolom terlebih dahulu!");
            return;
        }

        int colIndex = getColumnIndex(selectedKolom);
        if (colIndex != -1) {

            for (int i = 0; i < TableRIWAYAT.getColumnCount(); i++) {
                TableRIWAYAT.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer());
            }

            // Sorot kolom yang dipilih dengan warna kuning
            DefaultTableCellRenderer highlight = new DefaultTableCellRenderer();
            highlight.setBackground(java.awt.Color.YELLOW);
            highlight.setHorizontalAlignment(JLabel.CENTER);
            TableRIWAYAT.getColumnModel().getColumn(colIndex).setCellRenderer(highlight);

            TableRIWAYAT.repaint();
        }
    }//GEN-LAST:event_jToggleButtonPILIHActionPerformed

    private void jToggleButtonCARIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonCARIActionPerformed
        String kolom = jComboBox1.getSelectedItem().toString();
        String keyword = jTextFieldTEXTUNTUKCARI.getText();
        if (kolom.equals("--Pilih Kolom--")) {
            kolom = null;
        }
        showTable(kolom, keyword);
    }//GEN-LAST:event_jToggleButtonCARIActionPerformed

    private void jTextFieldTEXTUNTUKCARIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTEXTUNTUKCARIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTEXTUNTUKCARIActionPerformed

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
//            java.util.logging.Logger.getLogger(KELOLAJENISSAMPAH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(KELOLAJENISSAMPAH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(KELOLAJENISSAMPAH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(KELOLAJENISSAMPAH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
////        java.awt.EventQueue.invokeLater(new Runnable() {
////            public void run() {
////                new KELOLAJENISSAMPAH().setVisible(true);
////            }
////        });
////    }
//
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableRIWAYAT;
    private javax.swing.JButton jButtonHAPUS;
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
