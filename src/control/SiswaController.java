
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import model.SiswaModel;
import view.FrmSiswa;

/**
 *
 * @author Kawayyy
 */
public class SiswaController 
{
    private FrmSiswa view;
    private SiswaModel model;
    
     public SiswaController(FrmSiswa view){
        this.view = view;
        model = new SiswaModel();
    }

      public boolean insertSiswa(){
        return model.insertSiswa();
    }
      
      public boolean deleteSiswa(){
          return model.deleteSiswa();
      }
      
     public boolean updateSiswa(){
        return model.updateSiswa();
    }
     public List selectSiswa(String nisn, String nama){
        return model.selectSiswa(nisn, nama);
    }
     public String kodeBaru(){
        return model.kodeBaru();
    }
     
    public void inputSiswa(String nisn, String nama, String kelas ,
            String notelp ,String jen_kel ,String alamat ,String agama){
        model.setNisnSiswa(nisn);
        model.setNamaSiswa(nama);
        model.setKelasSiswa(kelas);
        model.setNotelpSiswa(notelp);
        model.setJeniskSiswa(jen_kel);
        model.setAlamatSiswa(alamat);
        model.setAgamaSiswa(agama);
    }
}
