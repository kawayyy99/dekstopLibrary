/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Kawayyy
 */
public class KoneksiDB 
{
    private Connection Koneksi;
    private ResultSet rs;
    private PreparedStatement ps;
    
    public Connection getkoneksi() {
        if(Koneksi==null){
              try {
                Class.forName("com.mysql.jdbc.Driver");
                try{
                    String url = "jdbc:mysql://localhost:3306/db_perpus";
                    Koneksi = DriverManager.getConnection(url, "root", "");
                    System.out.println("Koneksi Sukses");
                } catch (SQLException se) {
                    System.out.println("Koneksi gagal" + se);
                    System.exit(0);
                }
            } catch (ClassNotFoundException cnfe) {
                System.out.println("Class tidak ditemukan " + cnfe);
            }
        }
        return Koneksi;
    }    


public ResultSet getRs(){
    return rs;
}

public boolean eksekusiQuery(String query,boolean baris){
    try{
        ps = Koneksi.prepareStatement(query);
        if (baris) {
            rs = ps.executeQuery();
               } else {
                ps.executeUpdate();
               }
               return true;
        }catch (SQLException e) {
        return false;
        }
    }

    public static void main(String[] args)
    {
        new KoneksiDB().getkoneksi();
    }

    public boolean esksekusiQuery(String query, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
