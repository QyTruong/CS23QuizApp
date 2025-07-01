/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pqt.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class MyAlert {
   private static MyAlert instance;
   
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
   
   public Optional<ButtonType> ShowMessage(String msg, Alert.AlertType type){
       this.alert.setAlertType(type);
       this.alert.setContentText(msg);
       return this.alert.showAndWait();
   }
}
