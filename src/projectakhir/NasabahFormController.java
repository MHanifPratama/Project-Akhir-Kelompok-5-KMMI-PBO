/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectakhir;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author USER
 */
public class NasabahFormController implements  Initializable{
      @FXML
    private TextField tfId;
      
    @FXML
    private TextField tfNama;

    @FXML
    private TextField tfAlamat;

    @FXML
    private TextField tfNik;

    @FXML
    private TextField tfRekening;

    @FXML
    private TextField tfNomorRekening;

    @FXML
    private TextField tfSaldo;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnReload;
    
    @FXML
    private Button btnClearPerusahaan;
    
    @FXML
    private Button btnReloadPerusahaan;
    
    
    @FXML
    private Button btnClear;

    @FXML
    private TableView<Individu> tblNasabah;
    @FXML
    private TableView<Perusahaan> tblPerusahaan;
    
    @FXML
    private TableColumn<Individu,Long> colNik;

    @FXML
    private TableColumn<Individu,Long> colNpwp;
    
    @FXML
    private TableColumn<Individu,String> colNama;

    @FXML
    private TableColumn<Individu,String> colAlamat;

    
     @FXML
    private TableColumn<Individu,Integer> coID;

    @FXML
    private TableView<Rekening> tblRekening;
    
    @FXML
    private TableView<Rekening> tblRekPerusahaan;
    @FXML
    private TableColumn<Rekening, Integer> colno_rekening;;
     
    @FXML
    private TableColumn<Rekening, Double> colSaldo;

    @FXML
    private TextField tfIDKecil;

    @FXML
    private TextField tfNewRekening;

    @FXML
    private TextField tfNewSaldo;
    
    @FXML
    private TextField tfRekeningBaruPerusahaan;
    
    @FXML
    private TextField tfNewSaldoPerusahaan;
    
    @FXML
    private TextField tfIDKecilPerusahaan;
    
    @FXML
    private Button btnTambahkanAkun;
    
    @FXML
    private Button btnTambahkanAkun1Perusahaan;

    @FXML
    private Label labelDBStatus;
    
    @FXML
    private Label labelStatus;
    
    @FXML
    private Label labelStatusPerusahaan;
    
    @FXML
    private TextField tfNpwp;
    //String nib, String nama, String alamat, Integer id,Rekening rekening
    @FXML
    private TableColumn<Perusahaan, Integer> colDPerusahaan;

    @FXML
    private TableColumn<Perusahaan, String> colNamaPerusahaan;

    @FXML
    private TableColumn<Perusahaan, String> colAlamatPerusahaan;

    @FXML
    private TableColumn<Perusahaan, String> colNibPerusahaan;

    @FXML
    private TableColumn<Rekening, Integer> colRekeningPerusahaan;

    @FXML
    private TableColumn<Rekening, Double> colSaldoPerusahaan;
    
     @FXML
    private TextField tfIDPerusahaan;

    @FXML
    private TextField tfNamaPerusahaan;

    @FXML
    private TextField tfAlamatPerusahaan;
    
    
           
    @FXML
    private TextField tfNibPerusahaan;

    @FXML
    private TextField tfNomorRekeningPerusahaan;

    @FXML
    private TextField tfSaldoPerusahaan;
    
    @FXML
    private Button btnTambahSaldo;
    
    @FXML
    private Button btnTambahsaldoPerusahaan;
    

    
    @FXML
    private Button handeAddTambahSaldoKecilPerusahaan;
    
    @FXML
    void handeAddTambahSaldoKecilPerusahaan(ActionEvent event){
        Rekening rek = tblRekPerusahaan.getSelectionModel().getSelectedItem();
          try {
              lele.addSaldo(rek, Double.parseDouble(tfNewSaldoPerusahaan.getText()));
              labelSampingPerusahaan.setText("Deposit Berhasil");
          } catch (SQLException ex) {
              Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
              labelSampingPerusahaan.setText("Deposit Gagal");
          }
        btnReloadPerusahaan.fire();
        viewDataPerusahaan(Integer.parseInt(tfIDKecilPerusahaan.getText()));
        tfNewSaldoPerusahaan.setText("");
        
    }
    @FXML
    private Label labelSamping;
    @FXML
    private Label labelSampingPerusahaan;
    @FXML
    private Button btnWithdraw;
    
    @FXML
    private Button btnWithdrawPerusahaan;
    
