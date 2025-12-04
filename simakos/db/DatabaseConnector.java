/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.simakos.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author elitebook
 */

public class DatabaseConnector {
    
    private static final String DB_URL = "jdbc:mysql://localhost/simakos";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";
    
    private static Connection connection = null;
    
    public static Connection getConnection() throws SQLException {
        
        if(connection == null || connection.isClosed()){
            try{
                //Class.forName("com.cj.jdbc.Driver");
                
                System.out.println("coba koneksi databsase..");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                System.out.println("Koneksi Berhasil");
            }catch (SQLException e){
                System.out.println("koneksi gagal boss");
                throw e;
            }
        }
        return connection;
        
    }
    
    public static void closeConnection(){
        if(connection != null){
            try{
                connection.close();
                System.out.println("Koneksi database ditutup");
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    
}
