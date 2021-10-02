/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectakhir;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author USER
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("NasabahForm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        try {
//            NasabahDataModel database =  new NasabahDataModel("MYSQL");
//            Individu a = new Individu (1l, 2l, "MHanifPratama", "BandarLampung",new Rekening(1234, 890000.000), 69);
//            database.addNasabah(a);
//            System.out.println("Sudah Ditambahkan");
//        }   catch (SQLException ex) {
//            System.out.println("Gagal Ditambahkan");
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
          launch(args);
    }
    
}
