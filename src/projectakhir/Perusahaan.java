/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectakhir;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author USER
 */
public class Perusahaan extends Nasabah{
    private StringProperty nib;

    public Perusahaan(String nib, String nama, String alamat, Integer id ,ArrayList<Rekening> rekening) {
        super(nama, alamat, id ,rekening);
        this.nib = new SimpleStringProperty(nib);
    }

    public Perusahaan(String nib, String nama, String alamat,Rekening rekening,Integer id) {
        super(nama, alamat,  id, rekening);
        this.nib = new SimpleStringProperty(nib);
    }

    
    public String getNib() {
        return nib.get();
    }

    public void setNib(String nib) {
        this.nib.set(nib);
    }
    

    public void print() {
        System.out.println("Nama: " + getNama());
        System.out.println("Alamat: " + getAlamat());
        System.out.println("NIB: " + getNib());
        System.out.println("===========================================");
        String infoRekening=String.format("No Rekening"+"%26s","Saldo");
        System.out.println(infoRekening);
        System.out.println("===========================================");
        for(Rekening rekening : getRekening()){
            System.out.printf("%s%34.2f\n",rekening.getNoRekening(),rekening.getSaldo());
        }

    }

    
}
