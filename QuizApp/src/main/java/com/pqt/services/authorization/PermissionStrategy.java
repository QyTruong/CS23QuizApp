/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pqt.services.authorization;

/**
 *
 * @author PHAM QUY TRUONG
 */
public interface PermissionStrategy {
    boolean canAccess(String fxml);
}
