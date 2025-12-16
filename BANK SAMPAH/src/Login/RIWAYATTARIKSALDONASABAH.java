/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Login;

import Login.MENUPETUGAS;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author HP
 */
public class RIWAYATTARIKSALDONASABAH extends javax.swing.JFrame {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Nasabah nasabah;

    NumberFormat kursIDR = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

   public RIWAYATTARIKSALDONASABAH(Nasabah nasabah) { 
        this.nasabah = nasabah;
        initComponents();
        connect();
        initComboBox();
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

            // UBAH QUERY KE ENTITY PENARIKAN
            // Pastikan NamedQuery "Penarikan.findAll" ada di Entity Penarikan
            // Atau gunakan: "SELECT p FROM Penarikan p ORDER BY p.tanggal DESC"
            List<Penarikan> hasil = em.createQuery("SELECT p FROM Penarikan p ORDER BY p.tanggal DESC", Penarikan.class)
                    .getResultList();

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"ID Penarikan", "Tanggal", "Nama Nasabah", "Metode", "Jumlah Penarikan"}, 0
            );

            for (Penarikan p : hasil) {
                boolean match = true;

                // Logika Pencarian
                if (kolom != null && keyword != null && !keyword.isEmpty()) {
                    String value = "";
                    switch (kolom) {
                        case "ID Penarikan":
                            value = String.valueOf(p.getIdPenarikan()); // Pastikan ada getter ID
                            break;
                        case "Nama Nasabah":
                            value = (p.getIdNasabah() != null) ? p.getIdNasabah().getNamaNasabah() : "-";
                            break;
                        case "Metode":
                            value = p.getMetode(); // Sesuaikan nama getter di entity Penarikan
                            break;
                        case "Tanggal":
                            value = sdf.format(p.getTanggal());
                            break;
                    }
                    if (!value.toLowerCase().contains(keyword.toLowerCase())) {
                        match = false;
                    }
                }

                if (match) {
                    model.addRow(new Object[]{
                        p.getIdPenarikan(), // ID (Auto/Manual)
                        sdf.format(p.getTanggal()), // Tanggal
                        (p.getIdNasabah() != null ? p.getIdNasabah().getNamaNasabah() : "-"), // Nama Nasabah
                        p.getMetode(), // Metode (Cash/Transfer/E-Wallet)
                        kursIDR.format(p.getJumlah()) // Jumlah (Format Rupiah)
                    });
                }
            }

            TableRIWAYAT.setModel(model);

            // Center table content (kecuali kolom Jumlah biar rapi rata kanan/kiri)
            DefaultTableCellRenderer center = new DefaultTableCellRenderer();
            center.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < TableRIWAYAT.getColumnCount() - 1; i++) {
                TableRIWAYAT.getColumnModel().getColumn(i).setCellRenderer(center);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal tampil data: " + e.getMessage());
        }
    }

    private void initComboBox() {
        jComboBox1.removeAllItems();
        jComboBox1.addItem("--Pilih Kolom--");
        jComboBox1.addItem("ID Penarikan");
        jComboBox1.addItem("Nama Nasabah");
        jComboBox1.addItem("Metode");
        jComboBox1.addItem("Tanggal");
    }

    private int getColumnIndex(String kolom) {
        switch (kolom) {
            case "ID Penarikan":
                return 0;
            case "Tanggal":
                return 1;
            case "Nama Nasabah":
                return 2;
            case "Metode":
                return 3;
            case "Jumlah Penarikan":
                return 4;
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
        jButtonCETAK = new javax.swing.JButton();
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

        jButtonCETAK.setBackground(new java.awt.Color(153, 0, 0));
        jButtonCETAK.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jButtonCETAK.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCETAK.setText("CETAK");
        jButtonCETAK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCETAKActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCETAK, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 90, 100, 30));

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
        jLabel7.setText("BANK SAMPAH - MENU NASABAH");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 10, -1, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Pilih & cari berdasarkan :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("RIWATAT TARIK SETORAN");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, -1, 40));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Gabriola", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, 80));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/foto/Group 148.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCETAKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCETAKActionPerformed
        try {
            String path = "src/Login/STRUK SALDO.jasper";
            HashMap<String, Object> parameters = new HashMap<>();

            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/BANK_SAMPAH", "postgres", "BISMILLAH");

            JasperPrint jprint = JasperFillManager.fillReport(path, parameters, conn);
            JasperViewer jviewer = new JasperViewer(jprint, false);
            jviewer.setSize(800, 600);
            jviewer.setLocationRelativeTo(this);
            jviewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jviewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButtonCETAKActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        MENUNASABAH z = new MENUNASABAH(nasabah);
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
    private javax.swing.JButton jButtonCETAK;
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
