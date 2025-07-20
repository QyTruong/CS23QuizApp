/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pqt.pojo;

/**
 *
 * @author PHAM QUY TRUONG
 */
public class Account {
    private int id;
    private String username;
    private String password;
    private String phone;
    private String email;

    public Account(Builder b) {
        this.id = b.id;
        this.username = b.username;
        this.password = b.password;
        this.phone = b.phone;
        this.email = b.email;
    }
    
    public static class Builder {
        private int id;
        private String username;
        private String password;
        private String phone;
        private String email;
        
        public Builder(String username, String password){
            this.username = username;
            this.password = password;
        }
        
        public Builder addPhone(String phone){
            this.phone = phone;
            return this;
        }
        
        public Builder addEmail(String email){
            this.email = email;
            return this;
        }
        
        public Account build(){
            return new Account(this);
        }
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
