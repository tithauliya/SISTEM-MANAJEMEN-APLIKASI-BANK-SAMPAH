/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Login;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
public class inputsetoranNASABAH extends javax.swing.JFrame {

    EntityManagerFactory emf;
    EntityManager em;
    private Integer idNasabah;
    private String nama;
    private Saldo saldo;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public inputsetoranNASABAH() {
        initComponents();
        connect();
        setAutoID();

        jComboBoxNASABAH();
        jComboBoxJENISSAMPAH();
        jComboBoxPETUGAS1();
        jComboBoxSTATUS();
        tampilSetoran();
        setTanggalHariIni();

        jComboBoxJENISSAMPAH.addActionListener(e -> hitungTotalOtomatis());
        jTextFieldBERAT.addActionListener(e -> hitungTotalOtomatis());

        setLocationRelativeTo(null);

        TableSETORAN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = TableSETORAN.getSelectedRow();
                if (row >= 0) {
                    jTextFieldIDSETORAN.setText(TableSETORAN.getValueAt(row, 0).toString());
                    jTextFieldTANGGAL.setText(TableSETORAN.getValueAt(row, 1).toString());
                    jTextFieldBERAT.setText(TableSETORAN.getValueAt(row, 2).toString());
                    jTextFieldTOTALHARGA1.setText(TableSETORAN.getValueAt(row, 3).toString());
                    jComboBoxSTATUS.setSelectedItem(TableSETORAN.getValueAt(row, 4).toString());

                    // Jenis Sampah
                    String namaSampah = TableSETORAN.getValueAt(row, 5).toString();
                    for (int i = 0; i < jComboBoxJENISSAMPAH.getItemCount(); i++) {
                        JenisSampah js = (JenisSampah) jComboBoxJENISSAMPAH.getItemAt(i);
                        if (js != null && js.getNamaSampah().equals(namaSampah)) {
                            jComboBoxJENISSAMPAH.setSelectedIndex(i);
                            break;
                        }
                    }

                    // Nasabah
                    String namaNasabah = TableSETORAN.getValueAt(row, 6).toString();
                    for (int i = 0; i < jComboBoxNAMANASABAH1.getItemCount(); i++) {
                        Nasabah n = (Nasabah) jComboBoxNAMANASABAH1.getItemAt(i);
                        if (n != null && n.getNamaNasabah().equals(namaNasabah)) {
                            jComboBoxNAMANASABAH1.setSelectedIndex(i);
                            break;
                        }
                    }

                    // Petugas
                    String namaPetugas = TableSETORAN.getValueAt(row, 7).toString();
                    for (int i = 0; i < jComboBoxPETUGAS1.getItemCount(); i++) {
                        Petugas p = (Petugas) jComboBoxPETUGAS1.getItemAt(i);
                        if (p != null && p.getNamaPetugas().equals(namaPetugas)) {
                            jComboBoxPETUGAS1.setSelectedIndex(i);
                            break;
                        }
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

    private Date normalizeDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private void setAutoID() {
        jTextFieldIDSETORAN.setText(String.valueOf(autoID()));
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

    private void setTanggalHariIni() {
        jTextFieldTANGGAL.setText(sdf.format(new Date()));
    }

    private void hitungTotalOtomatis() {
        try {
            JenisSampah js = (JenisSampah) jComboBoxJENISSAMPAH.getSelectedItem();
            if (js == null || jTextFieldBERAT.getText().trim().isEmpty()) {
                jTextFieldTOTALHARGA1.setText("");
                return;
            }
            int berat = Integer.parseInt(jTextFieldBERAT.getText().trim());
            BigInteger total = js.getHargaPerKg().multiply(BigInteger.valueOf(berat));
            jTextFieldTOTALHARGA1.setText(total.toString());
        } catch (NumberFormatException e) {
            jTextFieldTOTALHARGA1.setText("");
        }
    }

    public void bersih() {
        jTextFieldIDSETORAN.setText("");
        jComboBoxNAMANASABAH1.setSelectedIndex(-1);
        jComboBoxJENISSAMPAH.setSelectedIndex(-1);
        jComboBoxPETUGAS1.setSelectedIndex(-1);
        jComboBoxSTATUS.setSelectedIndex(-1);
        jTextFieldBERAT.setText("");
        jTextFieldTOTALHARGA1.setText("");
        setTanggalHariIni();
        setAutoID();
    }

    public void tampilSetoran() {
        try {
            String[] kolom = {"ID Setoran", "Tanggal", "Berat (Kg)", "Total Harga", "Status", "Jenis Sampah", "Nasabah", "Petugas"};
            DefaultTableModel model = new DefaultTableModel(null, kolom);
            List<Setoran> list = em.createNamedQuery("Setoran.findAll", Setoran.class).getResultList();
            for (Setoran s : list) {
                String[] data = {
                    String.valueOf(s.getIdSetoran()),
                    sdf.format(normalizeDate(s.getTanggalSetoran())),
                    String.valueOf(s.getBeratKgSetoran()),
                    s.getTotalHargaSetoran().toString(),
                    s.getStatusSetoran(),
                    s.getIdJenissampah() != null ? s.getIdJenissampah().getNamaSampah() : "-",
                    s.getIdNasabah() != null ? s.getIdNasabah().getNamaNasabah() : "-",
                    s.getIdPetugas() != null ? s.getIdPetugas().getNamaPetugas() : "-"
                };
                model.addRow(data);
            }
            TableSETORAN.setModel(model);

            DefaultTableCellRenderer tengah = new DefaultTableCellRenderer();
            tengah.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < TableSETORAN.getColumnCount(); i++) {
                TableSETORAN.getColumnModel().getColumn(i).setCellRenderer(tengah);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menampilkan data setoran: " + e.getMessage());
        }
    }

    public void jComboBoxNASABAH() {
        try {
            List<Nasabah> list = em.createNamedQuery("Nasabah.findAll", Nasabah.class).getResultList();
            jComboBoxNAMANASABAH1.removeAllItems();
            jComboBoxNAMANASABAH1.addItem(null);
            for (Nasabah n : list) {
                jComboBoxNAMANASABAH1.addItem(n);
            }
            jComboBoxNAMANASABAH1.setSelectedIndex(-1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal load nasabah: " + e.getMessage());
        }
    }

    public void jComboBoxJENISSAMPAH() {
        try {
            List<JenisSampah> list = em.createNamedQuery("JenisSampah.findAll", JenisSampah.class).getResultList();
            jComboBoxJENISSAMPAH.removeAllItems();
            jComboBoxJENISSAMPAH.addItem(null);
            for (JenisSampah js : list) {
                jComboBoxJENISSAMPAH.addItem(js);
            }
            jComboBoxJENISSAMPAH.setSelectedIndex(-1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal load jenis sampah: " + e.getMessage());
        }
    }

    public void jComboBoxPETUGAS1() {
        try {
            List<Petugas> list = em.createNamedQuery("Petugas.findAll", Petugas.class).getResultList();
            jComboBoxPETUGAS1.removeAllItems();
            jComboBoxPETUGAS1.addItem(null);
            for (Petugas p : list) {
                jComboBoxPETUGAS1.addItem(p);
            }
            jComboBoxPETUGAS1.setSelectedIndex(-1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal load petugas: " + e.getMessage());
        }
    }

    public void jComboBoxSTATUS() {
        jComboBoxSTATUS.removeAllItems();
        jComboBoxSTATUS.addItem(null);
        jComboBoxSTATUS.addItem("Belum Disetorkan");
        jComboBoxSTATUS.addItem("Sudah Disetorkan");
        jComboBoxSTATUS.addItem("Diproses");
        jComboBoxSTATUS.addItem("Ditolak");
        jComboBoxSTATUS.setSelectedIndex(-1);
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
        TableSETORAN = new javax.swing.JTable();
        jToggleBATAL = new javax.swing.JToggleButton();
        jComboBoxSTATUS = new javax.swing.JComboBox();
        jComboBoxJENISSAMPAH = new javax.swing.JComboBox();
        jToggleSIMPAN = new javax.swing.JToggleButton();
        jTextFieldIDSETORAN = new javax.swing.JTextField();
        jTextFieldTOTALHARGA1 = new javax.swing.JTextField();
        jTextFieldBERAT = new javax.swing.JTextField();
        jComboBoxNAMANASABAH1 = new javax.swing.JComboBox();
        jComboBoxPETUGAS1 = new javax.swing.JComboBox();
        jToggleHAPUS = new javax.swing.JToggleButton();
        jToggleUPDATE = new javax.swing.JToggleButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButtonCETAK = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldTANGGAL = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nasabah");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setForeground(new java.awt.Color(0, 0, 0));

        TableSETORAN.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TableSETORAN);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, 580, 310));

        jToggleBATAL.setBackground(new java.awt.Color(102, 153, 0));
        jToggleBATAL.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jToggleBATAL.setForeground(new java.awt.Color(255, 255, 255));
        jToggleBATAL.setText("BATAL");
        jToggleBATAL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleBATALActionPerformed(evt);
            }
        });
        getContentPane().add(jToggleBATAL, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 400, 100, 30));

        jComboBoxSTATUS.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxSTATUS.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxSTATUS.setForeground(new java.awt.Color(0, 0, 0));
        jComboBoxSTATUS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        getContentPane().add(jComboBoxSTATUS, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 150, 20));

