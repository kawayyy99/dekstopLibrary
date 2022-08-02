/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.BukuController;
import control.PeminjamanController;
import control.SiswaController;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import koneksi.KoneksiDB;
import model.BukuModel;
import model.PeminjamanModel;
import model.SiswaModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Kawayyy
 */
public class FrmPeminjaman extends javax.swing.JFrame {
    private PeminjamanController control;
    private SiswaController controlSiswa;
    private BukuController controlBuku;
    private List<SiswaModel> listSiswa;
    private List<BukuModel> listBuku;
    private List<PeminjamanModel> listPeminjaman;
    private boolean status;
    private String proses;
    private String[] tabelHeader;
    private DefaultTableModel dtm;
    private int row;
    
    private String tanggalPinjamm = " ";
        String tanggalKembalii = " ";
        String statusPinjamm = " ";
        int denda = 5000;
        int telat;
        int Totall;
        
    
    public String noBuku;
    public String judulBuku;
    public String nisn;
    public String namaSiswa;

    public String getNoBuku() {
        return noBuku;
    }

    public void setNoBuku(String noBuku) {
        this.noBuku = noBuku;
    }

    public String getJudulBuku() {
        return judulBuku;
    }

    public void setJudulBuku(String judulBuku) {
        this.judulBuku = judulBuku;
    }

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getNamaSiswa() {
        return namaSiswa;
    }

    public void setNamaSiswa(String namaSiswa) {
        this.namaSiswa = namaSiswa;
    }
    
    public void siswaTerpilih(){
        FrmSiswa form = new FrmSiswa();
        form.frmPinjam = this;
        txtNisn.setText(nisn + " - " + namaSiswa);
    }
    
    public void bukuTerpilih(){
        FrmBuku form = new FrmBuku();
        form.frmBuku = this;
        txtNoBuku.setText(noBuku + " - " + judulBuku);
    }

