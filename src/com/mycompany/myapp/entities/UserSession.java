/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Ajengui
 */
public class UserSession {
    
    private static UserSession instance;
    
    private int id;
    private String username;
    private String email;
    
    
    private UserSession(int id, String email){
        this.id=id;
      
        this.email=email;
    }
    
    public static UserSession setInstance(int id,String email){
        if (instance==null) {
            instance= new UserSession(id, email);
        }
        return instance;
    }
    
    
    public static UserSession getInstance(){
        return instance;
    }
    
    public static void destroySession(){
        instance=null;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
    
    
    
}
