/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectakhir;

import com.sun.accessibility.internal.resources.accessibility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.text.html.HTML;
import projectakhir.db.DBHelper;

/**
 *
 * @author USER
 */
public class NasabahDataModel {
    public final Connection conn;

    public NasabahDataModel(String driver) throws SQLException {
        this.conn = DBHelper.getConnection(driver);
    }
    public void addNasabah(Individu lele) throws SQLException{
        String InsertNasabah = "INSERT INTO nasabah (id,nama,alamat)" 
                + " VALUES(?,?,?)";
        String InsertIndividu = "INSERT INTO individu (id,nik,npwp)" 
                + " VALUES (?,?,?)";
        String InsertRekening = "INSERT INTO rekening (no_rekening,saldo,id)" 
                + " VALUES (?,?,?)"; 
        PreparedStatement stmtNasabah = conn.prepareStatement(InsertNasabah);
        stmtNasabah.setInt(1, lele.getId());
        stmtNasabah.setString(2, lele.getNama());
        stmtNasabah.setString(3, lele.getAlamat());
        stmtNasabah.execute();
        
        PreparedStatement stmtIndividu = conn.prepareStatement(InsertIndividu);
        stmtIndividu.setInt(1, lele.getId());
        stmtIndividu.setLong(2, lele.getNik());
        stmtIndividu.setLong(3, lele.getNpwp());
        stmtIndividu.execute();
        
        PreparedStatement stmtRekening = conn.prepareStatement(InsertRekening);
        stmtRekening.setInt(1, lele.getRekening().get(0).getNoRekening());
        stmtRekening.setDouble(2, lele.getRekening().get(0).getSaldo());
        stmtRekening.setInt(3, lele.getId());
        stmtRekening.execute();
    }
    public void addPerusahaan(Perusahaan lele) throws  SQLException{
        String InsertNasabah = "INSERT INTO nasabah (id,nama,alamat)" 
                + " VALUES(?,?,?)";
        String InsertPerusahaan = "INSERT INTO perusahaan (id,nib)" 
                + " VALUES (?,?)";
        String InsertRekening = "INSERT INTO rekening (no_rekening,saldo,id)" 
                + " VALUES (?,?,?)"; 
        PreparedStatement stmtNasabah = conn.prepareStatement(InsertNasabah);
        stmtNasabah.setInt(1, lele.getId());
        stmtNasabah.setString(2, lele.getNama());
        stmtNasabah.setString(3, lele.getAlamat());
        stmtNasabah.execute();
        
        PreparedStatement stmtPerusahaan = conn.prepareStatement(InsertPerusahaan);
        stmtPerusahaan.setInt(1, lele.getId());
        stmtPerusahaan.setString(2, lele.getNib());
        stmtPerusahaan.execute();
        
        PreparedStatement stmtRekening = conn.prepareStatement(InsertRekening);
        stmtRekening.setInt(1, lele.getRekening().get(0).getNoRekening());
        stmtRekening.setDouble(2, lele.getRekening().get(0).getSaldo());
        stmtRekening.setInt(3, lele.getId());
        stmtRekening.execute();
    }
    public ObservableList<Individu> getIndividu(){
        ObservableList<Individu> data = FXCollections.observableArrayList();
//        String sql="SELECT `id`,`nama`, `alamat`, `nik`, `npwp` "
//                + "FROM `nasabah` NATURAL JOIN `individu` "
//                + "ORDER BY nama";
         String sql = "SELECT `nik`,`npwp`,`nama`,`alamat`,`id` FROM `nasabah` NATURAL JOIN `individu` ORDER BY nama";
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()){
                String sqlAccount = "SELECT no_rekening, saldo FROM rekening WHERE id = "+rs.getInt(1);
                ResultSet rsAccount = conn.createStatement().executeQuery(sqlAccount);
                ArrayList<Rekening> dataAccount = new ArrayList<>();
                while(rsAccount.next()){
                    dataAccount.add(new Rekening(rsAccount.getInt(1),rsAccount.getDouble(2)));
                }
                data.add(new Individu (rs.getLong(1),rs.getLong(2),rs.getString(3),
                        rs.getString(4),rs.getInt(5),dataAccount));
            }              
           
        } catch (SQLException ex) {
            Logger.getLogger(NasabahDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;
    }
    public ObservableList<Perusahaan> getPerusahaan(){
        ObservableList<Perusahaan> data = FXCollections.observableArrayList();
        //String nib, String nama, String alamat,Rekening rekening,Integer id
        String sql="SELECT `nib`, `nama`, `alamat`,`id`"
                + "FROM `nasabah` NATURAL JOIN `perusahaan` "
                + "ORDER BY nama";
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()){
                String sqlAccount = "SELECT no_rekening, saldo FROM rekening WHERE id = "+rs.getInt(1);
                ResultSet rsAccount = conn.createStatement().executeQuery(sqlAccount);
                ArrayList<Rekening> dataAccount = new ArrayList<>();
                while(rsAccount.next()){
                    dataAccount.add(new Rekening(rsAccount.getInt(1),rsAccount.getDouble(2)));
                }
                data.add(new Perusahaan (rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),dataAccount));
            }              //String nib, String nama, String alamat, Rekening rekening, Integer id;
            
        
        } catch (SQLException ex) {
            Logger.getLogger(NasabahDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;
    }
    public ObservableList<Rekening> getRekening(int id){
        ObservableList<Rekening> data = FXCollections.observableArrayList();
        //String sql="SELECT `no_rekening`, `no_rekening` FROM `rekening` WHERE id="+id;
        String sql = "SELECT `no_rekening`, `saldo`, `id` FROM `rekening` WHERE id="+id;
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while(rs.next()){
                data.add(new Rekening(rs.getInt(1), rs.getDouble(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NasabahDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
        
    }

    public int nextNasabahId () throws SQLException{
        String sql = "SELECT MAX(id) FROM nasabah";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        while(rs.next()){
            return rs.getInt(1)==0?1000000:rs.getInt(1)+1;
        }
        return 100001;
    }
    public int nextNasabahAngka (int id) throws SQLException{
        String sql = "SELECT MAX(no_rekening) FROM rekening WHERE id="+id;
        ResultSet rs = conn.createStatement().executeQuery(sql);
        while(rs.next()){
            return rs.getInt(1)+1;
        }
        return 0;
    }
    
    
    public void addAccount(int id,Rekening rek) throws SQLException{
        String InsertNasabah = "INSERT INTO rekening (id, no_rekening, saldo)" 
                + " VALUES(?,?,?)";
        PreparedStatement stmtNasabah = conn.prepareStatement(InsertNasabah);
        stmtNasabah.setInt(1, id);
        stmtNasabah.setInt(2, rek.getNoRekening());
        stmtNasabah.setDouble(3, rek.getSaldo());
        stmtNasabah.execute();
    }
    
    public void addSaldo(Rekening rek, double jumlah) throws SQLException{
        String insertNasabah = "UPDATE rekening SET saldo = ? WHERE no_rekening = ?";
        
        PreparedStatement stmtNasabah = conn.prepareStatement(insertNasabah);
        stmtNasabah.setDouble(1, (rek.getSaldo() + jumlah));
        stmtNasabah.setInt(2, rek.getNoRekening());
        stmtNasabah.execute();
        
    }
    public void withdraw(Rekening rek, double jumlah) throws SQLException{
        String insertNasabah = "UPDATE rekening "
                + "SET saldo = ? "
                + "WHERE no_rekening = ?";
        PreparedStatement stmtNasabah = conn.prepareStatement(insertNasabah);
        stmtNasabah.setDouble(1, (rek.getSaldo() - jumlah));
        stmtNasabah.setInt(2, rek.getNoRekening());
        stmtNasabah.execute();
    }
}
