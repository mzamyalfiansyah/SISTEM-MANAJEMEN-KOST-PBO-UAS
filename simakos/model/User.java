/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.simakos.model;

/**
 *
 * @author elitebook
 */
public class User {
    private int id_user;
    private String username;
    private String password;
    
    public User(int id_user, String username, String password){
        this.id_user = id_user;
        this.username = username;
        this.password = password;
    }
    
    public int getId(){return id_user;}
    public String getUsername(){return username;}
    public String getPassword(){return password;}

    
}
