/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.simakos.model;

/**
 *
 * @author elitebook
 */
public class Kamar {
    private int idKamar;
    private String nomorKamar; // Asumsi kolom ini yang disebut 'Nama' di UI
    private String status;      // Status kamar (misalnya: Terisi, Kosong)
    // Tambahkan properti lain yang mungkin ada di tabel 'kamar' Anda

    public Kamar(int idKamar, String nomorKamar, String status) {
        this.idKamar = idKamar;
        this.nomorKamar = nomorKamar;
        this.status = status;
    }

    // Getters
    public int getIdKamar() 
    { 
        return idKamar; 
    }
    public String getNomorKamar() { 
        return nomorKamar; 
    }
    public String getStatus() { 
        return status; 
    }
    
    
    
   
    
    
}
