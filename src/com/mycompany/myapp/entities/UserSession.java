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
     private static User user=null;
   
    
    public static void start(User currentUser) {
        user = currentUser;
    }

    public static User getCurrentSession() {
        if (user != null) {
            return user;
        }
        return null;

    }
    
        public static void close() throws Exception {
        if (user != null) {
            user =null;
        } else {
            throw new Exception("Session has not started yet!");
        }
    }
}
