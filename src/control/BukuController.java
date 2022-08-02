/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import model.BukuModel;
import view.FrmBuku;

/**
 *
 *@author Kawayyy
 */
public class BukuController {
     private FrmBuku view;
     private BukuModel model;
    
    public BukuController() {
        model = new BukuModel();
    }
    
    public BukuController(FrmBuku view){
        this.view = view;
        model = new BukuModel();
    }
    
    public boolean insertBuku(){
        return model.insertBuku();
    }
    
    public boolean updateBuku(){
        return model.UpdateBuku();
    }
    
    public boolean deleteBuku(){
        return model.deleteBuku();
    }
    
    public void inputBuku(String no_buku, String judul, String pengrang, 
            String penerbit, String kategori, String tahun, String rak, String jumlah){
        model.setNo_buku(no_buku);
        model.setJudul(judul);
        model.setPengarang(pengrang);
        model.setPenerbit(penerbit);
        model.setKategori(kategori);
        model.setTahun(tahun);
        model.setRak(rak);
        model.setJumlah(jumlah);
    }

    public List selectBuku(){
        return model.selectBuku();
    }
    
    public String noBaru(){
        return model.noBaru();
    }
}

    
