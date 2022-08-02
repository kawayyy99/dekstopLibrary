/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import model.AdminModel;
import view.FrmAdmin;
import view.FrmLogin;

/**
 *
 * @author Kawayyy
 */
public class AdminController {
    private FrmLogin view;
    private AdminModel model;
    private FrmAdmin view2;
    
    public AdminController(FrmLogin view){
        this.view = view;
        model = new AdminModel();
    }
    
    public void inputAdmin(String username, String nama, String password,
            String alamat, String telp, String email){
        model.setUsername(username);
        model.setNama(nama);
        model.setPassword(password);
        model.setAlamat(alamat);
        model.setTelp(telp);
        model.setEmail(email);
    }
    
    public boolean login(String username, String password){
        model.setUsername(username);
        model.setPassword(password);
        return model.Login();
    }
    
        public AdminController (FrmAdmin view2)
    {
            this.view2 = view2;
            model = new AdminModel();
    }
    public static String getUsername()
    {
        return AdminModel.getUsernameAdmin();
    }
    public static String getNama()
    {
        return AdminModel.getNamaAdmin();
    }
    public static String getPassword()
    {
        return AdminModel.getPasswordAdmin();
    }
    public static String getAlamat()
    {
        return AdminModel.getAlamatAdmin();
    }
    public static String getTelp()
    {
        return AdminModel.getTelpAdmin();
    }
    public static String getEmail()
    {
        return AdminModel.getEmailAdmin();
    }
    public void inputUser(String nama,String password, String alamat, String telp,String email)
    {
       AdminModel.setNamaAdmin(nama);
       AdminModel.setPasswordAdmin(password);
       AdminModel.setAlamatAdmin(alamat);
       AdminModel.setTelpAdmin(telp);
       AdminModel.setEmailAdmin(email);
       
    }
    
    public boolean updateAdmin(){
        return model.UpdateAdmin();
    }
    
    public List selectAdmin(String username, String nama){
        return model.selectAdmin(username, nama);
    }
    
    public boolean insertAdmin(){
        return model.insertAdmin();
    }
    
    public boolean deleteAdmin(){
        return model.deleteAdmin();
    }
}
