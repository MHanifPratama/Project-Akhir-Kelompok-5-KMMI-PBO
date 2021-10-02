/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectakhir.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class DBHelper {
    private static final String USER ="root";
    private static final String PASSWORD ="";
    private static final String DB ="koperasi";
    private static final String MYCONN = "jdbc:mysql://localhost/"+DB;
    private static final String SQCONN = "jdbc:sqlite:D:\\sqlite\\koperasi.sqlite";
    public static Connection getConnection(String driver) throws SQLException{
        Connection conn  = null;
        switch(driver){
            case "SQLITE":{
                try {
                System.out.println("SQLITE Connect");
            Class.forName("org.sqlite.JDBC");
            conn= DriverManager.getConnection(SQCONN);
        } catch (ClassNotFoundException ex) {
            System.out.println("Librari Tidak Ada (SQLITE)");
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
                break;
            }
            case "MYSQL":{
                try {
                    System.out.println("MYSQL Connect");
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(MYCONN,USER,PASSWORD);
        } catch (ClassNotFoundException ex) {
            System.out.println("Librari Tidak Ada(MYSQL)");
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
                break;
            }
        }

        return conn;
    }
}
