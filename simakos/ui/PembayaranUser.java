/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.simakos.ui;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;


/**
 *
 * @author elitebook
 */


public class PembayaranUser extends javax.swing.JFrame {
    private JFrame previousFrame; // simpan halaman sebelumnya
    

    private JTextField tfIdPembayaran, tfIdPenyewa, tfNama, tfNomorKamar, tfHargaSewa,
            tfMetode, tfJumlah, tfTanggal;
    private JButton btnBayar;
    /**
     * Creates new form PembayaranAdmin
     */
    
   
   public PembayaranUser() {
        setTitle("Form Pembayaran");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lbl;

        lbl = new JLabel("ID Pembayaran:");
        lbl.setBounds(20, 20, 150, 25);
        add(lbl);
        tfIdPembayaran = new JTextField();
        tfIdPembayaran.setBounds(180, 20, 150, 25);
        add(tfIdPembayaran);

        lbl = new JLabel("ID Penyewa:");
        lbl.setBounds(20, 60, 150, 25);
        add(lbl);
        tfIdPenyewa = new JTextField();
        tfIdPenyewa.setBounds(180, 60, 150, 25);
        add(tfIdPenyewa);

        lbl = new JLabel("Nama Lengkap:");
        lbl.setBounds(20, 100, 150, 25);
        add(lbl);
        tfNama = new JTextField();
        tfNama.setBounds(180, 100, 150, 25);
        add(tfNama);

        lbl = new JLabel("Nomor Kamar:");
        lbl.setBounds(20, 140, 150, 25);
        add(lbl);
        tfNomorKamar = new JTextField();
        tfNomorKamar.setBounds(180, 140, 150, 25);
        add(tfNomorKamar);

        lbl = new JLabel("Harga Sewa:");
        lbl.setBounds(20, 180, 150, 25);
        add(lbl);
        tfHargaSewa = new JTextField();
        tfHargaSewa.setBounds(180, 180, 150, 25);
        add(tfHargaSewa);

        lbl = new JLabel("Metode Pembayaran:");
        lbl.setBounds(20, 220, 150, 25);
        add(lbl);
        tfMetode = new JTextField();
        tfMetode.setBounds(180, 220, 150, 25);
        add(tfMetode);

        lbl = new JLabel("Jumlah Dibayarkan:");
        lbl.setBounds(20, 260, 150, 25);
        add(lbl);
        tfJumlah = new JTextField();
        tfJumlah.setBounds(180, 260, 150, 25);
        add(tfJumlah);

        lbl = new JLabel("Tanggal (YYYY-MM-DD):");
        lbl.setBounds(20, 300, 150, 25);
        add(lbl);
        tfTanggal = new JTextField();
        tfTanggal.setBounds(180, 300, 150, 25);
        add(tfTanggal);

        btnBayar = new JButton("Bayar");
        btnBayar.setBounds(130, 340, 100, 30);
        add(btnBayar);

        btnBayar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simpanPembayaran();
            }
        });
    }
    
