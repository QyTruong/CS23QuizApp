/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pqt.utils;

import javafx.scene.control.Alert;

public class MyAlert {
   private static MyAlert instance;
   
   public static MyAlert Instance
           ;
   
   private final Alert alert;
   
   private MyAlert(){
       alert = new Alert(Alert.AlertType.CONFIRMATION);
   }
   
   public static MyAlert GetInstance(){
       if (instance == null)
           instance = new MyAlert();
       return instance;
   }
   
   public void ShowMessage(String msg){
       this.alert.setContentText(msg);
       this.alert.showAndWait();
   }
   
   
}
