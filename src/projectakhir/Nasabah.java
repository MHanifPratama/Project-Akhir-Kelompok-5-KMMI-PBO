/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectakhir;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author USER
 */
abstract class Nasabah{
    private StringProperty nama ;
    private StringProperty alamat;
    private ArrayList<Rekening> rekening = new ArrayList<Rekening>();
    private IntegerProperty id;

    public Nasabah(String nama, String alamat, Integer id ,ArrayList<Rekening> rekening) {
        this.nama= new SimpleStringProperty(nama);
        this.alamat = new SimpleStringProperty(alamat);
        this.rekening = rekening;
        this.id = new SimpleIntegerProperty(id);
    }

    public Nasabah(String nama, String alamat, Integer id, Rekening rekening) {
        this.nama= new SimpleStringProperty(nama);
        this.alamat = new SimpleStringProperty(alamat);
        this.rekening.add(rekening);
        this.id = new SimpleIntegerProperty(id);
    }
    
    public ArrayList<Rekening> getRekening() {
        return rekening;
    }
    
    public void setRekening(ArrayList<Rekening> rekening) {
        this.rekening = rekening;
    }

    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }
    
    public String getNama() {
        return nama.get();
    }

    public void setNama(String nama) {
        this.nama.set(nama);
        
    }

    public String getAlamat() {
        return alamat.get();
    }

    public void setAlamat(String alamat) {
        this.alamat.set(alamat);
    }
    public void tambahRekening(Rekening rek){
        rekening.add(rek);
    }
    public void print(){
        System.out.println("Nama: " + getNama());
        System.out.println("Alamat: " + getAlamat());
        System.out.println("===========================================");
        String infoRekening=String.format("No Rekening"+"%26s","Saldo");
        System.out.println(infoRekening);
        System.out.println("===========================================");
        for(Rekening rekening : rekening){
            System.out.printf("%s%34.2f\n",rekening.getNoRekening(),rekening.getSaldo());
        }
    }

    

    

}
