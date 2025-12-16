/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Login;

import java.awt.Window;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import jnafilechooser.api.JnaFileChooser;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author HP
 */
public class DATAPETUGAS extends javax.swing.JFrame {

    EntityManagerFactory emf;
    EntityManager em;

    String idPetugasLama, namaPetugasLama, usernameLama, passwordLama, namaIbuLama;

    public void connect() {
        try {
            emf = Persistence.createEntityManagerFactory("BANK_SAMPAHPU");
            em = emf.createEntityManager();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Koneksi gagal: " + e.getMessage());
        }
    }

    public DATAPETUGAS() {
        initComponents();
        connect();
        showTable();
        autoID();
        setLocationRelativeTo(null);

        TablePETUGAS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = TablePETUGAS.getSelectedRow();

                idPetugasLama = TablePETUGAS.getValueAt(row, 0).toString();
                namaPetugasLama = TablePETUGAS.getValueAt(row, 1).toString();
                usernameLama = TablePETUGAS.getValueAt(row, 2).toString();
                passwordLama = TablePETUGAS.getValueAt(row, 3).toString();
                namaIbuLama = TablePETUGAS.getValueAt(row, 4).toString();
            }
        });

    }

    public void showTable() {
        try {
            if (em.isOpen()) {
                em.close();
            }
            em = emf.createEntityManager();

            List<Petugas> hasil = em.createNamedQuery("Petugas.findAll", Petugas.class)
                    .getResultList();

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{
                        "ID Petugas",
                        "Nama Petugas",
                        "Username",
                        "Password",
                        "Nama Ibu"
                    }, 0
            );

            for (Petugas p : hasil) {
                model.addRow(new Object[]{
                    p.getIdPetugas(),
                    p.getNamaPetugas(),
                    p.getUsernamePetugas(),
                    p.getPasswordPetugas(),
                    p.getNamaIbu()
                });
            }

            TablePETUGAS.setModel(model);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal tampil data: " + e.getMessage());
        }
    }

    public void exportToCSV(javax.swing.JTable table, String filePath) {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            FileWriter csv = new FileWriter(filePath);

            for (int i = 0; i < model.getColumnCount(); i++) {
                csv.write(model.getColumnName(i) + (i < model.getColumnCount() - 1 ? "," : ""));
            }
            csv.write("\n");

            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Object value = model.getValueAt(i, j);
                    csv.write((value != null ? value.toString() : "")
                            + (j < model.getColumnCount() - 1 ? "," : ""));
                }
                csv.write("\n");
            }

            csv.close();
            JOptionPane.showMessageDialog(this, "Data berhasil diekspor ke: " + filePath);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal ekspor: " + e.getMessage());
        }
    }

    public int autoID() {
        int id = 1;
        try {
            List<Integer> hasil = em.createQuery(
                    "SELECT s.idSetoran FROM Setoran s ORDER BY s.idSetoran DESC", Integer.class)
                    .setMaxResults(1)
                    .getResultList();
            if (!hasil.isEmpty()) {
                id = hasil.get(0) + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        TablePETUGAS = new javax.swing.JTable();
        jButtonEDIT = new javax.swing.JButton();
        jButtonTAMBAH = new javax.swing.JButton();
        jButtonUNDUHDATA = new javax.swing.JButton();
        jButtonHAPUS1 = new javax.swing.JButton();
        jButtonCETAK = new javax.swing.JButton();
        jLabel2KELUAR = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablePETUGAS.setBackground(new java.awt.Color(255, 255, 255));
        TablePETUGAS.setForeground(new java.awt.Color(0, 0, 0));
        TablePETUGAS.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TablePETUGAS);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 870, 300));

        jButtonEDIT.setBackground(new java.awt.Color(0, 51, 0));
        jButtonEDIT.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButtonEDIT.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEDIT.setText("EDIT");
        jButtonEDIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEDITActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonEDIT, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 100, 30));

        jButtonTAMBAH.setBackground(new java.awt.Color(0, 51, 0));
        jButtonTAMBAH.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButtonTAMBAH.setForeground(new java.awt.Color(255, 255, 255));
        jButtonTAMBAH.setText("TAMBAH");
        jButtonTAMBAH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTAMBAHActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonTAMBAH, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 100, 30));

        jButtonUNDUHDATA.setBackground(new java.awt.Color(0, 51, 0));
        jButtonUNDUHDATA.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButtonUNDUHDATA.setForeground(new java.awt.Color(255, 255, 255));
        jButtonUNDUHDATA.setText("UNDUH DATA");
        jButtonUNDUHDATA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUNDUHDATAActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonUNDUHDATA, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 90, 120, 30));

        jButtonHAPUS1.setBackground(new java.awt.Color(0, 51, 0));
        jButtonHAPUS1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButtonHAPUS1.setForeground(new java.awt.Color(255, 255, 255));
        jButtonHAPUS1.setText("HAPUS");
        jButtonHAPUS1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHAPUS1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonHAPUS1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, 100, 30));

        jButtonCETAK.setBackground(new java.awt.Color(51, 102, 0));
        jButtonCETAK.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButtonCETAK.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCETAK.setText("CETAK");
        jButtonCETAK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCETAKActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCETAK, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 90, 120, 30));

        jLabel2KELUAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/foto/Group 150.png"))); // NOI18N
        jLabel2KELUAR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2KELUARMouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2KELUAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, 50));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/foto/LOGO BANK SAMPAH.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 127, 83));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Gabriola", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("BANK SAMPAH - MENU PETUGAS");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, -1, 30));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("DATA PETUGAS");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 20, -1, 40));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Gabriola", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, 80));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/foto/Group 148.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEDITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEDITActionPerformed
        int row = TablePETUGAS.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data dulu di tabel!");
            return;
        }

        idPetugasLama = TablePETUGAS.getValueAt(row, 0).toString();
        namaPetugasLama = TablePETUGAS.getValueAt(row, 1).toString();
        usernameLama = TablePETUGAS.getValueAt(row, 2).toString();
        passwordLama = TablePETUGAS.getValueAt(row, 3).toString();
        namaIbuLama = TablePETUGAS.getValueAt(row, 4).toString();

        editDATAPETUGAS dialog = new editDATAPETUGAS(
                this,
                true,
                idPetugasLama,
                namaPetugasLama,
                usernameLama,
                passwordLama,
                namaIbuLama
        );

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        showTable();
    }//GEN-LAST:event_jButtonEDITActionPerformed

    private void jButtonTAMBAHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTAMBAHActionPerformed
        tambahDATAPETUGAS dialog = new tambahDATAPETUGAS(this, true); //
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        showTable();
    }//GEN-LAST:event_jButtonTAMBAHActionPerformed

    private void jButtonUNDUHDATAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUNDUHDATAActionPerformed
        try {
            if (TablePETUGAS.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Tidak ada data untuk diekspor!");
                return;
            }

            JnaFileChooser chooser = new JnaFileChooser();
            chooser.setMode(JnaFileChooser.Mode.Files);
            chooser.setTitle("Simpan Data Petugas");
            chooser.setDefaultFileName("Data_Petugas");
            chooser.setMultiSelectionEnabled(false);

            Window window = null;
            boolean action = chooser.showSaveDialog(window);

            if (action) {
                String fileString = chooser.getSelectedFile().getAbsolutePath();
                if (!fileString.toLowerCase().endsWith(".csv")) {
                    fileString += ".csv";
                }

                exportToCSV(TablePETUGAS, fileString);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal ekspor: " + e.getMessage());
        }
    }//GEN-LAST:event_jButtonUNDUHDATAActionPerformed

    private void jButtonHAPUS1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHAPUS1ActionPerformed
        int[] rows = TablePETUGAS.getSelectedRows(); // ambil semua baris yang dipilih

        if (rows.length == 0) {
            JOptionPane.showMessageDialog(this, "Pilih data petugas dulu di tabel!");
            return;
        }

        String[] idPetugasLama = new String[rows.length];
        String[] namaPetugasLama = new String[rows.length];
        String[] usernameLama = new String[rows.length];
        String[] passwordLama = new String[rows.length];
        String[] namaIbuLama = new String[rows.length];

        for (int i = 0; i < rows.length; i++) {

            idPetugasLama[i] = TablePETUGAS.getValueAt(rows[i], 0).toString();
            namaPetugasLama[i] = TablePETUGAS.getValueAt(rows[i], 1).toString();
            usernameLama[i] = TablePETUGAS.getValueAt(rows[i], 2).toString();
            passwordLama[i] = TablePETUGAS.getValueAt(rows[i], 3).toString();
            namaIbuLama[i] = TablePETUGAS.getValueAt(rows[i], 4).toString();
        }

        hapusDATAPETUGAS dialog = new hapusDATAPETUGAS(
                this,
                true,
                idPetugasLama,
                namaPetugasLama,
                usernameLama,
                passwordLama,
                namaIbuLama
        );

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        showTable();
    }//GEN-LAST:event_jButtonHAPUS1ActionPerformed

    private void jLabel2KELUARMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2KELUARMouseClicked
        MENUPETUGAS z = new MENUPETUGAS();
        z.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel2KELUARMouseClicked

    private void jButtonCETAKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCETAKActionPerformed
        try {
            String path = "src/Login/datapetugas.jasper";
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
            java.util.logging.Logger.getLogger(DATAPETUGAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DATAPETUGAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DATAPETUGAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DATAPETUGAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DATAPETUGAS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablePETUGAS;
    private javax.swing.JButton jButtonCETAK;
    private javax.swing.JButton jButtonEDIT;
    private javax.swing.JButton jButtonHAPUS1;
    private javax.swing.JButton jButtonTAMBAH;
    private javax.swing.JButton jButtonUNDUHDATA;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2KELUAR;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