private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/simakos";
        String user = "root";
        String pass = "";
        return DriverManager.getConnection(url, user, pass);
    }

    private void simpanPembayaran() {
        try {
            int idPembayaran = Integer.parseInt(tfIdPembayaran.getText().trim());
            int idPenyewa = Integer.parseInt(tfIdPenyewa.getText().trim());
            String nama = tfNama.getText().trim();
            String nomorKamar = tfNomorKamar.getText().trim();
            int hargaSewa = Integer.parseInt(tfHargaSewa.getText().trim());
            String metode = tfMetode.getText().trim();
            int jumlah = Integer.parseInt(tfJumlah.getText().trim());
            String tanggalStr = tfTanggal.getText().trim();

            String sql = "INSERT INTO pembayaran "
                    + "(id_pembayaran, id_penyewa, nama_lengkap, nomor_kamar, harga_sewa, metode_pembayaran, jumlah_dibayarkan, tanggal) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setInt(1, idPembayaran);
                pst.setInt(2, idPenyewa);
                pst.setString(3, nama);
                pst.setString(4, nomorKamar);
                pst.setInt(5, hargaSewa);
                pst.setString(6, metode);
                pst.setInt(7, jumlah);
                pst.setDate(8, java.sql.Date.valueOf(tanggalStr));

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "Pembayaran berhasil disimpan!");
                    clearForm();
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID, Harga, Jumlah harus angka!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Format tanggal harus YYYY-MM-DD!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void refreshForm() {
    // kosongkan semua field
    jTextField1.setText("");
    jTextField2.setText("");
    jTextField4.setText("");
    jTextField5.setText("");
    jTextField6.setText("");
    jTextField7.setText("");
    jTextField8.setText("");

    // reload data pembayaran terbaru
   
}

    
private void clearForm() {
        tfIdPembayaran.setText("");
        tfIdPenyewa.setText("");
        tfNama.setText("");
        tfNomorKamar.setText("");
        tfHargaSewa.setText("");
        tfMetode.setText("");
        tfJumlah.setText("");
        tfTanggal.setText("");
    }
    
private void insertPembayaran() {
    String sql = "INSERT INTO pembayaran(id_penyewa, nama_lengkap, nomor_kamar, harga_sewa, metode_pembayaran, jumlah_dibayarkan, tanggal) VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = getConnection();
         PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        int idPenyewa = Integer.parseInt(jTextField1.getText());
        String nama = jTextField2.getText();
        String nomorKamar = jTextField4.getText();
        int hargaSewa = Integer.parseInt(jTextField5.getText());
        String metode = jTextField6.getText();
        int jumlahBayar = Integer.parseInt(jTextField7.getText());
        String tanggal = jTextField8.getText(); // format: yyyy-MM-dd

        pst.setInt(1, idPenyewa);
        pst.setString(2, nama);
        pst.setString(3, nomorKamar);
        pst.setInt(4, hargaSewa);
        pst.setString(5, metode);
        pst.setInt(6, jumlahBayar);
        pst.setString(7, tanggal);

        pst.executeUpdate();

        // Ambil ID yang baru di-generate
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            int idPembayaranBaru = rs.getInt(1);
            JOptionPane.showMessageDialog(this, "Pembayaran berhasil! ID: " + idPembayaranBaru);
        }

        // Reset semua field agar kembali ke halaman kosong
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");

        // Optional: fokus ke field pertama
        jTextField1.requestFocus();

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Format angka salah!");
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

        jTextField3 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        panelKamar = new javax.swing.JPanel();
        labelKamar = new javax.swing.JLabel();
        panelPembayaran = new javax.swing.JPanel();
        labelPembayaran = new javax.swing.JLabel();
        panelAkun = new javax.swing.JPanel();
        labelKomentar = new javax.swing.JLabel();
        panelKomentar = new javax.swing.JPanel();
        labelAkun = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel16.setText("1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 102, 0));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("SIMAKOS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel10)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Logout");
        jLabel15.setToolTipText("");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Dashboard");
        jLabel34.setToolTipText("");
        jLabel34.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel34MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panelKamar.setBackground(new java.awt.Color(255, 255, 255));
        panelKamar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelKamarMouseClicked(evt);
            }
        });

        labelKamar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelKamar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelKamar.setText("Kamar");
        labelKamar.setToolTipText("");
        labelKamar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelKamar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelKamarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelKamarLayout = new javax.swing.GroupLayout(panelKamar);
        panelKamar.setLayout(panelKamarLayout);
        panelKamarLayout.setHorizontalGroup(
            panelKamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelKamar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelKamarLayout.setVerticalGroup(
            panelKamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panelPembayaran.setBackground(new java.awt.Color(255, 102, 0));
        panelPembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelPembayaranMouseClicked(evt);
            }
        });

        labelPembayaran.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelPembayaran.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelPembayaran.setText("Pembayaran");
        labelPembayaran.setToolTipText("");
        labelPembayaran.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelPembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelPembayaranMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelPembayaranLayout = new javax.swing.GroupLayout(panelPembayaran);
        panelPembayaran.setLayout(panelPembayaranLayout);
        panelPembayaranLayout.setHorizontalGroup(
            panelPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPembayaran, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelPembayaranLayout.setVerticalGroup(
            panelPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panelAkun.setBackground(new java.awt.Color(255, 255, 255));

        labelKomentar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelKomentar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelKomentar.setText("Komentar");
        labelKomentar.setToolTipText("");
        labelKomentar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelKomentar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelKomentarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelAkunLayout = new javax.swing.GroupLayout(panelAkun);
        panelAkun.setLayout(panelAkunLayout);
        panelAkunLayout.setHorizontalGroup(
            panelAkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelKomentar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelAkunLayout.setVerticalGroup(
            panelAkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAkunLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelKomentar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelKomentar.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelKomentarLayout = new javax.swing.GroupLayout(panelKomentar);
        panelKomentar.setLayout(panelKomentarLayout);
        panelKomentarLayout.setHorizontalGroup(
            panelKomentarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );
        panelKomentarLayout.setVerticalGroup(
            panelKomentarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        labelAkun.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelAkun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAkun.setText("Akun");
        labelAkun.setToolTipText("");
        labelAkun.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelAkun.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelAkunMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelKamar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelPembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelAkun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelKomentar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelAkun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAkun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelKomentar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Bayar kamar bulan ini");

        jPanel3.setBackground(new java.awt.Color(255, 204, 51));

        jLabel8.setText("Id Penyewa        : ");

        jLabel9.setText("Nama Lengkap  :");

        jLabel12.setText("Nomor Kamar   :");

        jLabel13.setText("Harga Sewa      :");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(51, 51, 51)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jLabel14.setText("Metode Pembayaran  :");

        jLabel17.setText("Jumlah Dibayarkan     :");

        jLabel18.setText("Tanggal                      :");

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 153, 51));
        jButton1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jButton1.setText("Bayar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnKembali.setBackground(new java.awt.Color(255, 153, 51));
        btnKembali.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnKembali.setText("Kembali");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
                        .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(171, 171, 171)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(170, 170, 170))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked
        // TODO add your handling code here:
        com.simakos.ui.DashboardUser    dbAdmin = new com.simakos.ui.DashboardUser();
        dbAdmin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel34MouseClicked

    private void labelKamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelKamarMouseClicked
        // TODO add your handling code here:
        com.simakos.ui.KamarUser kamar = new com.simakos.ui.KamarUser();
        kamar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_labelKamarMouseClicked

    private void panelKamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelKamarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_panelKamarMouseClicked

    private void labelPembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPembayaranMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_labelPembayaranMouseClicked

    private void panelPembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPembayaranMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_panelPembayaranMouseClicked

    private void labelKomentarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelKomentarMouseClicked
        // TODO add your handling code here:
        com.simakos.ui.KomentarUser komentar = new com.simakos.ui.KomentarUser ();
        komentar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_labelKomentarMouseClicked

    private void labelAkunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelAkunMouseClicked
        // TODO add your handling code here:
        int loggedInId = 1; // ambil dari login user
com.simakos.ui.AkunUser akun = new com.simakos.ui.AkunUser(loggedInId);
akun.setVisible(true);
    }//GEN-LAST:event_labelAkunMouseClicked

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            int idPenyewa = Integer.parseInt(jTextField1.getText());
            String nama = jTextField2.getText();
            String nomorKamar = jTextField4.getText();
            int hargaSewa = Integer.parseInt(jTextField5.getText());
            String metode = jTextField6.getText();
            int jumlah = Integer.parseInt(jTextField7.getText());
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date tgl = sdf.parse(jTextField8.getText());
            java.sql.Date sqlDate = new java.sql.Date(tgl.getTime());

            String sql = "INSERT INTO pembayaran(id_penyewa, nama_lengkap, nomor_kamar, harga_sewa, metode_pembayaran, jumlah_dibayarkan, tanggal) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setInt(1, idPenyewa);
                pst.setString(2, nama);
                pst.setString(3, nomorKamar);
                pst.setInt(4, hargaSewa);
                pst.setString(5, metode);
                pst.setInt(6, jumlah);
                pst.setDate(7, sqlDate);

                pst.executeUpdate();
            }

            JOptionPane.showMessageDialog(this, "Pembayaran berhasil!");

            // **kembali ke halaman sebelumnya**
            if (previousFrame != null) {
                previousFrame.setVisible(true); // tampilkan halaman sebelumnya
            }
            this.dispose(); // tutup halaman pembayaran

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    

    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        // TODO add your handling code here:
        if (previousFrame != null) {
    previousFrame.setVisible(true);
}
dispose();

    }//GEN-LAST:event_btnKembaliActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])throws SQLException  {
               java.awt.EventQueue.invokeLater(() -> new PembayaranUser().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel labelAkun;
    private javax.swing.JLabel labelKamar;
    private javax.swing.JLabel labelKomentar;
    private javax.swing.JLabel labelPembayaran;
    private javax.swing.JPanel panelAkun;
    private javax.swing.JPanel panelKamar;
    private javax.swing.JPanel panelKomentar;
    private javax.swing.JPanel panelPembayaran;
    // End of variables declaration//GEN-END:variables
}