    /**
     * Creates new form FrmPeminjamann
     */
    public FrmPeminjaman() {
        initComponents();
        control = new PeminjamanController(this);
        
        proses = "";
        tabelHeader = new String[]{"Nomor Peminjaman", "Tanggal Pinjaman", "NISN", "Nomor Buku", "Status", "Tanggal Kembalikan", "Denda"};
        dtm = new DefaultTableModel(null, tabelHeader);
        tblPinjam.setModel(dtm);
        tblPinjam.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            
            public void valueChanged(ListSelectionEvent e){
                row = tblPinjam.getSelectedRow();
                if(row >= 0){
                    txtNoPinjam.setText(dtm.getValueAt(row, 0).toString());
                    dtcPinjam.setDateFormatString(dtm.getValueAt(row, 1).toString());
                    txtNisn.setText(dtm.getValueAt(row, 2).toString());
                    txtNoBuku.setText(dtm.getValueAt(row, 3).toString());
                    rdPinjam.isSelected();
                    dtcKembali.setDateFormatString(dtm.getValueAt(row, 5).toString());
                    txtTotal.setText(dtm.getValueAt(row, 6).toString());
                }
            }
        });
        refreshTable();
    }
    
    private void refreshTable(){
        if(proses.equalsIgnoreCase("")){
        listPeminjaman = control.selectPeminjaman("", "");
        
        dtm = (DefaultTableModel)tblPinjam.getModel();
        dtm.setRowCount(0);
        for (PeminjamanModel data : listPeminjaman){
           dtm.addRow(new Object[]{
           data.getNoPeminjaman(),
           data.getTglPinjam(),
           data.getNisn(),
           data.getNoBuku(),
           data.getStatusPinjam(),
           data.getTglKembali(),
           data.getDenda()});
        }
        
        if(tblPinjam.getRowCount() > 0){
            row = tblPinjam.getRowCount() - 1;
            tblPinjam.setRowSelectionInterval(row, row);
            aturButton(true);
            aturText(false);
            proses = "";
        }
    }
    }
   
    
    public void aturText(boolean status){
        txtNoPinjam.setEnabled(!status);
        txtNisn.setEnabled(!status);
        txtNoBuku.setEnabled(!status);       
        dtcPinjam.setEnabled(!status);
        dtcKembali.setEnabled(!status);
        txtDenda.setEnabled(!status);
        rdPinjam.setEnabled(!status);
        rdKembali.setEnabled(!status);
        txtCari.setEnabled(status);
        txtTotal.setEnabled(status);
    }
    
    public void aturButton(boolean status){
        btnTambah.setEnabled(status);
        btnEdit.setEnabled(status);
        btnSimpan.setEnabled(!status);
        btnHapus.setEnabled(status);
        btnPrint.setEnabled(status);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btcarianggota = new javax.swing.JButton();
        btcaribuku = new javax.swing.JButton();
        txtNoPinjam = new javax.swing.JTextField();
        txtNisn = new javax.swing.JTextField();
        txtNoBuku = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        rdPinjam = new javax.swing.JRadioButton();
        rdKembali = new javax.swing.JRadioButton();
        btnKeluar = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtDenda = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPinjam = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 153));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel2.setToolTipText("");
        jPanel2.setLayout(null);

        jPanel3.setBackground(new java.awt.Color(255, 211, 173));

        jLabel1.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        jLabel1.setText("NO PEMINJAMAN");

        jLabel2.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        jLabel2.setText("NISN");

        jLabel3.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        jLabel3.setText("NO BUKU");

        jLabel8.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        jLabel8.setText("TANGGAL PINJAM");

        jLabel9.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        jLabel9.setText("TANGGAL KEMBALI");

        btcarianggota.setBackground(new java.awt.Color(0, 0, 0));
        btcarianggota.setFont(new java.awt.Font("Exotc350 DmBd BT", 0, 14)); // NOI18N
        btcarianggota.setForeground(new java.awt.Color(255, 255, 255));
        btcarianggota.setText("SISWA");
        btcarianggota.setPreferredSize(new java.awt.Dimension(87, 40));
        btcarianggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcarianggotaActionPerformed(evt);
            }
        });

        btcaribuku.setBackground(new java.awt.Color(0, 0, 0));
        btcaribuku.setFont(new java.awt.Font("Exotc350 DmBd BT", 0, 14)); // NOI18N
        btcaribuku.setForeground(new java.awt.Color(255, 255, 255));
        btcaribuku.setText("BUKU");
        btcaribuku.setPreferredSize(new java.awt.Dimension(87, 40));
        btcaribuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcaribukuActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel14.setText("STATUS");

        buttonGroup1.add(rdPinjam);
        rdPinjam.setForeground(new java.awt.Color(255, 255, 255));
        rdPinjam.setText("Meminjam");

        buttonGroup1.add(rdKembali);
        rdKembali.setForeground(new java.awt.Color(255, 255, 255));
        rdKembali.setText("Dikembalikan");

        btnKeluar.setBackground(new java.awt.Color(0, 0, 0));
        btnKeluar.setFont(new java.awt.Font("Exotc350 DmBd BT", 0, 14)); // NOI18N
        btnKeluar.setForeground(new java.awt.Color(255, 255, 255));
        btnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image 3/icon (47).png"))); // NOI18N
        btnKeluar.setText("CANCEL");
        btnKeluar.setPreferredSize(new java.awt.Dimension(87, 40));
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        btnTambah.setBackground(new java.awt.Color(0, 0, 0));
        btnTambah.setFont(new java.awt.Font("Exotc350 DmBd BT", 0, 14)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image 3/icon (163).png"))); // NOI18N
        btnTambah.setText("TAMBAH");
        btnTambah.setPreferredSize(new java.awt.Dimension(87, 40));
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnSimpan.setBackground(new java.awt.Color(0, 0, 0));
        btnSimpan.setFont(new java.awt.Font("Exotc350 DmBd BT", 0, 14)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image 3/icon (3).jpg"))); // NOI18N
        btnSimpan.setText("SIMPAN");
        btnSimpan.setPreferredSize(new java.awt.Dimension(87, 40));
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(0, 0, 0));
        btnEdit.setFont(new java.awt.Font("Exotc350 DmBd BT", 0, 14)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image 3/icon (146).png"))); // NOI18N
        btnEdit.setText("EDIT");
        btnEdit.setPreferredSize(new java.awt.Dimension(87, 40));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnHapus.setBackground(new java.awt.Color(0, 0, 0));
        btnHapus.setFont(new java.awt.Font("Exotc350 DmBd BT", 0, 14)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image 3/icon (167).png"))); // NOI18N
        btnHapus.setText("HAPUS");
        btnHapus.setPreferredSize(new java.awt.Dimension(87, 40));
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnPrint.setBackground(new java.awt.Color(0, 0, 0));
        btnPrint.setFont(new java.awt.Font("Exotc350 DmBd BT", 0, 14)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image 3/icon (42).png"))); // NOI18N
        btnPrint.setText("PRINT");
        btnPrint.setPreferredSize(new java.awt.Dimension(87, 40));
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        txtCari.setFont(new java.awt.Font("Exotc350 DmBd BT", 0, 12)); // NOI18N
        txtCari.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCari.setText("INI KOLOM PENCARIAN / BUKAN KOLOM IKAN");
        txtCari.setPreferredSize(new java.awt.Dimension(87, 30));
        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCariKeyPressed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(0, 0, 0));
        btnBack.setFont(new java.awt.Font("Exotc350 DmBd BT", 0, 14)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image 3/prev.png"))); // NOI18N
        btnBack.setText("KEMBALI");
        btnBack.setPreferredSize(new java.awt.Dimension(87, 40));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Rockwell", 0, 13)); // NOI18N
        jLabel4.setText("KETERLAMBATAN");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNisn)
                                    .addComponent(txtNoBuku, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btcaribuku, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btcarianggota, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtNoPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4))
                        .addGap(28, 28, 28)
                        .addComponent(txtDenda, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdKembali)
                                    .addComponent(rdPinjam)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(187, 187, 187)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNoPinjam)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel14)
                        .addComponent(rdPinjam)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btcarianggota, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNisn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9))
                            .addComponent(jLabel2))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNoBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btcaribuku, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtDenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rdKembali)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txtCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel3);
        jPanel3.setBounds(11, 40, 1013, 247);

        jPanel4.setBackground(new java.awt.Color(255, 204, 153));
        jPanel4.setLayout(null);

        tblPinjam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        jScrollPane1.setViewportView(tblPinjam);

        jPanel4.add(jScrollPane1);
        jScrollPane1.setBounds(10, 0, 993, 118);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image 3/background 3.jpg"))); // NOI18N
        jLabel7.setText("jLabel7");
        jPanel4.add(jLabel7);
        jLabel7.setBounds(0, -250, 1030, 370);

        jPanel2.add(jPanel4);
        jPanel4.setBounds(11, 280, 1010, 120);

        jLabel13.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image 3/next.png"))); // NOI18N
        jLabel13.setText("Peminjaman & Pengembalian ");
        jPanel2.add(jLabel13);
        jLabel13.setBounds(11, 12, 289, 22);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image 3/background 3.jpg"))); // NOI18N
        jLabel5.setText("jLabel5");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(0, 0, 1040, 430);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image 3/background 3.jpg"))); // NOI18N
        jLabel6.setText("jLabel6");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1035, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 1070, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1055, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
       aturButton(false);
       aturText(false);
       txtNoPinjam.setText(null);
       txtNisn.setText(null);
       txtNoBuku.setText(null);
       txtDenda.setText(null);
       txtTotal.setText(null);
       btnKeluar.setText("Cancel");
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        telat = Integer.parseInt(txtDenda.getText());
        Totall = denda * telat;
        txtTotal.setText(String.format(" ", Totall));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        tanggalPinjamm = format.format(dtcPinjam.getDate());
        tanggalKembalii = format.format(dtcKembali.getDate());
        if(rdPinjam.isSelected()==true){
            statusPinjamm = "Meminjam";
        }else{
            statusPinjamm = "Dikembalikan";
        }
        control.inputPeminjaman(txtNoPinjam.getText(), tanggalPinjamm, 
                txtNisn.getText(), txtNoBuku.getText(), statusPinjamm, 
                tanggalKembalii, Totall);
        
        status = control.insertPeminjaman();
        if(status == false){
            JOptionPane.showMessageDialog(null, "Penyimpanan Gagal");
        }
        aturText(true);
        aturButton(true);
        refreshTable();
        btnKeluar.setText("Keluar");
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        telat = Integer.parseInt(txtDenda.getText());
        Totall = denda * telat;
        txtTotal.setText(String.format(" ", Totall));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        tanggalPinjamm = format.format(dtcPinjam.getDate());
        tanggalKembalii = format.format(dtcKembali.getDate());
        if(rdPinjam.isSelected()==true){
            statusPinjamm = "Meminjam";
        }else{
            statusPinjamm = "Dikembalikan";
        }
        control.inputPeminjaman(txtNoPinjam.getText(), tanggalPinjamm, 
                txtNisn.getText(), txtNoBuku.getText(), statusPinjamm, 
                tanggalKembalii, Totall);
        
        boolean hasil = control.updatePeminjaman();
        if(hasil == false){
            JOptionPane.showMessageDialog(null, "Penyimpanan Gagal");
        }
        aturText(true);
        aturButton(true);
        refreshTable();
        btnKeluar.setText("Keluar");
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        control.inputPeminjaman(txtNoPinjam.getText(), tanggalPinjamm, 
                txtNisn.getText(), txtNoBuku.getText(), statusPinjamm, 
                tanggalKembalii, Totall);
        
        status = control.deletePeminjaman();
        if (status == false) {
            JOptionPane.showMessageDialog(null, "Maaf data tidak dapat dihapus karena digunakan "
                    + "sebagai data pekerjaan orang tua pendaftar");
        }
        refreshTable();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        // TODO add your handling code here:
        if (btnKeluar.getText().equals("Keluar")){
            dispose();
        }else {
            proses = "";
            aturText(true);
            aturButton(true);
            refreshTable();
            btnKeluar.setText("Keluar");
              }
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        try{
            KoneksiDB koneksi = new KoneksiDB();
           koneksi.getkoneksi();
           try{
               Map<String, Object> parameter = new HashMap<String, Object>();
               
               File rpt = new File("src/report/LaporanPeminjaman.jrxml");
               JasperDesign jasDesign = JRXmlLoader.load(rpt);
               parameter.clear();
               JasperReport jasReport = JasperCompileManager.compileReport(jasDesign);
               JasperPrint jasPrint = JasperFillManager.fillReport(jasReport, 
                       parameter, koneksi.getkoneksi());
               JasperViewer.viewReport(jasPrint, false);
           } catch (Exception e){
               JOptionPane.showMessageDialog(null, "Laporan tidak ditemukan" + e);
           }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariActionPerformed

    private void txtCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariKeyPressed

    private void btcaribukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcaribukuActionPerformed
        // TODO add your handling code here:
        FrmBuku form = new FrmBuku();
        form.frmBuku = this;
        form.setVisible(true);
    }//GEN-LAST:event_btcaribukuActionPerformed

    private void btcarianggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcarianggotaActionPerformed
        // TODO add your handling code here:
        FrmSiswa form = new FrmSiswa();
        form.frmPinjam = this;
        form.setVisible(true);
    }//GEN-LAST:event_btcarianggotaActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        FrmMain form = new FrmMain();
        form.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnBackActionPerformed

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
            java.util.logging.Logger.getLogger(FrmPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPeminjaman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btcarianggota;
    private javax.swing.JButton btcaribuku;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdKembali;
    private javax.swing.JRadioButton rdPinjam;
    private javax.swing.JTable tblPinjam;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtDenda;
    private javax.swing.JTextField txtNisn;
    private javax.swing.JTextField txtNoBuku;
    private javax.swing.JTextField txtNoPinjam;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
