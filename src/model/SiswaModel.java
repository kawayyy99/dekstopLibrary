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

/**
 *
 * @author Kawayyy
 */
public class SiswaModel 
    {
    private KoneksiDB koneksi;
    private String query;
    private boolean status;
    private ResultSet rsSiswa,rsKode;
    private List<SiswaModel> listSiswa;


    private String nisnSiswa;
    private String namaSiswa;
    private String kelasSiswa;
    private String notelpSiswa;
    private String jeniskSiswa;
    private String alamatSiswa;
    private String agamaSiswa;

    public String getNisnSiswa() {
        return nisnSiswa;
    }

    public void setNisnSiswa(String nisnSiswa) {
        this.nisnSiswa = nisnSiswa;
    }

    public String getNamaSiswa() {
        return namaSiswa;
    }

    public void setNamaSiswa(String namaSiswa) {
        this.namaSiswa = namaSiswa;
    }

    public String getKelasSiswa() {
        return kelasSiswa;
    }

    public void setKelasSiswa(String kelasSiswa) {
        this.kelasSiswa = kelasSiswa;
    }

    public String getNotelpSiswa() {
        return notelpSiswa;
    }

    public void setNotelpSiswa(String notelpSiswa) {
        this.notelpSiswa = notelpSiswa;
    }

    public String getJeniskSiswa() {
        return jeniskSiswa;
    }

    public void setJeniskSiswa(String jeniskSiswa) {
        this.jeniskSiswa = jeniskSiswa;
    }

    public String getAlamatSiswa() {
        return alamatSiswa;
    }

    public void setAlamatSiswa(String alamatSiswa) {
        this.alamatSiswa = alamatSiswa;
    }

    public String getAgamaSiswa() {
        return agamaSiswa;
    }

    public void setAgamaSiswa(String agamaSiswa) {
        this.agamaSiswa = agamaSiswa;
    }

     public SiswaModel(){
        koneksi = new KoneksiDB();
        koneksi.getkoneksi();
    }

      public boolean insertSiswa(){
        query = "INSERT INTO tbl_siswa VALUES ('"+nisnSiswa+"','"+namaSiswa+"','"+kelasSiswa+"','"+notelpSiswa+"','"
                +jeniskSiswa+"','"+alamatSiswa+"','"+agamaSiswa+"')";
        status = koneksi.eksekusiQuery(query, false);
        return status;
    }
      // update error
      public boolean updateSiswa(){
        query = "Update tbl_siswa SET nama_siswa = '"+namaSiswa+"',"
                    + " kelas = '"+kelasSiswa+"', no_telp = '"+notelpSiswa+"', jen_kel = '"+jeniskSiswa+"', alamat = '"+alamatSiswa+"', agama = '"+agamaSiswa+"'"
                    + " WHERE nisn = '"+nisnSiswa+"'";
            status = koneksi.eksekusiQuery(query,false);
            return status;
    }
      
      public boolean deleteSiswa(){
        query = "DELETE FROM tbl_siswa WHERE nisn = '" + nisnSiswa + "'";
        status = koneksi.eksekusiQuery(query, false);
        return status;
    }
      
      public List selectSiswa(String nisn, String nama){
        query = "SELECT * FROM tbl_siswa";
        status = koneksi.eksekusiQuery(query, true);
        if(status){
            rsSiswa = koneksi.getRs();
            listSiswa = new ArrayList<SiswaModel>();
            
            try{
                while(rsSiswa.next()){
                    SiswaModel model = new SiswaModel();
                    model.setNisnSiswa(rsSiswa.getString(1));
                    model.setNamaSiswa(rsSiswa.getString(2));
                    model.setKelasSiswa(rsSiswa.getString(3));
                    model.setNotelpSiswa(rsSiswa.getString(4));
                    model.setJeniskSiswa(rsSiswa.getString(5));
                    model.setAlamatSiswa(rsSiswa.getString(6));
                    model.setAgamaSiswa(rsSiswa.getString(7));
                    listSiswa.add(model);
                }
                rsSiswa.close();
                return listSiswa;
            } catch (SQLException se){
                return null;
            }
        }
        return null;
    }
      
      public String kodeBaru() {
        int temp = 0;
        String kode = "";
        query = "SELECT MAX(RIGHT(nisn,2)) FROM tbl_siswa";
        status = koneksi.eksekusiQuery(query, true);
        if (status) {
            rsKode = koneksi.getRs();
            try {
                rsKode.next();
                temp = Integer.parseInt(rsKode.getString(1)) + 1;
                rsKode.close();
            } catch (SQLException ex) {
                Logger.getLogger(SiswaModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (temp == 0) {
            kode = "S01";
        } else if (temp < 10) {
            kode = "S0" + temp;
        } else {
            kode = "S" + temp;
        }
        
        return kode;
    }
}
   

