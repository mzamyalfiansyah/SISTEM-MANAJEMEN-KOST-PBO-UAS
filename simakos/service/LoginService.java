/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.simakos.service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author elitebook
 */
public class LoginService {
    
    public com.simakos.model.Admin authenticateAdmin(String username, String password){
        
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        
        try(Connection conn = com.simakos.db.DatabaseConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            try(ResultSet rs = pstmt.executeQuery()){
                
                if (rs.next()){
                    return new com.simakos.model.Admin(
                        rs.getInt("id_admin"),
                        rs.getString("username"),
                        rs.getString("password")
                    );
                }
                
            }
            
        }catch(SQLException e){
        
            e.printStackTrace();
            
        }
            return null; //-> Admin itu gaada

    }

    
    public com.simakos.model.TenantUser authenticateUser(String username, String password){
        
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try(Connection conn = com.simakos.db.DatabaseConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
        
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    return new com.simakos.model.TenantUser( //kalau nemu user berarti ambil kolom dibawah ini
                        rs.getInt("id_users"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("nama_lengkap"),
                        rs.getString("email"),
                        rs.getString("no_hp")
                    );
                }
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    
    
    
    
}