    @FXML
    void HandleAddSaldoKecil(ActionEvent event){
        Rekening rek = tblRekening.getSelectionModel().getSelectedItem();
          try {
              lele.addSaldo(rek, Double.parseDouble(tfNewSaldo.getText()));
              labelSamping.setText("Deposit Berhasil");
          } catch (SQLException ex) {
              Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
                            labelSamping.setText("Deposit Berhasil");

          }
        btnReload.fire();
        viewDataAccount(Integer.parseInt(tfIDKecil.getText()));
        tfNewSaldo.setText("");
        
    }
    @FXML
    void HandleWithdraw(ActionEvent event){
        Rekening rek = tblRekening.getSelectionModel().getSelectedItem();
        if (rek.getSaldo()>=Double.parseDouble(tfNewSaldo.getText())){
            try {
                lele.withdraw(rek, Double.parseDouble(tfNewSaldo.getText()));
                btnReload.fire();
                viewDataAccount(Integer.parseInt(tfNewRekening.getText()));
                tfNewSaldo.setText("");
                labelSamping.setText("Withdraw Berhasil");
            } catch (SQLException ex) {
                labelSamping.setText("Withdraw Gagal");
                Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            labelSamping.setText("Withdraw Gagal");
        }
        btnReload.fire();
        viewDataAccount(Integer.parseInt(tfIDKecil.getText()));
        tfNewSaldo.setText("");
    }
    
    @FXML
    private Button btnWithdawPerusaan;
    
    @FXML
    void handleWithdrawPerusahaan(ActionEvent event){
        Rekening rek = tblRekPerusahaan.getSelectionModel().getSelectedItem();
        if (rek.getSaldo()>=Double.parseDouble(tfNewSaldoPerusahaan.getText())){
            try {
                lele.withdraw(rek, Double.parseDouble(tfNewSaldoPerusahaan.getText()));
                btnReloadPerusahaan.fire();
                viewDataAccount(Integer.parseInt(tfRekeningBaruPerusahaan.getText()));
                tfNewSaldoPerusahaan.setText("");
                labelSampingPerusahaan.setText("Withdraw Berhasil");
            } catch (SQLException ex) {
                labelSampingPerusahaan.setText("Withdraw Gagal");
                Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            labelSampingPerusahaan.setText("Withdraw Gagal");
        }
        btnReloadPerusahaan.fire();
        viewDataPerusahaan(Integer.parseInt(tfIDKecilPerusahaan.getText()));
        tfNewSaldoPerusahaan.setText("");
    }
    
    @FXML
    void HandleAddAccountKecil(ActionEvent event) {
          try {
              Rekening acc = new Rekening(Integer.parseInt(tfNewRekening.getText()),
                              Double.parseDouble(tfNewSaldo.getText()));
              lele.addAccount(Integer.parseInt(tfIDKecil.getText()),acc);
              viewDataAccount(Integer.parseInt(tfIDKecil.getText()));
              
          } catch (SQLException ex) {
              Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    @FXML
    void handleAddAccountKecilPerusahaan(ActionEvent event){
        try {
              Rekening acc = new Rekening(Integer.parseInt(tfRekeningBaruPerusahaan.getText()),
                              Double.parseDouble(tfNewSaldoPerusahaan.getText()));
              lele.addAccount(Integer.parseInt(tfIDKecilPerusahaan.getText()),acc);
              viewDataPerusahaan(Integer.parseInt(tfIDKecilPerusahaan.getText()));
          } catch (SQLException ex) {
              Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
        @FXML
    void handleClearPerusahaan(ActionEvent event) {
        try {
              tfIDPerusahaan.setText(""+lele.nextNasabahId());
              tfNomorRekeningPerusahaan.setText(tfIDPerusahaan.getText()+"1");
              tfNamaPerusahaan.setText("");
              tfAlamatPerusahaan.setText("");
              tfNibPerusahaan.setText("");
              
          } catch (SQLException ex) {
              Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @FXML
    void HandleClear(ActionEvent event) {
          try {
              tfId.setText(""+lele.nextNasabahId());
              tfNomorRekening.setText(tfId.getText()+"1");
              tfNama.setText("");
              tfAlamat.setText("");
              tfNik.setText("");
              tfNpwp.setText("");
              
              
          } catch (SQLException ex) {
              Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
          }
        
    }

    @FXML
    void HandleReload(ActionEvent event) {
        ObservableList<Individu> data = lele.getIndividu();
        colNik.setCellValueFactory(new PropertyValueFactory<>("nik"));
        colNpwp.setCellValueFactory(new PropertyValueFactory<>("npwp"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        coID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblNasabah.setItems(null);
        tblNasabah.setItems(data);
        btnTambahkanAkun.setDisable(true);
    }
    @FXML
    void handleReloadPerusahaan(ActionEvent event) {
        ObservableList<Perusahaan> data = lele.getPerusahaan();
        colNibPerusahaan.setCellValueFactory(new PropertyValueFactory<>("nib"));
        colNamaPerusahaan.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colAlamatPerusahaan.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        colDPerusahaan.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblPerusahaan.setItems(null);
        tblPerusahaan.setItems(data);
        btnTambahkanAkun1Perusahaan.setDisable(true);
    }
    
    
    @FXML
    void handleSave(ActionEvent event) {
        //Long nik, Long npwp, String nama, String alamat, Rekening rekening, Integer id
        Individu a = new Individu(Long.parseLong(tfNik.getText()), Long.parseLong(tfNpwp.getText()),tfNama.getText()
                    ,tfAlamat.getText(),new Rekening(Integer.parseInt(tfNomorRekening.getText())
                    ,Double.parseDouble(tfSaldo.getText())),Integer.parseInt(tfId.getText()));
          try {
              lele.addNasabah(a);
              labelStatus.setText("Data Berhasil Dibuat");
              
              
              btnClear.fire();
              btnReload.fire();
              
          } catch (SQLException ex) {
              labelStatus.setText("Data Gagal Dibuat");
              Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    @FXML
    void handleSavePerusahaan(ActionEvent event){
        Perusahaan b = new Perusahaan(tfNibPerusahaan.getText(),tfNamaPerusahaan.getText(),
                tfAlamatPerusahaan.getText(),new Rekening(Integer.parseInt(tfNomorRekeningPerusahaan.getText())
                    ,Double.parseDouble(tfSaldoPerusahaan.getText())),Integer.parseInt(tfIDPerusahaan.getText()));
          try {
              lele.addPerusahaan(b);
              labelStatusPerusahaan.setText("Data Berhasil Dibuat");
              btnClearPerusahaan.fire();
              btnReloadPerusahaan.fire();
          } catch (SQLException ex) {
              labelStatus.setText("Data Gagal Dibuat");
              Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
    private NasabahDataModel lele;
    @Override
    public  void initialize(URL url, ResourceBundle rb){
          try {
              lele = new NasabahDataModel("MYSQL");
              labelDBStatus.setText(lele.conn==null?"Not_Connected":"Connected");
              btnClear.fire();
              btnReload.fire();
              btnClearPerusahaan.fire();
              btnReloadPerusahaan.fire();
              
          } 
          catch (SQLException ex) {
              Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
          }
          tblNasabah.getSelectionModel().selectedIndexProperty().addListener(listener->{
              if (tblNasabah.getSelectionModel().getSelectedItem()!=null){
                  Individu a = tblNasabah.getSelectionModel().getSelectedItem();
                  viewDataAccount(a.getId());
                  btnTambahkanAkun.setDisable(false);
                  tfIDKecil.setText(""+a.getId());
                  try {
                      tfNewRekening.setText(""+lele.nextNasabahAngka(a.getId()));
                  } catch (SQLException ex) {
                      Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
              
          });
          tblPerusahaan.getSelectionModel().selectedIndexProperty().addListener(listener->{
              if (tblPerusahaan.getSelectionModel().getSelectedItem()!=null){
                  Perusahaan a = tblPerusahaan.getSelectionModel().getSelectedItem();
                  viewDataPerusahaan(a.getId());
                  btnTambahkanAkun1Perusahaan.setDisable(false);
                  tfIDKecilPerusahaan.setText(""+a.getId());
                  try {
                      tfRekeningBaruPerusahaan.setText(""+lele.nextNasabahAngka(a.getId()));
                  } catch (SQLException ex) {
                      Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  
                  
              }
              
          });
          
    }
    public void viewDataAccount(int id){
        ObservableList<Rekening> data = lele.getRekening(id);
        colno_rekening.setCellValueFactory(new PropertyValueFactory<>("noRekening"));
        colSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
        tblRekening.setItems(null);
        tblRekening.setItems(data);
        
    }
    public void viewDataPerusahaan(int id){
        ObservableList<Rekening> data = lele.getRekening(id);
        colRekeningPerusahaan.setCellValueFactory(new PropertyValueFactory<>("noRekening"));
        colSaldoPerusahaan.setCellValueFactory(new PropertyValueFactory<>("saldo"));
        tblRekPerusahaan.setItems(null);
        tblRekPerusahaan.setItems(data);
        
    }
}
