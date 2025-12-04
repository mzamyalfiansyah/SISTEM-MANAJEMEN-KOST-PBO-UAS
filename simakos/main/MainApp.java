package com.simakos.main;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.simakos.ui.Login;



/**
 *
 * @author elitebook
 */

public class MainApp {
    
    public static void main(String[]args){
        
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
        
        public void run(){
            
            Login LoginForm = new Login();
            LoginForm.setVisible(true);
            
        }
            
        });

    }
    
}
