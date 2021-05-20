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
public class User {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String login;
    private String password;
    private String image;
    private String sexe;
    private String Date_naiss;
    private String type;
    private boolean isvalider;

    public User() {
    }

    public User(int id, String nom, String prenom, String email, String login, String password,String image, String sexe, String Date_naiss, String type) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.login = login;
        this.password = password;
        this.image = image;
        this.sexe = sexe;
        this.Date_naiss = Date_naiss;
        this.type = type;
    }
       public User( String nom, String prenom, String email, String login, String password,String image, String sexe, String Date_naiss, String type) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.login = login;
        this.password = password;
        this.image = image;
        this.sexe = sexe;
        this.Date_naiss = Date_naiss;
        this.type = type;
    }
 

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getImage() {
        return image;
    }
    
    public String getSexe() {
        return sexe;
    }

    public String getDate_naiss() {
        return Date_naiss;
    }

    public String getType() {
        return type;
    }

    public boolean isIsvalider() {
        return isvalider;
    }
    
    

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setDate_naiss(String Date_naiss) {
        this.Date_naiss = Date_naiss;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIsvalider(boolean isvalider) {
        this.isvalider = isvalider;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", login=" + login + ", password=" + password + ", image=" + image + ", sexe=" + sexe + ", Date_naiss=" + Date_naiss + ", type=" + type + ", isvalider=" + isvalider + '}';
    }

    
    
    

    

    
    
    
    
}
