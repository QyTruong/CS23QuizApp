/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pqt.utils;

import com.pqt.pojo.Account;

/**
 *
 * @author PHAM QUY TRUONG
 */
public class LoginSession {
    private static LoginSession instance;
    private final Account acc;
    
    private LoginSession(Account acc){
        this.acc = acc;
    }
    
    public static void createSession(Account acc){
        if (instance == null){
            instance = new LoginSession(acc);
        }
    }
    public static LoginSession getInstance(){
        return instance;
    }
    
    public static void deleteSession(){
        instance = null;
    }
    
    public Account getAccount(){
        return acc;
    }
    
    public static boolean isLoggedIn(){
        return instance != null;
    }
}
