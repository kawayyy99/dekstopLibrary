/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import koneksi.KoneksiDB;
import view.FrmBuku;

/**
 *
 *@author Kawayyy
 */
public class BukuModel {

 private KoneksiDB koneksi;
    private ResultSet rsBuku, rsNob;
    private List<BukuModel> listBuku;
    private String query;
    private boolean status;


    private String no_buku;
    private String judul;
    private String pengarang;
    private String penerbit;
    private String kategori;
    private String tahun;
    private String rak;
    private String jumlah;
    
    
    public BukuModel(){
        koneksi = new KoneksiDB();
        koneksi.getkoneksi();
    }

        public String getNo_buku() {
        return no_buku;
    }

    public void setNo_buku(String no_buku) {
        this.no_buku = no_buku;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
    
    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }
    
        public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }
    
        public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }
    
       public String getRak() {
        return rak;
    }

    public void setRak(String rak) {
        this.rak = rak;
    }
    
        public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }
        
        public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
    
     public boolean insertBuku(){
        query = "INSERT INTO tbl_buku VALUES ('" + no_buku + "','" + judul + "','" + pengarang + "','" 
                + penerbit + "','" + kategori + "','" + tahun + "','" + rak + "','" + jumlah + "')";
        status = koneksi.eksekusiQuery(query, false);
        return status;
    }
    
    public boolean UpdateBuku() {
            query = "Update tbl_buku SET judul = '"+judul+"',"
                    + " pengarang = '"+pengarang+"', penerbit = '"+penerbit+"', kategori = '"+kategori+"', tahun_terbit = '"+tahun+"', rak = '"+rak+"',jumlah = '"+jumlah+"'"
                    + " WHERE no_buku = '"+no_buku+"'";
            status = koneksi.eksekusiQuery(query,false);
            return status;
        }
    
    public boolean deleteBuku(){
        query = "DELETE FROM tbl_buku WHERE no_buku = '" + no_buku + "'";
        status = koneksi.eksekusiQuery(query, false);
        return status;
    }
    
    public List selectBuku(){
        query = "SELECT * FROM tbl_buku";
        status = koneksi.eksekusiQuery(query, true);
        if(status){
            rsBuku = koneksi.getRs();
            listBuku = new ArrayList<BukuModel>();
            
            try{
                while(rsBuku.next()){
                    BukuModel model = new BukuModel();
                    model.setNo_buku(rsBuku.getString(1));
                    model.setJudul(rsBuku.getString(2));
                    model.setPengarang(rsBuku.getString(3));
                    model.setPenerbit(rsBuku.getString(4));
                    model.setKategori(rsBuku.getString(5));
                    model.setTahun(rsBuku.getString(6));
                    model.setRak(rsBuku.getString(7));
                    model.setJumlah(rsBuku.getString(8));
                    listBuku.add(model);
                }
                rsBuku.close();
                return listBuku;
            } catch (SQLException se){
                return null;
            }
        }
        return null;
    }
    
    public String noBaru() {
        int temp = 0;
        String kode = "";
        query = "SELECT MAX(RIGHT(no_buku,8)) FROM tbl_buku";
        status = koneksi.eksekusiQuery(query, true);
        if (status) {
            rsNob = koneksi.getRs();
            try {
                rsNob.next();
                temp = Integer.parseInt(rsNob.getString(1)) + 1;
                rsNob.close();
            } catch (SQLException ex) {
                Logger.getLogger(BukuModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (temp == 0) {
            no_buku = "1";
        } else if (temp < 10) {
            no_buku = "B1" + temp;
        } else {
            no_buku = "B" + temp;
        }
        
        return no_buku;
    }
}
