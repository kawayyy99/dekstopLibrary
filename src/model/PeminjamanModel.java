/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import koneksi.KoneksiDB;

/**
 *
 * @author Kawayyy
 */
public class PeminjamanModel {
    private KoneksiDB koneksi;
    private ResultSet rsPeminjaman, rsNoPeminjamanBaru;
    private List<PeminjamanModel> listPeminjaman;
    private String query;
    private boolean status;
    
    private String noPeminjaman;
    private String tglPinjam;
    private String tglKembali;
    private String noBuku;
    private String nisn;
    private String statusPinjam;
    private int denda;

    public int getDenda() {
        return denda;
    }

    public void setDenda(int denda) {
        this.denda = denda;
    }

    

    public String getNoPeminjaman() {
        return noPeminjaman;
    }

    public void setNoPeminjaman(String noPeminjaman) {
        this.noPeminjaman = noPeminjaman;
    }

    public String getTglPinjam() {
        return tglPinjam;
    }

    public void setTglPinjam(String tglPinjam) {
        this.tglPinjam = tglPinjam;
    }

    public String getTglKembali() {
        return tglKembali;
    }

    public void setTglKembali(String tglKembali) {
        this.tglKembali = tglKembali;
    }

    public String getNoBuku() {
        return noBuku;
    }

    public void setNoBuku(String noBuku) {
        this.noBuku = noBuku;
    }

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getStatusPinjam() {
        return statusPinjam;
    }

    public void setStatusPinjam(String statusPinjam) {
        this.statusPinjam = statusPinjam;
    }
    
    public boolean insertPeminjaman(){
        query = "INSERT INTO tbl_peminjaman VALUES ('" + noPeminjaman + "','" + tglPinjam + "','" + nisn + "','" 
                + noBuku + "','" + statusPinjam + "','" + tglKembali + "','" + denda + "')";
        status = koneksi.eksekusiQuery(query, false);
        return status;
    }
    
    public boolean UpdatePeminjaman() {
            query = "Update tbl_peminjaman SET tgl_pinjam = '"+tglPinjam+"',"
                    + " nisn = '"+nisn+"', no_buku = '"+noBuku+"', status_pinjam = '"+statusPinjam+"', tgl_kembali = '"+tglKembali+"', denda = '"+denda+"'"
                    + " WHERE no_peminjaman = '"+noPeminjaman+"'";
            status = koneksi.eksekusiQuery(query,false);
            return status;
        }
    
    public boolean deletePeminjaman(){
        query = "DELETE FROM tbl_peminjaman WHERE no_peminjaman = '" + noPeminjaman + "'";
        status = koneksi.eksekusiQuery(query, false);
        return status;
    }
    
    public List selectPeminjaman(String noPinjam, String namaSiswa){
        query = "SELECT * FROM tbl_peminjaman";
        status = koneksi.eksekusiQuery(query, true);
        if(status){
            rsPeminjaman = koneksi.getRs();
            listPeminjaman = new ArrayList<PeminjamanModel>();
            
            try{
                while(rsPeminjaman.next()){
                    PeminjamanModel model = new PeminjamanModel();
                    model.setNoPeminjaman(rsPeminjaman.getString(1));
                    model.setTglPinjam(rsPeminjaman.getString(2));
                    model.setNisn(rsPeminjaman.getString(3));
                    model.setNoBuku(rsPeminjaman.getString(4));
                    model.setStatusPinjam(rsPeminjaman.getString(5));
                    model.setTglKembali(rsPeminjaman.getString(6));
                    model.setDenda(rsPeminjaman.getInt(7));
                    listPeminjaman.add(model);
                }
                rsPeminjaman.close();
                return listPeminjaman;
            } catch (SQLException se){
                return null;
            }
        }
        return null;
    }
    
    public String noBaru() {
        int temp = 0;
        String kode = "";
        query = "SELECT MAX(RIGHT(no_peminjaman,8)) FROM tbl_peminjaman";
        status = koneksi.eksekusiQuery(query, true);
        if (status) {
            rsNoPeminjamanBaru = koneksi.getRs();
            try {
                rsNoPeminjamanBaru.next();
                temp = Integer.parseInt(rsNoPeminjamanBaru.getString(1)) + 1;
                rsNoPeminjamanBaru.close();
            } catch (SQLException ex) {
                Logger.getLogger(PeminjamanModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (temp == 0) {
            noPeminjaman = "1";
        } else if (temp < 10) {
            noPeminjaman = "P1" + temp;
        } else {
            noPeminjaman = "P" + temp;
        }
        
        return noPeminjaman;
    }
    
    public PeminjamanModel(){
        koneksi = new KoneksiDB();
        koneksi.getkoneksi();
    }
}
    