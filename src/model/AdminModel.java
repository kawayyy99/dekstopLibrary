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
import koneksi.KoneksiDB;

/**
 *
 * @author Kawayyy
 */
public class AdminModel {
    private KoneksiDB koneksi;
    private ResultSet rsLogin;
    private String query;
    private boolean status;
    
    public AdminModel(){
        koneksi = new KoneksiDB();
        koneksi.getkoneksi();
    }
    
    private String username;
    private String nama;
    private String password;
    private String alamat;
    private String telp;
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean Login(){
        query = "SELECT username, nama_admin "
                + "FROM tbl_admin "
                + "WHERE username = '"+ username +"' AND password = '"+password+"'";
        status = koneksi.eksekusiQuery(query, true);
        
        if(status){
            rsLogin = koneksi.getRs();
            try{
                rsLogin.next();
                nama = rsLogin.getString(2);
                if(nama == null){
                    status =  false;
                }else{
                    status = true;
                }
            }catch (SQLException se){
                status = false;
            }
        }
        return status;
    }
    
    private static String usernameAdmin;
    private static String namaAdmin;
    private static String passwordAdmin;
    private static String alamatAdmin;
    private static String telpAdmin;
    private static String emailAdmin;

    public static String getUsernameAdmin() {
        return usernameAdmin;
    }

    public static void setUsernameAdmin(String usernameAdmin) {
        AdminModel.usernameAdmin = usernameAdmin;
    }

    public static String getNamaAdmin() {
        return namaAdmin;
    }

    public static void setNamaAdmin(String namaAdmin) {
        AdminModel.namaAdmin = namaAdmin;
    }

    public static String getPasswordAdmin() {
        return passwordAdmin;
    }

    public static void setPasswordAdmin(String passwordAdmin) {
        AdminModel.passwordAdmin = passwordAdmin;
    }

    public static String getAlamatAdmin() {
        return alamatAdmin;
    }

    public static void setAlamatAdmin(String alamatAdmin) {
        AdminModel.alamatAdmin = alamatAdmin;
    }

    public static String getTelpAdmin() {
        return telpAdmin;
    }

    public static void setTelpAdmin(String telpAdmin) {
        AdminModel.telpAdmin = telpAdmin;
    }

    public static String getEmailAdmin() {
        return emailAdmin;
    }

    public static void setEmailAdmin(String emailAdmin) {
        AdminModel.emailAdmin = emailAdmin;
    }
    
    public boolean login(){
   query = "SELECT * FROM tbl_admin WHERE username = '"+username +"' AND password ='"+password+"'";
   status = koneksi.eksekusiQuery(query, true);
        
        if (status){
            rsLogin = koneksi.getRs();
            try {
                rsLogin.next();
                username = rsLogin.getString(1);
                namaAdmin = rsLogin.getString(2);
                passwordAdmin = rsLogin.getString(3);
                alamatAdmin = rsLogin.getString(4);
                telpAdmin = rsLogin.getString(5);
                emailAdmin = rsLogin.getString(6);
                if (namaAdmin == null){
                    status = false;
                }else{
                    status = true;
                }   
            } catch (SQLException se) {
                status = false;
            }
        }
        return status;
    }
    
    public boolean UpdateAdmin() {
            query = "Update tbl_admin SET nama_admin = '"+nama+"',"
                    + " password = '"+password+"', alamat = '"+alamat+"', no_telepon = '"+telp+"', email = '"+email+"'"
                    + " WHERE username = '"+username+"'";
            status = koneksi.eksekusiQuery(query,false);
            return status;
        }
    
    public List selectAdmin (String username,String nama){
            query = "SELECT username, nama_admin, MD5(password), alamat, no_telepon, email"
                   + " FROM tbl_admin"
                   + " WHERE username LIKE '%"+username+"%' OR nama_admin LIKE '%"+nama+"%'";
            status = koneksi.eksekusiQuery(query, true);
            if(status){
                ResultSet rsAdmin = koneksi.getRs();
                ArrayList<AdminModel> ListAdmin = new ArrayList<AdminModel>();
            
                try { 
                    while (rsAdmin.next()) {
                        AdminModel model = new AdminModel ();
                        model.setUsername(rsAdmin.getString(1));
                        model.setNama(rsAdmin.getString(2));
                        model.setPassword(rsAdmin.getString(3));
                        model.setAlamat(rsAdmin.getString(4));
                        model.setTelp(rsAdmin.getString(5));
                        model.setEmail(rsAdmin.getString(6));
                        ListAdmin.add(model);
                    }
                    rsAdmin.close();
                    return ListAdmin;
                } catch (SQLException se) {
                return null;
            }
        } 
            return null;
    }
    
    public boolean insertAdmin(){
        query = "INSERT INTO tbl_admin VALUES ('"+username+"','"+nama+"','"+password+"','"+alamat+"','"+telp+"','"+email+"')";
            status = koneksi.eksekusiQuery(query, false);
            return status;
    }
    
    public boolean deleteAdmin(){
        query = "DELETE FROM tbl_admin WHERE username = '" + username + "'";
        status = koneksi.eksekusiQuery(query, false);
        return status;
    }
}

