/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.simakos.model;

/**
 *
 * @author elitebook
 */
public class TenantUser {
    private int idUser;
    private String username;
    private String password;
    private String namaLengkap;
    private String email;
    private String noHp;
    
    public TenantUser(int idUser, String username, String password, String namaLengkap,
            String email, String noHp){
        
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.namaLengkap = namaLengkap;
        this.email = email;
        this.noHp = noHp;
        
    }

    public TenantUser(String username, String password) {
        
    }
    
    public int getIdUser(){return idUser;}
    public String getUsername(){return username;}
    
}
