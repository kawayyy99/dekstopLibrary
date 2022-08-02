/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import model.PeminjamanModel;
import view.FrmPeminjaman;

/**
 *
 * @author Kawayyy
 */
public class PeminjamanController {
    private FrmPeminjaman view;
    private PeminjamanModel model;
    
    public PeminjamanController(FrmPeminjaman view){
        this.view = view;
        model = new PeminjamanModel();
    }
    
    public boolean insertPeminjaman(){
        return model.insertPeminjaman();
    }
    
    public void inputPeminjaman(String NoPeminjaman, String tglPinjam,
            String nisn, String noBuku, 
            String statusPinjam, String tglKembali,
            int denda){
        model.setNoPeminjaman(NoPeminjaman);
        model.setTglPinjam(tglPinjam);
        model.setNisn(nisn);
        model.setNoBuku(noBuku);
        model.setStatusPinjam(statusPinjam);
        model.setTglKembali(tglKembali);
        model.setDenda(denda);
    }
    
    public List selectPeminjaman(String NoPinjam, String Nama){
        return model.selectPeminjaman(NoPinjam, Nama);
    }
    
    public String noBaru(){
        return model.noBaru();
    }
    
    public boolean deletePeminjaman(){
        return model.deletePeminjaman();
    }
    
    public boolean updatePeminjaman(){
        return model.UpdatePeminjaman();
    }
}
