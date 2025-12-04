/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.simakos.service;

import com.simakos.db.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author elitebook
 */
public class KamarService {
    
    String sql = "SELECT COUNT(id_kamar) AS total FROM kamar";



    public int getTotalKamarCount() {

        try (Connection conn = com.simakos.db.DatabaseConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql); 
            ResultSet rs = pstmt.executeQuery()){

            if(rs.next()){
                return rs.getInt("total");
            }

        }catch(SQLException e){
            System.err.println("Gagal menghitung jumlah kamar dari database.");
            e.printStackTrace();
        }
        return 0;
    }
    
    
 
    
    
    
    public List<com.simakos.model.Kamar> getAllKamar(){
        List<com.simakos.model.Kamar> listKamar = new ArrayList<>();
        
        String data = "SELECT * FROM kamar ORDER BY id_kamar ASC";
        
        try (Connection conn = com.simakos.db.DatabaseConnector.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(data);
                ResultSet rs = pstmt.executeQuery()){
            
            while(rs.next()){
                com.simakos.model.Kamar kamar = new com.simakos.model.Kamar(
                    rs.getInt("id_kamar"),
                    rs.getString("nomor_kamar"),
                    rs.getString("status")
                );
                listKamar.add(kamar);
            }
        }catch(SQLException e){
                System.err.println("Gagal mengambil data kamar.");
                e.printStackTrace();
        }
        return listKamar;
    }




}