        jComboBoxJENISSAMPAH.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxJENISSAMPAH.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jComboBoxJENISSAMPAH.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jComboBoxJENISSAMPAH, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 160, 30));

        jToggleSIMPAN.setBackground(new java.awt.Color(0, 51, 0));
        jToggleSIMPAN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jToggleSIMPAN.setForeground(new java.awt.Color(255, 255, 255));
        jToggleSIMPAN.setText("SIMPAN");
        jToggleSIMPAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleSIMPANActionPerformed(evt);
            }
        });
        getContentPane().add(jToggleSIMPAN, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 400, 100, 30));

        jTextFieldIDSETORAN.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldIDSETORAN.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextFieldIDSETORAN.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jTextFieldIDSETORAN, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 330, 30));

        jTextFieldTOTALHARGA1.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldTOTALHARGA1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextFieldTOTALHARGA1.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jTextFieldTOTALHARGA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 330, 30));

        jTextFieldBERAT.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldBERAT.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextFieldBERAT.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jTextFieldBERAT, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 160, 30));

        jComboBoxNAMANASABAH1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxNAMANASABAH1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxNAMANASABAH1.setForeground(new java.awt.Color(0, 0, 0));
        jComboBoxNAMANASABAH1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        getContentPane().add(jComboBoxNAMANASABAH1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 330, 30));

        jComboBoxPETUGAS1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxPETUGAS1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxPETUGAS1.setForeground(new java.awt.Color(0, 0, 0));
        jComboBoxPETUGAS1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        getContentPane().add(jComboBoxPETUGAS1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 330, 30));

        jToggleHAPUS.setBackground(new java.awt.Color(204, 0, 0));
        jToggleHAPUS.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jToggleHAPUS.setForeground(new java.awt.Color(255, 255, 255));
        jToggleHAPUS.setText("HAPUS");
        jToggleHAPUS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleHAPUSActionPerformed(evt);
            }
        });
        getContentPane().add(jToggleHAPUS, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 400, 100, 30));

        jToggleUPDATE.setBackground(new java.awt.Color(0, 51, 0));
        jToggleUPDATE.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jToggleUPDATE.setForeground(new java.awt.Color(255, 255, 255));
        jToggleUPDATE.setText("PERBARUI");
        jToggleUPDATE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleUPDATEActionPerformed(evt);
            }
        });
        getContentPane().add(jToggleUPDATE, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 400, 110, 30));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Total Harga Setoran");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, 20));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Nama Petugas");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, 20));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Nama Nasabah");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, 20));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Status");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, 20));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Berat (Kg)");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, -1, 20));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Jenis Sampah");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, 20));

        jButtonCETAK.setBackground(new java.awt.Color(255, 204, 0));
        jButtonCETAK.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButtonCETAK.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCETAK.setText("LAPORAN");
        jButtonCETAK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCETAKActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCETAK, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 400, 100, 30));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("ID Setoran");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, 20));

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

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Tanggal Setoran");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, 20));

        jTextFieldTANGGAL.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldTANGGAL.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextFieldTANGGAL.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jTextFieldTANGGAL, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 330, 30));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/foto/Group 151 (1).png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, -1));

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
            // 1. Validasi input
            if (jComboBoxNAMANASABAH1.getSelectedItem() == null
                    || jComboBoxJENISSAMPAH.getSelectedItem() == null
                    || jComboBoxPETUGAS1.getSelectedItem() == null
                    || jComboBoxSTATUS.getSelectedItem() == null
                    || jTextFieldBERAT.getText().trim().isEmpty()
                    || jTextFieldTOTALHARGA1.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Semua kolom harus diisi!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            em.getTransaction().begin();

            // Ambil data dari form
            Date tanggal = sdf.parse(jTextFieldTANGGAL.getText().trim());
            int berat = Integer.parseInt(jTextFieldBERAT.getText().trim());
            BigInteger totalHarga = new BigInteger(jTextFieldTOTALHARGA1.getText().trim());
            String status = jComboBoxSTATUS.getSelectedItem().toString();

            Nasabah nasabah = (Nasabah) jComboBoxNAMANASABAH1.getSelectedItem();
            JenisSampah jenis = (JenisSampah) jComboBoxJENISSAMPAH.getSelectedItem();
            Petugas petugas = (Petugas) jComboBoxPETUGAS1.getSelectedItem();

            // ====================================================================
            // [BARU] LOGIKA KURANGI SALDO KAS BANK SAMPAH (MEMBAYAR NASABAH)
            // ====================================================================
            if ("Sudah Disetorkan".equalsIgnoreCase(status)) {

                // Ambil Data Dompet Bank (ID = 1)
                DompetBankSampah_1 dompetBank = em.find(DompetBankSampah_1.class, 1);

                if (dompetBank == null) {
                    JOptionPane.showMessageDialog(this,
                            "Error: Data Dompet Bank Sampah (ID 1) belum ada di database!");
                    em.getTransaction().rollback();
                    return;
                }

                // Cek Saldo Cukup atau Tidak
                // Konversi long (Entity) ke BigInteger untuk perbandingan
                BigInteger saldoBankSekarang = BigInteger.valueOf(dompetBank.getTotalKasBank());

                if (saldoBankSekarang.compareTo(totalHarga) < 0) {
                    JOptionPane.showMessageDialog(this,
                            "Transaksi Gagal! Saldo Kas Bank Sampah tidak cukup.\n"
                            + "Sisa Kas: Rp " + saldoBankSekarang + "\n"
                            + "Butuh: Rp " + totalHarga);
                    em.getTransaction().rollback();
                    return;
                }

                // Kurangi Saldo Bank
                // Konversi balik BigInteger ke long untuk simpan ke Entity
                long sisaSaldoLong = saldoBankSekarang.subtract(totalHarga).longValue();

                dompetBank.setTotalKasBank(sisaSaldoLong);
                dompetBank.setTerakhirDiupdate(new Date());
                em.merge(dompetBank);
            }
            // ====================================================================

            // SIMPAN SETORAN
            Setoran setoran = new Setoran();
            setoran.setTanggalSetoran(normalizeDate(tanggal));
            setoran.setBeratKgSetoran(berat);
            setoran.setTotalHargaSetoran(totalHarga);
            setoran.setStatusSetoran(status);
            setoran.setIdNasabah(nasabah);
            setoran.setIdJenissampah(jenis);
            setoran.setIdPetugas(petugas);

            em.persist(setoran);

            // UPDATE SALDO NASABAH (MENAMBAH)
            if ("Sudah Disetorkan".equalsIgnoreCase(status)) {
                Saldo saldo = nasabah.getSaldo();
                if (saldo == null) {
                    saldo = new Saldo();
                    saldo.setIdNasabah(nasabah);
                    saldo.setTotalSaldo(BigInteger.ZERO);
                    em.persist(saldo);
                }

                // Refresh agar data saldo nasabah sinkron
                em.refresh(saldo);

                BigInteger saldoBaru = saldo.getTotalSaldo().add(totalHarga);
                saldo.setTotalSaldo(saldoBaru);
                em.merge(saldo);
            }

            // ===== UPDATE / INSERT STOK GUDANG =====
            StokGudang stokGudang = em.createQuery(
                    "SELECT s FROM StokGudang s WHERE s.idJenissampah = :jenis", StokGudang.class)
                    .setParameter("jenis", jenis)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (stokGudang == null) {
                stokGudang = new StokGudang();
                stokGudang.setIdJenissampah(jenis);
                stokGudang.setJumlah(1);
                stokGudang.setBeratMasuk(berat);
                stokGudang.setBeratTersedia(berat);
                stokGudang.setIdSetoran(setoran);
                stokGudang.setTanggalMasuk(new Date());
                stokGudang.setStatusStok("Tersedia");
                em.persist(stokGudang);
            } else {
                int newJumlah = stokGudang.getJumlah() + 1;
                int newBeratMasuk = stokGudang.getBeratMasuk() + berat;
                int newBeratTersedia = stokGudang.getBeratTersedia() + berat;

                stokGudang.setJumlah(newJumlah);
                stokGudang.setBeratMasuk(newBeratMasuk);
                stokGudang.setBeratTersedia(newBeratTersedia);
                stokGudang.setIdSetoran(setoran);
                em.merge(stokGudang);
            }

            em.getTransaction().commit();

            JOptionPane.showMessageDialog(this,
                    "Data berhasil disimpan!\nKas Bank Berkurang & Saldo Nasabah Bertambah.",
                    "Sukses", JOptionPane.INFORMATION_MESSAGE);

            tampilSetoran();
            bersih();
            setAutoID();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Terjadi kesalahan: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jToggleSIMPANActionPerformed

    private void jToggleHAPUSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleHAPUSActionPerformed
        try {
            if (jTextFieldIDSETORAN.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus!");
                return;
            }

            int id = Integer.parseInt(jTextFieldIDSETORAN.getText().trim());

            int konfirmasi = JOptionPane.showConfirmDialog(
                    this,
                    "Apakah yakin ingin menghapus data dengan ID: " + id + "?",
                    "Konfirmasi Hapus",
                    JOptionPane.YES_NO_OPTION
            );

            if (konfirmasi != JOptionPane.YES_OPTION) {
                return;
            }

            em.getTransaction().begin();
            Setoran set = em.find(Setoran.class, id);

            if (set != null) {

                // Kurangi stok gudang sesuai berat setoran
                StokGudang stok = (StokGudang) em.createQuery(
                        "SELECT s FROM StokGudang s WHERE s.idJenissampah = :jenis AND s.idSetoran = :setoran")
                        .setParameter("jenis", set.getIdJenissampah())
                        .setParameter("setoran", set)
                        .getResultStream()
                        .findFirst()
                        .orElse(null);

                if (stok != null) {
                    int stokBaru = stok.getJumlah() - set.getBeratKgSetoran();

                    if (stokBaru < 0) {
                        stokBaru = 0; // tidak boleh minus
                    }
                    stok.setJumlah(stokBaru);
                    em.merge(stok);
                }

                // Kurangi saldo nasabah jika status sudah disetorkan
                if ("Sudah Disetorkan".equals(set.getStatusSetoran())) {

                    Saldo saldo = set.getIdNasabah().getSaldo();
                    if (saldo != null) {
                        saldo.setTotalSaldo(
                                saldo.getTotalSaldo().subtract(set.getTotalHargaSetoran())
                        );

                        if (saldo.getTotalSaldo().compareTo(BigInteger.ZERO) < 0) {
                            saldo.setTotalSaldo(BigInteger.ZERO);
                        }

                        em.merge(saldo);
                    }
                }

                // Hapus setoran
                em.remove(set);
                em.getTransaction().commit();

                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");

                tampilSetoran();
                bersih();

            } else {
                em.getTransaction().rollback();
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan!");
            }

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(this, "Gagal menghapus data: " + e.getMessage());
        }
    }//GEN-LAST:event_jToggleHAPUSActionPerformed

    private void jToggleUPDATEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleUPDATEActionPerformed
        try {
            String idText = jTextFieldIDSETORAN.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Pilih setoran yang akan diperbarui!");
                return;
            }

            int idSetoran = Integer.parseInt(idText);
            Setoran s = em.find(Setoran.class, idSetoran);

            if (s == null) {
                JOptionPane.showMessageDialog(this, "Data setoran tidak ditemukan!");
                return;
            }

            // Validasi
            if (jComboBoxNAMANASABAH1.getSelectedItem() == null
                    || jComboBoxJENISSAMPAH.getSelectedItem() == null
                    || jComboBoxPETUGAS1.getSelectedItem() == null
                    || jTextFieldBERAT.getText().trim().isEmpty()
                    || jTextFieldTOTALHARGA1.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this, "Semua data harus diisi!");
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date tanggalBaru = sdf.parse(jTextFieldTANGGAL.getText().trim());
            int beratBaru = Integer.parseInt(jTextFieldBERAT.getText().trim());
            BigInteger totalHargaBaru = new BigInteger(jTextFieldTOTALHARGA1.getText().trim());
            String statusBaru = jComboBoxSTATUS.getSelectedItem().toString();

            Nasabah nasabahBaru = (Nasabah) jComboBoxNAMANASABAH1.getSelectedItem();
            JenisSampah jenisBaru = (JenisSampah) jComboBoxJENISSAMPAH.getSelectedItem();
            Petugas petugasBaru = (Petugas) jComboBoxPETUGAS1.getSelectedItem();

            // BERAT & SALDO SEBELUM UPDATE
            int beratLama = s.getBeratKgSetoran();
            BigInteger totalHargaLama = s.getTotalHargaSetoran();

            Nasabah nasabahLama = s.getIdNasabah();

            em.getTransaction().begin();

            // ==============================================
            // 1. KOREKSI SALDO (Kurangi saldo lama)
            // ==============================================
            if ("Sudah Disetorkan".equals(s.getStatusSetoran())) {

                Saldo saldoLamaObj = nasabahLama.getSaldo();
                if (saldoLamaObj != null) {
                    saldoLamaObj.setTotalSaldo(
                            saldoLamaObj.getTotalSaldo().subtract(totalHargaLama)
                    );

                    if (saldoLamaObj.getTotalSaldo().compareTo(BigInteger.ZERO) < 0) {
                        saldoLamaObj.setTotalSaldo(BigInteger.ZERO);
                    }

                    em.merge(saldoLamaObj);
                }
            }

            // ==============================================
            // 2. TAMBAH SALDO BARU
            // ==============================================
            if ("Sudah Disetorkan".equals(statusBaru)) {

                Saldo saldoBaruObj = nasabahBaru.getSaldo();
                if (saldoBaruObj == null) {
                    saldoBaruObj = new Saldo();
                    saldoBaruObj.setIdNasabah(nasabahBaru);
                    saldoBaruObj.setTotalSaldo(totalHargaBaru);

                    em.persist(saldoBaruObj);
                } else {
                    BigInteger salNow = saldoBaruObj.getTotalSaldo() != null
                            ? saldoBaruObj.getTotalSaldo()
                            : BigInteger.ZERO;

                    saldoBaruObj.setTotalSaldo(salNow.add(totalHargaBaru));

                    em.merge(saldoBaruObj);
                }
            }

            // ==============================================
            // 3. UPDATE STOK GUDANG
            // ==============================================
            List<StokGudang> stokList = em.createQuery(
                    "SELECT sg FROM StokGudang sg WHERE sg.idSetoran.idSetoran = :id",
                    StokGudang.class
            ).setParameter("id", idSetoran)
                    .getResultList();

            for (StokGudang sg : stokList) {

                // Kurangi stok lama
                int stokLamaBaru = sg.getJumlah() - beratLama;
                if (stokLamaBaru < 0) {
                    stokLamaBaru = 0;
                }

                // Tambah stok baru
                int stokAkhir = stokLamaBaru + beratBaru;

                sg.setJumlah(stokAkhir);
                sg.setIdJenissampah(jenisBaru); // jika jenis berubah
                sg.setIdSetoran(s);

                em.merge(sg);
            }

            // ==============================================
            // 4. UPDATE OBJEK SETORAN
            // ==============================================
            s.setTanggalSetoran(tanggalBaru);
            s.setBeratKgSetoran(beratBaru);
            s.setTotalHargaSetoran(totalHargaBaru);
            s.setStatusSetoran(statusBaru);
            s.setIdNasabah(nasabahBaru);
            s.setIdJenissampah(jenisBaru);
            s.setIdPetugas(petugasBaru);

            em.merge(s);

            em.getTransaction().commit();

            JOptionPane.showMessageDialog(this, "Setoran berhasil diperbarui!");
            tampilSetoran();
            bersih();

        } catch (Exception e) {
            try {
                em.getTransaction().rollback();
            } catch (Exception ex) {
            }
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Gagal memperbarui setoran!\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    }//GEN-LAST:event_jToggleUPDATEActionPerformed

    private void jButtonCETAKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCETAKActionPerformed
        try {
            String path = "src/Login/REPORTSETORAN.jasper";
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
//            java.util.logging.Logger.getLogger(inputsetoranNASABAH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(inputsetoranNASABAH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(inputsetoranNASABAH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(inputsetoranNASABAH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new inputsetoranNASABAH().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableSETORAN;
    private javax.swing.JButton jButtonCETAK;
    private javax.swing.JComboBox jComboBoxJENISSAMPAH;
    private javax.swing.JComboBox jComboBoxNAMANASABAH1;
    private javax.swing.JComboBox jComboBoxPETUGAS1;
    private javax.swing.JComboBox jComboBoxSTATUS;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldBERAT;
    private javax.swing.JTextField jTextFieldIDSETORAN;
    private javax.swing.JTextField jTextFieldTANGGAL;
    private javax.swing.JTextField jTextFieldTOTALHARGA1;
    private javax.swing.JToggleButton jToggleBATAL;
    private javax.swing.JToggleButton jToggleHAPUS;
    private javax.swing.JToggleButton jToggleSIMPAN;
    private javax.swing.JToggleButton jToggleUPDATE;
    // End of variables declaration//GEN-END:variables
}
